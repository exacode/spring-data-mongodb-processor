package org.springframework.data.mongodb.example.generics;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class GenericModelTest {
	@Test
	public void shouldCreateFieldWithUpperBoundType() {
		Assertions.assertThat(GenericModel_.subDocument).isInstanceOf(
				SubDocument_.SubDocument_Field.class);
		Assertions.assertThat(GenericModel_.subDocument.id).isEqualTo(
				"subDocument.id");
	}

	@Test
	public void shouldCreateArrayFieldWithUpperBoundType() {
		Assertions.assertThat(GenericModel_.subDocumentArray).isInstanceOf(
				SubDocument_.SubDocument_Array.class);
		Assertions.assertThat(GenericModel_.subDocumentArray.id).isEqualTo(
				"subDocumentArray.id");
	}

	@Test
	public void shouldCreateListFieldWithUpperBoundType() {
		Assertions.assertThat(GenericModel_.subDocumentList).isInstanceOf(
				SubDocument_.SubDocument_Array.class);
		Assertions.assertThat(GenericModel_.subDocumentList.id).isEqualTo(
				"subDocumentList.id");
	}
}
