package com.u2u.framework.util.security.springsecurity;

import java.util.LinkedHashMap;

/**
 * 
 * @ClassName: ResourceDetailsService <br>
 * @Description: RequestMap生成接口,由用户自行实现从数据库或其它地方查询URL-授权关系定义. <br>
 * @date 2015-3-2 下午2:32:19 <br>
 * 
 * @author Dean
 */
public interface ResourceDetailsService
{
    
    /**
     * 返回带顺序的URL-授权关系Map, key为受保护的URL, value为能访问该URL的授权名称列表,以','分隔.
     */
    public LinkedHashMap<String, String> getRequestMap()
        throws Exception; // NOSONAR
}
