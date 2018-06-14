package com.u2u.ibms.rest.pay.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.framework.base.BaseRestController;
import com.u2u.framework.beans.MobileResponse;
import com.u2u.ibms.rest.pay.service.MobilePayService;
import com.u2u.ibms.rest.pay.vo.AlipayCallbackRequest;
import com.u2u.ibms.rest.pay.vo.AlipayRequest;
import com.u2u.ibms.rest.pay.vo.AlipayVerifyRequest;

@Controller
@RequestMapping("/mobile/pay")
public class MobilePayController extends BaseRestController {

	private static Log log = LogFactory.getLog(MobilePayController.class);

	@Autowired
	private MobilePayService mobilePayService;



	/**
	 * 4.4.1 保存订单
	 * 
	 * @param username
	 * @param pwd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/alipay/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse alipay(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd,
			@RequestBody AlipayRequest request) throws Exception {
		request.setUsername(username);
		return success(mobilePayService.getAlipayInfos(request));
	}

	@RequestMapping(value = "/alipay/verify/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse alipayVerify(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd,
			@RequestBody AlipayVerifyRequest request) throws Exception {
		request.setUsername(username);
		return success(mobilePayService.verifyAlipayOrder(request));
	}

	@RequestMapping(value = "/alipay/callback")
	@ResponseBody
	public String getOrderList(HttpServletRequest servletRequest,
			AlipayCallbackRequest request) throws Exception {
		log.info("支付宝回调执行：" + new ObjectMapper().writeValueAsString(request));
		System.out.println("支付宝回调执行：" + new ObjectMapper().writeValueAsString(request));
		try {
			mobilePayService.alipayCallback(servletRequest, request);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

}
