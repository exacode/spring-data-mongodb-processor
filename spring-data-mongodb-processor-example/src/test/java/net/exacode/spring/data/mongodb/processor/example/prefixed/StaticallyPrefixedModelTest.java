package net.exacode.spring.data.mongodb.processor.example.prefixed;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class StaticallyPrefixedModelTest {

	@Test
	public void shouldPrefixModelFields() {
		Assertions.assertThat(StaticallyPrefixedModel_.value).isEqualTo(
				"PREFIX.value");
	}

}
