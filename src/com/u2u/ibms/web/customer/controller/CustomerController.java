package com.u2u.ibms.web.customer.controller;

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
import com.u2u.ibms.common.beans.Customer;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.RentCompanyInfo;
import com.u2u.ibms.common.beans.RentPersonInfo;
import com.u2u.ibms.web.customer.condition.CustomerCondition;
import com.u2u.ibms.web.customer.service.CustomerService;
import com.u2u.ibms.web.order.condition.OrderCondition;
import com.u2u.ibms.web.order.service.OrderService;


@Controller
@RequestMapping("/web/customer")
public class CustomerController extends BaseController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;	

	@Autowired
	private AuthorizeService authorizeService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/customer/customerList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			CustomerCondition condition) throws Exception {
		User user = authorizeService.getUser(SecurityContextUtil.getUserName());
		for (Role role : user.getRoles()) {
			if (role.getRoleName().equals(Constants.ROLE_CUSTOMER_OPERATOR)) {
				condition.setOperatorId(user.getId() + "");
			}
		}

		List<Customer> customers = customerService.getAll(buildRowBounds(baseRequest),
				condition, null);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total",
				customerService.getAll(buildRowBounds(), condition, null).size());
		result.put("rows", customers);
		return result;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/customer/customerAdd");
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(RentCompanyInfo renCompanyInfo,
			RentPersonInfo rentPersonInfo, String startDatetime,
			String endDatetime, HttpServletRequest request) {
		customerService.insert(request, renCompanyInfo, rentPersonInfo);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/order/orderEdit");
		mav.addObject("customer", orderService.getById(id));
		return mav;
	}

	@RequestMapping("/update")
	public ModelAndView update(String id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/customer/customerUpdate");
		String custType = id.substring(0,1);
		String rowID = id.substring(1);
		mav.addObject("customer", customerService.getById(rowID,custType));
		return mav;
	}

	@RequestMapping("/saveUpdate")
	@ResponseBody
	public AjaxDone saveUpdate(RentCompanyInfo renCompanyInfo,
			RentPersonInfo rentPersonInfo, String startDatetime,
			String endDatetime, HttpServletRequest request) {
		customerService.update(request, renCompanyInfo, rentPersonInfo);
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
