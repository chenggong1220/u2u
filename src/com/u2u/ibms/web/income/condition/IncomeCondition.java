package com.u2u.ibms.web.income.condition;

import com.u2u.framework.base.BaseCondition;

public class IncomeCondition extends BaseCondition {

	private String customer;
	private String contractId;
	private String type;//款类型
	private String invoiceStatus;//开票状态
	
	

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getContractId() {
		return contractId;
	}
	

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	

	public String getType() {
		return type;
	}
	

	public void setType(String type) {
		this.type = type;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}
	

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	
	

}
