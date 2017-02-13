package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.AppVersion;

public interface AppVersionMapper {

	List<AppVersion> getAll(RowBounds rb, @Param("version") String version);

	AppVersion getById(@Param("id") int id);

	void insert(AppVersion appVersion);

	void update(AppVersion appVersion);

	void delete(@Param("id") int id);

	AppVersion checkVersion();

}
