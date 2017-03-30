package com.u2u.ibms.rest.auth.vo;

public class LoginRequest {
	// 登陆用户名
	private String username;
	// 密码
	private String pwd;
	//App的设备ID
	private String regID;

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

	//Start: 为了消息推送，增加设备ID信息，SUNZHE,2017-03-26
	public String getRegID() {
		return regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}
	//End: 为了消息推送，增加设备ID信息，SUNZHE,2017-03-26
}
