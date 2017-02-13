package com.u2u.ibms.rest.bill.vo;

public class BillResponse {
	//账单号
	private String billNo;
	//金额
	private String amount;
	//支付状态(已支付、未支付)
	private String payStatus;
	//账期
	private String accountPeriod;
	//账期状态(已出账、未出账)
	private String accountPeriodStatus;
	//缴费时间
	private String payTime;
	/**
	 * 返回 账单号
	 * 
	 * @return 账单号
	*/
	public String getBillNo() {
		return billNo;
	}
	
	/**
	 * 设置 账单号
	 * 
	 * @param billNo
	 *            账单号
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	/**
	 * 返回 金额
	 * 
	 * @return 金额
	*/
	public String getAmount() {
		return amount;
	}
	
	/**
	 * 设置 金额
	 * 
	 * @param amount
	 *            金额
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	/**
	 * 返回 支付状态(已支付、未支付)
	 * 
	 * @return 支付状态(已支付、未支付)
	*/
	public String getPayStatus() {
		return payStatus;
	}
	
	/**
	 * 设置 支付状态(已支付、未支付)
	 * 
	 * @param payStatus
	 *            支付状态(已支付、未支付)
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	/**
	 * 返回 账期
	 * 
	 * @return 账期
	*/
	public String getAccountPeriod() {
		return accountPeriod;
	}
	
	/**
	 * 设置 账期
	 * 
	 * @param accountPeriod
	 *            账期
	 */
	public void setAccountPeriod(String accountPeriod) {
		this.accountPeriod = accountPeriod;
	}
	
	/**
	 * 返回 账期状态(已出账、未出账)
	 * 
	 * @return 账期状态(已出账、未出账)
	*/
	public String getAccountPeriodStatus() {
		return accountPeriodStatus;
	}
	
	/**
	 * 设置 账期状态(已出账、未出账)
	 * 
	 * @param accountPeriodStatus
	 *            账期状态(已出账、未出账)
	 */
	public void setAccountPeriodStatus(String accountPeriodStatus) {
		this.accountPeriodStatus = accountPeriodStatus;
	}
	
	/**
	 * 返回 缴费时间
	 * 
	 * @return 缴费时间
	*/
	public String getPayTime() {
		return payTime;
	}
	
	/**
	 * 设置 缴费时间
	 * 
	 * @param payTime
	 *            缴费时间
	 */
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	
	
}
