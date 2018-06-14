package com.u2u.ibms.web.report.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.base.BaseRequest;
import com.u2u.ibms.web.report.bean.RentDetail;
import com.u2u.ibms.web.report.condition.ReportCondition;
import com.u2u.ibms.web.report.service.ReportService;

@Controller
@RequestMapping("/web/rentDetail")
public class RentDetailController extends BaseController {

	@Autowired
	private ReportService reportService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/report/rentDetailList";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> page(BaseRequest baseRequest,
			ReportCondition condition) {
		List<RentDetail> ds =reportService.getAllRent(condition, buildRowBounds(baseRequest));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", reportService.getAllRent(condition, buildRowBounds()).size());
		result.put("rows", ds);
		return result;
	}
}
