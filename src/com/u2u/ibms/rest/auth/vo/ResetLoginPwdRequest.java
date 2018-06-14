package com.u2u.ibms.rest.auth.vo;

public class ResetLoginPwdRequest {
	//用户名
	private String username;
	// 手机号啊
	private String mobile;
	// 验证码
	private String smsCode;
	// 新密码
	private String password;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
	
	

}
