package com.u2u.ibms.web.contract.condition;

import com.u2u.framework.base.BaseCondition;

public class ContractCondition extends BaseCondition {

	private String contractId;
	private String checkinStatus;
	private String signoffStatus;
	private String sendStatus;
	private String backStatus;
	private String status;
	private String contractType;
	private String orderPerson;//收货人
	private String projectId;//项目编号
	private String rentType;//租赁类型
	private String provinceId;
	private String assetProvinceId;//租赁物所在地省
	
	private String operatorId;		//合同查看权限控制，by SUNZHE, 2017-02-12
	
	//Start: 客户专员查看合同权限控制，by SUNZHE, 2017-02-12
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	//End: 客户专员查看合同权限控制，by SUNZHE, 2017-02-12
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCheckinStatus() {
		return checkinStatus;
	}

	public void setCheckinStatus(String checkinStatus) {
		this.checkinStatus = checkinStatus;
	}

	public String getSignoffStatus() {
		return signoffStatus;
	}

	public void setSignoffStatus(String signoffStatus) {
		this.signoffStatus = signoffStatus;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getBackStatus() {
		return backStatus;
	}

	public void setBackStatus(String backStatus) {
		this.backStatus = backStatus;
	}

	public String getContractType() {
		return contractType;
	}
	

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getOrderPerson() {
		return orderPerson;
	}
	

	public void setOrderPerson(String orderPerson) {
		this.orderPerson = orderPerson;
	}

	public String getProjectId() {
		return projectId;
	}
	

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getRentType() {
		return rentType;
	}
	

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	public String getProvinceId() {
		return provinceId;
	}
	

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getAssetProvinceId() {
		return assetProvinceId;
	}
	

	public void setAssetProvinceId(String assetProvinceId) {
		this.assetProvinceId = assetProvinceId;
	}
	
	
	
	
	
	

}
