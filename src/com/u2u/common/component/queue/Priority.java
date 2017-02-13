package com.u2u.common.component.queue;

/**

 */
/**
 * 
 * @ClassName: Priority <br>
 * @Description: * 优先级接口 若要实现优先级队列，请将要入队的对象实现此接口， 并实现compareTo方法来定制自己的比较策略<br>
 * @date 2015-3-2 上午11:20:41 <br>
 * 
 * @author Dean
 */
public interface Priority extends Comparable<Priority>
{
    
}
