package com.u2u.ibms.web.billdetail.bean;

import com.u2u.framework.base.BaseBean;

public class Billdetail extends BaseBean {

	private int id;
	// 客户
	private String customer;
	// 客户联系方式
	private String contact;
	// 合同编号
	private String contract;
	// 设备序列号
	private String deviceno;
	// 型号
	private String type;
	// 日期
	private String devicedate;

	// 开机时长（小时）
	private String runningtime;
	// 计费时长（小时）
	private String chargingtime;
	// 不计费时长（小时）
	private String nochargingtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
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

	public String getDevicedate() {
		return devicedate;
	}

	public void setDevicedate(String devicedate) {
		this.devicedate = devicedate;
	}

	public String getRunningtime() {
		return runningtime;
	}

	public void setRunningtime(String runningtime) {
		this.runningtime = runningtime;
	}

	public String getChargingtime() {
		return chargingtime;
	}

	public void setChargingtime(String chargingtime) {
		this.chargingtime = chargingtime;
	}

	public String getNochargingtime() {
		return nochargingtime;
	}

	public void setNochargingtime(String nochargingtime) {
		this.nochargingtime = nochargingtime;
	}

}
