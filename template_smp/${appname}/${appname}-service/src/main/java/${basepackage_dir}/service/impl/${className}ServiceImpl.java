<#assign className = table.className>
<#assign flClassName = table.flClassName>
<#assign tableAlias = table.tableAlias>
<#assign indexs = table.indexs>
package ${basepackage}.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basepackage}.domain.mapper.${className}Mapper;
import ${basepackage}.service.${className}Service;

import cn.noob.framework.PageDto;

import ${basepackage}.domain.entity.${className};
import ${basepackage}.dto.${className}DTO;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * ${tableAlias} 服务接口实现
 * @author ${env.USERNAME!}
 * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
 */

@Service
public class ${className}ServiceImpl implements ${className}Service{
	@Autowired
	private ${className}Mapper ${flClassName}Mapper;
	
	<#list indexs as index>
	<#if index.unique>
	<#if !index.columns[0].pk>
	
	@Override
	public ${className} getBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>) {
		return ${flClassName}Mapper.getBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.columnNameFirstLower}</#list>);
	}
	</#if>
	
	@Override
    public Optional<${className}> findBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>) {
        return ${flClassName}Mapper.findBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.columnNameFirstLower}</#list>);
    }  
    <#if !index.columns[0].pk>
    
    @Override
    public int delBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>) {
        return ${flClassName}Mapper.delBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.columnNameFirstLower}</#list>);
    }
    </#if>
	<#else>
    
	@Override
	public List<${className}> getBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>) {
		return ${flClassName}Mapper.getBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.columnNameFirstLower}</#list>);
	}
	</#if>
	</#list>
	
	@Override
	public void add(${className} ${flClassName}){
		${flClassName}Mapper.insert(${flClassName});
	}
	
	@Override
	public int update(${className} ${flClassName}){
		return ${flClassName}Mapper.patchUpdate(${flClassName});
	}
	
	@Override
    public List<${className}> getAll(${className}DTO params){
    	return ${flClassName}Mapper.getAll(params);
    }
    
    @Override
    public PageDto<${className}> getAllPage(${className}DTO params){
    	int pageNum = params.getPageNum() == null ? 1 : params.getPageNum();
    	int pageSize = params.getPageSize() == null ? 10 : params.getPageSize();
    	Page<${className}> page = PageHelper.startPage(pageNum, pageSize);
    	${flClassName}Mapper.getAll(params);
    	return new PageDto<>(page);
    }
}