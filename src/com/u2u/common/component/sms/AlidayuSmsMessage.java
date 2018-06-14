package com.u2u.common.component.sms;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class AlidayuSmsMessage extends ThridPartyMessage {

	private String signName;

	private String templateCode;

	private String extend;

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public void bulidContext(Map<String, String> maps) throws Exception {
		this.context = (new ObjectMapper()).writeValueAsString(maps);
	}

}