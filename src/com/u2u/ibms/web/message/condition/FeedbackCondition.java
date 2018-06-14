package com.u2u.ibms.web.message.condition;

import com.u2u.framework.base.BaseCondition;

public class FeedbackCondition extends BaseCondition {

	private String phone;

	private String status;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
