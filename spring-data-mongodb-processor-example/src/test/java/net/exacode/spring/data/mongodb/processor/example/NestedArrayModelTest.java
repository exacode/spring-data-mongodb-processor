package net.exacode.spring.data.mongodb.processor.example;

import net.exacode.spring.data.mongodb.processor.shared.ArrayField;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class NestedArrayModelTest {
	@Test
	public void shouldGenerateSubDocumentArrays() {
		Assertions.assertThat(NestedArrayModel_.arrayOfListsOfStrings)
				.isInstanceOf(ArrayField.class);
		Assertions.assertThat(NestedArrayModel_.listOfArraysOfStrings)
				.isInstanceOf(ArrayField.class);
		Assertions.assertThat(NestedArrayModel_.listOfListsOfStrings)
				.isInstanceOf(ArrayField.class);
		Assertions.assertThat(NestedArrayModel_.listOfSetsOfStrings)
				.isInstanceOf(ArrayField.class);
	}

	@Test
	public void shouldGenerateSimpleFieldArrays() {
		Assertions.assertThat(NestedArrayModel_.arrayOfListsOfSubDocuments)
				.isInstanceOf(SubDocument_.SubDocument_Array.class);
		Assertions.assertThat(NestedArrayModel_.listOfArraysOfSubDocuments)
				.isInstanceOf(SubDocument_.SubDocument_Array.class);
		Assertions.assertThat(NestedArrayModel_.listOfListsOfSubDocuments)
				.isInstanceOf(SubDocument_.SubDocument_Array.class);
		Assertions.assertThat(NestedArrayModel_.listOfSetsOfSubDocuments)
				.isInstanceOf(SubDocument_.SubDocument_Array.class);
	}
}
