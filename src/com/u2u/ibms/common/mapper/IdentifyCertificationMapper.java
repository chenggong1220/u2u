package com.u2u.ibms.common.mapper;

import org.apache.ibatis.annotations.Param;

import com.u2u.ibms.common.beans.IdentifyCertification;

public interface IdentifyCertificationMapper {

	IdentifyCertification getByNameAndIdcard(@Param("name") String name,
			@Param("idCard") String idCard);

	void insert(IdentifyCertification identifyCertification);
}
