package com.u2u.ibms.common.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.Pays;

public interface PaysMapper {

	List<Pays> getAll(RowBounds rb,@Param("orderId") Integer orderId,@Param("startDate") Timestamp startDate,@Param("endDate") Timestamp endDate);

	List<Pays> getAllWithUser(RowBounds rb,@Param("userId") Integer userId,@Param("startDate") Timestamp startDate,@Param("endDate") Timestamp endDate);
	
	Pays getById(@Param("id") int id);

	void insert(Pays pays);

	void update(Pays pays);

	void delete(@Param("id") int id);

}
