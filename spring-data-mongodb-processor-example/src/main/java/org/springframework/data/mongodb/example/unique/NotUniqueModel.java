package org.springframework.data.mongodb.example.unique;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.example.unique.a.SubDocument;

@Document
public class NotUniqueModel {

	private SubDocument notUniqueA;

	private org.springframework.data.mongodb.example.unique.b.SubDocument notUniqueB;

	public SubDocument getNotUniqueA() {
		return notUniqueA;
	}

	public void setNotUniqueA(SubDocument notUniqueA) {
		this.notUniqueA = notUniqueA;
	}

	public org.springframework.data.mongodb.example.unique.b.SubDocument getNotUniqueB() {
		return notUniqueB;
	}

	public void setNotUniqueB(
			org.springframework.data.mongodb.example.unique.b.SubDocument notUniqueB) {
		this.notUniqueB = notUniqueB;
	}

}
