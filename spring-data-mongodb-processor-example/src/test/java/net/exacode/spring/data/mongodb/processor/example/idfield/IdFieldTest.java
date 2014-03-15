package net.exacode.spring.data.mongodb.processor.example.idfield;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class IdFieldTest {

	@Test
	public void shouldAddUnderscoreForIdField() {
		assertThat(IdFieldSample_.id).isEqualTo("_id");
		assertThat(IdFieldNestedSample_.id).isEqualTo("_id");
		assertThat(IdFieldSample_.nested.id).isEqualTo("nested._id");
	}

}
