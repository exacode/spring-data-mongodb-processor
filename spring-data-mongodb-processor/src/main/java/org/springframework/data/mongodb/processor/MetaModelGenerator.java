package org.springframework.data.mongodb.processor;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.processor.model.MetaModel;
import org.springframework.data.mongodb.processor.model.MetaModelField;
import org.springframework.data.mongodb.processor.model.Type;

/**
 * Crawls through field definitions of given model type.
 * 
 * @author mendlik
 */
class MetaModelGenerator {

	private final ModelUtils modelUtils;

	private final ProcessingEnvironment processingEnv;

	private final Set<TypeElement> modelTypes;

	public MetaModelGenerator(ProcessingEnvironment processingEnv,
			Set<TypeElement> modelTypes) {
		this.modelUtils = new ModelUtils(processingEnv);
		this.processingEnv = processingEnv;
		this.modelTypes = modelTypes;
	}

	/**
	 * Crawls through type (and super type) field definitions and generates meta
	 * model related data.
	 * 
	 * @param pw
	 * @param type
	 * @param outputFileName
	 * @throws IOException
	 */
	public MetaModel analyzeType(TypeElement type) {
		String outputFileName = processingEnv.getElementUtils()
				.getBinaryName(type).toString();
		outputFileName = outputFileName.replaceAll("\\$", "_").concat("_");
		MetaModel metaModel = new MetaModel(outputFileName);

		analyzeSuperclassFields(type.getSuperclass(), metaModel);
		analyzeFields(type, metaModel);

		return metaModel;
	}

	private void analyzeFields(TypeElement typeElement, MetaModel metaModel) {
		for (VariableElement field : ElementFilter.fieldsIn(typeElement
				.getEnclosedElements())) {
			analyzeField(field, metaModel);
		}
	}

	private void analyzeSuperclassFields(TypeMirror superclassTypeMirror,
			MetaModel metaModel) {
		while (superclassTypeMirror instanceof DeclaredType) {
			TypeElement declaredSuperclass = (TypeElement) ((DeclaredType) superclassTypeMirror)
					.asElement();
			for (VariableElement field : ElementFilter
					.fieldsIn(declaredSuperclass.getEnclosedElements())) {
				analyzeField(field, metaModel);
			}
			superclassTypeMirror = declaredSuperclass.getSuperclass();
		}
	}

	private void analyzeField(VariableElement field, MetaModel metaModel) {
		if (field.getModifiers().contains(Modifier.STATIC)
				|| field.getModifiers().contains(Modifier.TRANSIENT)) {
			return;
		}
		MetaModelField fieldModel = new MetaModelField(field.getSimpleName()
				.toString());
		TypeMirror typeMirror = field.asType();
		if (modelUtils.isCollection(typeMirror)) {
			TypeMirror collectionTypeArgument = modelUtils
					.getCollectionTypeArgument(typeMirror);
			if (isDocument(typeMirror)) {
				fieldModel.setType(getReferenceType(collectionTypeArgument));
				metaModel.addReferenceArrayField(fieldModel);
			} else {
				metaModel.addPrimitiveArrayField(fieldModel);
			}
		} else if (typeMirror.getKind() == TypeKind.ARRAY) {
			TypeMirror componentTypeMirror = typeMirror;
			while (componentTypeMirror.getKind() == TypeKind.ARRAY) {
				ArrayType arrayType = (ArrayType) componentTypeMirror;
				componentTypeMirror = arrayType.getComponentType();
			}
			if (isDocument(typeMirror)) {
				fieldModel.setType(getReferenceType(componentTypeMirror));
				metaModel.addReferenceArrayField(fieldModel);
			} else {
				metaModel.addPrimitiveArrayField(fieldModel);
			}
		} else if (isDocument(typeMirror)) {
			fieldModel.setType(getReferenceType(typeMirror));
			fieldModel.setIdField(field.getAnnotation(Id.class) != null);
			metaModel.addReferenceField(fieldModel);
		} else {
			metaModel.addPrimitiveField(fieldModel);
		}
	}

	private boolean isDocument(TypeMirror typeMirror) {
		return modelUtils.isDocument(typeMirror)
				&& modelTypes.contains(modelUtils.toTypeElement(typeMirror));
	}

	private Type getReferenceType(TypeMirror typeMirror) {
		TypeElement typeElement = modelUtils.toTypeElement(typeMirror);
		String canonicalName = processingEnv.getElementUtils()
				.getBinaryName(typeElement).toString().replaceAll("\\$", "_")
				.concat("_");
		return Type.createFromCanonicalName(canonicalName);
	}

}