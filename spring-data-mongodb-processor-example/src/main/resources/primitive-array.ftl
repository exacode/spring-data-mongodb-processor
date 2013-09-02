package org.springframework.data.mongodb.processor.meta;

public class PrimitiveArray {

	public final String _path;

	public PrimitiveArray(String path) {
		this._path = path;
	}

	public String index(int idx) {
		return _path + idx;
	}

}