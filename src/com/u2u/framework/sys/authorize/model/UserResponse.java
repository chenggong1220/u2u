package com.u2u.framework.sys.authorize.model;

/**
 * @ClassName: UserResponse <br>
 * @Description: TODO <br>
 * @date 2015-3-9 下午2:27:05 <br>
 * 
 * @author Freud
 */
public class UserResponse {
	private int id;

	private String username;

	private String roles;

	private String realname;
	private String mobile;
	private String dingding;
	private String bu;
	private String province;
	private String city;

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

	public String getBu() {
		return bu;
	}

	public void setBu(String bu) {
		this.bu = bu;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
