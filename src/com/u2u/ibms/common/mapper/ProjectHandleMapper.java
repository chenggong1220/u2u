package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.u2u.ibms.common.beans.ProjectHandle;

public interface ProjectHandleMapper {

	List<ProjectHandle> getAllByProjectId(@Param("projectId") Integer projectId);
	
	List<ProjectHandle> getAllByProjId(@Param("projectId") Integer projectId);

	ProjectHandle getAllByProjectIdAndLevel(@Param("projectId") Integer projectId, @Param("level") int level);

	ProjectHandle getById(@Param("id") int id);

	int insert(ProjectHandle projectHandle);

	void update(ProjectHandle projectHandle);

	void delete(@Param("id") int id);
}
