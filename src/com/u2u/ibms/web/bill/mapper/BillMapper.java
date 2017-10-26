package com.u2u.ibms.web.bill.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.web.bill.bean.Bill;

public interface BillMapper {

	Bill getById(@Param("id") int id);

	void insert(Bill bill);

	void update(Bill bill);

	void delete(@Param("id") int id);

	List<Bill> getAll(@Param("bankSerialNumber") String bankSerialNumber,
			@Param("accountNum") String accountNum,
			@Param("accountName") String accountName,
			@Param("cusName") String cusName, @Param("status") Boolean status,
			@Param("billingStatus") Boolean billingStatus, RowBounds rb,
			@Param("cusNum") String cusNum,
			@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate);
	
	List<Bill> getContractBill(
			@Param("company") String company,
			@Param("personal") String personal,
			@Param("billingMonth") String billingMonth,
			@Param("billType") String billType,
			@Param("contractNo") String contractNo,
			@Param("bankSerialNumber") String bankSerialNumber,
			@Param("accountNum") String accountNum,
			@Param("accountName") String accountName,
			@Param("cusName") String cusName, @Param("status") Boolean status,
			@Param("billingStatus") Boolean billingStatus, RowBounds rb,
			@Param("cusNum") String cusNum,
			@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate);	
	
	//Get one bill record, by SUNZHE, 2017-01-19
	Bill getOne(@Param("bankSerialNumber") String bankSerialNumber);
	

}
