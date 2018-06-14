package com.u2u.ibms.web.shop.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.sys.authorize.mapper.BuMapper;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.web.shop.bean.Shop;
import com.u2u.ibms.web.shop.condition.ShopCondition;
import com.u2u.ibms.web.shop.mapper.ShopMapper;

@Service
@Transactional
public class ShopService extends BaseService {

	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private BuMapper buMapper;

	public void insert(Shop shop) {
		shop.setCreateDate(DateUtil.currentTimestamp());
		shop.setOperateDate(DateUtil.currentTimestamp());
		shopMapper.insert(shop);
	}

	public Shop getById(int id) {
		return shopMapper.getById(id);
	}

	public void update(Shop shop) {
		shop.setOperateDate(DateUtil.currentTimestamp());
		shopMapper.update(shop);
	}

	public void delete(int id) {
		shopMapper.delete(id);
	}
	public List<Shop> getAll(ShopCondition condition, RowBounds rb) {
		List<Shop> shops = shopMapper.getAll(condition.getName(),condition.getBid(),condition.getContactName(), rb);
		if(CollectionUtils.isNotEmpty(shops)){
			for (Shop shop : shops) {
				shop.setBu(buMapper.getById(shop.getBid()));
			}
		}
		return shops;
	}
}
