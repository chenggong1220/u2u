package com.u2u.ibms.common.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.Income;

public interface IncomeMapper {

	List<Income> getAll(RowBounds rb, @Param("customer") String customer, 
			@Param("contractId") String contractId, 
			@Param("type") String type,
			@Param("invoiceStatus") String invoiceStatus,@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate);

	Income getById(@Param("id") int id);

	Income getByBillId(@Param("billId") int billId);

	public void insert(Income income);

	public void update(Income income);

	public void delete(@Param("id") int id);

}
