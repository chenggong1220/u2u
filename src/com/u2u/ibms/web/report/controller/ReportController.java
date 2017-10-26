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
import com.u2u.ibms.common.beans.Asset;
import com.u2u.ibms.web.asset.condition.AssetCondition;
import com.u2u.ibms.web.asset.service.AssetService;
import com.u2u.ibms.web.bill.bean.Bill;
import com.u2u.ibms.web.bill.condition.BillCondition;
import com.u2u.ibms.web.bill.service.BillService;
import com.u2u.ibms.web.report.bean.HasPay;
import com.u2u.ibms.web.report.bean.HasRent;
import com.u2u.ibms.web.report.bean.RentDetail;
import com.u2u.ibms.web.report.condition.BillingReportCondition;
import com.u2u.ibms.web.report.condition.ReportCondition;
import com.u2u.ibms.web.report.service.ReportService;

@Controller
@RequestMapping("/web/report")
public class ReportController extends BaseController {

	@Autowired
	private ReportService reportService;
	
	@Autowired
	private AssetService assetService;	

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


	//Start: 租赁物明细报表, SUNZHE, 2017-02-15
	@RequestMapping("/assetDetails")
	public String assetDetails() {
		return "/ibms/report/assetDetailList";
	}
	
	@RequestMapping("/assetDetailList")
	@ResponseBody
	public Map<String, Object> assetList(BaseRequest baseRequest,
			ReportCondition condition) {
		Map<String, Object> assetDetails = reportService.getAssetDetails(condition,
				buildRowBounds(baseRequest));

		return assetDetails;
	}
	//End: 租赁物明细报表, SUNZHE, 2017-02-15


	//Start: 账单报表, SUNZHE, 2017-10-18
	@RequestMapping("/billingList")
	public String billingList() {
		return "/ibms/report/billingList";
	}
	
	@Autowired
	private BillService billService;
	
	@RequestMapping("/billingDetails")
	@ResponseBody
	public Map<String, Object> billingDetails(BaseRequest baseRequest,
			BillingReportCondition condition) {

//		List<Bill> bills = billService.getAll(condition,buildRowBounds(baseRequest));
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("total", billService.getAll(condition, buildRowBounds())
//				.size());
//		result.put("rows", bills);	
//		return result;
	
/*		
		Map<String, Object> billingDetails = reportService.getBillingDetails(condition,
				buildRowBounds(baseRequest), buildRowBounds());	
		return billingDetails;
*/		

		//System.out.println("condition.getCustType(): " + condition.getCustType());
		List<Bill> bills = billService.getContractBill(condition,buildRowBounds(baseRequest));			
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", billService.getContractBill(condition, buildRowBounds())
				.size());
		result.put("rows", bills);	
		return result;			
		
		
	}
	//End: 账单报表, SUNZHE, 2017-10-18	

	
}
