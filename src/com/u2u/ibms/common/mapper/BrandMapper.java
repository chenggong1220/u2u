package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.Brand;

public interface BrandMapper {

	List<Brand> getAll(RowBounds rb, @Param("name") String name);

	Brand getById(@Param("id") int id);

	void insert(Brand brand);

	void update(Brand brand);

	void delete(@Param("id") int id);
}
