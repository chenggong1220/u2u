package com.u2u.ibms.rest.auth.vo;

import com.u2u.framework.base.BaseRequest;

public class UserInfoRequest extends BaseRequest{
	//用户名
	private String userName;
	//密码
	private String passWord;
	//短信码
	private String smsCode;
	//邮箱
	private String email;
	//真实姓名
	private String realName;
	//头像地址
	private String headAddr;
	//生日
	private String birthday;
	//省份
	private String province;
	//城市
	private String city;
	//所属行业
	private String industry;
	//紧急联系人
	private String emergencyContact;
	//办公电话
	private String officePhone;
	//钉钉号
	private String dingdingId;
	//备注
	private String remarks;
	//手机号
	private String telphone;
	/**
	 * 返回 用户名
	 * 
	 * @return 用户名
	*/
	public String getUserName() {
		return userName;
	}
	
	/**
	 * 设置 用户名
	 * 
	 * @param userName
	 *            用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 返回 密码
	 * 
	 * @return 密码
	*/
	public String getPassWord() {
		return passWord;
	}
	
	/**
	 * 设置 密码
	 * 
	 * @param passWord
	 *            密码
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	/**
	 * 返回 短信码
	 * 
	 * @return 短信码
	*/
	public String getSmsCode() {
		return smsCode;
	}
	
	/**
	 * 设置 短信码
	 * 
	 * @param smsCode
	 *            短信码
	 */
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	
	/**
	 * 返回 邮箱
	 * 
	 * @return 邮箱
	*/
	public String getEmail() {
		return email;
	}
	
	/**
	 * 设置 邮箱
	 * 
	 * @param email
	 *            邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 返回 真实姓名
	 * 
	 * @return 真实姓名
	*/
	public String getRealName() {
		return realName;
	}
	
	/**
	 * 设置 真实姓名
	 * 
	 * @param realName
	 *            真实姓名
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	/**
	 * 返回 头像地址
	 * 
	 * @return 头像地址
	*/
	public String getHeadAddr() {
		return headAddr;
	}
	
	/**
	 * 设置 头像地址
	 * 
	 * @param headAddr
	 *            头像地址
	 */
	public void setHeadAddr(String headAddr) {
		this.headAddr = headAddr;
	}
	
	/**
	 * 返回 生日
	 * 
	 * @return 生日
	*/
	public String getBirthday() {
		return birthday;
	}
	
	/**
	 * 设置 生日
	 * 
	 * @param birthday
	 *            生日
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	/**
	 * 返回 省份
	 * 
	 * @return 省份
	*/
	public String getProvince() {
		return province;
	}
	
	/**
	 * 设置 省份
	 * 
	 * @param province
	 *            省份
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * 返回 城市
	 * 
	 * @return 城市
	*/
	public String getCity() {
		return city;
	}
	
	/**
	 * 设置 城市
	 * 
	 * @param city
	 *            城市
	 */
	public void setCity(String city) {
		this.city = city;
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
	 * 返回 紧急联系人
	 * 
	 * @return 紧急联系人
	*/
	public String getEmergencyContact() {
		return emergencyContact;
	}
	
	/**
	 * 设置 紧急联系人
	 * 
	 * @param emergencyContact
	 *            紧急联系人
	 */
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
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

	/**
	 * 返回 手机号
	 * 
	 * @return 手机号
	*/
	public String getTelphone() {
		return telphone;
	}
	

	/**
	 * 设置 手机号
	 * 
	 * @param telphone
	 *            手机号
	 */
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
}
