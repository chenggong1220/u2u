 package com.u2u.ibms.web.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.base.BaseRequest;
import com.u2u.framework.beans.AjaxDone;
import com.u2u.framework.exception.ServiceAuthorizeException;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.web.order.condition.OrderCondition;
import com.u2u.ibms.web.order.service.OrderService;

@Controller
@RequestMapping("/web/suborder")
public class SubOrderController extends BaseController {

	@Autowired
	private OrderService orderService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/order/subOrderList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			OrderCondition condition) {
		List<Order> orders = orderService.getAll(buildRowBounds(baseRequest),
				condition, null);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total",
				orderService.getAll(buildRowBounds(), condition, null).size());
		result.put("rows", orders);
		return result;
	}

	@RequestMapping("/seperate")
	public ModelAndView seperate(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/order/subOrderAdd");
		mav.addObject("order", orderService.getById(id));
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(Order order) throws ServiceAuthorizeException {
		orderService.sepepate(order);
		return ajaxDoneSuccess(null);
	}
}
