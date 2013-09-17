<#if (!metaModel.type.defaultPackage)>package ${metaModel.type.packageName};</#if>

<#list metaModel.referenceFields as field> 
<#if ("${field.type.canonicalName}" != "${metaModel.type.canonicalName}" 
	&& metaModel.addImport(field.type.packageName, field.type.className))>
import ${field.type.packageName}.${field.type.className};
</#if>
</#list>
<#list metaModel.referenceArrayFields as field>
<#if ("${field.type.canonicalName}" != "${metaModel.type.canonicalName}" 
	&& metaModel.addImport(field.type.packageName, field.type.className))>
import ${field.type.packageName}.${field.type.className};
</#if>
</#list>
<#if metaModel.primitiveArrayFields?size gt 0>
import org.springframework.data.mongodb.processor.shared.ArrayField;
</#if>
import org.springframework.data.mongodb.processor.shared.DocumentProcessorConfiguration;

public class ${metaModel.type.className} {
 
	public final String _class = "_class";
	
	<#list metaModel.referenceFields as field>
	<#assign fieldTypeName=fieldType(field.type)/>
	public static final ${fieldTypeName} ${field.fieldName} = new ${fieldTypeName}("${field.fieldPathName}");
	</#list>
	<#list metaModel.referenceArrayFields as field>
	<#assign arrayTypeName=arrayType(field.type)/>
	public static final ${arrayTypeName} ${field.fieldName} = new ${arrayTypeName}("${field.fieldPathName}");
	</#list>
	<#list metaModel.primitiveFields as field>
	public static final String ${field.fieldName} = "${field.fieldPathName}";
	</#list>
	<#list metaModel.primitiveArrayFields as field>
	public static final ArrayField ${field.fieldName} = new ArrayField("${field.fieldPathName}");
	</#list>

	public static class ${metaModel.type.className}Field {
	
		public final String _path;
		
		public final String _class;
		
		<#list metaModel.referenceFields as field>
		public final ${fieldType(field.type)} ${field.fieldName};
		</#list>
		<#list metaModel.referenceArrayFields as field>
		public final ${arrayType(field.type)} ${field.fieldName};
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
			if(depth < DocumentProcessorConfiguration.MAX_DEPTH) {
				<#list metaModel.referenceFields as field>
				this.${field.fieldName} = new ${fieldType(field.type)}(path + ".${field.fieldPathName}", depth + 1);
				</#list> 
				<#list metaModel.referenceArrayFields as field>
				this.${field.fieldName} = new ${arrayType(field.type)}(path + ".${field.fieldPathName}", depth + 1);
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
			super(path, 0);
		}
 
		public ${metaModel.type.className}Array(String path, int depth) {
			super(path, depth);
		}

		public ${metaModel.type.className}Array index(int idx) {
			return new ${metaModel.type.className}Array(_path + "." + idx);
		}
	}
	
}

<#function fieldType type>
	<#return metaModel.getTypeReference(type.packageName, type.className) + '.' + type.className + 'Field'>
</#function>

<#function arrayType type>
	<#return metaModel.getTypeReference(type.packageName, type.className) + '.' + type.className + 'Array'>
</#function>