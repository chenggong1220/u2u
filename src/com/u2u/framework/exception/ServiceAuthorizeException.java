package com.u2u.framework.exception;

/**
 * @ClassName: ServiceAuthorizeException <br>
 * @Description: 权限验证异常 <br>
 * @date 2015年1月26日 上午9:12:39 <br>
 * 
 * @author Freud
 */
public class ServiceAuthorizeException extends Exception
{
    /**
     * <p>
     * Discription:[serialVersionUID]
     * </p>
     */
    private static final long serialVersionUID = -4364390907999921565L;
    
    public ServiceAuthorizeException(String message)
    {
        super(message);
    }
}
