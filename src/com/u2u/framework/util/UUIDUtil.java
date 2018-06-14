package com.u2u.framework.util;

import java.util.UUID;

/**
 * @ClassName: UUIDUtil <br>
 * @Description: UUID 生成策略类<br>
 * @date 2015-3-9 上午10:43:49 <br>
 * 
 * @author Freud
 */
public final class UUIDUtil
{
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @return
     * @author:[Freud]
     * @update:[2015-3-9] [更改人姓名][变更描述]
     */
    public static String generateUUID()
    {
        
        return UUID.randomUUID().toString().replace("-", "");
    }
    
}
