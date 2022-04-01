<#assign className = table.className>
<#assign flClassName = table.flClassName>
<#assign tableAlias = table.tableAlias>
package ${basepackage}.sdk.openfeign;

import java.util.Map;

import ${basepackage}.dto.${className}DTO;
import cn.utrust.fintech.openfeign.proxy.Page;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

public interface ${className}Client{
    /**
     * 创建${tableAlias}
     * @param ${flClassName}
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @RequestLine("POST /${flClassName}")
    @Headers("Content-Type: application/json")
    public ${className}DTO create(${className}DTO ${flClassName});

    /**
     * 更新
     * @param id ${tableAlias}ID
     * @param ${flClassName} 更新内容
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @RequestLine("PUT /${flClassName}/{id}")
    @Headers("Content-Type: application/json")
    public ${className}DTO update(@Param("id")  Long id, ${className}DTO ${flClassName});

    /**
     * 根据ID查询
     * @param id ${tableAlias}ID
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @RequestLine("GET /${flClassName}/{id}")
    public ${className}DTO findById(@Param("id") Long id);

    /**
     * 删除
     * @param id ${tableAlias}ID
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @RequestLine("DELETE /${flClassName}/{id}")
    public void delete(@Param("id") Long id);

    /**
     * 查询${tableAlias}列表
     * @param pageNum 当前页
     * @param pageSize 每页行数
     * @param params 过滤参数
     * @return
     * @author ${env.USERNAME!}
     * @date ${now?string("yyyy-MM-dd HH:mm:ss")}
     */
    @RequestLine("GET /${flClassName}/pageList?pageNum={pageNum}&pageSize={pageSize}")
    public Page<${className}DTO> pageList(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize, @QueryMap Map<String,Object> params);
}