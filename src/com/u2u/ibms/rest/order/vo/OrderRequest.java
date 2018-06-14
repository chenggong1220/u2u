package com.u2u.ibms.rest.order.vo;

public class OrderRequest {
	//订单编号
	private int orderId;

	/**
	 * 返回 订单编号
	 * 
	 * @return 订单编号
	*/
	public int getOrderId() {
		return orderId;
	}
	

	/**
	 * 设置 订单编号
	 * 
	 * @param orderId
	 *            订单编号
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	

}
