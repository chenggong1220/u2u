/**
 * 
 */
package com.u2u.framework.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.FastDateFormat;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/**
 * @author yaobo
 * 
 */
public class MobileUtil {
	private static final String privateKey = "fdas34ljfr好sja@#8$%dfkl;js&4*daklfjsdl;akfjsa342";

	// 生成Token
	public static String getToken(String user, String password) {
		Date date = new Date();
		return Hashing.md5().newHasher().putString(user, Charsets.UTF_8)
				.putString(password, Charsets.UTF_8)
				.putString(privateKey, Charsets.UTF_8)
				.putString(getDate(date), Charsets.UTF_8).hash().toString();
	}

	// 密码加密
	public static String getMD5Pass(String pass) {
		return Hashing.md5().newHasher().putString(pass, Charsets.UTF_8).hash()
				.toString();
	}

	public static String getDate(Date now) {
		return FastDateFormat.getInstance("yyyyMMdd").format(now);
	}

	// 验证token
	public static boolean validToken(String token, String user, String password) {
		String confirm = getToken(user, password);
		if (confirm.equals(token)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isPhoneNumberValid(String phoneNumber) {
		boolean isValid = false;
		String expression = "1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}";

		CharSequence inputStr = phoneNumber;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}

	public static void main(String[] args) {
		System.out.println(getToken("yaobo", "123456"));
		System.out.println(getToken("test50", "123456"));
		System.out.println(getToken("tester", "123456"));

		// System.out.println(validToken("3a3df9da441be4a18b968051da3a2762","yaobo","123456"));
		// System.out.println(getMD5Pass("sdfsd"));

		// System.out.println(isPhoneNumberValid("5446"));
	}

}
