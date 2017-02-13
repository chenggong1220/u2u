package com.u2u.ibms.rest.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.framework.base.BaseRestController;
import com.u2u.framework.beans.MobileResponse;
import com.u2u.ibms.rest.order.service.MobileOrderService;
import com.u2u.ibms.rest.order.vo.OrderRequest;
import com.u2u.ibms.rest.order.vo.SaveOrderRequest;

@Controller
@RequestMapping("/mobile/order")
public class MobileOrderController extends BaseRestController {

	@Autowired
	private MobileOrderService mobileOrderService;
	/**
	 * 4.4.1 保存订单
	 * 
	 * @param username
	 * @param pwd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveOrder/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse saveOrder(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd,
			@RequestBody SaveOrderRequest request) throws Exception {
		request.setUsername(username);
		mobileOrderService.saveOrder(request);
		return success("");
	}
	/**
	 * 4.4.2 获取订单列表
	 * 
	 * @param username
	 * @param pwd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getOrderList/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getOrderList(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd) throws Exception {
		return success(mobileOrderService.getOrderList(username));
	}
	/**
	 * 4.4.3 通过ID订单信息
	 * 
	 * @param username
	 * @param pwd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getOrderById/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getOrderById(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd,
			@RequestBody OrderRequest request) throws Exception {
		return success(mobileOrderService.getOrderById(request));
	}

}
