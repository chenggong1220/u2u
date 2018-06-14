package com.u2u.ibms.web.report.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.web.report.bean.HasRent;

public interface ReportMapper {


	HasRent getById(@Param("id") int id);

	void insert(HasRent report);

	void update(HasRent report);

	void delete(@Param("id") int id);

	List<HasRent> getAll(@Param("name") String name,@Param("bid") int bid,@Param("contactName")  String contactName, RowBounds rb);
}
