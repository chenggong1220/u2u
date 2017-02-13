package com.u2u.common.component.queue;

/**
 * 
 * @ClassName: AdvanceQueue <br>
 * @Description: 队列高级行为 (此行为仅暴露给业务中实际操作入队和出队行为的用户)<br>
 * @date 2015-3-2 上午11:19:25 <br>
 * 
 * @author Dean
 * @param <E>
 */
public interface AdvanceQueue<E>
{
    
    public void putInStrategy(E obj)
        throws Exception;
    
    public void putInStrategy(E obj, long maxlimit)
        throws Exception;
    
    public Object getInStrategy()
        throws Exception;
    
    public Object getInStrategy(long maxlimit)
        throws Exception;
    
}
