package com.u2u.ibms.web.billdetail.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.web.billdetail.bean.Billdetail;

public interface BilldetailMapper {

	Billdetail getById(@Param("id") int id);

	void insert(Billdetail billdetail);

	void update(Billdetail billdetail);

	void delete(@Param("id") int id);

	List<Billdetail> getAll(@Param("customer") String customer,
			@Param("deviceno") String deviceno,
			@Param("contractCode") String contractCode,
			@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, RowBounds rb, 
			@Param("type") String type);

	List<Billdetail> getByDevicenoAndDevicedate(@Param("deviceno") String deviceno,
			@Param("devicedate") String devicedate);

	List<Billdetail> getAllByContractId(@Param("contract") String contract,
			RowBounds rb);

	void deleteByDeviceNo(@Param("deviceNo") String deviceNo);
}
