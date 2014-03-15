package net.exacode.spring.data.mongodb.processor.example.unique;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class NotUniqueModelTest {
	@Test
	public void sholudCreateAboluteTypeReference() {
		assertThat(NotUniqueModel_.notUniqueA.size)
				.isEqualTo("notUniqueA.size");
		assertThat(NotUniqueModel_.notUniqueB.id).isEqualTo("notUniqueB._id");
	}
}
