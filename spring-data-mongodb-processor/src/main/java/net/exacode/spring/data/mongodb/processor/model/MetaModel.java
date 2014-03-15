package net.exacode.spring.data.mongodb.processor.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains all fields of analyzed meta model.
 * <p/>
 * Separates primitive fields from subdocuments and arrays.
 * 
 * @author mendlik
 * 
 */
public class MetaModel {

	private final Type type;

	private final String qualifiedDocumentClassName;

	private final String prefix;

	private final List<Map<String, MetaModelField>> fieldGroups = new ArrayList<Map<String, MetaModelField>>();
	private final Map<String, MetaModelField> referenceFields = new LinkedHashMap<String, MetaModelField>();
	private final Map<String, MetaModelField> referenceArrayFields = new LinkedHashMap<String, MetaModelField>();
	private final Map<String, MetaModelField> primitiveFields = new LinkedHashMap<String, MetaModelField>();
	private final Map<String, MetaModelField> primitiveArrayFields = new LinkedHashMap<String, MetaModelField>();

	private final ImportManager importManager;

	public MetaModel(String qualifiedMetaModelClassName,
			String qualifiedDocumentClassName, String prefix) {
		this.qualifiedDocumentClassName = qualifiedDocumentClassName;
		this.type = Type.create(qualifiedMetaModelClassName);
		this.importManager = new ImportManager(type);
		this.prefix = (prefix != null) ? prefix + "." : "";

		fieldGroups.add(referenceFields);
		fieldGroups.add(referenceArrayFields);
		fieldGroups.add(primitiveFields);
		fieldGroups.add(primitiveArrayFields);
	}

	public String getPrefix() {
		return prefix;
	}

	public String getQualifiedDocumentClassName() {
		return qualifiedDocumentClassName;
	}

	public Type getType() {
		return type;
	}

	public void addReferenceField(MetaModelField field) {
		addField(field, referenceFields);
	}

	public void addReferenceArrayField(MetaModelField field) {
		addField(field, referenceArrayFields);
	}

	public void addPrimitiveField(MetaModelField field) {
		addField(field, primitiveFields);
	}

	public void addPrimitiveArrayField(MetaModelField field) {
		addField(field, primitiveArrayFields);
	}

	public List<MetaModelField> getReferenceFields() {
		return new ArrayList<MetaModelField>(referenceFields.values());
	}

	public List<MetaModelField> getReferenceArrayFields() {
		return new ArrayList<MetaModelField>(referenceArrayFields.values());
	}

	public List<MetaModelField> getPrimitiveFields() {
		return new ArrayList<MetaModelField>(primitiveFields.values());
	}

	public List<MetaModelField> getPrimitiveArrayFields() {
		return new ArrayList<MetaModelField>(primitiveArrayFields.values());
	}

	public boolean addImport(String pkg, String className) {
		return importManager.addImport(pkg, className);
	}

	public String getTypeReference(String pkg, String className) {
		return importManager.getTypeReference(pkg, className);
	}

	private void addField(MetaModelField field,
			Map<String, MetaModelField> targetFieldGroup) {
		String fieldName = field.getFieldName();
		for (Map<String, MetaModelField> fieldGroup : fieldGroups) {
			fieldGroup.remove(fieldName);
		}
		targetFieldGroup.put(fieldName, field);
	}
}
