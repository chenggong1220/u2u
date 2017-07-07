package com.u2u.ibms.web.billdetail.condition;

import com.u2u.framework.base.BaseCondition;

public class BilldetailCondition extends BaseCondition {

	private String customer;
	private String deviceno;
	private String type;
	private String contractCode;

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}	
	
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getDeviceno() {
		return deviceno;
	}

	public void setDeviceno(String deviceno) {
		this.deviceno = deviceno;
	}

	public String getType() {
		return type;
	}
	

	public void setType(String type) {
		this.type = type;
	}
	

}
