package com.u2u.ibms.rest.message.vo;

public class MessageRequest {

	private String id;
	private String username;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
	
}
