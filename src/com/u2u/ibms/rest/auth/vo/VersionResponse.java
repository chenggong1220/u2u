package com.u2u.ibms.rest.auth.vo;

public class VersionResponse {

	private String version;
	// 是否强制 0:不需升级 1：可以升级，可以不升级 2：强制升级
	private String enforce;

	private String updateTime;

	
	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getVersion() {
		return version;
	}
	

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param version
	 *            bare_field_comment
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 返回 是否强制 0:正常升级 1：强制升级
	 * 
	 * @return 是否强制 0:正常升级 1：强制升级
	 */
	public String getEnforce() {
		return enforce;
	}

	/**
	 * 设置 是否强制 0:正常升级 1：强制升级
	 * 
	 * @param enforce
	 *            是否强制 0:正常升级 1：强制升级
	 */
	public void setEnforce(String enforce) {
		this.enforce = enforce;
	}

}
