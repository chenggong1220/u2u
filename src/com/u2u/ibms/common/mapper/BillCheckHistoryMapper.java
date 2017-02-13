package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.BillCheckHistory;

public interface BillCheckHistoryMapper {

	List<BillCheckHistory> getAll(RowBounds rb);

	BillCheckHistory getById(@Param("id") int id);

	void insert(BillCheckHistory billCheckHistory);

	void update(BillCheckHistory billCheckHistory);

	void delete(@Param("id") int id);
}
