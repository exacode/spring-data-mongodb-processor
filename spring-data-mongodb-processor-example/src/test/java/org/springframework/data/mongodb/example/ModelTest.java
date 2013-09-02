package org.springframework.data.mongodb.example;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class ModelTest {

	@Test
	public void sholudHaveSingleFields() {
		Assertions.assertThat(Model_.byteValue).isEqualTo("byteValue");
		Assertions.assertThat(Model_.shortValue).isEqualTo("shortValue");
		Assertions.assertThat(Model_.intValue).isEqualTo("intValue");
		Assertions.assertThat(Model_.longValue).isEqualTo("longValue");
		Assertions.assertThat(Model_.floatValue).isEqualTo("floatValue");
		Assertions.assertThat(Model_.doubleValue).isEqualTo("doubleValue");
		Assertions.assertThat(Model_.dateValue).isEqualTo("dateValue");
		Assertions.assertThat(Model_.dateTimeValue).isEqualTo("dateTimeValue");
		Assertions.assertThat(Model_.stringValue).isEqualTo("stringValue");
		Assertions.assertThat(Model_.bigDecimalValue).isEqualTo(
				"bigDecimalValue");
		Assertions.assertThat(Model_.byteWrapperValue).isEqualTo(
				"byteWrapperValue");
		Assertions.assertThat(Model_.shortWrapperValue).isEqualTo(
				"shortWrapperValue");
		Assertions.assertThat(Model_.intWrapperValue).isEqualTo(
				"intWrapperValue");
		Assertions.assertThat(Model_.longWrapperValue).isEqualTo(
				"longWrapperValue");
		Assertions.assertThat(Model_.floatWrapperValue).isEqualTo(
				"floatWrapperValue");
		Assertions.assertThat(Model_.doubleWrapperValue).isEqualTo(
				"doubleWrapperValue");
		Assertions.assertThat(Model_.modelValue._path).isEqualTo("modelValue");
	}

	@Test
	public void sholudHaveArrayFields() {
		Assertions.assertThat(Model_.byteArray._path).isEqualTo("byteArray");
		Assertions.assertThat(Model_.shortArray._path).isEqualTo("shortArray");
		Assertions.assertThat(Model_.intArray._path).isEqualTo("intArray");
		Assertions.assertThat(Model_.longArray._path).isEqualTo("longArray");
		Assertions.assertThat(Model_.floatArray._path).isEqualTo("floatArray");
		Assertions.assertThat(Model_.doubleArray._path)
				.isEqualTo("doubleArray");
		Assertions.assertThat(Model_.dateArray._path).isEqualTo("dateArray");
		Assertions.assertThat(Model_.dateTimeArray._path).isEqualTo(
				"dateTimeArray");
		Assertions.assertThat(Model_.stringArray._path)
				.isEqualTo("stringArray");
		Assertions.assertThat(Model_.bigDecimalArray._path).isEqualTo(
				"bigDecimalArray");
		Assertions.assertThat(Model_.byteWrapperArray._path).isEqualTo(
				"byteWrapperArray");
		Assertions.assertThat(Model_.shortWrapperArray._path).isEqualTo(
				"shortWrapperArray");
		Assertions.assertThat(Model_.intWrapperArray._path).isEqualTo(
				"intWrapperArray");
		Assertions.assertThat(Model_.longWrapperArray._path).isEqualTo(
				"longWrapperArray");
		Assertions.assertThat(Model_.floatWrapperArray._path).isEqualTo(
				"floatWrapperArray");
		Assertions.assertThat(Model_.doubleWrapperArray._path).isEqualTo(
				"doubleWrapperArray");
		Assertions.assertThat(Model_.modelArray._path).isEqualTo("modelArray");
	}

	@Test
	public void sholudHaveIndexableArrayFields() {
		Assertions.assertThat(Model_.byteArray.index(5)).isEqualTo(
				"byteArray.5");
		Assertions.assertThat(Model_.shortArray.index(5)).isEqualTo(
				"shortArray.5");
		Assertions.assertThat(Model_.intArray.index(5)).isEqualTo("intArray.5");
		Assertions.assertThat(Model_.longArray.index(5)).isEqualTo(
				"longArray.5");
		Assertions.assertThat(Model_.floatArray.index(5)).isEqualTo(
				"floatArray.5");
		Assertions.assertThat(Model_.doubleArray.index(5)).isEqualTo(
				"doubleArray.5");
		Assertions.assertThat(Model_.dateArray.index(5)).isEqualTo(
				"dateArray.5");
		Assertions.assertThat(Model_.dateTimeArray.index(5)).isEqualTo(
				"dateTimeArray.5");
		Assertions.assertThat(Model_.stringArray.index(5)).isEqualTo(
				"stringArray.5");
		Assertions.assertThat(Model_.bigDecimalArray.index(5)).isEqualTo(
				"bigDecimalArray.5");
		Assertions.assertThat(Model_.byteWrapperArray.index(5)).isEqualTo(
				"byteWrapperArray.5");
		Assertions.assertThat(Model_.shortWrapperArray.index(5)).isEqualTo(
				"shortWrapperArray.5");
		Assertions.assertThat(Model_.intWrapperArray.index(5)).isEqualTo(
				"intWrapperArray.5");
		Assertions.assertThat(Model_.longWrapperArray.index(5)).isEqualTo(
				"longWrapperArray.5");
		Assertions.assertThat(Model_.floatWrapperArray.index(5)).isEqualTo(
				"floatWrapperArray.5");
		Assertions.assertThat(Model_.doubleWrapperArray.index(5)).isEqualTo(
				"doubleWrapperArray.5");
		Assertions.assertThat(Model_.modelArray.index(5)).isEqualTo(
				"modelArray.5");
	}

	@Test
	public void sholudHaveIndexableListFields() {
		Assertions.assertThat(Model_.byteList.index(5)).isEqualTo("byteList.5");
		Assertions.assertThat(Model_.shortList.index(5)).isEqualTo(
				"shortList.5");
		Assertions.assertThat(Model_.intList.index(5)).isEqualTo("intList.5");
		Assertions.assertThat(Model_.longList.index(5)).isEqualTo("longList.5");
		Assertions.assertThat(Model_.floatList.index(5)).isEqualTo(
				"floatList.5");
		Assertions.assertThat(Model_.doubleList.index(5)).isEqualTo(
				"doubleList.5");
		Assertions.assertThat(Model_.dateList.index(5)).isEqualTo("dateList.5");
		Assertions.assertThat(Model_.dateTimeList.index(5)).isEqualTo(
				"dateTimeList.5");
		Assertions.assertThat(Model_.stringList.index(5)).isEqualTo(
				"stringList.5");
		Assertions.assertThat(Model_.bigDecimalList.index(5)).isEqualTo(
				"bigDecimalList.5");
		Assertions.assertThat(Model_.modelList.index(5)).isEqualTo(
				"modelList.5");
	}

	@Test
	public void sholudHaveListFields() {
		Assertions.assertThat(Model_.byteList._path).isEqualTo("byteList");
		Assertions.assertThat(Model_.shortList._path).isEqualTo("shortList");
		Assertions.assertThat(Model_.intList._path).isEqualTo("intList");
		Assertions.assertThat(Model_.longList._path).isEqualTo("longList");
		Assertions.assertThat(Model_.floatList._path).isEqualTo("floatList");
		Assertions.assertThat(Model_.doubleList._path).isEqualTo("doubleList");
		Assertions.assertThat(Model_.dateList._path).isEqualTo("dateList");
		Assertions.assertThat(Model_.dateTimeList._path).isEqualTo(
				"dateTimeList");
		Assertions.assertThat(Model_.stringList._path).isEqualTo("stringList");
		Assertions.assertThat(Model_.bigDecimalList._path).isEqualTo(
				"bigDecimalList");
		Assertions.assertThat(Model_.modelList._path).isEqualTo("modelList");
	}

	@Test
	public void sholudHaveAccessToNestedModelFields() {
		Assertions.assertThat(Model_.modelValue.byteValue).isEqualTo(
				"modelValue.byteValue");
		Assertions.assertThat(Model_.modelValue.shortValue).isEqualTo(
				"modelValue.shortValue");
		Assertions.assertThat(Model_.modelValue.intValue).isEqualTo(
				"modelValue.intValue");
		Assertions.assertThat(Model_.modelValue.longValue).isEqualTo(
				"modelValue.longValue");
		Assertions.assertThat(Model_.modelValue.floatValue).isEqualTo(
				"modelValue.floatValue");
		Assertions.assertThat(Model_.modelValue.doubleValue).isEqualTo(
				"modelValue.doubleValue");
		Assertions.assertThat(Model_.modelValue.dateValue).isEqualTo(
				"modelValue.dateValue");
		Assertions.assertThat(Model_.modelValue.dateTimeValue).isEqualTo(
				"modelValue.dateTimeValue");
		Assertions.assertThat(Model_.modelValue.stringValue).isEqualTo(
				"modelValue.stringValue");
		Assertions.assertThat(Model_.modelValue.bigDecimalValue).isEqualTo(
				"modelValue.bigDecimalValue");
		Assertions.assertThat(Model_.modelValue.byteWrapperValue).isEqualTo(
				"modelValue.byteWrapperValue");
		Assertions.assertThat(Model_.modelValue.shortWrapperValue).isEqualTo(
				"modelValue.shortWrapperValue");
		Assertions.assertThat(Model_.modelValue.intWrapperValue).isEqualTo(
				"modelValue.intWrapperValue");
		Assertions.assertThat(Model_.modelValue.longWrapperValue).isEqualTo(
				"modelValue.longWrapperValue");
		Assertions.assertThat(Model_.modelValue.floatWrapperValue).isEqualTo(
				"modelValue.floatWrapperValue");
		Assertions.assertThat(Model_.modelValue.doubleWrapperValue).isEqualTo(
				"modelValue.doubleWrapperValue");
		Assertions.assertThat(Model_.modelValue.modelValue._path).isEqualTo(
				"modelValue.modelValue");
	}
}
