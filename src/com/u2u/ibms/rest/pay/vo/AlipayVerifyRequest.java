package com.u2u.ibms.rest.pay.vo;

public class AlipayVerifyRequest {

	private String username;
	// 商户网站唯一订单号
	private String out_trade_no;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

}
