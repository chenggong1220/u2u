package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

public class IdentifyCertification extends BaseBean {

	private int id;
	private String name;
	private String idCard;
	private String idImg;

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

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIdImg() {
		return idImg;
	}

	public void setIdImg(String idImg) {
		this.idImg = idImg;
	}

}
