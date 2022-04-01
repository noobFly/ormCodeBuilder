<#assign className = table.className>
<#assign flClassName = table.flClassName>
<#assign tableAlias = table.tableAlias>
package ${basepackage}.rest;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ${basepackage}.service.${className}Service;
import ${basepackage}.domain.entity.${className};
import ${basepackage}.dto.${className}DTO;
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
    @PostMapping(value = "/create")
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
    @PostMapping(value = "/update")
    public void update(@RequestBody ${className} ${flClassName}) {
        ${flClassName}Service.update(${flClassName});
    }

    /**
     * 根据ID查询
     * @param id ${tableAlias}ID
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @PostMapping(value = "/getById")
    public ${className} findById(@RequestBody ${className} ${flClassName}) {
        return ${flClassName}Service.findBy<#list table.pkColumns as column><#if column_index gt 0>And</#if>${column.columnName}</#list>(<#list table.pkColumns as column><#if column_index gt 0>, </#if>${flClassName}.get${column.columnName}()</#list>).get();
    }

    /**
     * 删除
     * @param id ${tableAlias}ID
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @PostMapping(value = "/delById")
    public void delete(@RequestBody ${className} ${flClassName}) {
        //TODO
        throw new RuntimeException("未实现的功能");
    }

    /**
     * 查询${tableAlias}列表
     * @param params 过滤参数
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @PostMapping(value="/getPageList")
    public PageDto<${className}> pageList(@RequestBody ${className}DTO params) {
        return ${flClassName}Service.getAllPage(params);
    }
}