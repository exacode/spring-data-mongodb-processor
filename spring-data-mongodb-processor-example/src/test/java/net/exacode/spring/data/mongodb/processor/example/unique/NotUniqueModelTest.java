package net.exacode.spring.data.mongodb.processor.example.unique;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class NotUniqueModelTest {
	@Test
	public void sholudCreateAboluteTypeReference() {
		Assertions.assertThat(NotUniqueModel_.notUniqueA.size).isEqualTo(
				"notUniqueA.size");
		Assertions.assertThat(NotUniqueModel_.notUniqueB.id).isEqualTo(
				"notUniqueB.id");
	}
}
