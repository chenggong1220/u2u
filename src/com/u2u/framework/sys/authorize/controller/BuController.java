package com.u2u.framework.sys.authorize.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.base.BaseRequest;
import com.u2u.framework.beans.AjaxDone;
import com.u2u.framework.sys.authorize.beans.Bu;
import com.u2u.framework.sys.authorize.model.BuCondition;
import com.u2u.framework.sys.authorize.service.BuService;

@Controller
@RequestMapping("/auth/bu")
public class BuController extends BaseController {

	@Autowired
	private BuService buService;

	@RequestMapping("/index")
	public String index() {
		return "/framework/auth/bu/buList";
	}

	@RequestMapping("/json")
	@ResponseBody
	public List<Bu> json() {
		BuCondition condition = new BuCondition();
		return buService.getAll(new RowBounds(), condition);
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			BuCondition condition) {
		List<Bu> bus = buService.getAll(buildRowBounds(baseRequest), condition);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", buService.getAll(buildRowBounds(), condition)
				.size());
		result.put("rows", bus);
		return result;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/framework/auth/bu/buAdd");
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(Bu bu) {
		buService.insert(bu);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/framework/auth/bu/buEdit");
		mav.addObject("bu", buService.getById(id));
		return mav;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxDone update(Bu bu) {
		buService.update(bu);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxDone delete(int id) {
		buService.delete(id);
		return ajaxDoneSuccess(null);
	}
}
