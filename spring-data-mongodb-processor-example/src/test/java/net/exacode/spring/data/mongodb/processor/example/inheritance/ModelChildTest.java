package net.exacode.spring.data.mongodb.processor.example.inheritance;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ModelChildTest {
	@Test
	public void sholudHaveChildField() {
		assertThat(ModelChild_.childField).isEqualTo("childField");
	}

	@Test
	public void sholudHaveCommonField() {
		assertThat(ModelChild_.commonField).isEqualTo("commonField");
	}

	@Test
	public void sholudHaveParentField() {
		assertThat(ModelChild_.parentField).isEqualTo("parentField");
	}
}
