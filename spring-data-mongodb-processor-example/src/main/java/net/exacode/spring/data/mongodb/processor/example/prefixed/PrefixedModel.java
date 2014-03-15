package net.exacode.spring.data.mongodb.processor.example.prefixed;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PrefixedModel {

	private String value;

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
