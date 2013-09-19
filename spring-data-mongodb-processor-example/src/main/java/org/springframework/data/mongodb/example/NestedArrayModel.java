package org.springframework.data.mongodb.example;

import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class NestedArrayModel {

	private List<List<String>> listOfListsOfStrings;

	private List<Set<String>> listOfSetsOfStrings;

	private List<String[]> listOfArraysOfStrings;

	private List<String>[] arrayOfListsOfStrings;

	private List<List<SubDocument>> listOfListsOfSubDocuments;

	private List<Set<SubDocument>> listOfSetsOfSubDocuments;

	private List<SubDocument[]> listOfArraysOfSubDocuments;

	private List<SubDocument>[] arrayOfListsOfSubDocuments;

	public List<List<String>> getListOfListsOfStrings() {
		return listOfListsOfStrings;
	}

	public void setListOfListsOfStrings(List<List<String>> listOfListsOfStrings) {
		this.listOfListsOfStrings = listOfListsOfStrings;
	}

	public List<Set<String>> getListOfSetsOfStrings() {
		return listOfSetsOfStrings;
	}

	public void setListOfSetsOfStrings(List<Set<String>> listOfSetsOfStrings) {
		this.listOfSetsOfStrings = listOfSetsOfStrings;
	}

	public List<String[]> getListOfArraysOfStrings() {
		return listOfArraysOfStrings;
	}

	public void setListOfArraysOfStrings(List<String[]> listOfArraysOfStrings) {
		this.listOfArraysOfStrings = listOfArraysOfStrings;
	}

	public List<String>[] getArrayOfListsOfStrings() {
		return arrayOfListsOfStrings;
	}

	public void setArrayOfListsOfStrings(List<String>[] arrayOfListsOfStrings) {
		this.arrayOfListsOfStrings = arrayOfListsOfStrings;
	}

	public List<List<SubDocument>> getListOfListsOfSubDocuments() {
		return listOfListsOfSubDocuments;
	}

	public void setListOfListsOfSubDocuments(
			List<List<SubDocument>> listOfListsOfSubDocuments) {
		this.listOfListsOfSubDocuments = listOfListsOfSubDocuments;
	}

	public List<Set<SubDocument>> getListOfSetsOfSubDocuments() {
		return listOfSetsOfSubDocuments;
	}

	public void setListOfSetsOfSubDocuments(
			List<Set<SubDocument>> listOfSetsOfSubDocuments) {
		this.listOfSetsOfSubDocuments = listOfSetsOfSubDocuments;
	}

	public List<SubDocument[]> getListOfArraysOfSubDocuments() {
		return listOfArraysOfSubDocuments;
	}

	public void setListOfArraysOfSubDocuments(
			List<SubDocument[]> listOfArraysOfSubDocuments) {
		this.listOfArraysOfSubDocuments = listOfArraysOfSubDocuments;
	}

	public List<SubDocument>[] getArrayOfListsOfSubDocuments() {
		return arrayOfListsOfSubDocuments;
	}

	public void setArrayOfListsOfSubDocuments(
			List<SubDocument>[] arrayOfListsOfSubDocuments) {
		this.arrayOfListsOfSubDocuments = arrayOfListsOfSubDocuments;
	}

}
