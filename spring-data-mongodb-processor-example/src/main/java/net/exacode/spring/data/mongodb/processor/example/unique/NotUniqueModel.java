package net.exacode.spring.data.mongodb.processor.example.unique;

import net.exacode.spring.data.mongodb.processor.example.unique.a.SubDocument;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class NotUniqueModel {

	private SubDocument notUniqueA;

	private net.exacode.spring.data.mongodb.processor.example.unique.b.SubDocument notUniqueB;

	public SubDocument getNotUniqueA() {
		return notUniqueA;
	}

	public void setNotUniqueA(SubDocument notUniqueA) {
		this.notUniqueA = notUniqueA;
	}

	public net.exacode.spring.data.mongodb.processor.example.unique.b.SubDocument getNotUniqueB() {
		return notUniqueB;
	}

	public void setNotUniqueB(
			net.exacode.spring.data.mongodb.processor.example.unique.b.SubDocument notUniqueB) {
		this.notUniqueB = notUniqueB;
	}

}
