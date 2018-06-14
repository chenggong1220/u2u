package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

public class MainImg extends BaseBean {
	//编号
	private String id;
	//图片地址
	private String img;
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
	public String getImg() {
		return img;
	}
	
	/**
	 * 设置 图片地址
	 * 
	 * @param img
	 *            图片地址
	 */
	public void setImg(String img) {
		this.img = img;
	}
	
	
}
