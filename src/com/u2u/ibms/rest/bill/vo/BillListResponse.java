package com.u2u.ibms.rest.bill.vo;

public class BillListResponse {
	//订单编号
	private String orderNo;
	//当期累计使用时长(小时)
	private String totalUsedTime;
	//当期总费用(元)
	private String currentTotalCost ;

	/**
	 * 返回 订单编号
	 * 
	 * @return 订单编号
	*/
	public String getOrderNo() {
		return orderNo;
	}
	

	/**
	 * 设置 订单编号
	 * 
	 * @param orderNo
	 *            订单编号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	

	/**
	 * 返回 当期累计使用时长(小时)
	 * 
	 * @return 当期累计使用时长(小时)
	*/
	public String getTotalUsedTime() {
		return totalUsedTime;
	}
	
	/**
	 * 设置 当期累计使用时长(小时)
	 * 
	 * @param totalUsedTime
	 *            当期累计使用时长(小时)
	 */
	public void setTotalUsedTime(String totalUsedTime) {
		this.totalUsedTime = totalUsedTime;
	}
	
	/**
	 * 返回 当期总费用(元)
	 * 
	 * @return 当期总费用(元)
	*/
	public String getCurrentTotalCost() {
		return currentTotalCost;
	}
	
	/**
	 * 设置 当期总费用(元)
	 * 
	 * @param currentTotalCost
	 *            当期总费用(元)
	 */
	public void setCurrentTotalCost(String currentTotalCost) {
		this.currentTotalCost = currentTotalCost;
	}
}
