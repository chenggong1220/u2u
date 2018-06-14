package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.RentPersonInfo;

public interface RentPersonMapper {

	List<RentPersonInfo> getAll(RowBounds rb);

	RentPersonInfo getById(@Param("id") int id);

	int insert(RentPersonInfo rentPersonInfo);

	void update(RentPersonInfo rentPersonInfo);

	void delete(@Param("id") int id);

}
