package com.u2u.ibms.web.billcheck.condition;

import com.u2u.framework.base.BaseCondition;

public class BillCheckCondition extends BaseCondition {

	private String orderId;

	private String contractId;
	
	private String terms;//账单总期数
	private String payStatus;//付款状态
	
	
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getTerms() {
		return terms;
	}
	

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param terms
	 *            bare_field_comment
	 */
	public void setTerms(String terms) {
		this.terms = terms;
	}

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getPayStatus() {
		return payStatus;
	}
	

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param payStatus
	 *            bare_field_comment
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	

}
