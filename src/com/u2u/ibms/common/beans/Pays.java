package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

public class Pays extends BaseBean {

	private int id;
	private String payId;
	private String userName;
	private String paySourceStr;
	private int paySource;
	private int type;
	private int orderId;
	private int billCheckId;
	private int userId;
	private float amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public int getPaySource() {
		return paySource;
	}

	public void setPaySource(int paySource) {
		this.paySource = paySource;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getBillCheckId() {
		return billCheckId;
	}

	public void setBillCheckId(int billCheckId) {
		this.billCheckId = billCheckId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getUserName() {
		return userName;
	}
	

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param userName
	 *            bare_field_comment
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getPaySourceStr() {
		return paySourceStr;
	}
	

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param paySourceStr
	 *            bare_field_comment
	 */
	public void setPaySourceStr(String paySourceStr) {
		this.paySourceStr = paySourceStr;
	}

}
