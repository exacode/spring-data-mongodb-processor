package net.exacode.spring.data.mongodb.processor.example.prefixed;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PrefixedModelTest {

	@Test
	public void shouldPrefixModelField() {
		Assertions.assertThat(PrefixedModel_._prefixed("PREFIX").value)
				.isEqualTo("PREFIX.value");
	}

	@Test
	public void shouldAdd2PrefixesToModelField() {
		Assertions.assertThat(
				PrefixedModel_._prefixed("PREFIX1")._prefixed("PREFIX2").value)
				.isEqualTo("PREFIX1.PREFIX2.value");
	}
}
