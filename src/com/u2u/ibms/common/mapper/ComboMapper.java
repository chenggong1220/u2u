package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.Combo;

public interface ComboMapper {

	List<Combo> getAll(@Param("rentType") Integer rentType,
			@Param("assetTypeId") Integer assetTypeId,
			@Param("starProduct") Boolean starProduct,
			@Param("name") String name, RowBounds rb);

	List<Combo> getAllByAssetTypeIds(@Param("rentType") Integer rentType,
			@Param("assetTypeIds") List<Integer> assetTypeIds,
			@Param("starProduct") Boolean starProduct,
			@Param("name") String name, RowBounds rb);

	Combo getById(@Param("id") int id);

	void insert(Combo combo);

	void update(Combo combo);

	void delete(@Param("id") int id);
}
