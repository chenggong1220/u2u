package com.u2u.ibms.rest.pay.vo;

public class AlipayRequest {

	private int type; // 0会员费，1为押金，2为租金
	private float amount;
	private String username;
	private int billCheckId;// 账单ID

	public int getBillCheckId() {
		return billCheckId;
	}

	public void setBillCheckId(int billCheckId) {
		this.billCheckId = billCheckId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
