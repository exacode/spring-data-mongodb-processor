package org.springframework.data.mongodb.example.generics;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.springframework.data.mongodb.processor.shared.ArrayField;

public class NoBoundGenericModelTest {
	@Test
	public void shouldCreateField() {
		Assertions.assertThat(NoBoundsGenericModel_.subDocument).isInstanceOf(
				String.class);
		Assertions.assertThat(NoBoundsGenericModel_.subDocument).isEqualTo(
				"subDocument");
	}

	@Test
	public void shouldCreateArrayField() {
		Assertions.assertThat(NoBoundsGenericModel_.subDocumentArray)
				.isInstanceOf(ArrayField.class);
		Assertions.assertThat(NoBoundsGenericModel_.subDocumentArray._path)
				.isEqualTo("subDocumentArray");
	}

	@Test
	public void shouldCreateListField() {
		Assertions.assertThat(NoBoundsGenericModel_.subDocumentList)
				.isInstanceOf(ArrayField.class);
		Assertions.assertThat(NoBoundsGenericModel_.subDocumentList._path)
				.isEqualTo("subDocumentList");
	}
}
