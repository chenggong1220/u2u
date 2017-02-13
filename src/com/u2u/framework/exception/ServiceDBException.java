package com.u2u.framework.exception;

/**
 * @ClassName: ServiceDBException <br>
 * @Description: DB 操作异常 <br>
 * @date 2015年1月26日 上午9:08:02 <br>
 * 
 * @author Freud
 */
public class ServiceDBException extends Exception {

	/**
	 * <p>
	 * Discription:[serialVersionUID]
	 * </p>
	 */
	private static final long serialVersionUID = -4364390907999921565L;

	public ServiceDBException(String message) {
		super(message);
	}
}
