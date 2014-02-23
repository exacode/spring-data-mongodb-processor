<#if (!metaModel.type.defaultPackage)>package ${metaModel.type.packageName};</#if>

<#if metaModel.primitiveArrayFields?size gt 0>
<#assign imported=metaModel.addImport('org.springframework.data.mongodb.processor.shared', 'ArrayField')/>
import net.exacode.spring.data.mongodb.processor.shared.ArrayField;
</#if>
<#if (metaModel.referenceFields?has_content || metaModel.referenceArrayFields?has_content)>
<#assign imported=metaModel.addImport('org.springframework.data.mongodb.processor.shared', 'DocumentProcessorConfiguration')/>
import net.exacode.spring.data.mongodb.processor.shared.DocumentProcessorConfiguration;
</#if>
<#list metaModel.referenceFields as field> 
<#if (field.type.packageName?? && "${field.type.canonicalName}" != "${metaModel.type.canonicalName}" 
	&& metaModel.addImport(field.type.canonicalName, field.type.className + 'Field'))>
import ${field.type.canonicalName}.${field.type.className}Field;
</#if>
</#list>
<#list metaModel.referenceArrayFields as field>
<#if (field.type.packageName?? && "${field.type.canonicalName}" != "${metaModel.type.canonicalName}" 
	&& metaModel.addImport(field.type.canonicalName, field.type.className + 'Array'))>
import ${field.type.canonicalName}.${field.type.className}Array;
</#if>
</#list>
<#assign author="spring-data-mongodb-processor" />

/**
 * Spring Data MongoDB meta model of {@link ${metaModel.qualifiedDocumentClassName}}.
 * 
 * @author ${author}
 */
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

	/**
	 * Meta model of {@link ${metaModel.qualifiedDocumentClassName}}.
	 * Represents an instance of subdocument.
	 * 
	 * @see ${metaModel.type.canonicalName}
	 * @author ${author}
	 */
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
			<#if (metaModel.referenceFields?has_content || metaModel.referenceArrayFields?has_content)>
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
			</#if>	
			<#list metaModel.primitiveFields as field>
			this.${field.fieldName} = path + ".${field.fieldPathName}";
			</#list>
			<#list metaModel.primitiveArrayFields as field>
			this.${field.fieldName} = new ArrayField(path + ".${field.fieldPathName}");
			</#list>
		} 
		
		@Override
		public String toString() {
			return _path;
		}
		 
	}
	
	/**
	 * Meta model of {@link ${metaModel.qualifiedDocumentClassName}}.
	 * Represents an instance of mongodb array of subdocuments.
	 * 
	 * @see ${metaModel.type.canonicalName}
	 * @author ${author}
	 */
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
		
		@Override
		public String toString() {
			return _path;
		}
	}
	
}

<#function fieldType type>
	<#return metaModel.getTypeReference(type.canonicalName, type.className + 'Field')>
</#function>

<#function arrayType type>
	<#return metaModel.getTypeReference(type.canonicalName, type.className + 'Array')>
</#function>