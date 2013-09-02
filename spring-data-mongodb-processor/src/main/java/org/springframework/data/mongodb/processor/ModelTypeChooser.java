package org.springframework.data.mongodb.processor;

import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

/**
 * Finds all types that needs meta model.
 * 
 * @author mendlik
 */
public class ModelTypeChooser {

	private final ModelUtils modelUtils;

	private final Logger logger;

	public ModelTypeChooser(ProcessingEnvironment processingEnv) {
		this.modelUtils = new ModelUtils(processingEnv);
		this.logger = new Logger(processingEnv);
	}

	/**
	 * Finds all types that require meta model generation. Those types are
	 * determined from fields (single, array and collections) and nested types
	 * of passed {@code typeMirror}.
	 * <p/>
	 * Result is past by parameter.
	 * 
	 * @param typeMirror
	 *            - root level document type
	 * @param models
	 *            - unique set of types that requires meta model generation
	 */
	public void getDocumentTypes(TypeMirror typeMirror, Set<TypeElement> models) {
		if (typeMirror == null || typeMirror.getKind() != TypeKind.DECLARED) {
			return;
		}
		TypeElement typeElement = modelUtils.toTypeElement(typeMirror);
		if (models.contains(typeElement)) {
			return;
		}
		// Create meta model for analyzed type
		if (modelUtils.isDocument(typeElement.asType())) {
			logger.note("Found document: " + typeElement);
			models.add(typeElement);
			// Create Meta model for types of fields
			for (VariableElement field : ElementFilter.fieldsIn(typeElement
					.getEnclosedElements())) {
				if (isPersistableField(field)) {
					getDocumentTypes(field.asType(), models);
				}
			}
			// Create meta model for nested classes
			for (Element enclosedTypeElement : ElementFilter
					.typesIn(typeElement.getEnclosedElements())) {
				// Recurrence added for nested classes
				logger.note("Check nested class: "
						+ enclosedTypeElement.asType());
				getDocumentTypes(enclosedTypeElement.asType(), models);
			}
		} else if (modelUtils.isCollection(typeMirror)) {
			// Create meta model for types used in generic collection
			// definitions
			getDocumentTypes(modelUtils.getCollectionTypeArgument(typeMirror),
					models);
		} else if (typeMirror.getKind() == TypeKind.ARRAY) {
			// Create meta model for types used in arrays
			ArrayType arrayType = (ArrayType) typeMirror;
			getDocumentTypes(arrayType.getComponentType(), models);
		}
	}

	private boolean isPersistableField(VariableElement field) {
		Set<Modifier> fieldModifiers = field.getModifiers();
		boolean persistable = !fieldModifiers.contains(Modifier.STATIC);
		persistable = persistable
				&& fieldModifiers.contains(Modifier.TRANSIENT);
		return persistable;
	}
}
