package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.AssetManagerBack;

public interface AssetManagerBackMapper {

	List<AssetManagerBack> getAll(@Param("orderId") Integer orderId,
			RowBounds rb);

	AssetManagerBack getById(@Param("id") int id);

	void insert(AssetManagerBack assetManagerBack);

	void update(AssetManagerBack assetManagerBack);

	void delete(@Param("id") int id);
}
