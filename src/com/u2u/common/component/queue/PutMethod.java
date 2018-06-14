package com.u2u.common.component.queue;

/**
 * 
 * @ClassName: PutMethod <br>
 * @Description: 入队方式 <br>
 * @date 2015-3-2 上午11:21:29 <br>
 * 
 * @author Freud
 */
public interface PutMethod
{
    
    public boolean allowPut(BaseQueue<Object> queue, Object o);
    
}
