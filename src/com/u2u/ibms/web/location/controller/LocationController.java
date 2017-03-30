package com.u2u.ibms.web.location.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.ibms.common.beans.location.City;
import com.u2u.ibms.common.beans.location.District;
import com.u2u.ibms.common.beans.location.Province;
import com.u2u.ibms.rest.location.service.MobileLocationService;
import com.u2u.ibms.web.location.vo.LocationVo;

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
	

	@RequestMapping("/getLocation")
	@ResponseBody
	public List<LocationVo> getLocation() {
		List<Province> provinces = locationService.getProvinces();
		List<LocationVo> locations = new ArrayList<LocationVo>();
		for (Province province : provinces) {
			locations.add(this.convertLocation(province));
		}
		return locations;
	}

	public LocationVo convertLocation(Province province) {
		LocationVo loc = new LocationVo();
		loc.setId(Integer.valueOf(province.getId()));
		loc.setText(province.getName());
		for (City city : locationService.getCities(Integer.valueOf(province.getId()))) {
			if (loc.getChildren() == null) {
				loc.setChildren(new ArrayList<LocationVo>());
			}
			loc.getChildren().add(this.convertCity(city));
		}
		return loc;
	}

	public LocationVo convertCity(City city) {
		LocationVo loc = new LocationVo();
		loc.setId(city.getUnionCode());
		loc.setText(city.getName());
		return loc;
	}	
}
