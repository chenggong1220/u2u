package com.u2u.ibms.web.asset.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.u2u.framework.base.BaseCondition;
import com.u2u.framework.base.BaseController;
import com.u2u.framework.beans.AjaxDone;
import com.u2u.ibms.common.beans.Combo;
import com.u2u.ibms.web.asset.condition.ComboCondition;
import com.u2u.ibms.web.asset.service.ComboService;

@Controller
@RequestMapping("/web/asset/combo")
public class ComboController extends BaseController {

	@Autowired
	private ComboService comboService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/asset/combo/comboList";
	}

	@RequestMapping("/getByAssetTypeId")
	@ResponseBody
	public List<Combo> getByAssetTypeId(
			@RequestParam("assetTypeId") Integer assetTypeId,
			@RequestParam("rentType") Integer rentType) {
		return comboService.getByAssetTypeId(assetTypeId, rentType);
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(ComboCondition request,
			BaseCondition condition) {
		List<Combo> combos = comboService.getAll(request,
				buildRowBounds(request));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", comboService.getAll(request, buildRowBounds())
				.size());
		result.put("rows", combos);
		return result;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/asset/combo/comboAdd");
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(Combo combo) {
		comboService.insert(combo);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/asset/combo/comboEdit");
		mav.addObject("combo", comboService.get(id));
		return mav;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxDone update(Combo combo) {
		comboService.update(combo);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxDone delete(int id) {
		comboService.delete(id);
		return ajaxDoneSuccess(null);
	}
}
