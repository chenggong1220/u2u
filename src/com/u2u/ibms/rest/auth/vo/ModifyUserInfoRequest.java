package com.u2u.ibms.rest.auth.vo;

public class ModifyUserInfoRequest{

	// 登录名
	private String username;
	//昵称
	private String nickName;
	// 密码
	private String pwd;
	// 用户电话
	private String mobile;
	// 验证码
	private String smsCode;
	// 用户邮件地址
	private String email;
	// 所属省份
	private Integer province;
	// 所在城市
	private Integer city;

	private String headImg;
	// 所属行业
	private String industry;
	//用户类型
	private Integer userType;
	//真是姓名
	private String realName;
	//出生日期
	private String birthday;
	//紧急联系人
	private String emergencyPhone;
	//办公电话
	private String officePhone;
	//钉钉号
	private String dingdingId;
	//备注
	private String 	remarks;
	/**
	 * 返回 登录名
	 * 
	 * @return 登录名
	*/
	public String getUsername() {
		return username;
	}
	
	/**
	 * 设置 登录名
	 * 
	 * @param username
	 *            登录名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	/**
	 * 返回 昵称
	 * 
	 * @return 昵称
	*/
	public String getNickName() {
		return nickName;
	}
	

	/**
	 * 设置 昵称
	 * 
	 * @param nickName
	 *            昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	
	/**
	 * 返回 用户电话
	 * 
	 * @return 用户电话
	*/
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * 设置 用户电话
	 * 
	 * @param mobile
	 *            用户电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 返回 验证码
	 * 
	 * @return 验证码
	*/
	public String getSmsCode() {
		return smsCode;
	}
	
	/**
	 * 设置 验证码
	 * 
	 * @param smsCode
	 *            验证码
	 */
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	
	/**
	 * 返回 用户邮件地址
	 * 
	 * @return 用户邮件地址
	*/
	public String getEmail() {
		return email;
	}
	
	/**
	 * 设置 用户邮件地址
	 * 
	 * @param email
	 *            用户邮件地址
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 返回 所属省份
	 * 
	 * @return 所属省份
	*/
	public Integer getProvince() {
		return province;
	}
	
	/**
	 * 设置 所属省份
	 * 
	 * @param province
	 *            所属省份
	 */
	public void setProvince(Integer province) {
		this.province = province;
	}
	
	/**
	 * 返回 所在城市
	 * 
	 * @return 所在城市
	*/
	public Integer getCity() {
		return city;
	}
	
	/**
	 * 设置 所在城市
	 * 
	 * @param city
	 *            所在城市
	 */
	public void setCity(Integer city) {
		this.city = city;
	}
	
	
	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getHeadImg() {
		return headImg;
	}
	
	/**
	 * 设置 bare_field_comment
	 * 
	 * @param headImg
	 *            bare_field_comment
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	/**
	 * 返回 所属行业
	 * 
	 * @return 所属行业
	*/
	public String getIndustry() {
		return industry;
	}
	
	/**
	 * 设置 所属行业
	 * 
	 * @param industry
	 *            所属行业
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * 返回 用户类型
	 * 
	 * @return 用户类型
	*/
	public Integer getUserType() {
		return userType;
	}
	

	/**
	 * 设置 用户类型
	 * 
	 * @param userType
	 *            用户类型
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/**
	 * 返回 真是姓名
	 * 
	 * @return 真是姓名
	*/
	public String getRealName() {
		return realName;
	}
	

	/**
	 * 设置 真是姓名
	 * 
	 * @param realName
	 *            真是姓名
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * 返回 出生日期
	 * 
	 * @return 出生日期
	*/
	public String getBirthday() {
		return birthday;
	}
	

	/**
	 * 设置 出生日期
	 * 
	 * @param birthday
	 *            出生日期
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * 返回 紧急联系人
	 * 
	 * @return 紧急联系人
	*/
	public String getEmergencyPhone() {
		return emergencyPhone;
	}
	

	/**
	 * 设置 紧急联系人
	 * 
	 * @param emergencyPhone
	 *            紧急联系人
	 */
	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	/**
	 * 返回 办公电话
	 * 
	 * @return 办公电话
	*/
	public String getOfficePhone() {
		return officePhone;
	}
	

	/**
	 * 设置 办公电话
	 * 
	 * @param officePhone
	 *            办公电话
	 */
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	/**
	 * 返回 钉钉号
	 * 
	 * @return 钉钉号
	*/
	public String getDingdingId() {
		return dingdingId;
	}
	

	/**
	 * 设置 钉钉号
	 * 
	 * @param dingdingId
	 *            钉钉号
	 */
	public void setDingdingId(String dingdingId) {
		this.dingdingId = dingdingId;
	}
	

	/**
	 * 返回 备注
	 * 
	 * @return 备注
	*/
	public String getRemarks() {
		return remarks;
	}
	

	/**
	 * 设置 备注
	 * 
	 * @param remarks
	 *            备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
