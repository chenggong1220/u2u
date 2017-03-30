package com.u2u.ibms.rest.device.vo;

public class AssetTypeResponse {
	// 设备图片
	private String picture;
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
	//设备类型ID
	private String assetTypeId;
	//押金
	private String deposit;
	//保险
	private String insurance;	

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
	

	/**
	 * 返回 设备图片
	 * 
	 * @return 设备图片
	*/
	public String getPicture() {
		return picture;
	}
	
	/**
	 * 设置 设备图片
	 * 
	 * @param picture
	 *            设备图片
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	/**
	 * 返回 品牌分类
	 * 
	 * @return 品牌分类
	*/
	public String getBrand() {
		return brand;
	}
	
	/**
	 * 设置 品牌分类
	 * 
	 * @param brand
	 *            品牌分类
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	/**
	 * 返回 型号
	 * 
	 * @return 型号
	*/
	public String getModel() {
		return model;
	}
	
	/**
	 * 设置 型号
	 * 
	 * @param model
	 *            型号
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * 返回 主电机功率
	 * 
	 * @return 主电机功率
	*/
	public String getMachinePower() {
		return machinePower;
	}
	
	/**
	 * 设置 主电机功率
	 * 
	 * @param machinePower
	 *            主电机功率
	 */
	public void setMachinePower(String machinePower) {
		this.machinePower = machinePower;
	}
	
	/**
	 * 返回 运动方式
	 * 
	 * @return 运动方式
	*/
	public String getMoveMethod() {
		return moveMethod;
	}
	
	/**
	 * 设置 运动方式
	 * 
	 * @param moveMethod
	 *            运动方式
	 */
	public void setMoveMethod(String moveMethod) {
		this.moveMethod = moveMethod;
	}
	
	/**
	 * 返回 加工尺寸
	 * 
	 * @return 加工尺寸
	*/
	public String getFinishSize() {
		return finishSize;
	}
	
	/**
	 * 设置 加工尺寸
	 * 
	 * @param finishSize
	 *            加工尺寸
	 */
	public void setFinishSize(String finishSize) {
		this.finishSize = finishSize;
	}
	
	/**
	 * 返回 主轴转速范围
	 * 
	 * @return 主轴转速范围
	*/
	public String getMainShaftSpeed() {
		return mainShaftSpeed;
	}
	
	/**
	 * 设置 主轴转速范围
	 * 
	 * @param mainShaftSpeed
	 *            主轴转速范围
	 */
	public void setMainShaftSpeed(String mainShaftSpeed) {
		this.mainShaftSpeed = mainShaftSpeed;
	}
	
	/**
	 * 返回 刀具数量
	 * 
	 * @return 刀具数量
	*/
	public String getCutterCount() {
		return cutterCount;
	}
	
	/**
	 * 设置 刀具数量
	 * 
	 * @param cutterCount
	 *            刀具数量
	 */
	public void setCutterCount(String cutterCount) {
		this.cutterCount = cutterCount;
	}
	
	/**
	 * 返回 控制方式
	 * 
	 * @return 控制方式
	*/
	public String getControlMethod() {
		return controlMethod;
	}
	
	/**
	 * 设置 控制方式
	 * 
	 * @param controlMethod
	 *            控制方式
	 */
	public void setControlMethod(String controlMethod) {
		this.controlMethod = controlMethod;
	}
	
	/**
	 * 返回 控制系统
	 * 
	 * @return 控制系统
	*/
	public String getControlSystem() {
		return controlSystem;
	}
	
	/**
	 * 设置 控制系统
	 * 
	 * @param controlSystem
	 *            控制系统
	 */
	public void setControlSystem(String controlSystem) {
		this.controlSystem = controlSystem;
	}
	
	/**
	 * 返回 布局方式
	 * 
	 * @return 布局方式
	*/
	public String getLayout() {
		return layout;
	}
	
	/**
	 * 设置 布局方式
	 * 
	 * @param layout
	 *            布局方式
	 */
	public void setLayout(String layout) {
		this.layout = layout;
	}
	
	/**
	 * 返回 动力类型
	 * 
	 * @return 动力类型
	*/
	public String getDriving() {
		return driving;
	}
	
	/**
	 * 设置 动力类型
	 * 
	 * @param driving
	 *            动力类型
	 */
	public void setDriving(String driving) {
		this.driving = driving;
	}


	/**
	 * 返回 押金
	 * 
	 * @return 押金
	*/
	public String getDeposit() {
		return deposit;
	}
	


	/**
	 * 设置 押金
	 * 
	 * @param deposit
	 *            押金
	 */
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	
	//Start: 增加保险费信息，SUNZHE, 2017-03-26
	/**
	 * 返回 保险费   
	 * @return 保险费
	*/
	public String getInsurance() {
		return insurance;
	}
	
	/**
	 * 设置 保险费
	 * @param deposit   保险费
	 */
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	//Start: 增加保险费信息，SUNZHE, 2017-03-26
}
