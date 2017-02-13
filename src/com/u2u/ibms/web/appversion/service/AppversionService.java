package com.u2u.ibms.web.appversion.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.AppVersion;
import com.u2u.ibms.common.mapper.AppVersionMapper;
import com.u2u.ibms.web.appversion.condition.AppversionCondition;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AppversionService extends BaseService {

	@Autowired
	private AppVersionMapper appVersionMapper;

	public List<AppVersion> getAll(RowBounds rb, AppversionCondition condition) {
		return appVersionMapper.getAll(rb,
				getStringCondition(condition.getVersion()));
	}

	public AppVersion getById(int id) {
		return appVersionMapper.getById(id);
	}

	public void insert(AppVersion appVersion) {
		appVersion.setCreateDate(DateUtil.currentTimestamp());
		appVersion.setOperateDate(DateUtil.currentTimestamp());
		appVersionMapper.insert(appVersion);
	}

	public void update(AppVersion appVersion) {
		AppVersion exist = appVersionMapper.getById(appVersion.getId());
		exist.setVersion(appVersion.getVersion());
		exist.setEnforce(appVersion.isEnforce());
		exist.setOperateDate(DateUtil.currentTimestamp());
		appVersionMapper.update(exist);
	}

	public void delete(int id) {
		appVersionMapper.delete(id);
	}
}
