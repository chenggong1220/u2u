package com.u2u.ibms.web.location.vo;

import java.util.List;

public class LocationVo {

	private int id;
	private String text;
	private List<LocationVo> children;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<LocationVo> getChildren() {
		return children;
	}

	public void setChildren(List<LocationVo> children) {
		this.children = children;
	}

}
