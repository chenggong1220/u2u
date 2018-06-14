package com.u2u.ibms.web.bill.controller;

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
import com.u2u.ibms.web.bill.bean.Bill;
import com.u2u.ibms.web.bill.condition.BillCondition;
import com.u2u.ibms.web.bill.service.BillService;

@Controller
@RequestMapping("/web/bill/verification")
public class BillVerificationController extends BaseController {

	@Autowired
	private BillService billService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/bill/verification/billVerificationList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			BillCondition condition) {
		List<Bill> bills = billService.getAll(condition,
				buildRowBounds(baseRequest));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", billService.getAll(condition, buildRowBounds())
				.size());
		result.put("rows", bills);
		return result;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/bill/verification/billVerificationEdit");
		mav.addObject("bill", billService.getById(id));
		return mav;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxDone update(Bill bill) {
		billService.update(bill);
		return ajaxDoneSuccess(null);
	}

}
