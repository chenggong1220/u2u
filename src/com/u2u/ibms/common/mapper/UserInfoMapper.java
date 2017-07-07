package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.MainImg;
import com.u2u.ibms.common.beans.UserInfo;
import com.u2u.ibms.rest.auth.vo.ChangeLoginPwdRequest;
import com.u2u.ibms.rest.auth.vo.ResetLoginPwdRequest;

public interface UserInfoMapper {

	List<UserInfo> getUserInfos(RowBounds rb,
			@Param("username") String username, @Param("mobile") String mobile,
			@Param("provinceId") Integer provinceId,
			@Param("citiId") Integer citiId, 
			@Param("userType") String userType,
			@Param("regStartDate") String regStartDate,
			@Param("regEndDate") String regEndDate);

	UserInfo getById(@Param("id") Integer id);

	void insert(UserInfo userInfo);

	void update(UserInfo userInfo);

	void delete(@Param("id") Integer id);

	UserInfo getUserByUserName(@Param("username") String username);

	UserInfo getUserInfoByMobile(@Param("mobile") String mobile);

	void modifyLoginPwd(ChangeLoginPwdRequest request);

	int resetLoginPwd(ResetLoginPwdRequest request);

	int checkPwd(ChangeLoginPwdRequest request);

}
