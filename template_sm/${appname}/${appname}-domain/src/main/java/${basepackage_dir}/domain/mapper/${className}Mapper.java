<#assign className = table.className>
<#assign flClassName = table.flClassName>
<#assign indexs = table.indexs>
package ${basepackage}.domain.mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ${basepackage}.domain.entity.${className};

@Mapper
public interface ${className}Mapper {
	<#list indexs as index>
	<#if index.unique>
	/**
     * 查询
     <#list index.columns as column>
     * @param ${column.columnNameFirstLower} ${column.remarks}
     </#list>
     * @return
     */
	public ${className} getBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>@Param("${column.columnNameFirstLower}") ${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>);
	/**
     * 查询返回Optional<T>对象，可进行函数式操作
     <#list index.columns as column>
     * @param ${column.columnNameFirstLower} ${column.remarks}
     </#list>
     * @return
     */
    public Optional<${className}> findBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>@Param("${column.columnNameFirstLower}") ${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>);
	/**
     * 查询并加上更新锁（排他锁），必须在事务中使用
     <#list index.columns as column>
     * @param ${column.columnNameFirstLower} ${column.remarks}
     </#list>
     * @return
     */
    public Optional<${className}> findBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>WithLock(<#list index.columns as column><#if column_index gt 0>, </#if>@Param("${column.columnNameFirstLower}") ${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>);
	/**
     * 删除
     <#list index.columns as column>
     * @param ${column.columnNameFirstLower} ${column.remarks}
     </#list>
     * @return
     */
	public void delBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>@Param("${column.columnNameFirstLower}") ${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>);
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
	 * 新增
	 * @param ${flClassName}
	 */
	public void add(${className} ${flClassName});
	/**
     * 新增，只插入非null数据
     * @param ${flClassName}
     */
    public void patchAdd(${className} ${flClassName});
	/**
	 * 更新
	 * @param ${flClassName}
	 */
	public int update(${className} ${flClassName});
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
    public List<${className}> getAll(${className} ${flClassName});
    
    /**
     * 按条件查询
     * @param params 动态参数
     * @return
     */
    public List<${className}> getAll(Map<String,Object> params);
    /**
     * 按条件统计总行数
     * @param ${flClassName}
     * @return
     */
    public int count(${className} ${flClassName});
    
    /**
     * 按条件统计总行数
     * @param params 动态参数
     * @return
     */
    public int count(Map<String,Object> params);
}