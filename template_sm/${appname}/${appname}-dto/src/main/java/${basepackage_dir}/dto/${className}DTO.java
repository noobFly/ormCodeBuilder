<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import ${basepackage}.PageParam;
<#if table.exportedKeys.associatedTables?? && (table.exportedKeys.associatedTables?size gt 0)>
import java.util.List;
import java.util.ArrayList;
</#if>
public class ${className}DTO extends PageParam{
	<#list table.columns as column>
	/** ${column.columnAlias} */
	private ${column.javaType?replace("java.lang.","")} ${column.columnNameLower};
	</#list>

<@generateConstructor className+"DTO"/>
<@generateJavaColumns/>
<@generateJavaOneToMany/>
<@generateJavaManyToOne/>

    @Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
		<#list table.columns as column>
			.append("${column.columnName}",get${column.columnName}())
		</#list>
			.toString();
	}
}

<#macro generateJavaColumns>
	<#list table.columns as column> 
	
	/** ${column.columnAlias} */
	public void set${column.columnName}(${column.javaType?replace("java.lang.","")} value) {
		this.${column.columnNameLower} = value;
	}
	
    /** ${column.columnAlias} */
	<#if column.javaType?replace("java.lang.","") == "java.util.Date">
	@JsonFormat(shape = JsonFormat.Shape.NUMBER, timezone = "GMT+8")
	</#if>
	<#if column.sqlType==12>
	@Length(max=${column.size},message="${column.columnAlias}长度不能大于${column.size}")
	</#if>
	public ${column.javaType?replace("java.lang.","")} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	</#list>
</#macro>

<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private List<${fkPojoClass}> ${fkPojoClassVar}List = new ArrayList<>();
    public void set${fkPojoClass}List(List<${fkPojoClass}> ${fkPojoClassVar}List){
        this.${fkPojoClassVar}List = ${fkPojoClassVar}List;
    }
    
    public List<${fkPojoClass}> get${fkPojoClass}List() {
        return ${fkPojoClassVar}List;
    }
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private ${fkPojoClass} ${fkPojoClassVar};
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>
