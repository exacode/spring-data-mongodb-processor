package org.springframework.data.mongodb.processor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains all fields of analyzed meta model.
 * <p/>
 * Separates primitive fields from subdocuments and arrays.
 * 
 * @author mendlik
 * 
 */
public class MetaModel {

	private final Type type;

	private final List<Map<String, MetaModelField>> fieldGroups = new ArrayList<Map<String, MetaModelField>>();
	private final Map<String, MetaModelField> referenceFields = new LinkedHashMap<String, MetaModelField>();
	private final Map<String, MetaModelField> referenceArrayFields = new LinkedHashMap<String, MetaModelField>();
	private final Map<String, MetaModelField> primitiveFields = new LinkedHashMap<String, MetaModelField>();
	private final Map<String, MetaModelField> primitiveArrayFields = new LinkedHashMap<String, MetaModelField>();

	private final Map<String, String> importedTypes = new HashMap<String, String>();

	public MetaModel(String canonicalName) {
		this.type = Type.createFromCanonicalName(canonicalName);

		fieldGroups.add(referenceFields);
		fieldGroups.add(referenceArrayFields);
		fieldGroups.add(primitiveFields);
		fieldGroups.add(primitiveArrayFields);
	}

	public Type getType() {
		return type;
	}

	public void addReferenceField(MetaModelField field) {
		addField(field, referenceFields);
	}

	public void addReferenceArrayField(MetaModelField field) {
		addField(field, referenceArrayFields);
	}

	public void addPrimitiveField(MetaModelField field) {
		addField(field, primitiveFields);
	}

	public void addPrimitiveArrayField(MetaModelField field) {
		addField(field, primitiveArrayFields);
	}

	public List<MetaModelField> getReferenceFields() {
		return new ArrayList<MetaModelField>(referenceFields.values());
	}

	public List<MetaModelField> getReferenceArrayFields() {
		return new ArrayList<MetaModelField>(referenceArrayFields.values());
	}

	public List<MetaModelField> getPrimitiveFields() {
		return new ArrayList<MetaModelField>(primitiveFields.values());
	}

	public List<MetaModelField> getPrimitiveArrayFields() {
		return new ArrayList<MetaModelField>(primitiveArrayFields.values());
	}

	public boolean addImport(String pkg, String className) {
		String canonicalName = (pkg == null) ? className : pkg + "."
				+ className;
		if (importedTypes.containsKey(className)) {
			return false;
		}
		importedTypes.put(className, canonicalName);
		return true;
	}

	public String getTypeReference(String pkg, String className) {
		String canonicalName = (pkg == null) ? className : pkg + "."
				+ className;
		if (canonicalName.startsWith(type.getCanonicalName())) {
			return className;
		}
		String importedCanonicalName = importedTypes.get(className);
		if (importedCanonicalName != null
				&& importedCanonicalName.equals(canonicalName)) {
			return className;
		}
		return canonicalName;
	}

	private void addField(MetaModelField field,
			Map<String, MetaModelField> targetFieldGroup) {
		String fieldName = field.getFieldName();
		for (Map<String, MetaModelField> fieldGroup : fieldGroups) {
			fieldGroup.remove(fieldName);
		}
		targetFieldGroup.put(fieldName, field);
	}
}
