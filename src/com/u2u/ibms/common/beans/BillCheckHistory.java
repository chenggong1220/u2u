package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

public class BillCheckHistory extends BaseBean {

	private int id;
	private int billCheckId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBillCheckId() {
		return billCheckId;
	}

	public void setBillCheckId(int billCheckId) {
		this.billCheckId = billCheckId;
	}

}
