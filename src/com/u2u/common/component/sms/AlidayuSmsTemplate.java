package com.u2u.common.component.sms;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.u2u.framework.util.LogUtil;

public class AlidayuSmsTemplate implements ThridPartySmsTemplate {
	private static Log log = LogFactory.getLog(AlidayuSmsTemplate.class);

	private String url;
	private String appKey;
	private String appSecret;
	private String smsType;
	private Map<String, String> signNames;
	private Map<String, String> templateCodes;
	private TaobaoClient client;

	public AlidayuSmsTemplate() {

	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public void setSignNames(Map<String, String> signNames) {
		this.signNames = signNames;
	}
	
	public void setTemplateCodes(Map<String, String> templateCodes) {
		this.templateCodes = templateCodes;
	}

	public Map<String, String> getTemplateCodes() {
		return templateCodes;
	}

	@Override
	public ThridPartyMessage sendMessage(ThridPartyMessage message) {
		LogUtil.info(log, ">>短信发送 阿里大于发送开始..");
		AlidayuSmsMessage msg = (AlidayuSmsMessage) message;
		try {
			
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setExtend(msg.getExtend());
			req.setSmsType(smsType);
			req.setSmsFreeSignName(signNames.get(msg.getSignName()));
			req.setSmsParamString(msg.getContext());
			req.setRecNum(msg.getPhoneNum());
			req.setSmsTemplateCode(templateCodes.get(msg.getTemplateCode()));

			AlibabaAliqinFcSmsNumSendResponse rsp = getClient().execute(req);

			if (!"0".equals(rsp.getErrorCode())) {
				msg.setErrorCode(rsp.getErrorCode());
				msg.setErrorMessage(rsp.getSubMsg());
			}

			LogUtil.info(log, rsp.getBody());
		} catch (Exception e) {
			LogUtil.error(log, e);

			if (e instanceof com.taobao.api.ApiException) {
				msg.setErrorCode("-1");
				msg.setErrorMessage(((com.taobao.api.ApiException) e)
						.getMessage());
			} else {
				msg.setErrorCode("-2");
				msg.setErrorMessage(e.getLocalizedMessage());
			}
		}finally{
			LogUtil.info(log, ">>短信发送 阿里大于发送结束..");
		}

		return msg;
	}

	public synchronized TaobaoClient getClient() {
		if (client == null) {
			client = new DefaultTaobaoClient(url, appKey, appSecret);
		}
		return client;
	}
}
