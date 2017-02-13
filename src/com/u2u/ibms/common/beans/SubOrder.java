package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

public class SubOrder extends BaseBean {
	// 编号，自动生成
	private int id;
	// 订单编号
	private int orderId;
	// 套餐编号
	private int comboId;
	private String combo;

	private String brand;
	// 设备类型编号
	private int assetTypeId;
	private String assetType;
	// 租赁物数量
	private int count;
	// 押金
	private float deposit;
	// 净值
	private float amount;
	// 设备ID
	private int assetId;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCombo() {
		return combo;
	}

	public void setCombo(String combo) {
		this.combo = combo;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getComboId() {
		return comboId;
	}

	public void setComboId(int comboId) {
		this.comboId = comboId;
	}

	public float getDeposit() {
		return deposit;
	}

	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * 返回 设备ID
	 * 
	 * @return 设备ID
	 */
	public int getAssetId() {
		return assetId;
	}

	/**
	 * 设置 设备ID
	 * 
	 * @param assetId
	 *            设备ID
	 */
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	/**
	 * 返回 设备类型编号
	 * 
	 * @return 设备类型编号
	 */
	public int getAssetTypeId() {
		return assetTypeId;
	}

	/**
	 * 设置 设备类型编号
	 * 
	 * @param assetTypeId
	 *            设备类型编号
	 */
	public void setAssetTypeId(int assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

}
