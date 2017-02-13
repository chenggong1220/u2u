package com.u2u.ibms.web.asset.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.AssetManagerFandan;
import com.u2u.ibms.common.mapper.AssetManagerFandanMapper;
import com.u2u.ibms.web.order.service.OrderService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AssetManagerFandanService extends BaseService {

	@Autowired
	private AssetManagerFandanMapper assetManagerFandanMapper;

	@Autowired
	private OrderService orderService;

	public List<AssetManagerFandan> getAll(String orderId, RowBounds rb) {
		List<AssetManagerFandan> assetManagerFandans = assetManagerFandanMapper
				.getAll(getIntegerCondition(orderId), rb);
		for (AssetManagerFandan assetManagerFandan : assetManagerFandans) {
			assetManagerFandan.setOrder(orderService.getById(assetManagerFandan
					.getOrderId()));
		}
		return assetManagerFandans;
	}

	public AssetManagerFandan getById(int id) {
		AssetManagerFandan assetManagerFandan = assetManagerFandanMapper
				.getById(id);
		return assetManagerFandan;
	}

	public void insert(AssetManagerFandan assetManagerFandan) {
		assetManagerFandan.setCreateDate(DateUtil.currentTimestamp());
		assetManagerFandan.setOperateDate(DateUtil.currentTimestamp());
		assetManagerFandanMapper.insert(assetManagerFandan);
	}

	public void delete(int id) {
		assetManagerFandanMapper.delete(id);
	}
}
