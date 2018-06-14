package com.u2u.common.component.sms;

public interface SendSMSUtilService {

	/**
	 * 单个短信发送
	 * 
	 * @param phoneNo
	 * @param paramMaps
	 * @throws Exception
	 */
	public String sendSMS(String phoneNo, String content);
}
