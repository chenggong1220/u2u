package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

public class UserInfo extends BaseBean {

	private int id;

	private String username;
	private String password;
	private String nickname;
	private String email;
	private String mobile;

	private int provinceId;
	private int cityId;
	private String province;
	private String city;

	private String headImg;
	private String industry;

	private int userType;
	private int level;
	private int realnameVerify;

	private String realname;
	private String identify;
	private String identifyFrontImg;
	private String identifyBackendImg;
	private String certificationPicture;

	private boolean status;

	private float memberAmount;
	private boolean hasMemberAmount;
	private float payMemberAmount;

	private boolean hasDeposited;
	private float deposit;
	private float payDeposit;

	private float amount;

	private boolean pushMessage;

	public float getMemberAmount() {
		return memberAmount;
	}

	public void setMemberAmount(float memberAmount) {
		this.memberAmount = memberAmount;
	}


	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public boolean isHasMemberAmount() {
		return hasMemberAmount;
	}
	

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param hasMemberAmount
	 *            bare_field_comment
	 */
	public void setHasMemberAmount(boolean hasMemberAmount) {
		this.hasMemberAmount = hasMemberAmount;
	}
	

	public float getPayMemberAmount() {
		return payMemberAmount;
	}

	public void setPayMemberAmount(float payMemberAmount) {
		this.payMemberAmount = payMemberAmount;
	}

	public float getPayDeposit() {
		return payDeposit;
	}

	public void setPayDeposit(float payDeposit) {
		this.payDeposit = payDeposit;
	}

	public String getCertificationPicture() {
		return certificationPicture;
	}

	public void setCertificationPicture(String certificationPicture) {
		this.certificationPicture = certificationPicture;
	}

	public boolean isPushMessage() {
		return pushMessage;
	}

	public void setPushMessage(boolean pushMessage) {
		this.pushMessage = pushMessage;
	}


	public float getDeposit() {
		return deposit;
	}

	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getRealnameVerify() {
		return realnameVerify;
	}

	public void setRealnameVerify(int realnameVerify) {
		this.realnameVerify = realnameVerify;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String getIdentifyFrontImg() {
		return identifyFrontImg;
	}

	public void setIdentifyFrontImg(String identifyFrontImg) {
		this.identifyFrontImg = identifyFrontImg;
	}

	public String getIdentifyBackendImg() {
		return identifyBackendImg;
	}

	public void setIdentifyBackendImg(String identifyBackendImg) {
		this.identifyBackendImg = identifyBackendImg;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public boolean isHasDeposited() {
		return hasDeposited;
	}
	

	public void setHasDeposited(boolean hasDeposited) {
		this.hasDeposited = hasDeposited;
	}
	

	
}
