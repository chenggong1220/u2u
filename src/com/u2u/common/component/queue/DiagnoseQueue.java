package com.u2u.common.component.queue;

/**
 * 
 * @ClassName: DiagnoseQueue <br>
 * @Description: 队列诊断信息接口 (此行为仅暴露给用于的用户)<br>
 * @date 2015-3-2 上午11:20:09 <br>
 * 
 * @author Dean
 * @param <E>
 */
public interface DiagnoseQueue<E>
{
    
    public int size();
    
    public int capacity();
    
    public boolean isFull();
    
    public boolean isPriorityQueue();
    
    public E[] toArray(E[] objs);
    
}
