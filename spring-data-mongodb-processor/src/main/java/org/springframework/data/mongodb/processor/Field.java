package org.springframework.data.mongodb.processor;

/**
 * Simple document's field representation.
 * <p/>
 * Used during meta model generation.
 * 
 * @author mendlik
 * 
 */
public class Field {

	private String fieldName;
	private String typeName;
	private String pkg;
	private boolean idField;

	public Field(String fieldName) {
		super();
		this.fieldName = fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setPackage(String pkg) {
		this.pkg = pkg;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldPathName() {
		return idField ? "_id" : fieldName;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getPackage() {
		return pkg;
	}

	public boolean isIdField() {
		return idField;
	}

	public void setIdField(boolean idField) {
		this.idField = idField;
	}

}
