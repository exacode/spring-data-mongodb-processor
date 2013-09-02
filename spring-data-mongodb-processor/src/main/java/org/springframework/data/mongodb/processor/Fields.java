package org.springframework.data.mongodb.processor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains all fields of analyzed meta model.
 * <p/>
 * Separates primitive from subdocuments and arrays.
 * 
 * @author mendlik
 * 
 */
public class Fields {
	private final Map<String, Field> fieldNames = new LinkedHashMap<String, Field>();
	private final List<Map<String, Field>> fieldGroups = new ArrayList<Map<String, Field>>();
	private final Map<String, Field> referenceFields = new LinkedHashMap<String, Field>();
	private final Map<String, Field> referenceArrayFields = new LinkedHashMap<String, Field>();
	private final Map<String, Field> primitiveFields = new LinkedHashMap<String, Field>();
	private final Map<String, Field> primitiveArrayFields = new LinkedHashMap<String, Field>();

	public Fields() {
		fieldGroups.add(referenceFields);
		fieldGroups.add(referenceArrayFields);
		fieldGroups.add(primitiveFields);
		fieldGroups.add(primitiveArrayFields);
	}

	public void addReferenceField(Field field) {
		addField(field, referenceFields);
	}

	public void addReferenceArrayField(Field field) {
		addField(field, referenceArrayFields);
	}

	public void addPrimitiveField(Field field) {
		addField(field, primitiveFields);
	}

	public void addPrimitiveArrayField(Field field) {
		addField(field, primitiveArrayFields);
	}

	public List<Field> getReferenceFields() {
		return new ArrayList<Field>(referenceFields.values());
	}

	public List<Field> getReferenceArrayFields() {
		return new ArrayList<Field>(referenceArrayFields.values());
	}

	public List<Field> getPrimitiveFields() {
		return new ArrayList<Field>(primitiveFields.values());
	}

	public List<Field> getPrimitiveArrayFields() {
		return new ArrayList<Field>(primitiveArrayFields.values());
	}

	private void addField(Field field, Map<String, Field> targetFieldGroup) {
		String fieldName = field.getFieldName();
		if (fieldNames.containsKey(field.getFieldName())) {
			for (Map<String, Field> fieldGroup : fieldGroups) {
				fieldGroup.remove(fieldName);
			}
		}
		fieldNames.put(fieldName, field);
		targetFieldGroup.put(fieldName, field);
	}

}
