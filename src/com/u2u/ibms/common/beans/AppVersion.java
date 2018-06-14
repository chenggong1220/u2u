package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

public class AppVersion extends BaseBean {

	private int id;
	private String version;
	private boolean enforce;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isEnforce() {
		return enforce;
	}

	public void setEnforce(boolean enforce) {
		this.enforce = enforce;
	}

}
