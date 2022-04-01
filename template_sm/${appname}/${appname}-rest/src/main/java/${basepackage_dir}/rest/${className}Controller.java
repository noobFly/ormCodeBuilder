<#assign className = table.className>
<#assign flClassName = table.flClassName>
<#assign tableAlias = table.tableAlias>
package ${basepackage}.rest;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ${basepackage}.service.${className}Service;
import ${basepackage}.domain.entity.${className};
import cn.utrust.fintech.framework.PageDto;

/**
 * ${tableAlias} 控制器
 * @author ${env.USERNAME!}
 * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
@RequestMapping("/${flClassName}")
@RestController
@Validated
public class ${className}Controller{
    @Autowired
    private ${className}Service ${flClassName}Service;

    /**
     * 创建${tableAlias}
     * @param ${flClassName}
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @RequestMapping(method = RequestMethod.POST)
    public ${className} create(@Valid @RequestBody ${className} ${flClassName}) {
        //TODO 其它默认参数初始化
        ${flClassName}Service.add(${flClassName});
        return ${flClassName};
    }

    /**
     * 更新
     * @param id ${tableAlias}ID
     * @param ${flClassName} 更新内容
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") Long id,
            @RequestBody ${className} ${flClassName}) {
        ${flClassName}Service.update(${flClassName});
    }

    /**
     * 根据ID查询
     * @param id ${tableAlias}ID
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ${className} findById(@PathVariable("id") Long id) {
        return ${flClassName}Service.getById(id);
    }

    /**
     * 删除
     * @param id ${tableAlias}ID
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        ${flClassName}Service.delBy<#list table.pkColumns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list table.pkColumns as column><#if column_index gt 0>, </#if>${column.columnNameFirstLower}</#list>);
    }

    /**
     * 查询${tableAlias}列表
     * @param pageNum 当前页
     * @param pageSize 每页行数
     * @param params 过滤参数
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @RequestMapping(value="/pageList",method = RequestMethod.GET)
    public PageDto<${className}> pageList(@RequestParam Map<String,Object> params) {
        return ${flClassName}Service.getAllPage(params);
    }
}