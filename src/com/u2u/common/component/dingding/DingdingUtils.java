package com.u2u.common.component.dingding;

import java.util.concurrent.Future;

import com.dingtalk.open.client.api.model.corp.MessageBody;
import com.dingtalk.open.client.api.model.corp.MessageType;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.Response;
import com.u2u.common.component.dingding.message.LightAppMessageDelivery;
import com.u2u.common.component.dingding.message.MessageHelper;

/**
 * Title: [From Dean] <br>
 * Description:[钉钉信息发送工具类] <br>
 * Date: 2016年11月19日 <br>
 * Copyright (c) 2016 <br>
 * 
 * @author dongming
 */
public class DingdingUtils {
	
	private static final String DEFAULT_CHARSET = "UTF-8";

	private static final int CONNECT_TIME_OUT = 5000; // 链接超时时间3秒
	
	private static String agentId = "12131159";

	public static void main(String[] args) throws Exception {
		// sendToCorpConversation("deandongming", "测试消息，11请忽略，谢谢1123！");

		sendToCorpConversation1("deandongming", "fdsfsdf！");

	}
    /**
     * <p>Discription:[系统提供发送消息方式.]</p>
     * @param toUsers
     * @param message
     * @throws Exception
     * @author:[dongming]
     * @update:[2016年11月19日] [更改人姓名][变更描述]
     */
	public static void sendToCorpConversation(String toUsers, String message) throws Exception {
		// 获取access token
		String accessToken = AuthHelper.getAccessToken();

		MessageBody.TextBody textBody = new MessageBody.TextBody();
		textBody.setContent(message);

		LightAppMessageDelivery lightAppMessageDelivery = new LightAppMessageDelivery(toUsers, "", agentId);

		lightAppMessageDelivery.withMessage(MessageType.TEXT, textBody);
		MessageHelper.send(accessToken, lightAppMessageDelivery);

	}
    /**
     * <p>Discription:[直接异步post发送消息.]</p>
     * @param toUsers
     * @param message
     * @throws Exception
     * @author:[dongming]
     * @update:[2016年11月19日] [更改人姓名][变更描述]
     */
	public static void sendToCorpConversation1(String toUsers, String message) throws Exception {
		String accessToken = AuthHelper.getAccessToken();

		String URL = "https://oapi.dingtalk.com/message/send?access_token=" + accessToken;

		String Json = "{'touser':'" + toUsers + "','agentid':'" + agentId + "','msgtype':'text','text':{'content':'"
				+ message + "'}}";
		post(URL, Json);

	}

	public static String post(String url, String s) {
		AsyncHttpClient http = new AsyncHttpClient(
				new AsyncHttpClientConfig.Builder().setConnectTimeout(CONNECT_TIME_OUT).build());
		AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);

		builder.setHeader("Content-Type", "application/json");
		builder.setBodyEncoding(DEFAULT_CHARSET);
		builder.setBody(s);
		Future<Response> f = builder.execute();
		String body = null;
		try {
			body = f.get().getResponseBody(DEFAULT_CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
		}
		http.close();
		return body;
	}
}
