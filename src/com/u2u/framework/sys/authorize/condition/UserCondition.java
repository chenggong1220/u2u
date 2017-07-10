package com.u2u.framework.sys.authorize.condition;

import com.u2u.framework.base.BaseCondition;

//Added for more user search conditions, SUNZHE, 2017-06-20
public class UserCondition extends BaseCondition {

	private String username;
	private String userShopId;
	private String userBUId;
	private String userRoleId;
	

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}	
	
	public String getUserShopId() {
		return userShopId;
	}

	public void setUserShopId(String userShopId) {
		this.userShopId = userShopId;
	}	
	
	public String getUserBUId() {
		return userBUId;
	}

	public void setUserBUId(String userBUId) {
		this.userBUId = userBUId;
	}		

	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}


}
