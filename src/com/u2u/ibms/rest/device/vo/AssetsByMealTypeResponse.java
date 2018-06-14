package com.u2u.ibms.rest.device.vo;

public class AssetsByMealTypeResponse {
	//编号
	private String id;
	//图片地址
	private String imgPath;
	//设备名字
	private String assetName;
	//套餐类型
	private String mealType;
	//系统
	private String system;
	//描述
	private String decribe;
	//主轴范围
	private String scope;
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
	 * 返回 设备名字
	 * 
	 * @return 设备名字
	*/
	public String getAssetName() {
		return assetName;
	}
	
	/**
	 * 设置 设备名字
	 * 
	 * @param assetName
	 *            设备名字
	 */
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	
	/**
	 * 返回 套餐类型
	 * 
	 * @return 套餐类型
	*/
	public String getMealType() {
		return mealType;
	}
	
	/**
	 * 设置 套餐类型
	 * 
	 * @param mealType
	 *            套餐类型
	 */
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	
	/**
	 * 返回 系统
	 * 
	 * @return 系统
	*/
	public String getSystem() {
		return system;
	}
	
	/**
	 * 设置 系统
	 * 
	 * @param system
	 *            系统
	 */
	public void setSystem(String system) {
		this.system = system;
	}
	
	/**
	 * 返回 描述
	 * 
	 * @return 描述
	*/
	public String getDecribe() {
		return decribe;
	}
	
	/**
	 * 设置 描述
	 * 
	 * @param decribe
	 *            描述
	 */
	public void setDecribe(String decribe) {
		this.decribe = decribe;
	}
	
	/**
	 * 返回 主轴范围
	 * 
	 * @return 主轴范围
	*/
	public String getScope() {
		return scope;
	}
	
	/**
	 * 设置 主轴范围
	 * 
	 * @param scope
	 *            主轴范围
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	
}
