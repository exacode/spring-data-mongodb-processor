package org.springframework.data.mongodb.example;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class ModelChildTest {
	@Test
	public void sholudHaveChildField() {
		Assertions.assertThat(ModelChild_.childField).isEqualTo("childField");
	}

	@Test
	public void sholudHaveCommonField() {
		Assertions.assertThat(ModelChild_.commonField).isEqualTo("commonField");
	}

	@Test
	public void sholudHaveParentField() {
		Assertions.assertThat(ModelChild_.parentField).isEqualTo("parentField");
	}
}
