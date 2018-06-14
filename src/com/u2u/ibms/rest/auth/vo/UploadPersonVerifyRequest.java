package com.u2u.ibms.rest.auth.vo;

public class UploadPersonVerifyRequest {
	//编号
	private int id;
	//用户名
	private String userName;
	//真实姓名
	private String realName;
	//身份证号
	private String idCard;
	//正面照片
	private String front;
	//反面
	private String back;
	//营业执照
	private String certificationPicture;

	/**
	 * 返回 营业执照
	 * 
	 * @return 营业执照
	*/
	public String getCertificationPicture() {
		return certificationPicture;
	}
	

	/**
	 * 设置 营业执照
	 * 
	 * @param certificationPicture
	 *            营业执照
	 */
	public void setCertificationPicture(String certificationPicture) {
		this.certificationPicture = certificationPicture;
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
	 * 返回 身份证号
	 * 
	 * @return 身份证号
	*/
	public String getIdCard() {
		return idCard;
	}
	

	/**
	 * 设置 身份证号
	 * 
	 * @param idCard
	 *            身份证号
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	

	/**
	 * 返回 正面照片
	 * 
	 * @return 正面照片
	*/
	public String getFront() {
		return front;
	}
	
	/**
	 * 设置 正面照片
	 * 
	 * @param front
	 *            正面照片
	 */
	public void setFront(String front) {
		this.front = front;
	}
	
	/**
	 * 返回 反面
	 * 
	 * @return 反面
	*/
	public String getBack() {
		return back;
	}
	
	/**
	 * 设置 反面
	 * 
	 * @param back
	 *            反面
	 */
	public void setBack(String back) {
		this.back = back;
	}

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
	 * 返回 编号
	 * 
	 * @return 编号
	*/
	public int getId() {
		return id;
	}
	

	/**
	 * 设置 编号
	 * 
	 * @param id
	 *            编号
	 */
	public void setId(int id) {
		this.id = id;
	}
	

}
