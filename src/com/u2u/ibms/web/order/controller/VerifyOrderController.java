package com.u2u.ibms.web.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.u2u.ibms.common.beans.IdentifyCertification;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.RentCompanyInfo;
import com.u2u.ibms.common.beans.RentPersonInfo;
import com.u2u.ibms.common.beans.SubOrder;
import com.u2u.ibms.common.mapper.IdentifyCertificationMapper;
import com.u2u.ibms.web.order.condition.OrderCondition;
import com.u2u.ibms.web.order.service.OrderService;

@Controller
@RequestMapping("/web/order/verify")
public class VerifyOrderController extends BaseController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private AuthorizeService authorizeService;
	
	@Autowired
	private IdentifyCertificationMapper identifyCertificationMapper;		

	@RequestMapping("/index")
	public String index() {
		return "/ibms/order/verifyOrderList";
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
				condition, true);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total",
				orderService.getAll(buildRowBounds(), condition, true).size());
		result.put("rows", orders);
		return result;
	}

	@RequestMapping("/suborders")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest, int orderId) {
		List<SubOrder> subOrders = orderService.getSubOrders(
				buildRowBounds(baseRequest), orderId);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", orderService
				.getSubOrders(buildRowBounds(), orderId).size());
		result.put("rows", subOrders);
		return result;
	}

	@RequestMapping("/edit")
	public ModelAndView seperate(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/order/verifyOrderEdit");
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
	

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(Order order) {
		orderService.verify(order);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/identify/certification")
	@ResponseBody
	public IdentifyCertificationResponse identifyCertification(String id,
			String idCard) {
		IdentifyCertificationResponse response = new IdentifyCertificationResponse();
		try {
			response.setError(0);
			response.setResponse(orderService.identifyCertification(id, idCard));
			orderService.updateIdentify(id, idCard);
		} catch (Exception e) {
			response.setError(1);
			response.setResponse(e.getMessage());
		}
		return response;
	}
}

class IdentifyCertificationResponse {
	private int error;
	private String response;

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}