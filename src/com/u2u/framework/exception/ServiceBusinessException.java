package com.u2u.framework.exception;

/**
 * @ClassName: ServiceBusinessException <br>
 * @Description: Without log stack trace. <br>
 * @date 2015-1-25 上午01:49:37 <br>
 * 
 * @author Dean
 */
public class ServiceBusinessException extends Exception {

	/**
	 * <p>
	 * Discription:[serialVersionUID]
	 * </p>
	 */
	private static final long serialVersionUID = -4364390907999921565L;

	public ServiceBusinessException(String message) {
		super(message);
	}

}
