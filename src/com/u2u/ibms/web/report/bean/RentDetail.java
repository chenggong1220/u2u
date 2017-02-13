package com.u2u.ibms.web.report.bean;

import com.u2u.framework.base.BaseBean;

public class RentDetail extends BaseBean {
	private int id;
	private String contractNo;
	private String projectNo;
	private String projectStatus;
	private String startDate;
	private String rentDate;
	private String shouldRev;
	private String hasRev;
	private String deposit;
	private String shop;
	private String manager;
	private String username;
	private String mobile;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getRentDate() {
		return rentDate;
	}

	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}

	public String getShouldRev() {
		return shouldRev;
	}

	public void setShouldRev(String shouldRev) {
		this.shouldRev = shouldRev;
	}

	public String getHasRev() {
		return hasRev;
	}

	public void setHasRev(String hasRev) {
		this.hasRev = hasRev;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

}
