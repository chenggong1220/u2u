package com.u2u.ibms.rest.auth.vo;

public class WalletInfoResponse {
	//保证金
	private String bond;
	//押金
	private String deposit;
	//余额
	private String balance;
	/**
	 * 返回 保证金
	 * 
	 * @return 保证金
	*/
	public String getBond() {
		return bond;
	}
	
	/**
	 * 设置 保证金
	 * 
	 * @param bond
	 *            保证金
	 */
	public void setBond(String bond) {
		this.bond = bond;
	}
	
	/**
	 * 返回 押金
	 * 
	 * @return 押金
	*/
	public String getDeposit() {
		return deposit;
	}
	
	/**
	 * 设置 押金
	 * 
	 * @param deposit
	 *            押金
	 */
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	
	/**
	 * 返回 余额
	 * 
	 * @return 余额
	*/
	public String getBalance() {
		return balance;
	}
	
	/**
	 * 设置 余额
	 * 
	 * @param balance
	 *            余额
	 */
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
}
