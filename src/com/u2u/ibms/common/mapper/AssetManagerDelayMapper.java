package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.AssetManagerDelay;

public interface AssetManagerDelayMapper {

	List<AssetManagerDelay> getAll(@Param("orderId") Integer orderId,
			RowBounds rb);

	AssetManagerDelay getById(@Param("id") int id);

	void insert(AssetManagerDelay assetManagerDelay);

	void update(AssetManagerDelay assetManagerDelay);

	void delete(@Param("id") int id);
}
