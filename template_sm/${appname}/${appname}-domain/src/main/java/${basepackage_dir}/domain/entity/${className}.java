<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.domain.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;
<#if table.exportedKeys.associatedTables?? && (table.exportedKeys.associatedTables?size gt 0)>
import java.util.List;
import java.util.ArrayList;
</#if>
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import ${basepackage}.domain.Initializable;

public class ${className} extends Initializable{
	//columns START
	<#list table.columns as column>
	/** ${column.columnAlias} */
	private ${column.javaType?replace("java.lang.","")} ${column.columnNameLower};
	</#list>
	//columns END

<@generateConstructor className/>
<@generateJavaColumns/>
<@generateJavaOneToMany/>
<@generateJavaManyToOne/>

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
    @Override
	public int hashCode() {
        return new HashCodeBuilder()
            <#list table.pkColumns as column>
                .append(get${column.columnName}())
            </#list>
                .toHashCode();
	}
	
    @Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ${className})) {
			return false;
		}
		if(this == obj) {
			return true;
		}
		${className} other = (${className})obj;
		return new EqualsBuilder()
			<#list table.pkColumns as column>
			.append(get${column.columnName}(),other.get${column.columnName}())
			</#list>
			.isEquals();
	}
    
    @Override
    public void init() {
        super.init();
        //后面可修改初始化默认值
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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
