package org.springframework.data.mongodb.processor.model;

import java.util.HashMap;
import java.util.Map;

public class ImportManager {
	private final Map<String, String> importedTypes = new HashMap<String, String>();

	private final Type type;

	public ImportManager(Type type) {
		this.type = type;
	}

	public boolean addImport(String pkg, String className) {
		String canonicalName = toCanonicalName(pkg, className);
		if (importedTypes.containsKey(className)) {
			return false;
		}
		importedTypes.put(className, canonicalName);
		return true;
	}

	public String getTypeReference(String pkg, String className) {
		String canonicalName = toCanonicalName(pkg, className);
		if (canonicalName.startsWith(type.getCanonicalName())) {
			return className;
		}
		String importedCanonicalName = importedTypes.get(className);
		if (importedCanonicalName != null
				&& importedCanonicalName.equals(canonicalName)) {
			return className;
		}
		return canonicalName;
	}

	private String toCanonicalName(String pkg, String className) {
		return (pkg == null) ? className : pkg + "." + className;
	}
}
