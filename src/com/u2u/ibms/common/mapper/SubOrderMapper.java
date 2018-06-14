package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.SubOrder;

public interface SubOrderMapper {

	List<SubOrder> getAll(RowBounds rb);

	List<SubOrder> getByOrderId(RowBounds rb, @Param("orderId") int orderId);

	SubOrder getById(@Param("id") int id);

	void insert(SubOrder subOrder);

	void update(SubOrder subOrder);

	void delete(@Param("id") int id);

	void deleteByOrderId(@Param("orderId") int orderId);
}
