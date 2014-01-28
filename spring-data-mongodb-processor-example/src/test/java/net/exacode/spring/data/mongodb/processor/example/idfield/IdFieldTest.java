package net.exacode.spring.data.mongodb.processor.example.idfield;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class IdFieldTest {

	@Test
	public void shouldAddUnderscoreForIdField() {
		Assertions.assertThat(IdFieldSample_.id).isEqualTo("_id");
		Assertions.assertThat(IdFieldNestedSample_.id).isEqualTo("_id");
		Assertions.assertThat(IdFieldSample_.nested.id).isEqualTo("nested._id");
	}

}
