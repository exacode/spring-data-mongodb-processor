package org.springframework.data.mongodb.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Java APT mechanism utility class used during meta model generation.
 * 
 * @author mendlik
 * 
 */
public class ModelUtils {
	private final Types types;
	private final TypeElement collectionType;
	private final TypeElement objectType;
	private final WildcardType nullWildcardType;
	private final List<TypeElement> nonDocumentTypes = new ArrayList<TypeElement>();
	private final Map<String, DeclaredType> cachedParentTypes = new HashMap<String, DeclaredType>();

	public ModelUtils(ProcessingEnvironment processingEnv) {
		Elements elements = processingEnv.getElementUtils();
		this.types = processingEnv.getTypeUtils();
		this.collectionType = elements.getTypeElement("java.util.Collection");
		this.objectType = elements.getTypeElement("java.lang.Object");
		this.nullWildcardType = types.getWildcardType(null, null);

		// MongoDB Non document types from MongoMappingContext
		// Primitive wrappers
		nonDocumentTypes.add(elements.getTypeElement("java.lang.Integer"));
		nonDocumentTypes.add(elements.getTypeElement("java.lang.Long"));
		nonDocumentTypes.add(elements.getTypeElement("java.lang.Float"));
		nonDocumentTypes.add(elements.getTypeElement("java.lang.Double"));
		nonDocumentTypes.add(elements.getTypeElement("java.lang.Character"));
		nonDocumentTypes.add(elements.getTypeElement("java.lang.Byte"));
		nonDocumentTypes.add(elements.getTypeElement("java.lang.Short"));
		nonDocumentTypes.add(elements.getTypeElement("java.lang.Boolean"));
		// Basic types
		nonDocumentTypes.add(elements.getTypeElement("java.lang.String"));
		nonDocumentTypes.add(elements.getTypeElement("java.util.Date"));
		nonDocumentTypes.add(elements.getTypeElement("java.net.URL"));
		nonDocumentTypes.add(elements.getTypeElement("java.math.BigInteger"));
		nonDocumentTypes.add(elements.getTypeElement("java.math.BigDecimal"));
		nonDocumentTypes.add(elements.getTypeElement("java.util.Collection"));
		nonDocumentTypes.add(elements.getTypeElement("java.lang.Void"));
		// BSON types
		nonDocumentTypes
				.add(elements.getTypeElement("org.bson.types.ObjectId"));
		// Joda date types
		nonDocumentTypes.add(elements.getTypeElement("org.joda.time.DateTime"));
		nonDocumentTypes
				.add(elements.getTypeElement("org.joda.time.LocalDate"));
		nonDocumentTypes.add(elements
				.getTypeElement("org.joda.time.LocalDateTime"));
		nonDocumentTypes.add(elements
				.getTypeElement("org.joda.time.DateMidnight"));
	}

	/**
	 * Determines if given {@code type} should be treated as MongoDB document or
	 * nested document.
	 * 
	 * @param type
	 * @return
	 */
	public boolean isDocument(TypeMirror type) {
		if (type == null) {
			return false;
		}
		if (type.getKind().isPrimitive()) {
			return false;
		}
		if (type.getKind() == TypeKind.ARRAY) {
			return false;
		}
		if (isEnum(type)) {
			return false;
		}
		if (isCollection(type)) {
			return false;
		}
		for (TypeElement nonDocumentType : nonDocumentTypes) {
			// nonDocumentType == null when given type could not be found
			if (nonDocumentType != null && isA(type, nonDocumentType)) {
				return false;
			}
		}
		return type.getKind() == TypeKind.DECLARED;
	}

	/**
	 * Checks if given type is assignable to {@code Collection<?>}.
	 * 
	 * @param type
	 * @return
	 */
	public boolean isCollection(TypeMirror type) {
		return type != null && isA(type, collectionType);
	}

	/**
	 * Retrieves type argument {@code <T>} from type that implements
	 * {@code Collection<T>}.
	 * 
	 * 
	 * @param type
	 * @return collection type argument or null if type argument cannot be
	 *         determined.
	 * 
	 */
	public TypeMirror getCollectionTypeArgument(TypeMirror type) {
		if (type == null || !(type instanceof TypeElement)
				|| !isA(type, collectionType)) {
			return null;
		}
		TypeElement typeElemnet = (TypeElement) type;
		while (typeElemnet != null && !objectType.equals(typeElemnet)) {
			for (TypeMirror typeInterface : typeElemnet.getInterfaces()) {
				TypeElement typeInterfaceElement = toTypeElement(typeInterface);
				if (collectionType.equals(typeInterfaceElement)) {
					return toDeclaredType(typeInterface).getTypeArguments()
							.get(0);
				}
			}
			typeElemnet = toTypeElement(typeElemnet.getSuperclass());
		}
		return null;
	}

	/**
	 * 
	 * @param type
	 * @return instance of {@link TypeElement} or null if {@code type} cannot be
	 *         converted
	 */
	public TypeElement toTypeElement(TypeMirror type) {
		if (type != null && isTypeElement(type)) {
			return (TypeElement) ((DeclaredType) type).asElement();
		}
		return null;
	}

	private boolean isTypeElement(TypeMirror type) {
		return isDeclaredType(type)
				&& ((DeclaredType) type).asElement() instanceof TypeElement;
	}

	private boolean isDeclaredType(TypeMirror type) {
		return type instanceof DeclaredType;
	}

	private DeclaredType toDeclaredType(TypeMirror type) {
		if (isDeclaredType(type)) {
			return (DeclaredType) type;
		}
		return null;
	}

	private boolean isEnum(TypeMirror type) {
		if (isDeclaredType(type)) {
			DeclaredType declaredType = (DeclaredType) type;
			return declaredType.asElement().getKind() == ElementKind.ENUM;
		}
		return false;
	}

	private boolean isA(TypeMirror type, TypeElement typeElement) {
		// Have we used this type before?
		DeclaredType parentType = cachedParentTypes.get(typeElement
				.getQualifiedName().toString());
		if (parentType == null) {
			// How many generic type parameters does this typeElement require?
			int genericsCount = typeElement.getTypeParameters().size();

			// Fill the right number of types with nulls
			TypeMirror[] typeMirrors = new TypeMirror[genericsCount];
			for (int i = 0; i < genericsCount; i++) {
				typeMirrors[i] = nullWildcardType;
			}

			// Locate the correct DeclaredType to match with the type
			parentType = types.getDeclaredType(typeElement, typeMirrors);

			// Remember this DeclaredType
			cachedParentTypes.put(typeElement.getQualifiedName().toString(),
					parentType);
		}
		// Is the given type able to be assigned as the typeElement?
		return types.isAssignable(type, parentType);
	}
}
