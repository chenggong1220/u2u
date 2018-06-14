package com.u2u.ibms.rest.auth.vo;

public class ChangeLoginPwdRequest {
	//用户名
	private String username;
	//原密码
	private String oldPassword;
	//新密码
	private String newPassword;
	/**
	 * 返回 用户名
	 * 
	 * @return 用户名
	*/
	public String getUsername() {
		return username;
	}
	
	/**
	 * 设置 用户名
	 * 
	 * @param username
	 *            用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 返回 原密码
	 * 
	 * @return 原密码
	*/
	public String getOldPassword() {
		return oldPassword;
	}
	
	/**
	 * 设置 原密码
	 * 
	 * @param oldPassword
	 *            原密码
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	/**
	 * 返回 新密码
	 * 
	 * @return 新密码
	*/
	public String getNewPassword() {
		return newPassword;
	}
	
	/**
	 * 设置 新密码
	 * 
	 * @param newPassword
	 *            新密码
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	

	
}
