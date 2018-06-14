package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.RentType;

public interface RentTypeMapper {

	List<RentType> getAll(RowBounds rb);

	RentType getById(@Param("id") int id);

	void insert(RentType rentType);

	void update(RentType rentType);

	void delete(@Param("id") int id);
}
