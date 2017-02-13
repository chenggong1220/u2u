package com.u2u.ibms.rest.auth.vo;

import java.util.List;

public class ContactCustomerServiceResponse {
	private String customerServiceMobile;
	private List<QuestionInfo> questionInfo;
	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getCustomerServiceMobile() {
		return customerServiceMobile;
	}
	
	/**
	 * 设置 bare_field_comment
	 * 
	 * @param customerServiceMobile
	 *            bare_field_comment
	 */
	public void setCustomerServiceMobile(String customerServiceMobile) {
		this.customerServiceMobile = customerServiceMobile;
	}
	
	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public List<QuestionInfo> getQuestionInfo() {
		return questionInfo;
	}
	
	/**
	 * 设置 bare_field_comment
	 * 
	 * @param questionInfo
	 *            bare_field_comment
	 */
	public void setQuestionInfo(List<QuestionInfo> questionInfo) {
		this.questionInfo = questionInfo;
	}
	
	
}
