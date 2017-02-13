package com.u2u.ibms.rest.device.vo;

import com.u2u.framework.base.BaseRequest;

public class AssetTypeByRenTypeRequest extends BaseRequest {
	//租赁类型ID
	private int rentTypeId;

	/**
	 * 返回 租赁类型ID
	 * 
	 * @return 租赁类型ID
	*/
	public int getRentTypeId() {
		return rentTypeId;
	}
	

	/**
	 * 设置 租赁类型ID
	 * 
	 * @param rentTypeId
	 *            租赁类型ID
	 */
	public void setRentTypeId(int rentTypeId) {
		this.rentTypeId = rentTypeId;
	}

}
