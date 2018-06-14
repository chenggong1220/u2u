package com.u2u.ibms.rest.auth.vo;

public class ChangePushMessageRequest {
	private String username;
	private String pwd;
	private int pushMessage;

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getUsername() {
		return username;
	}
	


	/**
	 * 设置 bare_field_comment
	 * 
	 * @param username
	 *            bare_field_comment
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	


	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getPwd() {
		return pwd;
	}
	


	/**
	 * 设置 bare_field_comment
	 * 
	 * @param pwd
	 *            bare_field_comment
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	


	

	public int getPushMessage() {
		return pushMessage;
	}
	

	public void setPushMessage(int pushMessage) {
		this.pushMessage = pushMessage;
	}
	
}
