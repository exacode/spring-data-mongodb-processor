package org.springframework.data.mongodb.example.inheritance;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ModelParent {
	private String commonField;

	private String parentField;

	public String getCommonField() {
		return commonField;
	}

	public void setCommonField(String commonField) {
		this.commonField = commonField;
	}

	public String getParentField() {
		return parentField;
	}

	public void setParentField(String parentField) {
		this.parentField = parentField;
	}

}
