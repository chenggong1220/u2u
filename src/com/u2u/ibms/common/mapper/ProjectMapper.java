package com.u2u.ibms.common.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.Project;

public interface ProjectMapper {

	List<Project> getAll(RowBounds rb, @Param("projectId") String projectId,
			@Param("result") Integer result,
			@Param("creditResult") Integer creditResult,
			@Param("operatorId") String operatorId,
			@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, 
			@Param("orderCode") String orderCode, 
			@Param("operator") String operator);

	Project getById(@Param("id") int id);

	int insert(Project project);

	void update(Project project);

	void delete(@Param("id") int id);
}
