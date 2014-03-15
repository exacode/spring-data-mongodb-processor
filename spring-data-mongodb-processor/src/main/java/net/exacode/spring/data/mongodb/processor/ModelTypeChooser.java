package net.exacode.spring.data.mongodb.processor;

import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

import net.exacode.spring.data.mongodb.processor.shared.MetaModelExclude;

/**
 * Determines which types need a dedicated meta model.
 * 
 * @author mendlik
 */
public class ModelTypeChooser {

	private final AptUtils aptUtils;

	public ModelTypeChooser(AptUtils aptUtils) {
		this.aptUtils = aptUtils;
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
		TypeElement typeElement = aptUtils.toTypeElement(aptUtils
				.getUpperBound(typeMirror));
		if (typeElement == null || models.contains(typeElement)) {
			return;
		}
		// Create meta model for analyzed type
		if (aptUtils.isDocument(typeElement.asType())) {
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
				getDocumentTypes(enclosedTypeElement.asType(), models);
			}
		} else if (aptUtils.isCollection(typeMirror)
				|| TypeKind.ARRAY.equals(typeMirror)) {
			// Create meta model for types used in generic collection
			// definitions
			getDocumentTypes(
					aptUtils.getCollectionOrArrayTypeArgument(typeMirror),
					models);
		}
	}

	private boolean isPersistableField(VariableElement field) {
		MetaModelExclude metaModelExclude = field
				.getAnnotation(MetaModelExclude.class);
		Set<Modifier> fieldModifiers = field.getModifiers();
		boolean persistable = metaModelExclude == null;
		persistable = persistable && !fieldModifiers.contains(Modifier.STATIC);
		persistable = persistable
				&& !fieldModifiers.contains(Modifier.TRANSIENT);
		return persistable;
	}
}
