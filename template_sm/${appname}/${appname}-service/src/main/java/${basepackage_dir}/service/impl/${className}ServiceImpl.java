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

import cn.utrust.fintech.framework.PageDto;

import ${basepackage}.domain.entity.${className};

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
	@Override
	public ${className} getBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>) {
		return ${flClassName}Mapper.getBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.columnNameFirstLower}</#list>);
	}
	@Override
    public Optional<${className}> findBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>) {
        return ${flClassName}Mapper.findBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.columnNameFirstLower}</#list>);
    }
    @Override
    public Optional<${className}> findBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>WithLock(<#list index.columns as column><#if column_index gt 0>, </#if>${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>) {
        return ${flClassName}Mapper.findBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>WithLock(<#list index.columns as column><#if column_index gt 0>, </#if>${column.columnNameFirstLower}</#list>);
    }
	@Override
	public void delBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>){
		${flClassName}Mapper.delBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.columnNameFirstLower}</#list>);
	}
	<#else>
	@Override
	public List<${className}> getBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.javaType?replace("java.lang.","")} ${column.columnNameFirstLower}</#list>) {
		return ${flClassName}Mapper.getBy<#list index.columns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list index.columns as column><#if column_index gt 0>, </#if>${column.columnNameFirstLower}</#list>);
	}
	</#if>
	</#list>
	@Override
	public void add(${className} ${flClassName}){
		${flClassName}Mapper.add(${flClassName});
	}
	@Override
	public int update(${className} ${flClassName}){
		return ${flClassName}Mapper.patchUpdate(${flClassName});
	}
	@Override
    public List<${className}> getAll(${className} ${flClassName}){
    	return ${flClassName}Mapper.getAll(${flClassName});
    }
    @Override
    public List<${className}> getAll(Map<String,Object> params){
    	return ${flClassName}Mapper.getAll(params);
    }
    @Override
    public PageDto<${className}> getAllPage(Map<String,Object> params){
    	int pageNum = Integer.valueOf(params.get("pageNum") == null ? "1" : params.get("pageNum").toString());
    	int pageSize = Integer.valueOf(params.get("pageSize") == null ? "10" : params.get("pageSize").toString());
    	Page<${className}> page = PageHelper.startPage(pageNum, pageSize);
    	${flClassName}Mapper.getAll(params);
    	return new PageDto<>(page);
    }
    @Override
    public int count(${className} ${flClassName}){
    	return ${flClassName}Mapper.count(${flClassName});
    }
    @Override
    public int count(Map<String,Object> params){
    	return ${flClassName}Mapper.count(params);
    }
}