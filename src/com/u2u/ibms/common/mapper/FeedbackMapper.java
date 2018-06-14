package com.u2u.ibms.common.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.Feedback;

public interface FeedbackMapper {

	// List<Feedback> getAll(RowBounds rb);

	public List<Feedback> getAll(@Param("phone") String phone,
			@Param("status") Integer status,
			@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, RowBounds rb);

	Feedback getById(@Param("id") Integer id);

	void insert(Feedback feedback);

	void update(Feedback feedback);

	void delete(@Param("id") Integer id);

}
