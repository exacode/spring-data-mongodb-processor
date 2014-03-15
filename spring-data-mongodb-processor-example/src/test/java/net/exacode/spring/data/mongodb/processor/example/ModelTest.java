package net.exacode.spring.data.mongodb.processor.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ModelTest {

	@Test
	public void shouldHaveSingleFields() {
		assertThat(Model_.byteValue).isEqualTo("byteValue");
		assertThat(Model_.shortValue).isEqualTo("shortValue");
		assertThat(Model_.intValue).isEqualTo("intValue");
		assertThat(Model_.longValue).isEqualTo("longValue");
		assertThat(Model_.floatValue).isEqualTo("floatValue");
		assertThat(Model_.doubleValue).isEqualTo("doubleValue");
		assertThat(Model_.dateValue).isEqualTo("dateValue");
		assertThat(Model_.dateTimeValue).isEqualTo("dateTimeValue");
		assertThat(Model_.stringValue).isEqualTo("stringValue");
		assertThat(Model_.bigDecimalValue).isEqualTo("bigDecimalValue");
		assertThat(Model_.byteWrapperValue).isEqualTo("byteWrapperValue");
		assertThat(Model_.shortWrapperValue).isEqualTo("shortWrapperValue");
		assertThat(Model_.intWrapperValue).isEqualTo("intWrapperValue");
		assertThat(Model_.longWrapperValue).isEqualTo("longWrapperValue");
		assertThat(Model_.floatWrapperValue).isEqualTo("floatWrapperValue");
		assertThat(Model_.doubleWrapperValue).isEqualTo("doubleWrapperValue");
		assertThat(Model_.modelValue._path).isEqualTo("modelValue");
		assertThat(Model_.subDocument._path).isEqualTo("subDocument");
		assertThat(Model_.objectValue._path).isEqualTo("objectValue");
	}

	@Test
	public void shouldHaveArrayFields() {
		assertThat(Model_.byteArray._path).isEqualTo("byteArray");
		assertThat(Model_.shortArray._path).isEqualTo("shortArray");
		assertThat(Model_.intArray._path).isEqualTo("intArray");
		assertThat(Model_.longArray._path).isEqualTo("longArray");
		assertThat(Model_.floatArray._path).isEqualTo("floatArray");
		assertThat(Model_.doubleArray._path).isEqualTo("doubleArray");
		assertThat(Model_.dateArray._path).isEqualTo("dateArray");
		assertThat(Model_.dateTimeArray._path).isEqualTo("dateTimeArray");
		assertThat(Model_.stringArray._path).isEqualTo("stringArray");
		assertThat(Model_.bigDecimalArray._path).isEqualTo("bigDecimalArray");
		assertThat(Model_.byteWrapperArray._path).isEqualTo("byteWrapperArray");
		assertThat(Model_.shortWrapperArray._path).isEqualTo(
				"shortWrapperArray");
		assertThat(Model_.intWrapperArray._path).isEqualTo("intWrapperArray");
		assertThat(Model_.longWrapperArray._path).isEqualTo("longWrapperArray");
		assertThat(Model_.floatWrapperArray._path).isEqualTo(
				"floatWrapperArray");
		assertThat(Model_.doubleWrapperArray._path).isEqualTo(
				"doubleWrapperArray");
		assertThat(Model_.modelArray._path).isEqualTo("modelArray");
		assertThat(Model_.subDocumentArray._path).isEqualTo("subDocumentArray");
		assertThat(Model_.objectArray._path).isEqualTo("objectArray");
	}

	@Test
	public void shouldHaveIndexableArrayFields() {
		assertThat(Model_.byteArray.index(5)).isEqualTo("byteArray.5");
		assertThat(Model_.shortArray.index(5)).isEqualTo("shortArray.5");
		assertThat(Model_.intArray.index(5)).isEqualTo("intArray.5");
		assertThat(Model_.longArray.index(5)).isEqualTo("longArray.5");
		assertThat(Model_.floatArray.index(5)).isEqualTo("floatArray.5");
		assertThat(Model_.doubleArray.index(5)).isEqualTo("doubleArray.5");
		assertThat(Model_.dateArray.index(5)).isEqualTo("dateArray.5");
		assertThat(Model_.dateTimeArray.index(5)).isEqualTo("dateTimeArray.5");
		assertThat(Model_.stringArray.index(5)).isEqualTo("stringArray.5");
		assertThat(Model_.bigDecimalArray.index(5)).isEqualTo(
				"bigDecimalArray.5");
		assertThat(Model_.byteWrapperArray.index(5)).isEqualTo(
				"byteWrapperArray.5");
		assertThat(Model_.shortWrapperArray.index(5)).isEqualTo(
				"shortWrapperArray.5");
		assertThat(Model_.intWrapperArray.index(5)).isEqualTo(
				"intWrapperArray.5");
		assertThat(Model_.longWrapperArray.index(5)).isEqualTo(
				"longWrapperArray.5");
		assertThat(Model_.floatWrapperArray.index(5)).isEqualTo(
				"floatWrapperArray.5");
		assertThat(Model_.doubleWrapperArray.index(5)).isEqualTo(
				"doubleWrapperArray.5");
		assertThat(Model_.modelArray.index(5)._path).isEqualTo("modelArray.5");
		assertThat(Model_.subDocumentArray.index(5)._path).isEqualTo(
				"subDocumentArray.5");
		assertThat(Model_.objectArray.index(5)._path)
				.isEqualTo("objectArray.5");
	}

	@Test
	public void shouldHaveIndexableListFields() {
		assertThat(Model_.byteList.index(5)).isEqualTo("byteList.5");
		assertThat(Model_.shortList.index(5)).isEqualTo("shortList.5");
		assertThat(Model_.intList.index(5)).isEqualTo("intList.5");
		assertThat(Model_.longList.index(5)).isEqualTo("longList.5");
		assertThat(Model_.floatList.index(5)).isEqualTo("floatList.5");
		assertThat(Model_.doubleList.index(5)).isEqualTo("doubleList.5");
		assertThat(Model_.dateList.index(5)).isEqualTo("dateList.5");
		assertThat(Model_.dateTimeList.index(5)).isEqualTo("dateTimeList.5");
		assertThat(Model_.stringList.index(5)).isEqualTo("stringList.5");
		assertThat(Model_.bigDecimalList.index(5))
				.isEqualTo("bigDecimalList.5");
		assertThat(Model_.modelList.index(5)._path).isEqualTo("modelList.5");
		assertThat(Model_.subDocumentList.index(5)._path).isEqualTo(
				"subDocumentList.5");
		assertThat(Model_.objectList.index(5)._path).isEqualTo("objectList.5");
	}

	@Test
	public void shouldHaveListFields() {
		assertThat(Model_.byteList._path).isEqualTo("byteList");
		assertThat(Model_.shortList._path).isEqualTo("shortList");
		assertThat(Model_.intList._path).isEqualTo("intList");
		assertThat(Model_.longList._path).isEqualTo("longList");
		assertThat(Model_.floatList._path).isEqualTo("floatList");
		assertThat(Model_.doubleList._path).isEqualTo("doubleList");
		assertThat(Model_.dateList._path).isEqualTo("dateList");
		assertThat(Model_.dateTimeList._path).isEqualTo("dateTimeList");
		assertThat(Model_.stringList._path).isEqualTo("stringList");
		assertThat(Model_.bigDecimalList._path).isEqualTo("bigDecimalList");
		assertThat(Model_.modelList._path).isEqualTo("modelList");
		assertThat(Model_.subDocumentList._path).isEqualTo("subDocumentList");
		assertThat(Model_.objectList._path).isEqualTo("objectList");
	}

	@Test
	public void shouldHaveAccessToNestedModelFields() {
		assertThat(Model_.modelValue.byteValue).isEqualTo(
				"modelValue.byteValue");
		assertThat(Model_.modelValue.shortValue).isEqualTo(
				"modelValue.shortValue");
		assertThat(Model_.modelValue.intValue).isEqualTo("modelValue.intValue");
		assertThat(Model_.modelValue.longValue).isEqualTo(
				"modelValue.longValue");
		assertThat(Model_.modelValue.floatValue).isEqualTo(
				"modelValue.floatValue");
		assertThat(Model_.modelValue.doubleValue).isEqualTo(
				"modelValue.doubleValue");
		assertThat(Model_.modelValue.dateValue).isEqualTo(
				"modelValue.dateValue");
		assertThat(Model_.modelValue.dateTimeValue).isEqualTo(
				"modelValue.dateTimeValue");
		assertThat(Model_.modelValue.stringValue).isEqualTo(
				"modelValue.stringValue");
		assertThat(Model_.modelValue.bigDecimalValue).isEqualTo(
				"modelValue.bigDecimalValue");
		assertThat(Model_.modelValue.byteWrapperValue).isEqualTo(
				"modelValue.byteWrapperValue");
		assertThat(Model_.modelValue.shortWrapperValue).isEqualTo(
				"modelValue.shortWrapperValue");
		assertThat(Model_.modelValue.intWrapperValue).isEqualTo(
				"modelValue.intWrapperValue");
		assertThat(Model_.modelValue.longWrapperValue).isEqualTo(
				"modelValue.longWrapperValue");
		assertThat(Model_.modelValue.floatWrapperValue).isEqualTo(
				"modelValue.floatWrapperValue");
		assertThat(Model_.modelValue.doubleWrapperValue).isEqualTo(
				"modelValue.doubleWrapperValue");
		assertThat(Model_.modelValue.modelValue._path).isEqualTo(
				"modelValue.modelValue");
		assertThat(Model_.subDocument.size).isEqualTo("subDocument.size");
	}
}
