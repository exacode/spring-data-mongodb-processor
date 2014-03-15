package net.exacode.spring.data.mongodb.processor.example.generics;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class GenericModelTest {
	@Test
	public void shouldCreateFieldWithUpperBoundType() {
		assertThat(GenericModel_.subDocument).isInstanceOf(
				SubDocument_.SubDocument_Field.class);
		assertThat(GenericModel_.subDocument.id).isEqualTo("subDocument._id");
	}

	@Test
	public void shouldCreateArrayFieldWithUpperBoundType() {
		assertThat(GenericModel_.subDocumentArray).isInstanceOf(
				SubDocument_.SubDocument_Array.class);
		assertThat(GenericModel_.subDocumentArray.id).isEqualTo(
				"subDocumentArray._id");
	}

	@Test
	public void shouldCreateListFieldWithUpperBoundType() {
		assertThat(GenericModel_.subDocumentList).isInstanceOf(
				SubDocument_.SubDocument_Array.class);
		assertThat(GenericModel_.subDocumentList.id).isEqualTo(
				"subDocumentList._id");
	}
}
