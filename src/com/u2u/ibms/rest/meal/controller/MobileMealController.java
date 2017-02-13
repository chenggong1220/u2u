package com.u2u.ibms.rest.meal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.framework.base.BaseRestController;
import com.u2u.framework.beans.MobileResponse;
import com.u2u.ibms.rest.meal.service.MobileMealService;
import com.u2u.ibms.rest.meal.vo.MealInfoByAssetTypeRequest;
import com.u2u.ibms.rest.meal.vo.MealInfoByIdRequest;

@Controller
@RequestMapping("/mobile/meal")
public class MobileMealController extends BaseRestController {

	@Autowired
	private MobileMealService mobileMealService;
	/**
	 * 4.2.1 通过设备类型ID获取套餐信息列表
	 * @param username
	 * @param pwd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMealInfoByAssetTypeId/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getMealInfoByAssetTypeId(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd,
			@RequestBody MealInfoByAssetTypeRequest request) throws Exception {
		return success(mobileMealService.getMealInfoByAssetTypeId(request));
	}
	/**
	 * 4.2.2 通过套餐ID获取套餐信息
	 * @param username
	 * @param pwd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMealInfoByMealId/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getMealInfoByMealId(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd,
			@RequestBody MealInfoByIdRequest request) throws Exception {
		return success(mobileMealService.getMealInfoByMealId(request));
	}
}
