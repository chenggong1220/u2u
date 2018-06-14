package com.u2u.ibms.web.appversion.controller;

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
import com.u2u.ibms.common.beans.AppVersion;
import com.u2u.ibms.web.appversion.condition.AppversionCondition;
import com.u2u.ibms.web.appversion.service.AppversionService;

@Controller
@RequestMapping("/web/appversion")
public class AppVersionController extends BaseController {

	@Autowired
	private AppversionService appversionService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/appversion/appversionList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			AppversionCondition condition) {
		List<AppVersion> appversions = appversionService.getAll(
				buildRowBounds(baseRequest), condition);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total",
				appversionService.getAll(buildRowBounds(), condition).size());
		result.put("rows", appversions);
		return result;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/appversion/appversionAdd");
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(AppVersion appVersion) {
		appversionService.insert(appVersion);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/appversion/appversionEdit");
		mav.addObject("appversion", appversionService.getById(id));
		return mav;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxDone update(AppVersion appVersion) {
		appversionService.update(appVersion);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxDone delete(int id) {
		appversionService.delete(id);
		return ajaxDoneSuccess(null);
	}
}
