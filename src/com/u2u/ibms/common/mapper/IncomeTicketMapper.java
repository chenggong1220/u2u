package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.IncomeTicket;

public interface IncomeTicketMapper {

	List<IncomeTicket> getAll(RowBounds rb);

	IncomeTicket getById(@Param("id") int id);

	IncomeTicket getByIncomeId(@Param("incomeId") int incomeId);

	public void insert(IncomeTicket incomeTicket);

	public void update(IncomeTicket incomeTicket);

	public void delete(@Param("id") int id);

}
