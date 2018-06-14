package com.u2u.framework.beans;

/**
 * @ClassName: AjaxDone <br>
 * @Description: 成功返回对象 <br>
 * @date 2015年1月22日 下午5:21:49 <br>
 * 
 * @author Freud
 */
public class AjaxDone {

	/**
	 * The status code.
	 */
	private String statusCode;

	/**
	 * The message.
	 */
	private String message;

	/**
	 * The return url.
	 */
	private String returnUrl;

	public AjaxDone() {
		super();
	}

	public AjaxDone(String statusCode, String message, String returnUrl) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.returnUrl = returnUrl;
	}

	/**
	 * 
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return
	 * @author:[Freud]
	 * @update:[2015年1月22日] [更改人姓名][变更描述]
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * 
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param statusCode
	 * @author:[Freud]
	 * @update:[2015年1月22日] [更改人姓名][变更描述]
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * 
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return
	 * @author:[Freud]
	 * @update:[2015年1月22日] [更改人姓名][变更描述]
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param message
	 * @author:[Freud]
	 * @update:[2015年1月22日] [更改人姓名][变更描述]
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return
	 * @author:[Freud]
	 * @update:[2015年1月22日] [更改人姓名][变更描述]
	 */
	public String getReturnUrl() {
		return returnUrl;
	}

	/**
	 * 
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param returnUrl
	 * @author:[Freud]
	 * @update:[2015年1月22日] [更改人姓名][变更描述]
	 */
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
}
