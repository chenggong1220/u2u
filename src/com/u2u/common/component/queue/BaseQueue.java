package com.u2u.common.component.queue;

/**
 * 
 * @ClassName: BaseQueue <br>
 * @Description: 队列基本行为 (此行为仅暴露给定制策略的用户)<br>
 * @date 2015-3-2 上午11:19:47 <br>
 * 
 * @author Dean
 * @param <E>
 */
public interface BaseQueue<E>
{
    
    public void put(E obj)
        throws Exception;
    
    public boolean put(E obj, long maxlimit)
        throws Exception;
    
    public Object get()
        throws Exception;
    
    public Object get(long maxlimit)
        throws Exception;
    
    public int capacity();
    
    public int size();
    
    public void clear();
    
    public boolean isFull();
    
    public E[] toArray(E[] objs);
    
    public boolean remove(Object o);
    
}
