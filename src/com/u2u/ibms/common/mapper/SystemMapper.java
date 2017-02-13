package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.MainImg;

public interface SystemMapper {

	List<MainImg> getAll(RowBounds rb);


}
