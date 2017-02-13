package com.u2u.ibms.web.recieve.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.base.BaseRequest;
import com.u2u.framework.beans.AjaxDone;
import com.u2u.ibms.common.beans.Contract;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.web.billcheck.condition.BillCheckCondition;
import com.u2u.ibms.web.billcheck.service.BillCheckService;
import com.u2u.ibms.web.contract.condition.ContractCondition;
import com.u2u.ibms.web.contract.service.ContractService;
import com.u2u.ibms.web.order.service.OrderService;
import com.u2u.ibms.web.recieve.service.ReceiveService;

@Controller
@RequestMapping("/web/receive")
public class ReceiveController extends BaseController {

	@Autowired
	private ContractService contractService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private BillCheckService billCheckService;

	@Autowired
	private ReceiveService receiveService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/receive/receiveList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest, ContractCondition condition) {
		List<Contract> contracts = contractService.getAll(buildRowBounds(baseRequest), condition);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", contractService.getAll(buildRowBounds(), condition).size());
		result.put("rows", contracts);
		return result;
	}

	@RequestMapping("/checkin")
	public ModelAndView checkin(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/receive/receiveCheckin");
		Contract contract = contractService.getById(id);
		mav.addObject("contract", contract);
		Order order = orderService.getById(contract.getOrderId());
		mav.addObject("order", order);
		BillCheckCondition condition = new BillCheckCondition();
		condition.setOrderId(order.getId() + "");
		mav.addObject("billChecks", billCheckService.getAll(new RowBounds(), condition));
		return mav;
	}

	@RequestMapping("/checkin/save")
	@ResponseBody
	public AjaxDone checkinSave(HttpServletRequest request, String orderId, String leftDeposit) {
		receiveService.insert(orderId, leftDeposit, request);
		return ajaxDoneSuccess(null);
	}

}
