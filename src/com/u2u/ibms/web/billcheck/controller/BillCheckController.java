package com.u2u.ibms.web.billcheck.controller;

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
import com.u2u.ibms.common.beans.BillCheck;
import com.u2u.ibms.web.billcheck.condition.BillCheckCondition;
import com.u2u.ibms.web.billcheck.service.BillCheckService;
import com.u2u.ibms.web.billcheck.vo.AssetListResponse;

@Controller
@RequestMapping("/web/billcheck")
public class BillCheckController extends BaseController {

	@Autowired
	private BillCheckService billCheckService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/billcheck/billcheckList";
	}

	@RequestMapping("/json")
	@ResponseBody
	public List<BillCheck> json(BillCheckCondition condition) {
		return billCheckService.getAll(buildRowBounds(), condition);
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			BillCheckCondition condition) {
		List<BillCheck> billchecks = billCheckService.getAll(
				buildRowBounds(baseRequest), condition);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", billCheckService
				.getAll(buildRowBounds(), condition).size());
		result.put("rows", billchecks);
		return result;
	}

	@RequestMapping("/out")
	@ResponseBody
	public AjaxDone out(int id) {
		billCheckService.update(id);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/detail")
	public ModelAndView detail(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/billcheck/billcheckDetail");
		mav.addObject("billcheck", billCheckService.getById(id));
		return mav;
	}

	@RequestMapping("/detail/list")
	@ResponseBody
	public Map<String, Object> detailList(BaseRequest baseRequest,
			Integer contractId) {
		List<AssetListResponse> assetListResponses = billCheckService
				.getAssetListResponse(contractId);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", assetListResponses.size());
		result.put("rows", assetListResponses);
		return result;
	}
}
