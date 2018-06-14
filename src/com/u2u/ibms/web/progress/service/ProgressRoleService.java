package com.u2u.ibms.web.progress.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.beans.UserRole;
import com.u2u.framework.sys.authorize.mapper.AuthorizeMapper;
import com.u2u.ibms.web.progress.bean.ProgressRole;
import com.u2u.ibms.web.progress.condition.ProgressCondition;
import com.u2u.ibms.web.progress.mapper.ProgressRoleMapper;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ProgressRoleService extends BaseService {
	@Autowired
	private AuthorizeMapper authorizeMapper;
	@Autowired
	private ProgressRoleMapper progressRoleMapper;

	public void insert(ProgressRole progressRole) {
		progressRoleMapper.insert(progressRole);
	}

	public void insert(ProgressCondition condition) {
		if (condition.getRids() == null) {
			return;
		}
		for (String rid : condition.getRids().split(",")) {
			if (StringUtils.isNotEmpty(rid)) {

				try {
					ProgressRole pr = new ProgressRole();
					pr.setPid(Integer.parseInt(condition.getPid()));
					pr.setRid(Integer.parseInt(rid));
					progressRoleMapper.insert(pr);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void update(ProgressRole progressRole) {
		progressRoleMapper.update(progressRole);
	}

	public List<ProgressRole> getById(int pid) {
		return progressRoleMapper.getById(pid);
	};

	public List<User> getUserById(int pid) {
		List<ProgressRole> prs = getById(pid);
		List<User> users = new ArrayList<>();
		List<Integer> roleIds = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(prs)) {
			for (ProgressRole progressRole : prs) {
				roleIds.add(progressRole.getRid());
			}
			if (CollectionUtils.isNotEmpty(roleIds)) {
				List<UserRole> userRoles = authorizeMapper.getUserRoleRelationsByRoleIds(roleIds);
				if(CollectionUtils.isNotEmpty(userRoles)){
					for (UserRole userRole : userRoles) {
						
						users.add(authorizeMapper.getUserById(userRole.getUserId()));
					}
				}
			}
		}
		return users;
	};

	public void delete(String id) {

		progressRoleMapper.delete(Integer.parseInt(id));
	}
}
