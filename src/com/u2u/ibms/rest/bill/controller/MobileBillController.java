package com.u2u.ibms.rest.bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.framework.base.BaseRestController;
import com.u2u.framework.beans.MobileResponse;
import com.u2u.ibms.rest.bill.service.MobileBillService;
import com.u2u.ibms.rest.bill.vo.HistoryBillRequest;
@Controller
@RequestMapping("/mobile/bill")
public class MobileBillController extends BaseRestController {
	@Autowired
	private MobileBillService mobileBillService;

	/**
	 * 4.6.1 获取账单列表
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getBillList/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getBillList(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd) throws Exception {
		return success(mobileBillService.getBillList(username));
	}

//	/**
//	 * 4.6.2 通过账单ID获取账单信息
//	 * 
//	 * @param username
//	 * @param pwd
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/getBillByBillNo/{username}/{pwd}", method = RequestMethod.POST)
//	@ResponseBody
//	public MobileResponse getBillByBillNo(
//			@PathVariable(value = "username") String username,
//			@PathVariable(value = "pwd") String pwd,
//			@RequestBody String request) throws Exception {
//		return success(mobileBillService
//				.getBillByBillNo(getRealClazz(request, BillRequest.class)));
//	}
	/**
	 * 4.6.3 历史账单查询
	 * 
	 * @param username
	 * @param pwd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getHistoryBill/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getHistoryBill(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd,
			@RequestBody String request) throws Exception {
		HistoryBillRequest historyBillRequest = getRealClazz(request, HistoryBillRequest.class);
		return success(mobileBillService.getHistoryBill(historyBillRequest));
	}
}
