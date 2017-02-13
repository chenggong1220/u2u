package com.u2u.ibms.rest.order.vo;

public class OrderListResponse {
	//订单ID
	private String orderId;
	// 订单编码
	private String orderNo;
	// 设备信息
	private String assetInfo;
	// 租赁类型
	private String leaseType;
	// 订单创建时间
	private String orderCreateTime;
	// 订单状态
	private String orderStatus;
	/**
	 * 返回 订单ID
	 * 
	 * @return 订单ID
	*/
	public String getOrderId() {
		return orderId;
	}
	
	/**
	 * 设置 订单ID
	 * 
	 * @param orderId
	 *            订单ID
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * 返回 设备信息
	 * 
	 * @return 设备信息
	*/
	public String getAssetInfo() {
		return assetInfo;
	}
	
	/**
	 * 设置 设备信息
	 * 
	 * @param assetInfo
	 *            设备信息
	 */
	public void setAssetInfo(String assetInfo) {
		this.assetInfo = assetInfo;
	}
	
	/**
	 * 返回 租赁类型
	 * 
	 * @return 租赁类型
	*/
	public String getLeaseType() {
		return leaseType;
	}
	
	/**
	 * 设置 租赁类型
	 * 
	 * @param leaseType
	 *            租赁类型
	 */
	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
	}
	
	

	/**
	 * 返回 订单创建时间
	 * 
	 * @return 订单创建时间
	*/
	public String getOrderCreateTime() {
		return orderCreateTime;
	}
	

	/**
	 * 设置 订单创建时间
	 * 
	 * @param orderCreateTime
	 *            订单创建时间
	 */
	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}
	

	/**
	 * 返回 订单状态
	 * 
	 * @return 订单状态
	*/
	public String getOrderStatus() {
		return orderStatus;
	}
	
	/**
	 * 设置 订单状态
	 * 
	 * @param orderStatus
	 *            订单状态
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * 返回 订单编码
	 * 
	 * @return 订单编码
	*/
	public String getOrderNo() {
		return orderNo;
	}
	

	/**
	 * 设置 订单编码
	 * 
	 * @param orderNo
	 *            订单编码
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
