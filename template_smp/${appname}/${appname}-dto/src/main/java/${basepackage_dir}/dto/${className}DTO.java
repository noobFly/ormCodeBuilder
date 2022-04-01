<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import ${basepackage}.PageParam;
<#if table.exportedKeys.associatedTables?? && (table.exportedKeys.associatedTables?size gt 0)>
import java.util.List;
import java.util.ArrayList;
</#if>

@Data
@EqualsAndHashCode(callSuper = false)
public class ${className}DTO extends PageParam{
	<#list table.columns as column>
	/** ${column.columnAlias} */
	<#if column.javaType?replace("java.lang.","") == "java.util.Date">
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, timezone = "GMT+8")
    </#if>
    <#if column.sqlType==12>
    @Length(max=${column.size},message="${column.columnAlias}长度不能大于${column.size}")
    </#if>
	private ${column.javaType?replace("java.lang.","")} ${column.columnNameLower};
	<#if column.isDateTimeColumn && column.columnNameFirstLower != "updateTime">
	/** ${column.columnAlias}--开始*/
	private ${column.javaType?replace("java.lang.","")} ${column.columnNameLower}Begin;
	/** ${column.columnAlias}--结束 */
	private ${column.javaType?replace("java.lang.","")} ${column.columnNameLower}End;
    </#if>
    </#list>

    @Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
		<#list table.columns as column>
			.append("${column.columnName}",get${column.columnName}())
		</#list>
			.toString();
	}
}

