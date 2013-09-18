package org.springframework.data.mongodb.example.generics;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ModelWithGenericSubDocument {
	private GenericSubDocument<SubDocument> genericSubDocument;

	public GenericSubDocument<SubDocument> getGenericSubDocument() {
		return genericSubDocument;
	}

	public void setGenericSubDocument(
			GenericSubDocument<SubDocument> genericSubDocument) {
		this.genericSubDocument = genericSubDocument;
	}

}
