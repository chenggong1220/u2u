package com.u2u.ibms.common.mapper;

import org.apache.ibatis.annotations.Param;

import com.u2u.ibms.common.beans.CreditVerify;

public interface CreditVerifyMapper {

	CreditVerify getByProjectId(@Param("projectId") int projectId);

	CreditVerify getById(@Param("id") int id);

	void insert(CreditVerify creditVerify);

	void update(CreditVerify creditVerify);

	void delete(@Param("id") int id);
}
