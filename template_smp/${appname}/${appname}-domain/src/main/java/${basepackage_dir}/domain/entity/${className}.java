<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("")
public class ${className} {

	//columns START
	<#list table.columns as column>
	/** ${column.columnAlias} */
	<#if column.javaType?replace("java.lang.","") == "java.util.Date">
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, timezone = "GMT+8")
    </#if>
    <#if column.sqlType==12>
    @Length(max=${column.size},message="${column.columnAlias}长度不能大于${column.size}")
    </#if>
    <#if column.pk>
    @TableId<#if column.defaultValue?? && column.defaultValue=="AUTO_INCREMENT">(type = IdType.AUTO)<#else>(type = IdType.ID_WORKER)</#if>
    </#if>
    <#if column.columnNameFirstLower == "updateTime" || column.columnNameFirstLower == "createTime">
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    </#if>
   @ApiModelProperty(value = "${column.columnAlias}")
   private ${column.javaType?replace("java.lang.","")} ${column.columnNameLower};
	</#list>
	//columns END

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
    
    @Override
    public void init() {
        super.init();
        //后面可修改初始化默认值
    }
}
