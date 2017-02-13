package com.u2u.common.component.jpush;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.u2u.common.component.sms.SendSMSUtilServiceImpl;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

@Service("jPushService")
public class JPushServiceImpl implements JPushService {
	private static Log log = LogFactory.getLog(SendSMSUtilServiceImpl.class);
	private String masterSecret;
	private String appKey;

	public void setMasterSecret(String masterSecret) {
		System.out.println("setMasterSecret方法执行");
		this.masterSecret = masterSecret;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	
	public String getMasterSecret() {
		return masterSecret;
	}
	
	public String getAppKey() {
		return appKey;
	}
	
	@Override
	public void push(String content)
			throws APIConnectionException, APIRequestException {
		JPushClient jpushClient = new JPushClient(masterSecret, appKey);
		PushPayload pushPayload=	JpushUtils.buildPushObject_android_and_ios(content);
		PushResult result = jpushClient.sendPush(pushPayload);
		log.info("success->msg_id=" + result.msg_id + ",sendno="
				+ result.sendno);
		int resultCode = result.getResponseCode();
		log.info("result=" + resultCode);
		System.out.println("msg_id="+result.msg_id+",sendno="+result.sendno+",resultCode="+resultCode);
	}
}
