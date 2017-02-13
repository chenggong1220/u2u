package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

/**
 * 套餐
 * 
 * @author Freud
 * 
 */
public class Combo extends BaseBean {

	// 列表[套餐名，套餐类型，费率，最低使用时长，计费规则，注意事项]
	private int id;
	private int rentType;
	private String name;
	private int type;
	private float amount;
	private int minimumUseTime;
	private String amountRule;
	private String notice;
	// 是否明星产品
	private Boolean starProduct;
	private int assetTypeId;
	private AssetType assetType;

	public Boolean getStarProduct() {
		return starProduct;
	}

	public void setStarProduct(Boolean starProduct) {
		this.starProduct = starProduct;
	}

	public int getRentType() {
		return rentType;
	}

	public void setRentType(int rentType) {
		this.rentType = rentType;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getMinimumUseTime() {
		return minimumUseTime;
	}

	public void setMinimumUseTime(int minimumUseTime) {
		this.minimumUseTime = minimumUseTime;
	}

	public String getAmountRule() {
		return amountRule;
	}

	public void setAmountRule(String amountRule) {
		this.amountRule = amountRule;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public int getAssetTypeId() {
		return assetTypeId;
	}

	public void setAssetTypeId(int assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

}
