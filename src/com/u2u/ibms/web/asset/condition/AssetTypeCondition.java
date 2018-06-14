package com.u2u.ibms.web.asset.condition;

import com.u2u.framework.base.BaseCondition;

public class AssetTypeCondition extends BaseCondition {

	private String brandId;
	private String model;

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
