package com.u2u.ibms.web.asset.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.Asset;
import com.u2u.ibms.common.beans.AssetManagerBack;
import com.u2u.ibms.common.beans.AssetManagerRentAsset;
import com.u2u.ibms.common.mapper.AssetManagerBackMapper;
import com.u2u.ibms.common.mapper.AssetManagerRentAssetMapper;
import com.u2u.ibms.common.mapper.AssetMapper;
import com.u2u.ibms.web.order.service.OrderService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AssetManagerBackService extends BaseService {

	@Autowired
	private AssetManagerBackMapper assetManagerBackMapper;

	@Autowired
	private OrderService orderService;

	@Autowired
	private AssetManagerRentAssetMapper assetManagerRentAssetMapper;

	@Autowired
	private AssetMapper assetMapper;

	public List<AssetManagerBack> getAll(String orderId, RowBounds rb) {
		List<AssetManagerBack> assetManagerBacks = assetManagerBackMapper
				.getAll(getIntegerCondition(orderId), rb);
		for (AssetManagerBack assetManagerBack : assetManagerBacks) {
			assetManagerBack.setOrder(orderService.getById(assetManagerBack
					.getOrderId()));
		}
		return assetManagerBacks;
	}

	public AssetManagerBack getById(int id) {
		AssetManagerBack assetManagerBack = assetManagerBackMapper.getById(id);
		return assetManagerBack;
	}

	public void insert(AssetManagerBack assetManagerBack, String rentStatus) {
		assetManagerBack.setStatus(getIntegerCondition(rentStatus));
		assetManagerBack.setType(1);
		assetManagerBack.setCreateDate(DateUtil.currentTimestamp());
		assetManagerBack.setOperateDate(DateUtil.currentTimestamp());
		assetManagerBackMapper.insert(assetManagerBack);

		AssetManagerRentAsset assetManagerRentAsset = assetManagerRentAssetMapper
				.getById(assetManagerBack.getManagerRentAssetId());
		assetManagerRentAsset.setAssetId(assetManagerBack.getNewAssetId());
		assetManagerRentAsset.setOperateDate(DateUtil.currentTimestamp());
		assetManagerRentAssetMapper.update(assetManagerRentAsset);

		Asset oldAsset = assetMapper.getById(assetManagerBack.getOldAssetId());
		oldAsset.setRent(getIntegerCondition(rentStatus));
		oldAsset.setOperateDate(DateUtil.currentTimestamp());
		assetMapper.update(oldAsset);

		Asset newAsset = assetMapper.getById(assetManagerBack.getNewAssetId());
		newAsset.setRent(0);
		newAsset.setOperateDate(DateUtil.currentTimestamp());
		assetMapper.update(newAsset);
	}

	public void delete(int id) {
		assetManagerBackMapper.delete(id);
	}
}
