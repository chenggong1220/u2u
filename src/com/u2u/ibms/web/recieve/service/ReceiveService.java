package com.u2u.ibms.web.recieve.service;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.BillCheck;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.mapper.BillCheckMapper;
import com.u2u.ibms.common.mapper.OrderMapper;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ReceiveService extends BaseService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private BillCheckMapper billCheckMapper;

	public void insert(String orderId, String leftDeposit, HttpServletRequest request) {
		Order exist = orderMapper.getById(getIntegerCondition(orderId));
		exist.setLeftDeposit(getFloatCondition(leftDeposit));
		exist.setOperateDate(DateUtil.currentTimestamp());
		orderMapper.update(exist);

		@SuppressWarnings("unchecked")
		Enumeration<String> enumerations = request.getParameterNames();

		Set<String> billCheckNames = new HashSet<String>();

		while (enumerations.hasMoreElements()) {
			String current = enumerations.nextElement();
			if (current.startsWith("billcheck_id_")) {
				billCheckNames.add(current.replace("billcheck_id_", ""));
			}
		}

		for (String billCheck : billCheckNames) {
			String id = request.getParameter("billcheck_id_" + billCheck);
			String value = request.getParameter("billcheck_value_" + billCheck);
			BillCheck existBillCheck = billCheckMapper.getById(Integer.valueOf(id));
			Float amount = getFloatCondition(value);
			existBillCheck.setPayAmount(amount);
			if (amount >= existBillCheck.getAllAmount()) {
				existBillCheck.setPayStatus(1);
			}
			billCheckMapper.update(existBillCheck);
		}
	}
}
