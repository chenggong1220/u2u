package com.u2u.ibms.common.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.Contract;

public interface ContractMapper {

	List<Contract> getAll(RowBounds rb,
			@Param("contractId") String contractId,
			@Param("sendStatus") Boolean sendStatus,
			@Param("backStatus") Boolean backStatus,
			@Param("status") String status,
			@Param("checkinStatus") Boolean checkinStatus,
			@Param("signoffStatus") Boolean signoffStatus,
			@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate,
			@Param("contractType") String contractType,
			@Param("orderPerson") String orderPerson,
			@Param("projectId") String projectId,
			@Param("rentType") String rentType, 
			@Param("provinceId") String provinceId,
			@Param("assetProvinceId") String assetProvinceId,
			@Param("customerName") String customerName,
			@Param("operatorId") String operatorId);
	
	Contract getByOrderId(@Param("orderId") int orderId);

	Contract getById(@Param("id") int id);

	void insert(Contract contract);

	void update(Contract contract);

	void delete(@Param("id") int id);
}
