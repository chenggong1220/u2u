package com.u2u.ibms.web.billcheck.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.Asset;
import com.u2u.ibms.common.beans.BillCheck;
import com.u2u.ibms.common.beans.Contract;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.SubOrder;
import com.u2u.ibms.common.mapper.AssetMapper;
import com.u2u.ibms.common.mapper.AssetTypeMapper;
import com.u2u.ibms.common.mapper.BillCheckMapper;
import com.u2u.ibms.common.mapper.ComboMapper;
import com.u2u.ibms.common.mapper.ContractMapper;
import com.u2u.ibms.common.mapper.OrderMapper;
import com.u2u.ibms.common.mapper.SubOrderMapper;
import com.u2u.ibms.web.billcheck.condition.BillCheckCondition;
import com.u2u.ibms.web.billcheck.vo.AssetListResponse;
import com.u2u.ibms.web.billdetail.bean.Billdetail;
import com.u2u.ibms.web.billdetail.mapper.BilldetailMapper;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class BillCheckService extends BaseService {

	@Autowired
	private BillCheckMapper billCheckMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private ContractMapper contractMapper;

	@Autowired
	private BilldetailMapper billdetailMapper;

	@Autowired
	private AssetMapper assetMapper;
	@Autowired
	private AssetTypeMapper assetTypeMapper;

	@Autowired
	private SubOrderMapper subOrderMapper;

	@Autowired
	private ComboMapper comboMapper;

	public List<BillCheck> getAll(RowBounds rb, BillCheckCondition condition) {
		List<BillCheck> billChecks = billCheckMapper.getAll(rb,
				getIntegerCondition(condition.getOrderId()),
				getIntegerCondition(condition.getContractId()), null,
				getStringCondition(condition.getPayStatus()),
				getStringCondition(condition.getTerms()));
		for (final BillCheck billCheck : billChecks) {
			this.converToBillCheck(billCheck);
		}
		return billChecks;
	}

	public BillCheck getById(int id) {
		return converToBillCheck(billCheckMapper.getById(id));
	}

	public BillCheck converToBillCheck(final BillCheck billCheck) {
		billCheck.setOrder(orderMapper.getById(billCheck.getOrderId()));
		billCheck
				.setContract(contractMapper.getById(billCheck.getContractId()));
		return billCheck;
	}

	public List<AssetListResponse> getAssetListResponse(Integer contractId) {
		Contract contract = contractMapper.getById(contractId);
		List<Billdetail> details = billdetailMapper.getAllByContractId(
				contract.getContractId(), new RowBounds());
		List<AssetListResponse> responses = new ArrayList<AssetListResponse>();
		for (Billdetail detail : details) {
			AssetListResponse response = new AssetListResponse();
			Asset asset = assetMapper.getByAssetId(detail.getDeviceno());
			response.setModel(assetTypeMapper.getById(asset.getAssetTypeId())
					.getModel());
			response.setAssetId(detail.getDeviceno());
			response.setDeviceDate(detail.getDevicedate());	//为在账单详情列表中显示设备运行日期，by SUNZHE, 2017-02-06
			response.setUseTime(detail.getChargingtime());
			response.setRepairTime(detail.getNochargingtime());
			Order order = orderMapper.getById(contract.getOrderId());
			List<SubOrder> subs = subOrderMapper.getByOrderId(new RowBounds(),
					order.getId());
			for (SubOrder sub : subs) {
				if (sub.getAssetTypeId() == asset.getAssetTypeId()) {
					Float amount = comboMapper.getById(sub.getComboId())
							.getAmount();
					try {
						if (order.getRentType() == 0) {
							response.setAmount(String.valueOf(Float
									.valueOf(detail.getChargingtime()) * amount));
						} else {
							//response.setAmount(String.valueOf(amount));
							response.setAmount("0");
						}
					} catch (Exception e) {
						response.setAmount("0");
					}
				}
			}
			responses.add(response);
		}
		return responses;
	}

	public void insert(BillCheck billCheck) {
		billCheck.setCreateDate(DateUtil.currentTimestamp());
		billCheck.setOperateDate(DateUtil.currentTimestamp());
		billCheckMapper.insert(billCheck);
	}

	public void update(int id) {
		BillCheck exist = billCheckMapper.getById(id);
		exist.setStatus(true);
		Timestamp outdate = DateUtil.currentTimestamp();
		exist.setOutdate(outdate);
		exist.setEnddate(new Timestamp(outdate.getTime() + 30 * 24 * 60 * 60
				* 1000));
		exist.setOperateDate(DateUtil.currentTimestamp());
		billCheckMapper.update(exist);
	}

}
