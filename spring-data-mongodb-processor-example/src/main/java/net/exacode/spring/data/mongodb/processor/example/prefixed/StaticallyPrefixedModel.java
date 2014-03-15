package net.exacode.spring.data.mongodb.processor.example.prefixed;

import net.exacode.spring.data.mongodb.processor.shared.MetaModel;

@MetaModel(prefix = "PREFIX")
public class StaticallyPrefixedModel {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
