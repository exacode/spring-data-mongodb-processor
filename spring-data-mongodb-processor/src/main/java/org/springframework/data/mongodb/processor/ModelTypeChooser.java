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
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.ElementFilter;

/**
 * Determines which types need a dedicated meta model.
 * 
 * @author mendlik
 */
public class ModelTypeChooser {

	private final AptUtils aptUtils;

	private final Logger logger;

	public ModelTypeChooser(ProcessingEnvironment processingEnv) {
		this.aptUtils = new AptUtils(processingEnv);
		this.logger = new Logger(processingEnv);
	}

	/**
	 * Finds all types that require meta model generation.
	 * <p>
	 * Those types are determined from document's fields (like: references,
	 * arrays and collections) and nested types.
	 * <p>
	 * Result is past by {@code models} parameter.
	 * 
	 * @param typeMirror
	 *            - root level document type
	 * @param models
	 *            - unique set of types that requires meta model generation
	 */
	public void getDocumentTypes(TypeMirror typeMirror, Set<TypeElement> models) {
		if (typeMirror == null) {
			return;
		}
		if (TypeKind.TYPEVAR.equals(typeMirror.getKind())) {
			TypeVariable typeVariable = (TypeVariable) typeMirror;
			getDocumentTypes(typeVariable.getLowerBound(), models);
		} else if (TypeKind.WILDCARD.equals(typeMirror.getKind())) {
			WildcardType wildcardVariable = (WildcardType) typeMirror;
			getDocumentTypes(wildcardVariable.getExtendsBound(), models);
		} else {
			TypeElement typeElement = aptUtils.toTypeElement(typeMirror);
			if (typeElement == null || models.contains(typeElement)) {
				return;
			}
			// Create meta model for analyzed type
			if (aptUtils.isDocument(typeElement.asType())) {
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
			} else if (aptUtils.isCollection(typeMirror)) {
				// Create meta model for types used in generic collection
				// definitions
				getDocumentTypes(
						aptUtils.getCollectionTypeArgument(typeMirror),
						models);
			} else if (typeMirror.getKind() == TypeKind.ARRAY) {
				// Create meta model for types used in arrays
				ArrayType arrayType = (ArrayType) typeMirror;
				getDocumentTypes(arrayType.getComponentType(), models);
			}
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
