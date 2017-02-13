package com.u2u.ibms.common.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.CustomerName;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.UserInfo;

public interface OrderMapper {

	List<Order> getAll(RowBounds rb, @Param("code") String code,
			@Param("createSource") Integer createSource,
			@Param("operatorId") Integer operatorId,
			@Param("assigned") Boolean assigned,
			@Param("depositCheck") Boolean depositCheck,
			@Param("delStatus") Boolean delStatus,
			@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, 
			@Param("status") String status, 
			@Param("rentType") String rentType, 
			@Param("customerName")String customerName, 
			@Param("operator") String operator, 
			@Param("assetProvinceId") String assetProvinceId);

	Order getById(@Param("id") int id);

	int insert(Order order);

	void update(Order order);

	void delete(@Param("id") int id);

	List<Order> getByUser(UserInfo userInfo);

	List<CustomerName> getCustomerNames(RowBounds rowBounds);
}
