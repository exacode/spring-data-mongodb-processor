package org.springframework.data.mongodb.processor.shared;

public class ArrayField {

	public final String _path;

	public ArrayField(String path) {
		this._path = path;
	}

	public String index(int idx) {
		return _path + "." + idx;
	}

}