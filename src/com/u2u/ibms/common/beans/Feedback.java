package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

/**
 * Title: [From Dean] <br>
 * Description:[类描述] <br>
 * Date: 2016年11月5日 <br>
 * Copyright (c) 2016 <br>
 * 
 * @author dongming
 */
public class Feedback extends BaseBean {

	private int id;
	private String phone;
	private String feedback;
	private int status;
	private String handlePerson;
	private String handleMethod;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getHandlePerson() {
		return handlePerson;
	}

	public void setHandlePerson(String handlePerson) {
		this.handlePerson = handlePerson;
	}

	public String getHandleMethod() {
		return handleMethod;
	}

	public void setHandleMethod(String handleMethod) {
		this.handleMethod = handleMethod;
	}

}
