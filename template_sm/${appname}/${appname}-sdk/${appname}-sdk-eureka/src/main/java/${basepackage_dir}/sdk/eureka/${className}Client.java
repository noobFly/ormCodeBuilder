<#assign className = table.className>
<#assign flClassName = table.flClassName>
<#assign tableAlias = table.tableAlias>
package ${basepackage}.sdk.eureka;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ${basepackage}.dto.${className}DTO;
import cn.utrust.fintech.framework.PageDto;

/**
 * ${tableAlias} 控制器
 * @author ${env.USERNAME!}
 * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
@RequestMapping("/${flClassName}")
@FeignClient(value="${appname}-service")
public interface ${className}Client{
    /**
     * 创建${tableAlias}
     * @param ${flClassName}
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @RequestMapping(method = RequestMethod.POST)
    public ${className}DTO create(@RequestBody ${className}DTO ${flClassName});

    /**
     * 更新
     * @param id ${tableAlias}ID
     * @param ${flClassName} 更新内容
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ${className}DTO update(@PathVariable("id") Long id, @RequestBody ${className}DTO ${flClassName});

    /**
     * 根据ID查询
     * @param id ${tableAlias}ID
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ${className}DTO findById(@PathVariable("id") Long id);

    /**
     * 删除
     * @param id ${tableAlias}ID
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id);

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
    public PageDto<${className}DTO> pageList(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize, @RequestParam Map<String,Object> params);
}