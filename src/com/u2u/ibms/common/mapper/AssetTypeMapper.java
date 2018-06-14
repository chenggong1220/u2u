package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.AssetType;

public interface AssetTypeMapper {

	List<AssetType> getAll(RowBounds rb, @Param("brandId") Integer brandId,
			@Param("model") String model);

	List<AssetType> getByIds(RowBounds rb, @Param("ids") List<Integer> ids);

	AssetType getById(@Param("id") int id);

	void insert(AssetType assetType);

	void update(AssetType assetType);

	void delete(@Param("id") int id);
}
