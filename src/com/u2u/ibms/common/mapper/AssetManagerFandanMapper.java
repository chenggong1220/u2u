package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.AssetManagerFandan;

public interface AssetManagerFandanMapper {

	List<AssetManagerFandan> getAll(@Param("orderId") Integer orderId,
			RowBounds rb);

	AssetManagerFandan getById(@Param("id") int id);

	void insert(AssetManagerFandan assetManagerFandan);

	void update(AssetManagerFandan assetManagerFandan);

	void delete(@Param("id") int id);

}
