package net.exacode.spring.data.mongodb.processor.example.idfield;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class IdFieldSample {

	private final String id;

	private final IdFieldNestedSample nested;

	public IdFieldSample(String id, IdFieldNestedSample nested) {
		this.id = id;
		this.nested = nested;
	}

	public String getId() {
		return id;
	}

	public IdFieldNestedSample getNested() {
		return nested;
	}

}
