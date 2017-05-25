package com.u2u.common.component.jpush;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.u2u.framework.spring.SpringContextHolder;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.web.schedule.ScheduleBillCheck;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JPushUtils {
	private static JPushServiceImpl jPushService;
	private static Log log = LogFactory.getLog(JPushUtils.class);

	// ------------------------------通知----------------------------------------------
	/**
	 * 极光推送(无消息ID版)
	 * 
	 * @param title
	 * @param content
	 * @return
	 */
	public static PushPayload buildPushObject_android_and_ios(String content) {
		return PushPayload.newBuilder().setPlatform(Platform.all()) // 推送平台设置
				.setAudience(Audience.all()) // 推送设备指定
				.setNotification(Notification.newBuilder().setAlert(content)
						.addPlatformNotification(AndroidNotification
								.newBuilder().setAlert(content).build())
						.addPlatformNotification(IosNotification.newBuilder()
								.setAlert(content).incrBadge(1).build())
						.build())
				.setOptions(Options.newBuilder()
						// 此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
						//TODO 生产环境需更改
						.setApnsProduction(false)
						// 此字段是给开发者自己给推送编号，方便推送者分辨推送记录
						.setSendno(1)
						// 此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
						.setTimeToLive(60 * 60 * 24).build())
				.build();
	}
	
	//Start: Send App Message, SUNZHE, 2017-03-25
//	public static void pushMessage(String alert, String content,String audience){
//		发送消息给单个人
//		pushMessage(setAudience(alert, content, audience));
//	}

	public static void pushMessage(String alert,String content,Collection<String> audience){
		pushMessage(setAudience(alert, content, audience));
	}
	
	private static void pushMessage(PushPayload payload){
	    if (jPushService == null){
	    	jPushService =SpringContextHolder.getBean("jPushService");
	    }

		try { 
			//System.out.println("=========== Push Message Sect Info: " + jPushService.getAppKey() + "------" + jPushService.getMasterSecret());
			JPushClient jpushClient = new JPushClient(jPushService.getMasterSecret(), jPushService.getAppKey());
		    PushResult result = jpushClient.sendPush(payload); 
		    log.info("推送消息返回结果： " + result + "(" + DateUtil.currentTimestamp2String(DateUtil.PATTERN_STANDARD) + ")"); 
		} catch (APIConnectionException ce) { 
		    log.error("Connection error. Should retry later. ", ce); 
		    log.error("Sendno: " + payload.getSendno()); 
		} catch (APIRequestException re) { 
			log.error("Error response from JPush server. Should review and fix it. ", re); 
			log.info("HTTP Status: " + re.getStatus()); 
			log.info("Error Code: " + re.getErrorCode()); 
			log.info("Error Message: " + re.getErrorMessage()); 
			log.info("Msg ID: " + re.getMsgId()); 
			log.error("Sendno: " + payload.getSendno()); 			
		} 		
	}
	
	/*
	//发送消息给单个人
	private static PushPayload setAudience(String alert, String content, String regID) { 
		//System.out.println("=========== Audience: " + regID);
		return PushPayload.newBuilder() 
			.setPlatform(Platform.all()) 
			.setAudience(Audience.registrationId(regID)) 
			.setNotification(Notification.alert(content)) 
			.build(); 
	}
	*/
	
	//.setMessage(Message.content(content))
	private static PushPayload setAudience(String alert, String content, Collection<String> regIDs) { 
		return PushPayload.newBuilder() 
			.setPlatform(Platform.all()) 
			.setAudience(Audience.registrationId(regIDs)) 
			.setNotification(Notification.alert(content)) 
			.build(); 
	}
	//End: 
}
