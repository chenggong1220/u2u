package com.u2u.framework.sys.authorize.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.framework.sys.authorize.beans.Department;

/**
 * @author Freud
 */
public interface DepartmentMapper {

	List<Department> getAll(RowBounds rb, @Param("name") String name);

	Department getById(@Param("id") int id);

	void insert(Department department);

	void update(Department department);

	void delete(@Param("id") int id);
}
