package com.u2u.framework.sys.authorize.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.sys.authorize.beans.Bu;
import com.u2u.framework.sys.authorize.mapper.BuMapper;
import com.u2u.framework.sys.authorize.model.BuCondition;
import com.u2u.framework.util.DateUtil;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class BuService extends BaseService {

	@Autowired
	private BuMapper buMapper;

	public List<Bu> getAll(RowBounds rb, BuCondition condition) {
		return buMapper.getAll(rb, getStringCondition(condition.getName()));
	}

	public Bu getById(int id) {
		return buMapper.getById(id);
	}

	public void insert(Bu bu) {
		bu.setCreateDate(DateUtil.currentTimestamp());
		bu.setOperateDate(DateUtil.currentTimestamp());
		buMapper.insert(bu);
	}

	public void update(Bu bu) {
		Bu exist = buMapper.getById(bu.getId());
		exist.setName(bu.getName());
		exist.setOperateDate(DateUtil.currentTimestamp());
		buMapper.update(exist);
	}

	public void delete(int id) {
		buMapper.delete(id);
	}
}
