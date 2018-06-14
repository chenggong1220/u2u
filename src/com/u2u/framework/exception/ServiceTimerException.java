package com.u2u.framework.exception;

/**
 * 
 * @ClassName: ServiceTimerException <br>
 * @Description: 定时器操作异常 <br>
 * @date 2015-3-2 上午10:37:27 <br>
 * 
 * @author Freud
 */
public class ServiceTimerException extends Exception
{
    /**
     * <p>
     * Discription:[serialVersionUID]
     * </p>
     */
    private static final long serialVersionUID = -4364390907999921565L;
    
    public ServiceTimerException(String message)
    {
        super(message);
    }
}
