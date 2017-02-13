package com.u2u.common.component.dingding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.u2u.framework.spring.SpringContextHolder;
import com.u2u.framework.sys.authorize.beans.Role;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.beans.UserRole;
import com.u2u.framework.sys.authorize.mapper.AuthorizeMapper;
import com.u2u.ibms.web.progress.bean.ProgressRole;
import com.u2u.ibms.web.progress.mapper.ProgressRoleMapper;

public class DingDingAuthUtil {

	public static void send(Integer progress) {
		send(progress, "您有新订单需要处理!");
	}

	public static void send(User user, String message) {
		try {
			if (user != null && StringUtils.isNotEmpty(user.getDingding())) {
				DingdingUtils.sendToCorpConversation(user.getDingding(),
						message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void send(User user) {
		try {
			if (user != null && StringUtils.isNotEmpty(user.getDingding())) {
				DingdingUtils.sendToCorpConversation(user.getDingding(),
						"您有新订单需要处理!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void send(Integer progress, String message) {
		try {
			ProgressRoleMapper progressRoleMapper = SpringContextHolder
					.getBean(ProgressRoleMapper.class);
			List<ProgressRole> progressRoles = progressRoleMapper
					.getById(progress);

			Map<Integer, User> users = new HashMap<Integer, User>();

			for (ProgressRole progressRole : progressRoles) {
				AuthorizeMapper authorizeMapper = SpringContextHolder
						.getBean(AuthorizeMapper.class);
				List<Role> roles = authorizeMapper.getRoleById(progressRole
						.getRid());

				if (CollectionUtils.isNotEmpty(roles)) {
					List<UserRole> userRoles = authorizeMapper
							.getUserRoleRelationsByRoleIds(Arrays
									.asList(new Integer[] { roles.get(0)
											.getId() }));
					for (UserRole userRole : userRoles) {
						User user = authorizeMapper.getUserById(userRole
								.getId());

						User exist = users.get(user.getId());
						if (exist == null) {
							users.put(user.getId(), user);
						}
					}
				}

				// sendByRole(progressRole.getRid(), message);
			}

			for (Entry<Integer, User> user : users.entrySet()) {
				try {
					if (StringUtils.isNotEmpty(user.getValue().getDingding())) {
						DingdingUtils.sendToCorpConversation(user.getValue()
								.getDingding(), message);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
