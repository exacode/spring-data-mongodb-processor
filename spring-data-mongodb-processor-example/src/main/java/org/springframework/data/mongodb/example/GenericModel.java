package org.springframework.data.mongodb.example;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GenericModel<T extends Model> {

	private T model;

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

}
