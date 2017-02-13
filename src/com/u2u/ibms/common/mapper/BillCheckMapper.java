package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.BillCheck;
import com.u2u.ibms.common.beans.UserInfo;

public interface BillCheckMapper {

	List<BillCheck> getAll(RowBounds rb, @Param("orderId") Integer orderId,
			@Param("contractId") Integer contractId,
			@Param("status") Boolean status, 
			@Param("payStatus") String payStatus, 
			@Param("terms") String terms);

	BillCheck getById(@Param("id") int id);

	BillCheck getByContractIdAndCurrentTerm(
			@Param("contractId") Integer contracId,
			@Param("currentTerm") Integer currentTerm);

	void insert(BillCheck billcheck);

	void update(BillCheck billcheck);

	void delete(@Param("id") int id);

	List<BillCheck> getByOrder(String orderId);

	List<BillCheck> getLastOrder(UserInfo userinfo);
}
