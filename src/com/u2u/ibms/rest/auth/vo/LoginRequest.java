package com.u2u.ibms.rest.auth.vo;

public class LoginRequest {
	// 登陆用户名
	private String username;
	// 密码
	private String pwd;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 返回 密码
	 * 
	 * @return 密码
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * 设置 密码
	 * 
	 * @param pwd
	 *            密码
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
