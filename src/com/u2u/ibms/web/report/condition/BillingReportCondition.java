package com.u2u.ibms.web.report.condition;

import com.u2u.framework.base.BaseCondition;

public class BillingReportCondition extends BaseCondition {

		private int id;
		private String bankSerialNumber;// 银行流水号
		private String accountNum;
		private String accountName;
		private String cusName;
		private String dealDate;
		private String amount;
		private String billingDate;
		private String financialNum;

		private String statusId;
		private String billingStatusId;
		private String cusNum;//lianxidianhua
		private String contractNo;
		private String billType;

		private String billingMonth;
		private String custType;
		
		public String getCustType() {
			return custType;
		}

		public void setCustType(String custType) {
			this.custType = custType;
		}			
		
		public String getBillingMonth() {
			return billingMonth;
		}

		public void setBillingMonth(String billingMonth) {
			this.billingMonth = billingMonth;
		}			
		
		public String getBillType() {
			return billType;
		}

		public void setBillType(String billType) {
			this.billType = billType;
		}	
		
		public String getContractNo() {
			return contractNo;
		}

		public void setContractNo(String contractNo) {
			this.contractNo = contractNo;
		}	

		public String getCusName() {
			return cusName;
		}

		public void setCusName(String cusName) {
			this.cusName = cusName;
		}

		public String getStatusId() {
			return statusId;
		}

		public void setStatusId(String statusId) {
			this.statusId = statusId;
		}

		public String getBillingStatusId() {
			return billingStatusId;
		}

		public void setBillingStatusId(String billingStatusId) {
			this.billingStatusId = billingStatusId;
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

		public String getBankSerialNumber() {
			return bankSerialNumber;
		}

		public void setBankSerialNumber(String bankSerialNumber) {
			this.bankSerialNumber = bankSerialNumber;
		}

		public String getCusNum() {
			return cusNum;
		}
		

		public void setCusNum(String cusNum) {
			this.cusNum = cusNum;
		}
		

	}
