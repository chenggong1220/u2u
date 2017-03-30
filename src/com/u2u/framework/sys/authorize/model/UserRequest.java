package com.u2u.framework.sys.authorize.model;

import com.u2u.framework.base.BaseRequest;

/**
 * @ClassName: BookRequest <br>
 * @Description: TODO <br>
 * @date 2015-3-10 下午1:26:54 <br>
 * 
 * @author Freud
 */
public class UserRequest extends BaseRequest {

	private int id;

	private String username;

	private String password;

	private String roles;

	private String realname;
	private String mobile;
	private String dingding;
	private int buId;
	private int provinceId;
	private int cityId;

	private String email;
	private Integer[] cities;
	private int shopId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer[] getCities() {
		return cities;
	}

	public void setCities(Integer[] cities) {
		this.cities = cities;
	}
	
	
	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}
