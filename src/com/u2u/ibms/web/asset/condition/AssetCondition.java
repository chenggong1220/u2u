package com.u2u.ibms.web.asset.condition;

import com.u2u.framework.base.BaseCondition;

public class AssetCondition extends BaseCondition {

	private String brandId;
	private String assetTypeId;
	private String rent;
	private String provinceId;
	private String cityId;
	
	//start: Add more query conditions, SUNZHE, 2017-06-19 
	private String assetCode;
	private String assetLocation;
	private String assetShopId;
	
	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}	
	
	public String getAssetLocation() {
		return assetLocation;
	}

	public void setAssetLocation(String assetLocation) {
		this.assetLocation = assetLocation;
	}	
	
	public String getAssetShopId() {
		return assetShopId;
	}

	public void setAssetShopId(String assetShopId) {
		this.assetShopId = assetShopId;
	}		
	//End: Add more query conditions, SUNZHE, 2017-06-19 

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getRent() {
		return rent;
	}

	public void setRent(String rent) {
		this.rent = rent;
	}

	public String getAssetTypeId() {
		return assetTypeId;
	}

	public void setAssetTypeId(String assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

}
