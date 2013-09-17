package org.springframework.data.mongodb.example;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GenericModel<T extends Model> {

	private T model;

	private List<T> models;

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

}
