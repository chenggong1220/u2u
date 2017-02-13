package com.u2u.ibms.web.asset.controller;

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
import com.u2u.ibms.common.beans.Brand;
import com.u2u.ibms.web.asset.condition.BrandCondition;
import com.u2u.ibms.web.asset.service.BrandService;

@Controller
@RequestMapping("/web/asset/brand")
public class BrandController extends BaseController {

	@Autowired
	private BrandService brandService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/asset/brand/brandList";
	}

	@RequestMapping("/json")
	@ResponseBody
	public List<Brand> json() {
		return brandService.getAll(new RowBounds(), new BrandCondition());
	}

	@RequestMapping("/add/json")
	@ResponseBody
	public List<Brand> addJson(Integer rentType) {
		return brandService.getByRentType(rentType);
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			BrandCondition condition) {
		List<Brand> brands = brandService.getAll(buildRowBounds(baseRequest),
				condition);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", brandService.getAll(buildRowBounds(), condition)
				.size());
		result.put("rows", brands);
		return result;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/asset/brand/brandAdd");
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(Brand brand) {
		brandService.insert(brand);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/asset/brand/brandEdit");
		mav.addObject("brand", brandService.getById(id));
		return mav;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxDone update(Brand brand) {
		brandService.update(brand);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxDone delete(int id) {
		brandService.delete(id);
		return ajaxDoneSuccess(null);
	}
}
