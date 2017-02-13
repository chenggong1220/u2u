package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.RentCompanyInfo;
import com.u2u.ibms.common.beans.SubOrder;

public interface RentCompanyMapper {

	List<SubOrder> getAll(RowBounds rb);

	RentCompanyInfo getById(@Param("id") int id);

	int insert(RentCompanyInfo renCompanyInfo);

	void update(RentCompanyInfo renCompanyInfo);

	void delete(@Param("id") int id);
}
