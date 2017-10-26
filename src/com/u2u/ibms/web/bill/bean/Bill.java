package com.u2u.ibms.web.bill.bean;

import com.u2u.framework.base.BaseBean;

public class Bill extends BaseBean {

	private int id;
	private String accountNum;
	private String accountName;
	private String cusName;
	private String cusNum;
	private String dealDate;
	private String amount;
	private String billingDate;
	private boolean billingStatus;
	private boolean status;
	private String financialNum;
	private String bankSerialNumber;//银行流水号

	private int deposit;		//为增加资金类型，改Boolean类型为int型，0：保证金；1：服务费; 2：租金；
	private int contractId;
	private String contractNo;
	private int billCheckId;
	
	private String remark;//备注

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}	
	
	public int getBillCheckId() {
		return billCheckId;
	}

	public void setBillCheckId(int billCheckId) {
		this.billCheckId = billCheckId;
	}

	public boolean isBillingStatus() {
		return billingStatus;
	}

	public void setBillingStatus(boolean billingStatus) {
		this.billingStatus = billingStatus;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
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

	public final String getBillingDate() {
		return billingDate;
	}

	public final void setBillingDate(String billingDate) {
		this.billingDate = billingDate;
	}

	public final String getFinancialNum() {
		return financialNum;
	}

	public final void setFinancialNum(String financialNum) {
		this.financialNum = financialNum;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusNum() {
		return cusNum;
	}

	public void setCusNum(String cusNum) {
		this.cusNum = cusNum;
	}

	public String getBankSerialNumber() {
		return bankSerialNumber;
	}
	
	public void setBankSerialNumber(String bankSerialNumber) {
		this.bankSerialNumber = bankSerialNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
