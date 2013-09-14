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

	private String fieldName;
	private boolean idField;
	private Type type;

	public MetaModelField(String fieldName) {
		super();
		this.fieldName = fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
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

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isIdField() {
		return idField;
	}

	public void setIdField(boolean idField) {
		this.idField = idField;
	}

}
