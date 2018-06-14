package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.MailHistory;

public interface MailHistoryMapper {

	List<MailHistory> getAll(RowBounds rb,
			@Param("operatorId") Integer operatorId);

	MailHistory getByContractId(@Param("contractId") int contractId);

	void insert(MailHistory mailHistory);

	void update(MailHistory mailHistory);

	void delete(@Param("id") int id);
}
