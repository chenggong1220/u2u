package com.u2u.common.component.identificationcard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Title: [From Dean] <br>
 * Description:[钉钉信息发送工具类] <br>
 * Date: 2016年11月19日 <br>
 * Copyright (c) 2016 <br>
 * 
 * @author dongming
 */
public class IdentityUtils {

	private static final String key = "c9266e9ff45cc84b4536bb86e5556793";

	public static void main(String[] args) throws Exception {
		// sendToCorpConversation("deandongming", "测试消息，11请忽略，谢谢1123！");

		String result = identityCard("董明", "210311198303180012");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		IdentityResponse response = objectMapper.readValue(result,
				IdentityResponse.class);
		System.out.println(response.getResult().getRes());

	}

	/**
	 * <p>
	 * Discription:[验证身份证头像 .]
	 * </p>
	 * 
	 * @param toUsers
	 * @param message
	 * @throws Exception
	 * @author:[dongming]
	 * @update:[2016年11月19日] [更改人姓名][变更描述]
	 */
	public static String identityCard(String realname, String idcard)
			throws Exception {

		String URL = "http://v.juhe.cn/idcardhead/query?key=" + key;

		String param = "realname=" + realname + "&idcard=" + idcard;
		// post(URL, Json);
		String sr = IdentityUtils.sendPost(URL, param);

		return sr;

	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
