package com.u2u.common.component.jpush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;

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
