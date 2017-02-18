package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.Asset;

public interface AssetMapper {

	List<Asset> getAll(@Param("assetTypeIds") List<Integer> assetTypeIds,
			@Param("rent") Boolean rent,
			@Param("provinceId") Integer provinceId,
			@Param("cityId") Integer cityId, RowBounds rb);

	Asset getByAssetId(@Param("assetId") String assetid);

	Asset getById(@Param("id") int id);

	void insert(Asset asset);

	void update(Asset asset);

	void delete(@Param("id") int id);
	
	//Start: 租赁物明细报表, SUNZHE, 2017-02-15
	List<Asset> getByContract(@Param("assetTypeIds") List<Integer> assetTypeIds,
			@Param("rent") Boolean rent,
			@Param("provinceId") Integer provinceId,
			@Param("cityId") Integer cityId, RowBounds rb);
	//End: 租赁物明细报表, SUNZHE, 2017-02-15	
}
