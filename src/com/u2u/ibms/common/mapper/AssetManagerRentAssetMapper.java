package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.AssetManagerRentAsset;

public interface AssetManagerRentAssetMapper {

	List<AssetManagerRentAsset> getAll(@Param("orderId") Integer orderId, RowBounds rb);

	AssetManagerRentAsset getById(@Param("id") int id);

	AssetManagerRentAsset getByOrderIdAndAssetId(@Param("orderId") int orderId, @Param("assetId") int assetId);

	void insert(AssetManagerRentAsset assetManagerRentAsset);

	void update(AssetManagerRentAsset assetManagerRentAsset);

	void delete(@Param("id") int id);

}
