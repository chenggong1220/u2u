package com.u2u.common.component.sms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u2u.framework.util.LogUtil;

/**
 * Title: [From Dean] <br>
 * Description:[短信发送类] <br>
 * Date: 2016年11月20日 <br>
 * Copyright (c) 2016  <br>
 * 
 * @author dongming
 */
@Service("sendHandleUtilService")
public class SendSMSUtilServiceImpl implements SendSMSUtilService{
	// 日志
	private static Log log = LogFactory.getLog(SendSMSUtilServiceImpl.class);

	// 阿里大鱼
	@Autowired
	private ThridPartySmsTemplate smsTemplate;

	/**
	 * 单个短信发送
	 * 
	 * @param phoneNo
	 * @param paramMaps
	 * @throws Exception
	 */
	public String sendSMS(String phoneNo, String content) {
		log.info(">>短信发送 开始..");
		String errorResult = null;
		try {
			if (phoneNo == null || "".equals(phoneNo)) {
				log.info(">>短信发送 电话号为空,无法发送..");
				errorResult = "短信发送 电话号为空,无法发送..";
			} else {

				AlidayuSmsMessage message = new AlidayuSmsMessage();

				if (errorResult == null) {
					message.setContext(content);
					message.setPhoneNum(phoneNo);
					ThridPartyMessage result = smsTemplate.sendMessage(message);
					// 错误码
					if (result.getErrorCode() != null) {
						errorResult = "错误码：" + result.getErrorCode() + ",错误信息：" + result.getErrorMessage();
					}
				}
			}
		} catch (Exception ex) {
			errorResult = "短信发送 异常 " + ex.getMessage();
			LogUtil.error(log, errorResult);
		} finally {
			log.info(">>短信发送 结束..");
		}
		return errorResult;
	}

}
