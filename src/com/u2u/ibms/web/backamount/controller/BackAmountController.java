package com.u2u.ibms.web.backamount.controller;

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
import com.u2u.ibms.common.beans.BackAmount;
import com.u2u.ibms.common.beans.Contract;
import com.u2u.ibms.web.backamount.service.BackAmountService;
import com.u2u.ibms.web.contract.condition.ContractCondition;
import com.u2u.ibms.web.contract.service.ContractService;

@Controller
@RequestMapping("/web/backamount")
public class BackAmountController extends BaseController {

	@Autowired
	private ContractService contractService;

	@Autowired
	private BackAmountService backAmountService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/backamount/backAmountList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			ContractCondition condition) {
		List<Contract> contracts = contractService.getAll(
				buildRowBounds(baseRequest), condition);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", contractService.getAll(
				buildRowBounds(), condition).size());
		result.put("rows", contracts);
		return result;
	}

	@RequestMapping("/back")
	public ModelAndView back(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/backamount/backAmountBack");
		Contract contract = contractService.getById(id);
		mav.addObject("contract", contract);
		mav.addObject("backamount",
				backAmountService.getByContractId(contract.getId()));
		return mav;
	}

	@RequestMapping("/back/save")
	@ResponseBody
	public AjaxDone backSave(BackAmount backAmount) {
		backAmountService.insert(backAmount);
		return ajaxDoneSuccess(null);
	}

}
