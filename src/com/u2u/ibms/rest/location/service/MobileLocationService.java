package com.u2u.ibms.rest.location.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u2u.ibms.common.beans.location.City;
import com.u2u.ibms.common.beans.location.District;
import com.u2u.ibms.common.beans.location.Province;
import com.u2u.ibms.common.mapper.LocationMapper;

@Service
public class MobileLocationService {

	@Autowired
	private LocationMapper locationMapper;

	public List<Province> getProvinces() {
		return locationMapper.getProvinces();
	}

	public List<City> getCities(Integer provinceId) {
		return locationMapper.getCities(provinceId);
	}

	public List<District> getDistricts(Integer cityId) {
		return locationMapper.getDistricts(cityId);
	}
}
