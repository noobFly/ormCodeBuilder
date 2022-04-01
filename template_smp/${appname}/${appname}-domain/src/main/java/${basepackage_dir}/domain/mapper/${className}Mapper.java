<#assign className = table.className>
<#assign flClassName = table.flClassName>
<#assign indexs = table.indexs>
package ${basepackage}.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ${basepackage}.domain.entity.${className};
import ${basepackage}.dto.${className}DTO;

@Mapper
public interface ${className}Mapper extends BaseMapper<${className}> {
	
    <#list indexs as index>
	<#if index.unique>
	<#if !index.columns[0].pk>
	/**
     * 查询
     <#list index.columns as column>
     * @param ${column.columnNameFirstLower} ${column.remarks}
     </#list>
     * @return
     */
	public ${className} getBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>@Param("${column.columnNameFirstLower}") ${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>);
	</#if>
	
	/**
     * 查询返回Optional<T>对象，可进行函数式操作
     <#list index.columns as column>
     * @param ${column.columnNameFirstLower} ${column.remarks}
     </#list>
     * @return
     */
    public Optional<${className}> findBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>@Param("${column.columnNameFirstLower}") ${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>);
    
    <#if !index.columns[0].pk>
    /**
     * 根据主键删除
     <#list index.columns as column>
     * @param ${column.columnNameFirstLower} ${column.remarks}
     </#list>
     * @return
     */
    public int delBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>@Param("${column.columnNameFirstLower}") ${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>);
    </#if>
    <#else>
	
    /**
     * 查询
     <#list index.columns as column>
     * @param ${column.columnNameFirstLower} ${column.remarks}
     </#list>
     * @return
     */
	public List<${className}> getBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>@Param("${column.columnNameFirstLower}") ${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>);
	</#if>
	</#list>
	
	/**
     * 更新，只更新非null数据
     * @param ${flClassName}
     */
    public int patchUpdate(${className} ${flClassName});
    
    /**
     * 按条件查询
     * @param ${flClassName}
     * @return
     */
    public List<${className}> getAll(${className}DTO param);
}