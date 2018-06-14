package com.u2u.ibms.web.asset.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.exception.ServiceAuthorizeException;
import com.u2u.framework.sys.authorize.security.SecurityContextUtil;
import com.u2u.framework.sys.authorize.service.AuthorizeService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.AssetManagerDelay;
import com.u2u.ibms.common.mapper.AssetManagerDelayMapper;
import com.u2u.ibms.web.order.service.OrderService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AssetManagerDelayService extends BaseService {

	@Autowired
	private AssetManagerDelayMapper assetManagerDelayMapper;

	@Autowired
	private OrderService orderService;

	@Autowired
	private AuthorizeService authorizeService;

	public List<AssetManagerDelay> getAll(String orderId, RowBounds rb) {
		List<AssetManagerDelay> assetManagerDelays = assetManagerDelayMapper
				.getAll(getIntegerCondition(orderId), rb);
		for (AssetManagerDelay assetManagerDelay : assetManagerDelays) {
			assetManagerDelay.setOrder(orderService.getById(assetManagerDelay
					.getOrderId()));
		}
		return assetManagerDelays;
	}

	public AssetManagerDelay getById(int id) {
		AssetManagerDelay assetManagerDelay = assetManagerDelayMapper
				.getById(id);
		return assetManagerDelay;
	}

	public void insert(AssetManagerDelay assetManagerDelay, String selectDate)
			throws ServiceAuthorizeException {
		assetManagerDelay.setUserId(authorizeService.getUser(
				SecurityContextUtil.getUserName()).getId());
		assetManagerDelay.setDelayDate(DateUtil.string2Timestamp(selectDate,
				DateUtil.PATTERN_DATE));
		assetManagerDelay.setCreateDate(DateUtil.currentTimestamp());
		assetManagerDelay.setOperateDate(DateUtil.currentTimestamp());
		assetManagerDelayMapper.insert(assetManagerDelay);
	}

	public void delete(int id) {
		assetManagerDelayMapper.delete(id);
	}
}
