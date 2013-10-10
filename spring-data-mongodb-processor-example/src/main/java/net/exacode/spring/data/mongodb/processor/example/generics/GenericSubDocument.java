package net.exacode.spring.data.mongodb.processor.example.generics;

import java.util.List;

public class GenericSubDocument<T> {
	private T value;

	private List<T> valueList;

	private T[] valueArray;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public List<T> getValueList() {
		return valueList;
	}

	public void setValueList(List<T> valueList) {
		this.valueList = valueList;
	}

	public T[] getValueArray() {
		return valueArray;
	}

	public void setValueArray(T[] valueArray) {
		this.valueArray = valueArray;
	}

}
