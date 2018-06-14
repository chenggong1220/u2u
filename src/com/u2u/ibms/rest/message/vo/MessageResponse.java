package com.u2u.ibms.rest.message.vo;

public class MessageResponse {
	// 编号
	private String id;
	// 标题
	private String title;
	// 描述
	private String description;
	// 阅读状态
	private String status;
	// 创建时间
	private String createDate;

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
	 * 返回 标题
	 * 
	 * @return 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置 标题
	 * 
	 * @param title
	 *            标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 返回 描述
	 * 
	 * @return 描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置 描述
	 * 
	 * @param description
	 *            描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 返回 阅读状态
	 * 
	 * @return 阅读状态
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置 阅读状态
	 * 
	 * @param status
	 *            阅读状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 返回 创建时间
	 * 
	 * @return 创建时间
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * 设置 创建时间
	 * 
	 * @param createDate
	 *            创建时间
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
