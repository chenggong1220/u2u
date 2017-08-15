package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

/**
 * 租赁公司信息
 * 
 * @author think
 * 
 */
public class RentCompanyInfo extends BaseBean {
	private int id;
	// 企业名称
	private String name;
	// 地址
	private String address;
	private int companyProvinceId;
	private int companyCityId;
	private String companyProvince;
	private String companyCity;
	// 通讯地址
	private String postalAddress;
	// 邮编
	private String postcode;
	//通讯地址省
	private int postalProvinceId;
	//通讯地址市
	private int postalCityId;
	// 营业执照
	private String businessLicensePath;
	// 上一年纳税申请表
	private String lastYearApplicationFormPath;
	// 法人代表
	private String legalName;
	// 法人电话
	private String legalMobile;
	// email
	private String legalEmail;
	// 证件路径
	private String certificatePath;
	// 紧急联系人
	private String emergencyContact;
	// 关系
	private String relation;
	// 紧急联系人电话
	private String emergencyContactMobile;
	// 证件正面
	private String idCardFrontImg;
	// 证件反面
	private String idCardBackImg;
	// 证件手持
	private String idCardHandImg;
	// 证件正面
	private String c_idCardFrontImg;
	// 证件反面
	private String c_idCardBackImg;
	// 证件手持
	private String c_idCardHandImg;
		
	//企业代码
	private String compCode;
	
	//Start: Added Actual Controller, Carter Sun, 2017-08-01
	//企业实际控制人
	private String actualController;
	//企业实际控制人电话
	private String controllerMobile;
	//上年纳税凭证
	private String lastYearTaxProofPath;	
	//End: Added Actual Controller, Carter Sun, 2017-08-01	
	
	public String getCompanyProvince() {
		return companyProvince;
	}

	public void setCompanyProvince(String companyProvince) {
		this.companyProvince = companyProvince;
	}

	public String getCompanyCity() {
		return companyCity;
	}

	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}

	public int getCompanyProvinceId() {
		return companyProvinceId;
	}

	public void setCompanyProvinceId(int companyProvinceId) {
		this.companyProvinceId = companyProvinceId;
	}

	public int getCompanyCityId() {
		return companyCityId;
	}

	public void setCompanyCityId(int companyCityId) {
		this.companyCityId = companyCityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	 * 返回 营业执照
	 * 
	 * @return 营业执照
	 */
	public String getBusinessLicensePath() {
		return businessLicensePath;
	}

	/**
	 * 设置 营业执照
	 * 
	 * @param businessLicensePath
	 *            营业执照
	 */
	public void setBusinessLicensePath(String businessLicensePath) {
		this.businessLicensePath = businessLicensePath;
	}

	/**
	 * 返回 上一年纳税申请表
	 * 
	 * @return 上一年纳税申请表
	 */
	public String getLastYearApplicationFormPath() {
		return lastYearApplicationFormPath;
	}

	/**
	 * 设置 上一年纳税申请表
	 * 
	 * @param lastYearApplicationFormPath
	 *            上一年纳税申请表
	 */
	public void setLastYearApplicationFormPath(
			String lastYearApplicationFormPath) {
		this.lastYearApplicationFormPath = lastYearApplicationFormPath;
	}

	/**
	 * 返回 法人代表
	 * 
	 * @return 法人代表
	 */
	public String getLegalName() {
		return legalName;
	}

	/**
	 * 设置 法人代表
	 * 
	 * @param legalName
	 *            法人代表
	 */
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	/**
	 * 返回 法人电话
	 * 
	 * @return 法人电话
	 */
	public String getLegalMobile() {
		return legalMobile;
	}

	/**
	 * 设置 法人电话
	 * 
	 * @param legalMobile
	 *            法人电话
	 */
	public void setLegalMobile(String legalMobile) {
		this.legalMobile = legalMobile;
	}

	/**
	 * 返回 email
	 * 
	 * @return email
	 */
	public String getLegalEmail() {
		return legalEmail;
	}

	/**
	 * 设置 email
	 * 
	 * @param legalEmail
	 *            email
	 */
	public void setLegalEmail(String legalEmail) {
		this.legalEmail = legalEmail;
	}

	/**
	 * 返回 证件路径
	 * 
	 * @return 证件路径
	 */
	public String getCertificatePath() {
		return certificatePath;
	}

	/**
	 * 设置 证件路径
	 * 
	 * @param certificatePath
	 *            证件路径
	 */
	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
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
	 * 返回 通讯地址
	 * 
	 * @return 通讯地址
	 */
	public String getPostalAddress() {
		return postalAddress;
	}

	/**
	 * 设置 通讯地址
	 * 
	 * @param postalAddress
	 *            通讯地址
	 */
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	/**
	 * 返回 通讯地址省
	 * 
	 * @return 通讯地址省
	*/
	public int getPostalProvinceId() {
		return postalProvinceId;
	}
	

	/**
	 * 设置 通讯地址省
	 * 
	 * @param postalProvinceId
	 *            通讯地址省
	 */
	public void setPostalProvinceId(int postalProvinceId) {
		this.postalProvinceId = postalProvinceId;
	}
	

	/**
	 * 返回 通讯地址市
	 * 
	 * @return 通讯地址市
	*/
	public int getPostalCityId() {
		return postalCityId;
	}
	

	/**
	 * 设置 通讯地址市
	 * 
	 * @param postalCityId
	 *            通讯地址市
	 */
	public void setPostalCityId(int postalCityId) {
		this.postalCityId = postalCityId;
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
	 * 返回 证件正面
	 * 
	 * @return 证件正面
	*/
	public String getC_idCardFrontImg() {
		return c_idCardFrontImg;
	}
	

	/**
	 * 设置 证件正面
	 * 
	 * @param c_idCardFrontImg
	 *            证件正面
	 */
	public void setC_idCardFrontImg(String c_idCardFrontImg) {
		this.c_idCardFrontImg = c_idCardFrontImg;
	}
	



	/**
	 * 返回 证件手持
	 * 
	 * @return 证件手持
	*/
	public String getC_idCardHandImg() {
		return c_idCardHandImg;
	}
	

	/**
	 * 设置 证件手持
	 * 
	 * @param c_idCardHandImg
	 *            证件手持
	 */
	public void setC_idCardHandImg(String c_idCardHandImg) {
		this.c_idCardHandImg = c_idCardHandImg;
	}

	/**
	 * 返回 证件反面
	 * 
	 * @return 证件反面
	*/
	public String getC_idCardBackImg() {
		return c_idCardBackImg;
	}
	

	/**
	 * 设置 证件反面
	 * 
	 * @param c_idCardBackImg
	 *            证件反面
	 */
	public void setC_idCardBackImg(String c_idCardBackImg) {
		this.c_idCardBackImg = c_idCardBackImg;
	}
	

	/**
	 * 返回 企业代码
	 * 
	 * @return 企业代码  
	*/
	public String getCompCode() {
		return compCode;
	}
	

	/**
	 * 设置企业代码
	 * 
	 * @param compCode
	 * 企业代码           
	 */
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	
	
	/**
	 * 返回 公司实际控制人
	 * @return actualController 
	*/
	public String getActualController() {
		return actualController;
	}
	

	/**
	 * 设置公司实际控制人
	 * @param actualController      
	 */
	public void setActualController(String actualController) {
		this.actualController = actualController;
	}	
	
	/**
	 * 返回 公司实际控制人电话
	 * @return actualController 
	*/
	public String getControllerMobile() {
		return controllerMobile;
	}
	

	/**
	 * 设置公司实际控制人电话
	 * @param actualController      
	 */
	public void setControllerMobile(String controllerMobile) {
		this.controllerMobile = controllerMobile;
	}
	
	/**
	 * 返回 上年纳税凭证路径
	 * @return lastYearTaxProofPath 
	*/
	public String getLastYearTaxProofPath() {
		return lastYearTaxProofPath;
	}
	

	/**
	 * 设置上年纳税凭证路径
	 * @param lastYearTaxProofPath      
	 */
	public void setLastYearTaxProofPath(String lastYearTaxProofPath) {
		this.lastYearTaxProofPath = lastYearTaxProofPath;
	}	
}
