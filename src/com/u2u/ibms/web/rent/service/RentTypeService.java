package com.u2u.ibms.web.rent.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.ibms.common.beans.RentType;
import com.u2u.ibms.common.mapper.RentTypeMapper;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class RentTypeService extends BaseService {

	@Autowired
	private RentTypeMapper rentTypeMapper;

	public List<RentType> get(RowBounds rb) {

		return rentTypeMapper.getAll(rb);
	}
}
