package com.u2u.ibms.common.util;

import java.util.Date;

import com.u2u.framework.spring.SpringContextHolder;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.mapper.CommonMapper;

public class CommonIdGenerator {

	public static String generateOrderId() {
		return "O_"
				+ DateUtil
						.date2String(new Date(), "yyyyMMdd" + getIncreases(4));
	}

	public static String generateProjectId() {
		return "P_"
				+ DateUtil
						.date2String(new Date(), "yyyyMMdd" + getIncreases(4));
	}

	public static String generateContractId() {
		return "UFE_"
				+ DateUtil.date2String(new Date(), "yyyy" + getIncreases(6));
	}

	public static String generateAlipayId() {
		return "ALIPAY_"
				+ DateUtil.date2String(new Date(), "yyyyMMdd" + getIncreases(8));
	}

	public static String generatePayId() {
		return "PAY_"
				+ DateUtil.date2String(new Date(), "yyyyMMdd" + getIncreases(8));
	}

	public static void main(String[] args) {
		System.out.println(generateOrderId());
		System.out.println(generateProjectId());
		System.out.println(generateContractId());
	}

	private static String getIncreases(int length) {
		int i = SpringContextHolder.getBean(CommonMapper.class)
				.getNextInteval();
		String ret = i + "";
		if (ret.length() >= length) {
			return ret.substring(ret.length() - length);
		} else {

			while (ret.length() < length) {
				ret = "0" + ret;
			}
			return ret;
		}
	}
}
