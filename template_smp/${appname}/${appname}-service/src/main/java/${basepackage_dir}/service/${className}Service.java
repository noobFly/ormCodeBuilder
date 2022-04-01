<#assign className = table.className>
<#assign flClassName = table.flClassName>
<#assign tableAlias = table.tableAlias>
<#assign indexs = table.indexs>
package ${basepackage}.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import cn.utrust.fintech.framework.PageDto;

import ${basepackage}.domain.entity.${className};
import ${basepackage}.dto.${className}DTO;

/**
 * ${tableAlias} 服务接口
 * @author ${env.USERNAME!}
 * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
public interface ${className}Service {
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
	public ${className} getBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>);
	</#if>
	
	/**
     * 查询返回Optional<T>对象，可进行函数式操作
     <#list index.columns as column>
     * @param ${column.columnNameFirstLower} ${column.remarks}
     </#list>
     * @return
     */
    public Optional<${className}> findBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>);    
    <#if !index.columns[0].pk>
    
    /**
     * 根据主键删除
     <#list index.columns as column>
     * @param ${column.columnNameFirstLower} ${column.remarks}
     </#list>
     * @return
     */
    public int delBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>);
    </#if>
    <#else>
    
	/**
     * 查询
     <#list index.columns as column>
     * @param ${column.columnNameFirstLower} ${column.remarks}
     </#list>
     * @return
     */
	public List<${className}> getBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>);
	</#if>
	</#list>
	
	/**
	 * 新增
	 * @param ${flClassName}
	 */
	public void add(${className} ${flClassName});
	
	/**
	 * 更新
	 * @param ${flClassName}
	 * @return 更新的行数
	 */
	public int update(${className} ${flClassName});
    
	/**
     * 按条件查询
     * @param ${flClassName}
     * @return
     */
    public List<${className}> getAll(${className}DTO param);

    /**
     * 分页查询
     * @param params 动态参数
     */
    public PageDto<${className}> getAllPage(${className}DTO param);

}