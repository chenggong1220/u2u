package com.u2u.ibms.common.beans;

import java.sql.Timestamp;
import java.util.List;

import com.u2u.framework.base.BaseBean;
import com.u2u.framework.sys.authorize.beans.User;

public class Customer extends BaseBean {

	private String id;	//Rent_Person_Type + ID 

	private int rentPersonId;// 租赁人信息
	private int rentCompanyId;// 租赁公司信息

	private int operatorId;
	private String operator;
	private String operatorMobile;

	//=======================================
	private int custID;
	private String custType;
	private String custName;
	private String custAddress;
	private String legalName;
	private String legalMobile;
    private String emergencyContact;
    private String emergencyMobile;
    private String relation;	
	private RentPersonInfo rentPersonInfo;
	private RentCompanyInfo rentCompanyInfo;	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}	
	
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	
	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	
	public String getLegalMobile() {
		return legalMobile;
	}

	public void setLegalMobile(String legalMobile) {
		this.legalMobile = legalMobile;
	}	
	
	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	
	public String getEmergencyMobile() {
		return emergencyMobile;
	}

	public void setEmergencyMobile(String emergencyMobile) {
		this.emergencyMobile = emergencyMobile;
	}	
	
	
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}	

	public RentPersonInfo getRentPersonInfo() {
		return rentPersonInfo;
	}

	public void setRentPersonInfo(RentPersonInfo rentPersonInfo) {
		this.rentPersonInfo = rentPersonInfo;
	}

	public RentCompanyInfo getRentCompanyInfo() {
		return rentCompanyInfo;
	}

	public void setRentCompanyInfo(RentCompanyInfo rentCompanyInfo) {
		this.rentCompanyInfo = rentCompanyInfo;
	}	
	//------------------------------------	
	

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}


	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorMobile() {
		return operatorMobile;
	}

	public void setOperatorMobile(String operatorMobile) {
		this.operatorMobile = operatorMobile;
	}

	public int getRentPersonId() {
		return rentPersonId;
	}

	public void setRentPersonId(int rentPersonId) {
		this.rentPersonId = rentPersonId;
	}

	public int getRentCompanyId() {
		return rentCompanyId;
	}

	public void setRentCompanyId(int rentCompanyId) {
		this.rentCompanyId = rentCompanyId;
	}



}
