package com.u2u.framework.dal.object;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * @ClassName: AbstractDO <br>
 * @Description: DO对象基类 <br>
 * @date 2015-3-2 下午2:05:53 <br>
 * 
 * @author Freud
 */
public abstract class AbstractDO implements Serializable
{
    
    private static final long serialVersionUID = -3942149913171834745L;
    
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
    
}
