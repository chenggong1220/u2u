package com.u2u.ibms.web.shop.bean;

import com.u2u.framework.base.BaseBean;
import com.u2u.framework.sys.authorize.beans.Bu;

public class Shop extends BaseBean {
	//
	private int id;
	// buid
	private int bid;
	// 名称
	private String name;
	// 地址
	private String address;
	// 联系人
	private String contactName;
	// 联系地址
	private String contactAddress;
	// 电子邮件
	private String email;
	// 备注
	private String note;
	private Bu bu;

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Bu getBu() {
		return bu;
	}

	public void setBu(Bu bu) {
		this.bu = bu;
	}

}
