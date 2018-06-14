package com.u2u.ibms.web.asset.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.AssetManagerRisk;
import com.u2u.ibms.common.mapper.AssetManagerRiskMapper;
import com.u2u.ibms.web.order.service.OrderService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AssetManagerRiskService extends BaseService {

	@Autowired
	private AssetManagerRiskMapper assetManagerRiskMapper;

	@Autowired
	private OrderService orderService;

	public List<AssetManagerRisk> getAll(String orderId, RowBounds rb) {
		List<AssetManagerRisk> assetManagerRisks = assetManagerRiskMapper
				.getAll(getIntegerCondition(orderId), rb);
		for (AssetManagerRisk assetManagerRisk : assetManagerRisks) {
			assetManagerRisk.setOrder(orderService.getById(assetManagerRisk
					.getOrderId()));
		}
		return assetManagerRisks;
	}

	public AssetManagerRisk getById(int id) {
		AssetManagerRisk assetManagerRisk = assetManagerRiskMapper.getById(id);
		return assetManagerRisk;
	}

	public void insert(AssetManagerRisk assetManagerRisk) {
		assetManagerRisk.setCreateDate(DateUtil.currentTimestamp());
		assetManagerRisk.setOperateDate(DateUtil.currentTimestamp());
		assetManagerRiskMapper.insert(assetManagerRisk);
	}

	public void delete(int id) {
		assetManagerRiskMapper.delete(id);
	}
}
