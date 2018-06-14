package com.u2u.ibms.web.asset.service;

import java.text.MessageFormat;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.exception.ServiceBusinessException;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.Constants;
import com.u2u.ibms.common.beans.Asset;
import com.u2u.ibms.common.beans.AssetManagerRentAsset;
import com.u2u.ibms.common.beans.Contract;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.mapper.AssetManagerRentAssetMapper;
import com.u2u.ibms.common.mapper.AssetMapper;
import com.u2u.ibms.common.mapper.ContractMapper;
import com.u2u.ibms.common.mapper.OrderMapper;
import com.u2u.ibms.web.order.service.OrderService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AssetManagerRentAssetService extends BaseService {

	@Autowired
	private AssetManagerRentAssetMapper assetManagerRentAssetMapper;

	@Autowired
	private OrderService orderService;

	@Autowired
	private AssetService assetService;

	@Autowired
	private AssetMapper assetMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private ContractMapper contractMapper;

	public List<AssetManagerRentAsset> getAll(String orderId, RowBounds rb) {
		List<AssetManagerRentAsset> assetManagerRentAssets = assetManagerRentAssetMapper
				.getAll(getIntegerCondition(orderId), rb);
		for (final AssetManagerRentAsset assetManagerRentAsset : assetManagerRentAssets) {
			this.convert(assetManagerRentAsset);
		}
		return assetManagerRentAssets;
	}

	public AssetManagerRentAsset getById(int id) {
		AssetManagerRentAsset assetManagerRentAsset = assetManagerRentAssetMapper
				.getById(id);
		return this.convert(assetManagerRentAsset);
	}

	private AssetManagerRentAsset convert(
			final AssetManagerRentAsset assetManagerRentAsset) {
		assetManagerRentAsset.setAsset(assetService
				.getById(assetManagerRentAsset.getAssetId()));
		assetManagerRentAsset.setOrder(orderService
				.getById(assetManagerRentAsset.getOrderId()));
		return assetManagerRentAsset;
	}

	public void insert(AssetManagerRentAsset assetManagerRentAsset,
			String selectDate) throws ServiceBusinessException {
		AssetManagerRentAsset exist = assetManagerRentAssetMapper
				.getByOrderIdAndAssetId(assetManagerRentAsset.getOrderId(),
						assetManagerRentAsset.getAssetId());
		if (exist != null) {
			Asset asset = assetService.getById(assetManagerRentAsset
					.getAssetId());
			throw new ServiceBusinessException(MessageFormat.format(
					"已经存在编号为[{0}]的设备，不允许重复添加!", asset.getCode()));
		}

		assetManagerRentAsset.setReceiveStatus(false);
		assetManagerRentAsset.setReceiveDate(null);
		assetManagerRentAsset.setRentDate(DateUtil.string2Timestamp(selectDate,
				DateUtil.PATTERN_DATE));
		assetManagerRentAsset.setCreateDate(DateUtil.currentTimestamp());
		assetManagerRentAsset.setOperateDate(DateUtil.currentTimestamp());
		assetManagerRentAssetMapper.insert(assetManagerRentAsset);

		Asset asset = assetMapper.getById(assetManagerRentAsset.getAssetId());
		asset.setRent(0);
		asset.setOperateDate(DateUtil.currentTimestamp());
		assetMapper.update(asset);
	}

	public void receive(String contractId, String rentAssetId, String selectDate) {
		AssetManagerRentAsset exist = assetManagerRentAssetMapper
				.getById(getIntegerCondition(rentAssetId));
		exist.setReceiveStatus(true);
		exist.setReceiveDate(DateUtil.string2Timestamp(selectDate,
				DateUtil.PATTERN_DATE));
		exist.setOperateDate(DateUtil.currentTimestamp());
		assetManagerRentAssetMapper.update(exist);

		Contract contract = contractMapper
				.getById(getIntegerCondition(contractId));
		List<AssetManagerRentAsset> assets = assetManagerRentAssetMapper
				.getAll(contract.getOrderId(), new RowBounds());
		boolean receive = true;
		for (AssetManagerRentAsset asset : assets) {
			receive = receive && asset.isReceiveStatus();
		}

		if (receive) {
			contract.setSendCheckinStatus(true);
			contract.setSendCheckinDate(DateUtil.currentTimestamp());
			contractMapper.update(contract);

			Order order = orderMapper.getById(contract.getOrderId());
			order.setStatus(Constants.ORDER_11_ASSET_RECEIVE);
			order.setOperateDate(DateUtil.currentTimestamp());
			orderMapper.update(order);
		}
	}

	public void delete(int id) {
		AssetManagerRentAsset rentAsset = assetManagerRentAssetMapper
				.getById(id);
		assetManagerRentAssetMapper.delete(id);
		Asset asset = assetMapper.getById(rentAsset.getAssetId());
		asset.setRent(1);
		asset.setOperateDate(DateUtil.currentTimestamp());
		assetMapper.update(asset);
	}
}
