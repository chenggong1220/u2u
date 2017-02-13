package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

/**
 * 租赁人信息
 * 
 * @author think
 * 
 */
public class RentPersonInfo extends BaseBean {
	private int id;
	// 姓名
	private String name;
	// 电话
	private String mobile;
	// Email
	private String email;
	// 证件正面
	private String idCardFrontImg;
	// 证件反面
	private String idCardBackImg;
	// 证件手持
	private String idCardHandImg;
	private Integer personProvinceId;
	private Integer personCityId;
	private String personProvince;
	private String personCity;
	// 通讯地址
	private String address;
	// 邮编
	private String postcode;
	// 紧急联系人
	private String emergencyContact;
	// 关系
	private String relation;
	// 紧急联系人电话
	private String emergencyContactMobile;
	private String idCardImg;
	public Integer getPersonProvinceId() {
		return personProvinceId;
	}

	public void setPersonProvinceId(Integer personProvinceId) {
		this.personProvinceId = personProvinceId;
	}

	public Integer getPersonCityId() {
		return personCityId;
	}

	public void setPersonCityId(Integer personCityId) {
		this.personCityId = personCityId;
	}

	public String getPersonProvince() {
		return personProvince;
	}

	public void setPersonProvince(String personProvince) {
		this.personProvince = personProvince;
	}

	public String getPersonCity() {
		return personCity;
	}

	public void setPersonCity(String personCity) {
		this.personCity = personCity;
	}

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param id
	 *            bare_field_comment
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 返回 姓名
	 * 
	 * @return 姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 姓名
	 * 
	 * @param name
	 *            姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 电话
	 * 
	 * @return 电话
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置 电话
	 * 
	 * @param mobile
	 *            电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 返回 Email
	 * 
	 * @return Email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置 Email
	 * 
	 * @param email
	 *            Email
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * 返回 通讯地址
	 * 
	 * @return 通讯地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置 通讯地址
	 * 
	 * @param address
	 *            通讯地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 返回 邮编
	 * 
	 * @return 邮编
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * 设置 邮编
	 * 
	 * @param postcode
	 *            邮编
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
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
	 * 返回 关系
	 * 
	 * @return 关系
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * 设置 关系
	 * 
	 * @param relation
	 *            关系
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}

	/**
	 * 返回 紧急联系人电话
	 * 
	 * @return 紧急联系人电话
	 */
	public String getEmergencyContactMobile() {
		return emergencyContactMobile;
	}

	/**
	 * 设置 紧急联系人电话
	 * 
	 * @param emergencyContactMobile
	 *            紧急联系人电话
	 */
	public void setEmergencyContactMobile(String emergencyContactMobile) {
		this.emergencyContactMobile = emergencyContactMobile;
	}

	public String getIdCardFrontImg() {
		return idCardFrontImg;
	}
	

	public void setIdCardFrontImg(String idCardFrontImg) {
		this.idCardFrontImg = idCardFrontImg;
	}
	

	public String getIdCardBackImg() {
		return idCardBackImg;
	}
	

	public void setIdCardBackImg(String idCardBackImg) {
		this.idCardBackImg = idCardBackImg;
	}
	

	public String getIdCardHandImg() {
		return idCardHandImg;
	}
	

	public void setIdCardHandImg(String idCardHandImg) {
		this.idCardHandImg = idCardHandImg;
	}

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getIdCardImg() {
		return idCardImg;
	}
	

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param idCardImg
	 *            bare_field_comment
	 */
	public void setIdCardImg(String idCardImg) {
		this.idCardImg = idCardImg;
	}
	
	

}
