<#if (!metaModel.type.defaultPackage)>package ${metaModel.type.packageName};</#if>

<#list metaModel.referenceFields as field> 
<#if ("${field.type.canonicalName}" != "${metaModel.type.canonicalName}")>
import ${field.type.canonicalName}_.${field.type.className}Field;
</#if>
</#list>
<#list metaModel.referenceArrayFields as field>
<#if ("${field.type.canonicalName}" != "${metaModel.type.canonicalName}")>
import ${field.type.canonicalName}_.${field.type.className}Array;
</#if>
</#list>
<#if metaModel.primitiveArrayFields?size gt 0>
import org.springframework.data.mongodb.processor.meta.ArrayField;
</#if>

public class ${metaModel.type.className} {
 
	public final String _class = "_class";
	
	<#list metaModel.referenceFields as field>
	public static final ${field.type.className}Field ${field.fieldName} = new ${field.type.className}Field("${field.fieldPathName}");
	</#list>
	<#list metaModel.referenceArrayFields as field>
	public static final ${field.type.className}Array ${field.fieldName} = new ${field.type.className}Array("${field.fieldPathName}");
	</#list>
	<#list metaModel.primitiveFields as field>
	public static final String ${field.fieldName} = "${field.fieldPathName}";
	</#list>
	<#list metaModel.primitiveArrayFields as field>
	public static final ArrayField ${field.fieldName} = new ArrayField("${field.fieldPathName}");
	</#list>

	public static class ${metaModel.type.className}Field {
	
		private static final int MAX_DEPTH = 10;

		public final String _path;
		
		public final String _class;
		
		<#list metaModel.referenceFields as field>
		public final ${field.type.className}Field ${field.fieldName};
		</#list>
		<#list metaModel.referenceArrayFields as field>
		public final ${field.type.className}Array ${field.fieldName};
		</#list>
		<#list metaModel.primitiveFields as field>
		public final String ${field.fieldName};
		</#list>
		<#list metaModel.primitiveArrayFields as field>
		public final ArrayField ${field.fieldName};
		</#list> 
		
		public ${metaModel.type.className}Field(String path) {
			this(path, 0);
		}

		public ${metaModel.type.className}Field(String path, int depth) {
			this._path = path;
			this._class = path + "._class";
			if(depth < MAX_DEPTH) {
				<#list metaModel.referenceFields as field>
				this.${field.fieldName} = new ${field.type.className}Field(path + ".${field.fieldPathName}", depth + 1);
				</#list> 
				<#list metaModel.referenceArrayFields as field>
				this.${field.fieldName} = new ${field.type.className}Array(path + ".${field.fieldPathName}", depth + 1);
				</#list>
			} else {
				<#list metaModel.referenceFields as field>
				this.${field.fieldName} = null;
				</#list> 
				<#list metaModel.referenceArrayFields as field>
				this.${field.fieldName} = null;
				</#list>		
			}
			<#list metaModel.primitiveFields as field>
			this.${field.fieldName} = path + ".${field.fieldPathName}";
			</#list>
			<#list metaModel.primitiveArrayFields as field>
			this.${field.fieldName} = new ArrayField(path + ".${field.fieldPathName}");
			</#list>
		} 
		 
	}
	 
	public static class ${metaModel.type.className}Array extends ${metaModel.type.className}Field {
 
		public ${metaModel.type.className}Array(String path) {
			super(path);
		}

		public ${metaModel.type.className}Array index(int idx) {
			return new ${metaModel.type.className}Array(_path + "." + idx);
		}
	}
	
}