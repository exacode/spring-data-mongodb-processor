package org.springframework.data.mongodb.processor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
	 * Crawls through type (and super type) field definitions and generates
	 * context for writing meta model.
	 * 
	 * @param pw
	 * @param type
	 * @param outputFileName
	 * @throws IOException
	 */
	public Map<String, Object> analyzeType(TypeElement type) {
		Map<String, Object> context = new HashMap<String, Object>();

		String outputFileName = processingEnv.getElementUtils()
				.getBinaryName(type).toString();
		outputFileName = outputFileName.replaceAll("\\$", "_");
		context.put("fileName", outputFileName + "_");

		int lastDot = outputFileName.lastIndexOf('.');
		String typeName = outputFileName.substring(lastDot + 1);
		context.put("modelTypeName", typeName);
		if (lastDot > 0) {
			context.put("package", outputFileName.substring(0, lastDot));
		}

		Fields fields = new Fields();
		analyzeSuperclassFields(type.getSuperclass(), fields);
		analyzeFields(type, fields);

		context.put("fields", fields);
		return context;
	}

	private void analyzeFields(TypeElement typeElement, Fields fields) {
		for (VariableElement field : ElementFilter.fieldsIn(typeElement
				.getEnclosedElements())) {
			analyzeField(field, fields);
		}
	}

	private void analyzeSuperclassFields(TypeMirror superclassTypeMirror,
			Fields fields) {
		while (superclassTypeMirror instanceof DeclaredType) {
			TypeElement declaredSuperclass = (TypeElement) ((DeclaredType) superclassTypeMirror)
					.asElement();
			for (VariableElement field : ElementFilter
					.fieldsIn(declaredSuperclass.getEnclosedElements())) {
				analyzeField(field, fields);
			}
			superclassTypeMirror = declaredSuperclass.getSuperclass();
		}
	}

	private void analyzeField(VariableElement field, Fields fields) {
		if (field.getModifiers().contains(Modifier.STATIC)
				|| field.getModifiers().contains(Modifier.TRANSIENT)) {
			return;
		}
		Field fieldModel = new Field(field.getSimpleName().toString());
		TypeMirror typeMirror = field.asType();
		if (modelUtils.isCollection(typeMirror)) {
			TypeMirror collectionTypeArgument = modelUtils
					.getCollectionTypeArgument(typeMirror);
			if (isDocument(typeMirror)) {
				createReferenceFieldDefinition(fieldModel,
						modelUtils.toTypeElement(collectionTypeArgument));
				fields.addReferenceArrayField(fieldModel);
			} else {
				fields.addPrimitiveArrayField(fieldModel);
			}
		} else if (typeMirror.getKind() == TypeKind.ARRAY) {
			TypeMirror componentTypeMirror = typeMirror;
			while (componentTypeMirror.getKind() == TypeKind.ARRAY) {
				ArrayType arrayType = (ArrayType) componentTypeMirror;
				componentTypeMirror = arrayType.getComponentType();
			}
			if (isDocument(typeMirror)) {
				createReferenceFieldDefinition(fieldModel,
						modelUtils.toTypeElement(componentTypeMirror));
				fields.addReferenceArrayField(fieldModel);
			} else {
				fields.addPrimitiveArrayField(fieldModel);
			}
		} else if (isDocument(typeMirror)) {
			createReferenceFieldDefinition(fieldModel,
					modelUtils.toTypeElement(typeMirror));
			fieldModel.setIdField(field.getAnnotation(Id.class) != null);
			fields.addReferenceField(fieldModel);
		} else {
			fields.addPrimitiveField(fieldModel);
		}
	}

	private boolean isDocument(TypeMirror typeMirror) {
		return modelUtils.isDocument(typeMirror)
				&& modelTypes.contains(modelUtils.toTypeElement(typeMirror));
	}

	private Field createReferenceFieldDefinition(Field fieldModel,
			TypeElement type) {
		String canonicalName = processingEnv.getElementUtils()
				.getBinaryName(type).toString().replaceAll("\\$", "_");
		int lastDot = canonicalName.lastIndexOf('.');
		String typeName = canonicalName.substring(lastDot + 1);
		String pkg = canonicalName.substring(0, lastDot);
		fieldModel.setPackage(pkg);
		fieldModel.setTypeName(typeName);
		return fieldModel;
	}

}