package com.u2u.ibms.rest.auth.vo;

public class VersionRequest {
	private String softName;
	private String curVersion;

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	 */
	public String getCurVersion() {
		return curVersion;
	}

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param curVersion
	 *            bare_field_comment
	 */
	public void setCurVersion(String curVersion) {
		this.curVersion = curVersion;
	}

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	 */
	public String getSoftName() {
		return softName;
	}

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param softName
	 *            bare_field_comment
	 */
	public void setSoftName(String softName) {
		this.softName = softName;
	}

}
