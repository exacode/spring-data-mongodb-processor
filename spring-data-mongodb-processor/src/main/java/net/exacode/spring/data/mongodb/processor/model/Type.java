package net.exacode.spring.data.mongodb.processor.model;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import net.exacode.spring.data.mongodb.processor.AptUtils;

public class Type {

	private final String className;

	private final String packageName;

	private final String canonicalName;

	public static Map<String, Type> types = new HashMap<String, Type>();

	public static Type create(AptUtils aptUtils, TypeMirror typeMirror) {
		TypeElement typeElement = aptUtils.toTypeElement(typeMirror);
		String binaryName = aptUtils.getElementUtils()
				.getBinaryName(typeElement).toString();
		String canonicalName = aptUtils.canonicalNameFromBinaryName(binaryName);
		return create(canonicalName);
	}

	public static Type create(String canonicalName) {
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
