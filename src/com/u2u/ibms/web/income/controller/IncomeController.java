package com.u2u.ibms.web.income.controller;

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
import com.u2u.ibms.common.beans.Income;
import com.u2u.ibms.common.beans.IncomeTicket;
import com.u2u.ibms.web.income.condition.IncomeCondition;
import com.u2u.ibms.web.income.service.IncomeService;

@Controller
@RequestMapping("/web/income")
public class IncomeController extends BaseController {

	@Autowired
	private IncomeService incomeService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/income/incomeList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			IncomeCondition condition) {
		List<Income> incomes = incomeService.getAll(
				buildRowBounds(baseRequest), condition);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", incomeService.getAll(buildRowBounds(), condition)
				.size());
		result.put("rows", incomes);
		return result;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/income/incomeEdit");
		Income income = incomeService.getById(id);
		IncomeTicket incomeTicket = incomeService.getByIncomeId(id);
		mav.addObject("income", income);
		mav.addObject("incomeTicket", incomeTicket);
		return mav;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxDone update(String selectDate, String incomeId,
			IncomeTicket incomeTicket) {
		incomeService.save(selectDate, incomeId, incomeTicket);
		return ajaxDoneSuccess(null);
	}
}
