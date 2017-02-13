package com.u2u.ibms.web.userinfo.vo;

import com.u2u.framework.base.BaseRequest;

public class UserInfoVo extends BaseRequest {

	private String username;
	private String mobile;

	private Integer provinceId;
	private Integer cityId;
	
	private String userType;
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getUserType() {
		return userType;
	}
	

	public void setUserType(String userType) {
		this.userType = userType;
	}
	

}
