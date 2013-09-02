package ${package};

<#list fields.referenceFields as field> 
import ${field.package}.${field.typeName}_.${field.typeName}Field;
</#list>
<#list fields.referenceArrayFields as field>
import ${field.package}.${field.typeName}_.${field.typeName}Array;
</#list>
<#if fields.primitiveArrayFields?size gt 0>
import org.springframework.data.mongodb.processor.meta.PrimitiveArray;
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
	public static final PrimitiveArray ${field.fieldName} = new PrimitiveArray("${field.fieldPathName}");
	</#list>

	public static class ${modelTypeName}Field {

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
		public final PrimitiveArray ${field.fieldName};
		</#list>

		public ${modelTypeName}Field(String path) {
			this._path = path;
			this._class = path + "._class";
			<#list fields.referenceFields as field>
			this.${field.fieldName} = new ${field.typeName}Field(path + ".${field.fieldPathName}");
			</#list> 
			<#list fields.referenceArrayFields as field>
			this.${field.fieldName} = new ${field.typeName}Array(path + ".${field.fieldPathName}");
			</#list>
			<#list fields.primitiveFields as field>
			this.${field.fieldName} = path + ".${field.fieldPathName}";
			</#list>
			<#list fields.primitiveArrayFields as field>
			this.${field.fieldName} = new PrimitiveArray(path + ".${field.fieldPathName}");
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