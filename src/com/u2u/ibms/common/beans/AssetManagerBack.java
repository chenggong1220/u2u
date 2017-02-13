package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

public class AssetManagerBack extends BaseBean {

	private int id;
	private int orderId;
	private int type;
	private int oldAssetId;
	private int newAssetId;
	private int managerRentAssetId;
	private int status;
	private int provinceId;
	private int cityId;
	private String address;

	private Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getOldAssetId() {
		return oldAssetId;
	}

	public void setOldAssetId(int oldAssetId) {
		this.oldAssetId = oldAssetId;
	}

	public int getNewAssetId() {
		return newAssetId;
	}

	public void setNewAssetId(int newAssetId) {
		this.newAssetId = newAssetId;
	}

	public int getManagerRentAssetId() {
		return managerRentAssetId;
	}

	public void setManagerRentAssetId(int managerRentAssetId) {
		this.managerRentAssetId = managerRentAssetId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
