package com.u2u.ibms.rest.meal.vo;

public class MealInfoByAssetTypeRequest {
	// 设备类型ID
	private String assetTypeId;

	private String rentType;

	public String getRentType() {
		return rentType;
	}

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	/**
	 * 返回 设备类型ID
	 * 
	 * @return 设备类型ID
	 */
	public String getAssetTypeId() {
		return assetTypeId;
	}

	/**
	 * 设置 设备类型ID
	 * 
	 * @param assetTypeId
	 *            设备类型ID
	 */
	public void setAssetTypeId(String assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

}
