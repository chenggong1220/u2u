package com.u2u.ibms.web.asset.condition;

import com.u2u.framework.base.BaseRequest;

public class ComboCondition extends BaseRequest {

	private String rentType;
	private String brandId;
	private String assetTypeId;
	private String name;
	private String starProduct;

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getStarProduct() {
		return starProduct;
	}

	public void setStarProduct(String starProduct) {
		this.starProduct = starProduct;
	}

	public String getRentType() {
		return rentType;
	}

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	public String getAssetTypeId() {
		return assetTypeId;
	}

	public void setAssetTypeId(String assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
