package com.u2u.ibms.rest.auth.vo;

public class LoginRespose {

	// 用户账号
	private String username;
	// 用户邮件地址
	private String email;
	// 用户电话
	private String mobile;
	// 昵称
	private String nickname;

	private String userType;

	private String headImg;
	//是否推送消息
	private int pushMessage;
	//
	private String provinceId;
	private String proviceName;
	private String cityId;
	private String cityName;
	
	
	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getProviceName() {
		return proviceName;
	}
	



	/**
	 * 设置 bare_field_comment
	 * 
	 * @param proviceName
	 *            bare_field_comment
	 */
	public void setProviceName(String proviceName) {
		this.proviceName = proviceName;
	}
	



	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getCityName() {
		return cityName;
	}
	



	/**
	 * 设置 bare_field_comment
	 * 
	 * @param cityName
	 *            bare_field_comment
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	



	/**
	 * 返回 
	 * 
	 * @return 
	*/
	public String getProvinceId() {
		return provinceId;
	}
	


	/**
	 * 设置 
	 * 
	 * @param provinceId
	 *            
	 */
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	


	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getCityId() {
		return cityId;
	}
	


	/**
	 * 设置 bare_field_comment
	 * 
	 * @param cityId
	 *            bare_field_comment
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	


	/**
	 * 返回 是否推送消息
	 * 
	 * @return 是否推送消息
	*/
	public int getPushMessage() {
		return pushMessage;
	}
	

	/**
	 * 设置 是否推送消息
	 * 
	 * @param pushMessage
	 *            是否推送消息
	 */
	public void setPushMessage(int pushMessage) {
		this.pushMessage = pushMessage;
	}
	

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
