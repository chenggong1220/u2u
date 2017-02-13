package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

/**
 * 设备类型
 * 
 * @author Freud
 * 
 */
public class AssetType extends BaseBean {

	// 设备ID
	public int id;
	// // 租赁类型
	// private int rentType;
	private int brandId;
	// 品牌分类
	private String brand;
	// 型号
	private String model;
	// 主电机功率
	private String machinePower;
	// 运动方式
	private String moveMethod;
	// 加工尺寸
	private String finishSize;
	// 主轴转速范围
	private String mainShaftSpeed;
	// 刀具数量
	private String cutterCount;
	// 控制方式
	private String controlMethod;
	// 控制系统
	private String controlSystem;
	// 布局方式
	private String layout;
	// 动力类型
	private String driving;
	// 设备图片
	private String picture;

	// 押金
	private float deposit;
	// 净值
	private float amount;
	
	// 保险金 add by dean
	private float insuranceAmount;

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getDeposit() {
		return deposit;
	}

	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}

	// public int getRentType() {
	// return rentType;
	// }
	//
	// public void setRentType(int rentType) {
	// this.rentType = rentType;
	// }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMachinePower() {
		return machinePower;
	}

	public void setMachinePower(String machinePower) {
		this.machinePower = machinePower;
	}

	public String getMoveMethod() {
		return moveMethod;
	}

	public void setMoveMethod(String moveMethod) {
		this.moveMethod = moveMethod;
	}

	public String getFinishSize() {
		return finishSize;
	}

	public void setFinishSize(String finishSize) {
		this.finishSize = finishSize;
	}

	public String getMainShaftSpeed() {
		return mainShaftSpeed;
	}

	public void setMainShaftSpeed(String mainShaftSpeed) {
		this.mainShaftSpeed = mainShaftSpeed;
	}

	public String getCutterCount() {
		return cutterCount;
	}

	public void setCutterCount(String cutterCount) {
		this.cutterCount = cutterCount;
	}

	public String getControlMethod() {
		return controlMethod;
	}

	public void setControlMethod(String controlMethod) {
		this.controlMethod = controlMethod;
	}

	public String getControlSystem() {
		return controlSystem;
	}

	public void setControlSystem(String controlSystem) {
		this.controlSystem = controlSystem;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getDriving() {
		return driving;
	}

	public void setDriving(String driving) {
		this.driving = driving;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public float getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(float insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}
	
	

}
