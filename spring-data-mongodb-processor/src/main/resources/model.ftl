package ${package};

<#list fields.referenceFields as field> 
<#if ("${field.package}.${field.typeName}" != "${package}.${modelTypeName}")>
import ${field.package}.${field.typeName}_.${field.typeName}Field;
</#if>
</#list>
<#list fields.referenceArrayFields as field>
<#if ("${field.package}.${field.typeName}" != "${package}.${modelTypeName}")>
import ${field.package}.${field.typeName}_.${field.typeName}Array;
</#if>
</#list>
<#if fields.primitiveArrayFields?size gt 0>
import org.springframework.data.mongodb.processor.meta.ArrayField;
</#if> 

public class ${modelTypeName}_ {
 
	public final String _class = "_class";
	
	<#list fields.referenceFields as field>
	public static final ${field.typeName}Field ${field.fieldName} = new ${field.typeName}Field("${field.fieldPathName}");
	</#list>
	<#list fields.referenceArrayFields as field>
	public static final ${field.typeName}Array ${field.fieldName} = new ${field.typeName}Array("${field.fieldPathName}");
	</#list>
	<#list fields.primitiveFields as field>
	public static final String ${field.fieldName} = "${field.fieldPathName}";
	</#list>
	<#list fields.primitiveArrayFields as field>
	public static final ArrayField ${field.fieldName} = new ArrayField("${field.fieldPathName}");
	</#list>

	public static class ${modelTypeName}Field {
	
		private static final int MAX_DEPTH = 10;

		public final String _path;
		
		public final String _class;
		
		<#list fields.referenceFields as field>
		public final ${field.typeName}Field ${field.fieldName};
		</#list>
		<#list fields.referenceArrayFields as field>
		public final ${field.typeName}Array ${field.fieldName};
		</#list>
		<#list fields.primitiveFields as field>
		public final String ${field.fieldName};
		</#list>
		<#list fields.primitiveArrayFields as field>
		public final ArrayField ${field.fieldName};
		</#list> 
		
		public ${modelTypeName}Field(String path) {
			this(path, 0);
		}

		public ${modelTypeName}Field(String path, int depth) {
			this._path = path;
			this._class = path + "._class";
			if(depth < MAX_DEPTH) {
				<#list fields.referenceFields as field>
				this.${field.fieldName} = new ${field.typeName}Field(path + ".${field.fieldPathName}", depth + 1);
				</#list> 
				<#list fields.referenceArrayFields as field>
				this.${field.fieldName} = new ${field.typeName}Array(path + ".${field.fieldPathName}", depth + 1);
				</#list>
			} else {
				<#list fields.referenceFields as field>
				this.${field.fieldName} = null;
				</#list> 
				<#list fields.referenceArrayFields as field>
				this.${field.fieldName} = null;
				</#list>		
			}
			<#list fields.primitiveFields as field>
			this.${field.fieldName} = path + ".${field.fieldPathName}";
			</#list>
			<#list fields.primitiveArrayFields as field>
			this.${field.fieldName} = new ArrayField(path + ".${field.fieldPathName}");
			</#list>
		} 
		 
	}
	 
	public static class ${modelTypeName}Array extends ${modelTypeName}Field {
 
		public ${modelTypeName}Array(String path) {
			super(path);
		}

		public ${modelTypeName}Array index(int idx) {
			return new ${modelTypeName}Array(_path + "." + idx);
		}
	}
	
}