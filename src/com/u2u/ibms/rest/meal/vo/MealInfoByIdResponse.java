package com.u2u.ibms.rest.meal.vo;

public class MealInfoByIdResponse {
	//套餐名
	private String mealName;
	//套餐类型
	private String mealType;
	//费率
	private String rate;
	//最低使用时长
	private String miniUserdTime;
	//计费规则
	private String accountRules; 
	//注意事项
	private String attentionItem;
	/**
	 * 返回 套餐名
	 * 
	 * @return 套餐名
	*/
	public String getMealName() {
		return mealName;
	}
	
	/**
	 * 设置 套餐名
	 * 
	 * @param mealName
	 *            套餐名
	 */
	public void setMealName(String mealName) {
		this.mealName = mealName;
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
	 * 返回 费率
	 * 
	 * @return 费率
	*/
	public String getRate() {
		return rate;
	}
	
	/**
	 * 设置 费率
	 * 
	 * @param rate
	 *            费率
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	/**
	 * 返回 最低使用时长
	 * 
	 * @return 最低使用时长
	*/
	public String getMiniUserdTime() {
		return miniUserdTime;
	}
	
	/**
	 * 设置 最低使用时长
	 * 
	 * @param miniUserdTime
	 *            最低使用时长
	 */
	public void setMiniUserdTime(String miniUserdTime) {
		this.miniUserdTime = miniUserdTime;
	}
	
	/**
	 * 返回 计费规则
	 * 
	 * @return 计费规则
	*/
	public String getAccountRules() {
		return accountRules;
	}
	
	/**
	 * 设置 计费规则
	 * 
	 * @param accountRules
	 *            计费规则
	 */
	public void setAccountRules(String accountRules) {
		this.accountRules = accountRules;
	}
	
	/**
	 * 返回 注意事项
	 * 
	 * @return 注意事项
	*/
	public String getAttentionItem() {
		return attentionItem;
	}
	
	/**
	 * 设置 注意事项
	 * 
	 * @param attentionItem
	 *            注意事项
	 */
	public void setAttentionItem(String attentionItem) {
		this.attentionItem = attentionItem;
	}
	
	
}
