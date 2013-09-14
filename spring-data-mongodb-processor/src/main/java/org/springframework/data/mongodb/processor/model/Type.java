package org.springframework.data.mongodb.processor.model;

import java.util.HashMap;
import java.util.Map;

public class Type {

	private final String className;

	private final String packageName;

	private final String canonicalName;

	public static Map<String, Type> types = new HashMap<String, Type>();

	public static Type createFromCanonicalName(String canonicalName) {
		Type type = types.get(canonicalName);
		if (type == null) {
			type = new Type(canonicalName);
			types.put(canonicalName, type);
		}
		return type;
	}

	private Type(String canonicalName) {
		int lastDot = canonicalName.lastIndexOf('.');
		this.className = canonicalName.substring(lastDot + 1);
		String pkg = null;
		if (lastDot > -1) {
			pkg = canonicalName.substring(0, lastDot);
		}
		this.packageName = pkg;
		this.canonicalName = canonicalName;
	}

	public String getClassName() {
		return className;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getCanonicalName() {
		return canonicalName;
	}

	public boolean isDefaultPackage() {
		return packageName == null;
	}

	@Override
	public String toString() {
		return "Type [canonicalName=" + canonicalName + "]";
	}

}
