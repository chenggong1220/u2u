package com.u2u.ibms.rest.location.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.framework.base.BaseRestController;
import com.u2u.framework.beans.MobileResponse;
import com.u2u.ibms.rest.location.service.MobileLocationService;

@Controller
@RequestMapping("/mobile/location")
public class MobileLocationController extends BaseRestController {

	@Autowired
	private MobileLocationService mobileLocationService;

	/**
	 * 5.1 获取省
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getProvices")
	@ResponseBody
	public MobileResponse getProvinces() throws Exception {
		return success(mobileLocationService.getProvinces());
	}
	/**
	 * 5.2 获取市
	 * 
	 * @param provinceId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCities/{provinceId}")
	@ResponseBody
	public MobileResponse getCities(
			@PathVariable("provinceId") Integer provinceId) throws Exception {
		return success(mobileLocationService.getCities(provinceId));
	}
	/**
	 * 5.3 获取县
	 * 
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getDistricts/{cityId}")
	@ResponseBody
	public MobileResponse getDistricts(@PathVariable("cityId") Integer cityId)
			throws Exception {
		return success(mobileLocationService.getDistricts(cityId));
	}
}
