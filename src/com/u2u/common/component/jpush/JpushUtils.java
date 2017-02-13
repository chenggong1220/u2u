package com.u2u.common.component.jpush;

import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JpushUtils {
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
}
