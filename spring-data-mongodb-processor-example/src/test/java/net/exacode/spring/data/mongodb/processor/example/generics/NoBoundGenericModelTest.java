package net.exacode.spring.data.mongodb.processor.example.generics;

import static org.assertj.core.api.Assertions.assertThat;
import net.exacode.spring.data.mongodb.processor.shared.ArrayField;

import org.junit.Test;

public class NoBoundGenericModelTest {
	@Test
	public void shouldCreateField() {
		assertThat(NoBoundsGenericModel_.subDocument)
				.isInstanceOf(String.class);
		assertThat(NoBoundsGenericModel_.subDocument).isEqualTo("subDocument");
	}

	@Test
	public void shouldCreateArrayField() {
		assertThat(NoBoundsGenericModel_.subDocumentArray).isInstanceOf(
				ArrayField.class);
		assertThat(NoBoundsGenericModel_.subDocumentArray._path).isEqualTo(
				"subDocumentArray");
	}

	@Test
	public void shouldCreateListField() {
		assertThat(NoBoundsGenericModel_.subDocumentList).isInstanceOf(
				ArrayField.class);
		assertThat(NoBoundsGenericModel_.subDocumentList._path).isEqualTo(
				"subDocumentList");
	}
}
