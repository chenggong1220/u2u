package com.u2u.ibms.common;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u2u.common.component.dingding.DingDingAuthUtil;
import com.u2u.framework.base.BaseService;
import com.u2u.framework.sys.authorize.beans.Role;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.beans.UserRole;
import com.u2u.framework.sys.authorize.mapper.AuthorizeMapper;
import com.u2u.framework.util.DateUtil;
import com.u2u.framework.util.StringUtils;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.location.City;
import com.u2u.ibms.common.mapper.LocationMapper;
import com.u2u.ibms.common.mapper.OrderMapper;
import com.u2u.ibms.web.order.service.OrderService;

@Service("AutoSubOrderService")
public class AutoSubOrderService extends BaseService {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private AuthorizeMapper authorizeMapper;

	@Autowired
	private LocationMapper locationMapper;

	public boolean seperate(Order order) {
		if (order != null) {
			Role role = authorizeMapper.getRoleByName("客户专员");
			if (role != null) {
				List<UserRole> userRoles = authorizeMapper.getUserRoleRelationsByRoleIds(Arrays
						.asList(new Integer[] { role.getId() }));

				for (UserRole userRole : userRoles) {
					User user = authorizeMapper.getUserById(userRole.getUserId());
					if (StringUtils.isNotEmpty(user.getCities())) {
						List<String> cities = Arrays.asList(user.getCities().split(","));

						City city = locationMapper.getCityById(order.getCityId());
						if (CollectionUtils.isNotEmpty(cities) && cities.contains(String.valueOf(city.getUnionCode()))) {
							order.setOperatorId(user.getId());
							order.setOperator(user.getRealname());
							// order.setOperatorMobile(user.getMobile());
							order.setManagerId(user.getId());

							User manager = getCustomerManager();
							if (manager != null) {
								order.setManagerId(manager.getId());
							}

							order.setAssigned(true);
							order.setOperateDate(DateUtil.currentTimestamp());
							orderMapper.update(order);

							DingDingAuthUtil.send(user);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public User getCustomerManager() {
		Role role = authorizeMapper.getRoleByName("客户经理");
		if (role != null) {
			List<UserRole> userRoles = authorizeMapper.getUserRoleRelationsByRoleIds(Arrays.asList(new Integer[] { role
					.getId() }));
			if (CollectionUtils.isNotEmpty(userRoles)) {
				return authorizeMapper.getUserById(userRoles.get(0).getUserId());
			}
		}
		return null;
	}
}
