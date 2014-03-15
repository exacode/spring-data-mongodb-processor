package net.exacode.spring.data.mongodb.processor.example.exclusion;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ModelWithExclusionTest {

	@Test(expected = NoSuchFieldException.class)
	public void shouldHaveNoExcludedField() throws NoSuchFieldException,
			SecurityException {
		ModelWithExclusion_.class.getField("excludedSubModel");
	}

	@Test(expected = ClassNotFoundException.class)
	public void shouldHaveNoExcludedField2() throws ClassNotFoundException {
		ModelWithExclusion_.class.getClassLoader().loadClass(
				ExcludedSubModel.class.getCanonicalName() + "_");
	}

	@Test
	public void shouldHaveSimplePathForPathOnlyExclusion() {
		assertThat(ModelWithExclusion_.excludedSubModelWithPath).isEqualTo(
				"excludedSubModelWithPath");
	}
}
