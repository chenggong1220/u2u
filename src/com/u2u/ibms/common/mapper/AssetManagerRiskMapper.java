package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.AssetManagerRisk;

public interface AssetManagerRiskMapper {

	List<AssetManagerRisk> getAll(@Param("orderId") Integer orderId,
			RowBounds rb);

	AssetManagerRisk getById(@Param("id") int id);

	void insert(AssetManagerRisk assetManagerRisk);

	void update(AssetManagerRisk assetManagerRisk);

	void delete(@Param("id") int id);
}
