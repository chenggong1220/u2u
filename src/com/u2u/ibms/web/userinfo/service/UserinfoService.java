package com.u2u.ibms.web.userinfo.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.UserInfo;
import com.u2u.ibms.common.mapper.LocationMapper;
import com.u2u.ibms.common.mapper.UserInfoMapper;
import com.u2u.ibms.web.userinfo.vo.UserInfoVo;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserinfoService extends BaseService {

	@Autowired
	private UserInfoMapper userinfoMapper;

	@Autowired
	private LocationMapper locationMapper;

	public List<UserInfo> getAll(RowBounds rb, UserInfoVo request) {
		//System.out.println("Request.toString(): " + request.toString());
		List<UserInfo> users = userinfoMapper.getUserInfos(rb,
				getStringCondition(request.getUsername()),
				getStringCondition(request.getMobile()),
				request.getProvinceId(), request.getCityId(),
				getStringCondition(request.getUserType()),
				request.getRegStartDate(),
				request.getRegEndDate());

		for (UserInfo user : users) {
			user.setProvince(locationMapper.getProvinceById(
					user.getProvinceId()).getName());
			user.setCity(locationMapper.getCityById(user.getCityId()).getName());
		}
		return users;
	}

	public UserInfo getById(int id) {
		UserInfo userInfo = userinfoMapper.getById(id);
		userInfo.setProvince(locationMapper.getProvinceById(
				userInfo.getProvinceId()).getName());
		userInfo.setCity(locationMapper.getCityById(userInfo.getCityId())
				.getName());
		return userInfo;
	}

	public void update(UserInfo userInfo) {
		UserInfo exist = userinfoMapper.getById(userInfo.getId());
		exist.setRealnameVerify(userInfo.getRealnameVerify());
		exist.setOperateDate(DateUtil.currentTimestamp());
		userinfoMapper.update(exist);
	}

	public void delete(int id) {
		userinfoMapper.delete(id);
	}
}
