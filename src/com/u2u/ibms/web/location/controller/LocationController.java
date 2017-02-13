package com.u2u.ibms.web.location.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.ibms.common.beans.location.City;
import com.u2u.ibms.common.beans.location.District;
import com.u2u.ibms.common.beans.location.Province;
import com.u2u.ibms.rest.location.service.MobileLocationService;

@Controller
@RequestMapping("/web/location")
public class LocationController {

	@Autowired
	private MobileLocationService locationService;

	@RequestMapping("/getProvinces")
	@ResponseBody
	public List<Province> getProvinces() {
		return locationService.getProvinces();
	}

	@RequestMapping("/getCities")
	@ResponseBody
	public List<City> getCities(int provinceId) {
		return locationService.getCities(provinceId);
	}

	@RequestMapping("/getDistricts")
	@ResponseBody
	public List<District> getDistricts(int cityId) {
		return locationService.getDistricts(cityId);
	}
}
