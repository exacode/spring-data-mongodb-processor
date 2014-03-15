package net.exacode.spring.data.mongodb.processor.example;

import static org.assertj.core.api.Assertions.assertThat;
import net.exacode.spring.data.mongodb.processor.shared.ArrayField;

import org.junit.Test;

public class NestedArrayModelTest {
	@Test
	public void shouldGenerateSubDocumentArrays() {
		assertThat(NestedArrayModel_.arrayOfListsOfStrings).isInstanceOf(
				ArrayField.class);
		assertThat(NestedArrayModel_.listOfArraysOfStrings).isInstanceOf(
				ArrayField.class);
		assertThat(NestedArrayModel_.listOfListsOfStrings).isInstanceOf(
				ArrayField.class);
		assertThat(NestedArrayModel_.listOfSetsOfStrings).isInstanceOf(
				ArrayField.class);
	}

	@Test
	public void shouldGenerateSimpleFieldArrays() {
		assertThat(NestedArrayModel_.arrayOfListsOfSubDocuments).isInstanceOf(
				SubDocument_.SubDocument_Array.class);
		assertThat(NestedArrayModel_.listOfArraysOfSubDocuments).isInstanceOf(
				SubDocument_.SubDocument_Array.class);
		assertThat(NestedArrayModel_.listOfListsOfSubDocuments).isInstanceOf(
				SubDocument_.SubDocument_Array.class);
		assertThat(NestedArrayModel_.listOfSetsOfSubDocuments).isInstanceOf(
				SubDocument_.SubDocument_Array.class);
	}
}
