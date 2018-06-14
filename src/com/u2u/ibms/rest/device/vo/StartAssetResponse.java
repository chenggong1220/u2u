package com.u2u.ibms.rest.device.vo;

public class StartAssetResponse {
	// 编号
	private String id;
	// 图片地址
	private String imgPath;
	// 型号
	private String model;
	// 套餐类型
	private String type;
	//套餐类型名称
	private String typeName;
	// 押金
	private String deposit;
	// 控制系统
	private String ControlSystem;
	// 主电机功率
	private String machinePower;
	// 主轴转速范围
	private String mainShaftSpeed;
	
	
	/**
	 * 返回 控制系统
	 * 
	 * @return 控制系统
	*/
	public String getControlSystem() {
		return ControlSystem;
	}
	

	/**
	 * 设置 控制系统
	 * 
	 * @param controlSystem
	 *            控制系统
	 */
	public void setControlSystem(String controlSystem) {
		ControlSystem = controlSystem;
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
	 * 返回 编号
	 * 
	 * @return 编号
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 编号
	 * 
	 * @param id
	 *            编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 返回 图片地址
	 * 
	 * @return 图片地址
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * 设置 图片地址
	 * 
	 * @param imgPath
	 *            图片地址
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
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
	 * 返回 套餐类型
	 * 
	 * @return 套餐类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置 套餐类型
	 * 
	 * @param type
	 *            套餐类型
	 */
	public void setType(String type) {
		this.type = type;
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


	/**
	 * 返回 套餐类型名称
	 * 
	 * @return 套餐类型名称
	*/
	public String getTypeName() {
		return typeName;
	}
	


	/**
	 * 设置 套餐类型名称
	 * 
	 * @param typeName
	 *            套餐类型名称
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	

}
