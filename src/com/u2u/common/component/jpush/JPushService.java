package com.u2u.common.component.jpush;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;

public interface JPushService {

	/**
	 * 推送消息
	 * 
	 * @param content
	 *            消息内容
	 * @throws APIConnectionException
	 * @throws APIRequestException
	 */
	public void push(String content)
			throws APIConnectionException, APIRequestException;

}
