package com.u2u.ibms.rest.auth.vo;

public class PersonVerifyInfoResponse {
	//真实姓名
	private String realName;
	//身份证号
	private String idCard;
	//正面照片
	private String front;
	//反面
	private String back;
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
	
}
