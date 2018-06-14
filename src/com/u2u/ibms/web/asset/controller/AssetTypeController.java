package com.u2u.ibms.web.asset.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.base.BaseRequest;
import com.u2u.framework.beans.AjaxDone;
import com.u2u.ibms.common.beans.AssetType;
import com.u2u.ibms.web.asset.condition.AssetTypeCondition;
import com.u2u.ibms.web.asset.service.AssetTypeService;

@Controller
@RequestMapping("/web/asset/type")
public class AssetTypeController extends BaseController {

	@Autowired
	private AssetTypeService assetTypeService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/asset/type/assetTypeList";
	}

	@RequestMapping("/json")
	@ResponseBody
	public List<AssetType> json(Integer brandId) {
		AssetTypeCondition condition = new AssetTypeCondition();
		condition.setBrandId(brandId.toString());
		return assetTypeService.getAll(new RowBounds(), condition);
	}

	@RequestMapping("/add/json")
	@ResponseBody
	public List<AssetType> addJson(@RequestParam("brandId") Integer brandId,
			@RequestParam("rentType") Integer rentType) {
		return assetTypeService.getByRentType(brandId, rentType);
	}

	@RequestMapping("/json/get")
	@ResponseBody
	public AssetType jsonGet(Integer assetTypeId) {
		return assetTypeService.getById(assetTypeId);
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			AssetTypeCondition condition) {
		List<AssetType> assetTypes = assetTypeService.getAll(
				buildRowBounds(baseRequest), condition);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", assetTypeService
				.getAll(buildRowBounds(), condition).size());
		result.put("rows", assetTypes);
		return result;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/asset/type/assetTypeAdd");
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(AssetType assetType) {
		assetTypeService.insert(assetType);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/asset/type/assetTypeEdit");
		mav.addObject("assetType", assetTypeService.getById(id));
		return mav;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxDone update(AssetType assetType) {
		assetTypeService.update(assetType);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxDone delete(int id) {
		assetTypeService.delete(id);
		return ajaxDoneSuccess(null);
	}
}
