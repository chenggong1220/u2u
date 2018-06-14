package com.u2u.ibms.web.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.web.shop.bean.Shop;

public interface ShopMapper {


	Shop getById(@Param("id") int id);

	void insert(Shop shop);

	void update(Shop shop);

	void delete(@Param("id") int id);

	List<Shop> getAll(@Param("name") String name,@Param("bid") int bid,@Param("contactName")  String contactName, RowBounds rb);
}
