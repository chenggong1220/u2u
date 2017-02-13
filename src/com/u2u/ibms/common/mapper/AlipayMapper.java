package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.Alipay;

public interface AlipayMapper {

	List<Alipay> getAll(RowBounds rb);

	Alipay getById(@Param("out_trade_no") String out_trade_no);

	void insert(Alipay alipay);

	void update(Alipay alipay);

	void delete(@Param("id") int id);
}
