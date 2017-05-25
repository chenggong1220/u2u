package com.u2u.ibms.web.order.controller;

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
import com.u2u.framework.sys.authorize.beans.Role;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.security.SecurityContextUtil;
import com.u2u.framework.sys.authorize.service.AuthorizeService;
import com.u2u.ibms.common.Constants;
import com.u2u.ibms.common.CustomerName;
import com.u2u.ibms.common.beans.IdentifyCertification;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.RentCompanyInfo;
import com.u2u.ibms.common.beans.RentPersonInfo;
import com.u2u.ibms.common.mapper.IdentifyCertificationMapper;
import com.u2u.ibms.web.order.condition.OrderCondition;
import com.u2u.ibms.web.order.service.OrderService;

@Controller
@RequestMapping("/web/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private AuthorizeService authorizeService;
	
	@Autowired
	private IdentifyCertificationMapper identifyCertificationMapper;	

	@RequestMapping("/index")
	public String index() {
		return "/ibms/order/orderList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			OrderCondition condition) throws Exception {
		User user = authorizeService.getUser(SecurityContextUtil.getUserName());
		for (Role role : user.getRoles()) {
			if (role.getRoleName().equals(Constants.ROLE_CUSTOMER_OPERATOR)) {
				condition.setOperatorId(user.getId() + "");
			}
		}

		List<Order> orders = orderService.getAll(buildRowBounds(baseRequest),
				condition, null);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total",
				orderService.getAll(buildRowBounds(), condition, null).size());
		result.put("rows", orders);
		return result;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/order/orderAdd");
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(Order order, RentCompanyInfo renCompanyInfo,
			RentPersonInfo rentPersonInfo, String startDatetime,
			String endDatetime, HttpServletRequest request) {
		orderService.insert(request, order, renCompanyInfo, rentPersonInfo);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/order/orderEdit");
		mav.addObject("order", orderService.getById(id));
		return mav;
	}

	@RequestMapping("/detail")
	public ModelAndView detail(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/order/orderDetail");
		Order order = orderService.getById(id);
		mav.addObject("order", order);
		
		String name = null;
		if (order.getRentPersonType() == 0) {
			name = order.getRentPersonInfo().getName();
		} else {
			name = order.getRentCompanyInfo().getLegalName();
		}
		IdentifyCertification identifyCertification = identifyCertificationMapper
				.getByNameAndIdcard(name, order.getIdCard());
		mav.addObject("realpicture", identifyCertification);		
		
		return mav;
	}

	@RequestMapping("/verify")
	public ModelAndView verify(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/order/orderVerify");
		mav.addObject("order", orderService.getById(id));
		return mav;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxDone update(Order order) {
		orderService.update(order);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxDone delete(int id) {
		orderService.delete(id);
		return ajaxDoneSuccess(null);
	}
	
	@RequestMapping(value = "/getCustomerName")
	@ResponseBody
	public List<CustomerName> getCustomerName() {
		List<CustomerName> customerNames = orderService.getCustomerNames(new RowBounds());
		return customerNames;
	}
}
