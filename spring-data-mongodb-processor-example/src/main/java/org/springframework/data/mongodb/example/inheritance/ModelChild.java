package org.springframework.data.mongodb.example.inheritance;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ModelChild extends ModelParent {

	private String commonField;

	private String childField;

	public String getChildField() {
		return childField;
	}

	public void setChildField(String childField) {
		this.childField = childField;
	}

	@Override
	public String getCommonField() {
		return commonField;
	}

	@Override
	public void setCommonField(String commonField) {
		this.commonField = commonField;
	}

}
