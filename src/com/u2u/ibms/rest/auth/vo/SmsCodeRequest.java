package com.u2u.ibms.rest.auth.vo;

public class SmsCodeRequest {

	// 用户电话
	private String mobile;

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

}
