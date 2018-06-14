package com.u2u.ibms.rest.auth.vo;

public class CheckVersionRequest {
	//版本
	private String version;
	//是否强制升级
	private String enforce;

	/**
	 * 返回 版本
	 * 
	 * @return 版本
	*/
	public String getVersion() {
		return version;
	}
	

	/**
	 * 设置 版本
	 * 
	 * @param version
	 *            版本
	 */
	public void setVersion(String version) {
		this.version = version;
	}


	/**
	 * 返回 是否强制升级
	 * 
	 * @return 是否强制升级
	*/
	public String getEnforce() {
		return enforce;
	}
	


	/**
	 * 设置 是否强制升级
	 * 
	 * @param enforce
	 *            是否强制升级
	 */
	public void setEnforce(String enforce) {
		this.enforce = enforce;
	}
	
}
