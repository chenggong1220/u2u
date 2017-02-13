package com.u2u.ibms.web.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.framework.base.BaseController;
import com.u2u.ibms.web.report.bean.HasPay;
import com.u2u.ibms.web.report.bean.HasRent;
import com.u2u.ibms.web.report.condition.ReportCondition;
import com.u2u.ibms.web.report.service.ReportService;

@Controller
@RequestMapping("/web/report")
public class ReportController extends BaseController {

	@Autowired
	private ReportService reportService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/report/reportList";
	}

	@RequestMapping("/getHasRent")
	@ResponseBody
	public List<HasRent> getHasRent(ReportCondition condition) {
		return reportService.getHasRent(condition, buildRowBounds());
	}
	
	@RequestMapping("/getHasPay")
	@ResponseBody
	public List<HasPay> getHasPay(ReportCondition condition) {
		return reportService.getHasPay(condition, buildRowBounds());
	}


}
