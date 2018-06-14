package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.BackAmount;

public interface BackAmountMapper {

	List<BackAmount> getAll(@Param("contractId") Integer contractId,
			RowBounds rb);

	BackAmount getById(@Param("id") int id);

	BackAmount getByContractId(@Param("contractId") int contractId);

	void insert(BackAmount backAmount);

	void update(BackAmount backAmount);

	void delete(@Param("id") int id);
}
