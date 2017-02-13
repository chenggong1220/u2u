package com.u2u.framework.sys.authorize.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.u2u.framework.base.BaseBean;
import com.u2u.ibms.web.shop.bean.Shop;

/**
 * @ClassName: User <br>
 * @Description: 用户类 <br>
 * @date 2015年1月14日 上午11:24:42 <br>
 * 
 * @author Freud
 */
public class User extends BaseBean implements Serializable {

	/**
	 * <p>
	 * Discription:[字段功能描述]
	 * </p>
	 */
	private static final long serialVersionUID = 2285784752695077307L;

	private int id;
	private String username;
	private String password;
	private String realname;
	private String mobile;
	private String dingding;
	private int buId;
	private int provinceId;
	private int cityId;

	private int shopId;
	private Shop shop;
	private List<Role> roles = new ArrayList<>();

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDingding() {
		return dingding;
	}

	public void setDingding(String dingding) {
		this.dingding = dingding;
	}

	public int getBuId() {
		return buId;
	}

	public void setBuId(int buId) {
		this.buId = buId;
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

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return int id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param id
	 *            The id to set.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return String username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param username
	 *            The username to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return String password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return List<Role> roles.
	 */
	public List<Role> getRoles() {
		if (roles == null) {
			roles = new ArrayList<Role>();
		}
		return roles;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param roles
	 *            The roles to set.
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
