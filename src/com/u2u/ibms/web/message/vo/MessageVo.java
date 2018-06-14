package com.u2u.ibms.web.message.vo;

import com.u2u.framework.base.BaseRequest;

public class MessageVo extends BaseRequest {

	private String title;
	private String type;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
