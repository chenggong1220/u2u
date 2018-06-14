package com.u2u.ibms.rest.bill.vo;

import com.u2u.framework.base.BaseRequest;

public class HistoryBillRequest extends BaseRequest {
	private String orderId;

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getOrderId() {
		return orderId;
	}
	

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param orderId
	 *            bare_field_comment
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	

	
	
}
