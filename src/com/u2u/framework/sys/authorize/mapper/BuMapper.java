package com.u2u.framework.sys.authorize.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.framework.sys.authorize.beans.Bu;

/**
 * @author Freud
 */
public interface BuMapper {

	List<Bu> getAll(RowBounds rb, @Param("name") String name);

	Bu getById(@Param("id") int id);

	void insert(Bu bu);

	void update(Bu bu);

	void delete(@Param("id") int id);
}
