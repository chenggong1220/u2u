package com.u2u.ibms.web.pay.condition;

import com.u2u.framework.base.BaseCondition;

public class PayCondition extends BaseCondition {

	private Integer id;
	private Integer userId;
	private String userName;
	private String accountNum;
	private String accountName;
	private String dealDate;
	private String amount;
	private String payingDate;
	private String financialNum;

	private String statusId;
	private String payingStatusId;

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getPayingStatusId() {
		return payingStatusId;
	}

	public void setPayingStatusId(String payingStatusId) {
		this.payingStatusId = payingStatusId;
	}

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final String getAccountNum() {
		return accountNum;
	}

	public final void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public final String getAccountName() {
		return accountName;
	}

	public final void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public final String getDealDate() {
		return dealDate;
	}

	public final void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}

	public final String getAmount() {
		return amount;
	}

	public final void setAmount(String amount) {
		this.amount = amount;
	}

	public final String getPayingDate() {
		return payingDate;
	}

	public final void setPayingDate(String payingDate) {
		this.payingDate = payingDate;
	}

	public final String getFinancialNum() {
		return financialNum;
	}

	public final void setFinancialNum(String financialNum) {
		this.financialNum = financialNum;
	}

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public Integer getUserId() {
		return userId;
	}
	

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param userId
	 *            bare_field_comment
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	
	

}
