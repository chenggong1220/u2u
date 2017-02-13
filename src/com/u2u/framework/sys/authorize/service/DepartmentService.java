package com.u2u.framework.sys.authorize.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.sys.authorize.beans.Department;
import com.u2u.framework.sys.authorize.mapper.DepartmentMapper;
import com.u2u.framework.sys.authorize.model.DepartmentCondition;
import com.u2u.framework.util.DateUtil;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class DepartmentService extends BaseService {

	@Autowired
	private DepartmentMapper departmentMapper;

	public List<Department> getAll(RowBounds rb, DepartmentCondition condition) {
		return departmentMapper.getAll(rb,
				getStringCondition(condition.getName()));
	}

	public Department getById(int id) {
		return departmentMapper.getById(id);
	}

	public void insert(Department department) {
		department.setCreateDate(DateUtil.currentTimestamp());
		department.setOperateDate(DateUtil.currentTimestamp());
		departmentMapper.insert(department);
	}

	public void update(Department department) {
		Department exist = departmentMapper.getById(department.getId());
		exist.setName(department.getName());
		exist.setLevel(department.getLevel());
		exist.setParentId(department.getParentId());
		exist.setOperateDate(DateUtil.currentTimestamp());
		departmentMapper.update(exist);
	}

	public void delete(int id) {
		departmentMapper.delete(id);
	}
}
