package com.u2u.ibms.web.order.condition;

import com.u2u.framework.base.BaseCondition;

public class OrderCondition extends BaseCondition {

	private String code;
	private String createSource;
	private String operatorId;
	private String delStatus;
	private String status;
	private String customerName;
	private String rentType;
	private String operator;
	private String assetProvinceId;
	
	
	public String getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreateSource() {
		return createSource;
	}

	public void setCreateSource(String createSource) {
		this.createSource = createSource;
	}

	public String getStatus() {
		return status;
	}
	

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomerName() {
		return customerName;
	}
	

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getRentType() {
		return rentType;
	}
	

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	public String getOperator() {
		return operator;
	}
	

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getAssetProvinceId() {
		return assetProvinceId;
	}
	

	public void setAssetProvinceId(String assetProvinceId) {
		this.assetProvinceId = assetProvinceId;
	}
	
	
	
	
	
	

}
