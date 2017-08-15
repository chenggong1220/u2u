package com.u2u.ibms.web.project.condition;

import com.u2u.framework.base.BaseCondition;

public class ProjectCondition extends BaseCondition {

	private String projectId;
	private String result;
	private String creditResult;
	private String operatorId;
	private String orderCode;
	private String operator;
	
	private String customerName;
	private String managerRealname;
	private String IDNo;
	
	public String getIDNo() {
		return IDNo;
	}

	public void setIDNo(String IDNo) {
		this.IDNo = IDNo;
	}	
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}	
	
	public String getManagerRealname() {
		return managerRealname;
	}

	public void setManagerRealname(String managerRealname) {
		this.managerRealname = managerRealname;
	}		
	
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getCreditResult() {
		return creditResult;
	}

	public void setCreditResult(String creditResult) {
		this.creditResult = creditResult;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getOrderCode() {
		return orderCode;
	}
	

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOperator() {
		return operator;
	}
	

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	

}
