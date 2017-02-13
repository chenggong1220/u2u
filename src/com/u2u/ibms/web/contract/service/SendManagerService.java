package com.u2u.ibms.web.contract.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.common.component.dingding.DingDingAuthUtil;
import com.u2u.framework.base.BaseService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.Constants;
import com.u2u.ibms.common.beans.Asset;
import com.u2u.ibms.common.beans.AssetManagerRentAsset;
import com.u2u.ibms.common.beans.Contract;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.SubOrder;
import com.u2u.ibms.common.mapper.AssetManagerRentAssetMapper;
import com.u2u.ibms.common.mapper.AssetMapper;
import com.u2u.ibms.common.mapper.ContractMapper;
import com.u2u.ibms.common.mapper.OrderMapper;
import com.u2u.ibms.common.mapper.SubOrderMapper;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class SendManagerService extends BaseService {

	@Autowired
	private ContractMapper contractMapper;

	@Autowired
	private AssetMapper assetMapper;

	@Autowired
	private AssetManagerRentAssetMapper assetManagerRentAssetMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private SubOrderMapper subOrderMapper;

	public void send(String contractId, Integer[] ids, String selectDate) {
		Contract exist = contractMapper
				.getById(getIntegerCondition(contractId));
		exist.setSendStatus(true);
		exist.setSendDate(DateUtil.string2Timestamp(selectDate,
				DateUtil.PATTERN_DATE));
		exist.setOperateDate(DateUtil.currentTimestamp());
		contractMapper.update(exist);

		for (Integer id : ids) {
			Asset asset = assetMapper.getById(id);
			asset.setRent(0);
			asset.setOperateDate(DateUtil.currentTimestamp());
			assetMapper.update(asset);

			AssetManagerRentAsset rentAsset = assetManagerRentAssetMapper
					.getByOrderIdAndAssetId(exist.getOrderId(), id);

			if (rentAsset == null) {
				rentAsset = new AssetManagerRentAsset();
				rentAsset.setOrderId(exist.getOrderId());
				rentAsset.setAssetId(id);
				rentAsset.setReceiveStatus(false);
				rentAsset.setReceiveDate(null);
				rentAsset.setRentDate(new Timestamp(exist.getSendDate()
						.getTime() + 7 * 24 * 60 * 60 * 1000));
				rentAsset.setCreateDate(DateUtil.currentTimestamp());
				rentAsset.setOperateDate(DateUtil.currentTimestamp());
				assetManagerRentAssetMapper.insert(rentAsset);
			} else {
				rentAsset = new AssetManagerRentAsset();
				rentAsset.setOrderId(exist.getOrderId());
				rentAsset.setAssetId(id);
				rentAsset.setRentDate(new Timestamp(exist.getSendDate()
						.getTime() + 7 * 24 * 60 * 60 * 1000));
				rentAsset.setOperateDate(DateUtil.currentTimestamp());
				assetManagerRentAssetMapper.update(rentAsset);
			}
		}

		List<SubOrder> subOrders = subOrderMapper.getByOrderId(new RowBounds(),
				exist.getOrderId());

		Map<Integer, Integer> typeMaps = new HashMap<Integer, Integer>();
		for (SubOrder subOrder : subOrders) {
			Integer value = typeMaps.get(subOrder.getAssetTypeId());
			if (value == null) {
				value = subOrder.getCount();
			} else {
				value = value + subOrder.getCount();
			}
			typeMaps.put(subOrder.getAssetTypeId(), subOrder.getCount());
		}

		for (AssetManagerRentAsset existAsset : assetManagerRentAssetMapper
				.getAll(exist.getOrderId(), new RowBounds())) {
			Asset asset = assetMapper.getById(existAsset.getAssetId());
			if (typeMaps.containsKey(asset.getAssetTypeId())) {
				typeMaps.put(asset.getAssetTypeId(),
						typeMaps.get(asset.getAssetTypeId()) - 1);
			}
		}

		for (Entry<Integer, Integer> entry : typeMaps.entrySet()) {
			if (entry.getValue() < 0) {
				throw new RuntimeException("发现错误发货，请重新核对发货数量！");
			}
		}

		Order order = orderMapper.getById(exist.getOrderId());
		order.setStatus(Constants.ORDER_10_ASSET_SEND);
		order.setOperateDate(DateUtil.currentTimestamp());
		orderMapper.update(order);
		DingDingAuthUtil.send(Constants.DINGDING_11_ASSET_RECEIVE);
	}

	public void sendCheckin(Contract contract, String selectDate) {
		Contract exist = contractMapper.getById(contract.getId());
		exist.setSendCheckinStatus(true);
		exist.setSendCheckinDate(DateUtil.string2Timestamp(selectDate,
				DateUtil.PATTERN_DATE));
		exist.setOperateDate(DateUtil.currentTimestamp());
		contractMapper.update(exist);
	}

	public void back(Contract contract, String selectDate) {
		Contract exist = contractMapper.getById(contract.getId());
		exist.setBackStatus(true);
		exist.setBackDate(DateUtil.string2Timestamp(selectDate,
				DateUtil.PATTERN_DATE));
		exist.setOperateDate(DateUtil.currentTimestamp());
		contractMapper.update(exist);
	}
}
