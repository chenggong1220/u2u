package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.u2u.ibms.common.beans.location.City;
import com.u2u.ibms.common.beans.location.District;
import com.u2u.ibms.common.beans.location.Province;

public interface LocationMapper {

	List<Province> getProvinces();

	Province getProvinceById(@Param("id") Integer id);

	List<City> getCities(@Param("provinceId") Integer provinceId);

	City getCityById(@Param("id") Integer id);

	City getCityByUnionCode(@Param("unionCode") Integer unionCode);
	
	List<District> getDistricts(@Param("cityId") Integer cityId);

	District getDistrictById(@Param("id") Integer id);
}
