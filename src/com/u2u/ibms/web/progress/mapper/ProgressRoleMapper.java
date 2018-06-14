package com.u2u.ibms.web.progress.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.web.progress.bean.ProgressRole;

public interface ProgressRoleMapper {


	List<ProgressRole> getById(@Param("pid") int pid);

	void insert(ProgressRole progress);

	void update(ProgressRole progress);

	void delete(@Param("pid") int pid);

	List<ProgressRole> getAll(String acountName, RowBounds rb);
}
