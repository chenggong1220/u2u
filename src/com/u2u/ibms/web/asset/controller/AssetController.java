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
import com.u2u.ibms.common.beans.Asset;
import com.u2u.ibms.web.asset.condition.AssetCondition;
import com.u2u.ibms.web.asset.service.AssetService;

@Controller
@RequestMapping("/web/asset")
public class AssetController extends BaseController {

	@Autowired
	private AssetService assetService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/asset/assetList";
	}

	@RequestMapping("/json")
	@ResponseBody
	public List<Asset> getAll(String assetTypeId) {
		AssetCondition condition = new AssetCondition();
		condition.setAssetTypeId(assetTypeId);
		return assetService.getAll(condition, new RowBounds());
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			AssetCondition condition) {
		List<Asset> assets = assetService.getAll(condition,
				buildRowBounds(baseRequest));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", assetService.getAll(condition, buildRowBounds())
				.size());
		result.put("rows", assets);
		return result;
	}
	
	//start: Add more query conditions, SUNZHE, 2017-06-19 	
	@RequestMapping("/assetList")
	@ResponseBody
	public Map<String, Object> assetList(BaseRequest baseRequest,
			AssetCondition condition) {
		List<Asset> assets = assetService.getSearchedAll(condition,
				buildRowBounds(baseRequest));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", assetService.getSearchedAll(condition, buildRowBounds())
				.size());
		result.put("rows", assets);
		return result;
	}	
	//End: Add more query conditions, SUNZHE, 2017-06-19 	

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/asset/assetAdd");
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(Asset asset) {
		assetService.insert(asset);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/asset/assetEdit");
		mav.addObject("asset", assetService.getById(id));
		return mav;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxDone update(Asset asset) {
		assetService.update(asset);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxDone delete(int id) {
		assetService.delete(id);
		return ajaxDoneSuccess(null);
	}
}
