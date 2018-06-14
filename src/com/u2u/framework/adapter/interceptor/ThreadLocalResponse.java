package com.u2u.framework.adapter.interceptor;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: ThreadLocalUtils <br>
 * @Description: ThreadLocal工具类 <br>
 * @date 2015年1月21日 下午5:10:17 <br>
 * 
 * @author Freud
 */
public final class ThreadLocalResponse {

	/**
	 * 当前线程中存储一个HttpServletResponse的对象
	 */
	private static final ThreadLocal<HttpServletResponse> threadContext = new ThreadLocal<HttpServletResponse>();

	/**
	 * 设置response
	 * 
	 * @param response
	 */
	public static void set(HttpServletResponse response) {
		threadContext.set(response);
	}

	/**
	 * 通过response
	 * 
	 * @return
	 */
	public static HttpServletResponse get() {
		return threadContext.get();
	}

	/**
	 * 删除response
	 * 
	 */
	public static void remove() {
		threadContext.remove();
	}


}
