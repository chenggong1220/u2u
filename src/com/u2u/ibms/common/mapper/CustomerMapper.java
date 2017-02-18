package com.u2u.ibms.common.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.CustomerName;
import com.u2u.ibms.common.beans.Customer;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.UserInfo;

public interface CustomerMapper {

	List<Customer> getAll(RowBounds rb);
	
	Customer getById(@Param("id") String id, 
					 @Param("custType") String custType);
	
}
