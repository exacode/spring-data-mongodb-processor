package org.springframework.data.mongodb.processor.model;

/**
 * Simple document's field representation.
 * <p/>
 * Used during meta model generation.
 * 
 * @author mendlik
 * 
 */
public class MetaModelField {

	private final String fieldName;
	private final boolean idField;
	private final Type type;

	public MetaModelField(String fieldName) {
		this(fieldName, null, false);
	}

	public MetaModelField(String fieldName, Type type) {
		this(fieldName, type, false);
	}

	public MetaModelField(String fieldName, Type type, boolean idField) {
		this.fieldName = fieldName;
		this.type = type;
		this.idField = idField;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldPathName() {
		return idField ? "_id" : fieldName;
	}

	public Type getType() {
		return type;
	}

	public boolean isIdField() {
		return idField;
	}

}
