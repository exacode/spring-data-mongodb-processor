package org.springframework.data.mongodb.processor;

import java.io.IOException;
import java.util.Set;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
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

	private final AptUtils aptUtils;

	private final Set<TypeElement> modelTypes;

	public MetaModelGenerator(AptUtils aptUtils, Set<TypeElement> modelTypes) {
		this.aptUtils = aptUtils;
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
		String outputFileName = aptUtils.getElementUtils().getBinaryName(type)
				.toString();
		outputFileName = outputFileName.replaceAll("\\$", "_").concat("_");
		String qualifiedName = type.getQualifiedName().toString();
		MetaModel metaModel = new MetaModel(outputFileName, qualifiedName);

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
		String fieldName = field.getSimpleName().toString();
		TypeMirror typeMirror = field.asType();
		if (aptUtils.isCollection(typeMirror)
				|| typeMirror.getKind() == TypeKind.ARRAY) {
			analyzeArrayField(metaModel, fieldName, typeMirror);
		} else {
			analyzeSingleField(field, metaModel, fieldName, typeMirror);
		}
	}

	private void analyzeSingleField(VariableElement field, MetaModel metaModel,
			String fieldName, TypeMirror typeMirror) {
		boolean idField = field.getAnnotation(Id.class) != null;
		typeMirror = aptUtils.getUpperBound(typeMirror);
		if (isDocument(typeMirror)) {
			Type type = Type.create(aptUtils, typeMirror);
			metaModel.addReferenceField(new MetaModelField(fieldName, type,
					idField));
		} else {
			metaModel.addPrimitiveField(new MetaModelField(fieldName, null,
					idField));
		}
	}

	private void analyzeArrayField(MetaModel metaModel, String fieldName,
			TypeMirror typeMirror) {
		TypeMirror componentTypeMirror = aptUtils
				.getCollectionOrArrayTypeArgument(typeMirror);
		componentTypeMirror = aptUtils.getUpperBound(componentTypeMirror);
		if (isDocument(componentTypeMirror)) {
			Type type = Type.create(aptUtils, componentTypeMirror);
			metaModel
					.addReferenceArrayField(new MetaModelField(fieldName, type));
		} else {
			metaModel.addPrimitiveArrayField(new MetaModelField(fieldName));
		}
	}

	private boolean isDocument(TypeMirror typeMirror) {
		return aptUtils.isDocument(typeMirror)
				&& modelTypes.contains(aptUtils.toTypeElement(typeMirror));
	}

}