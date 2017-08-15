package com.u2u.ibms.web.contract.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
import com.u2u.ibms.common.mapper.ContractMapper;
import com.u2u.ibms.common.mapper.OrderMapper;
import com.u2u.ibms.common.mapper.SubOrderMapper;
import com.u2u.ibms.web.asset.condition.AssetCondition;
import com.u2u.ibms.web.asset.service.AssetManagerRentAssetService;
import com.u2u.ibms.web.asset.service.AssetService;
import com.u2u.ibms.web.contract.condition.ContractCondition;
import com.u2u.ibms.web.order.service.OrderService;
import com.u2u.ibms.web.project.service.ProjectService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ContractService extends BaseService {

	@Autowired
	private ContractMapper contractMapper;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private AssetService assetService;

	@Autowired
	private SubOrderMapper subOrderMapper;

	@Autowired
	private AssetManagerRentAssetService assetManagerRentAssetService;

	public List<Contract> getAll(RowBounds rb, ContractCondition condition) {
		
		List<Contract> contracts = contractMapper.getAll(rb,
				getStringCondition(condition.getContractId()),
				getBooleanCondition(condition.getSendStatus()),
				getBooleanCondition(condition.getBackStatus()),
				getStringCondition(condition.getStatus()),
				getBooleanCondition(condition.getCheckinStatus()),
				getBooleanCondition(condition.getSignoffStatus()), null, null,
				getStringCondition(condition.getContractType()),
				getStringCondition(condition.getOrderPerson()),
				getStringCondition(condition.getProjectId()),
				getStringCondition(condition.getRentType()),
				getStringCondition(condition.getProvinceId()),
				getStringCondition(condition.getAssetProvinceId()),
				getStringCondition(condition.getCustomerName()),
				getStringCondition(condition.getOperatorId()));

		for (final Contract contract : contracts) {
			convertToContract(contract);
		}
		
		return contracts;
	}

	public List<Contract> getSendAll(RowBounds rb,
			ContractCondition condition) {
		List<Contract> list = new ArrayList<Contract>();
		List<Contract> contracts = contractMapper.getAll(new RowBounds(),
				getStringCondition(condition.getContractId()),
				getBooleanCondition(condition.getSendStatus()),
				getBooleanCondition(condition.getBackStatus()),
				getStringCondition(condition.getStatus()),
				getBooleanCondition(condition.getCheckinStatus()),
				getBooleanCondition(condition.getSignoffStatus()), null, null,
				getStringCondition(condition.getContractType()), null, null,
				null, getStringCondition(condition.getProvinceId()),null,null,null);
		for (int i = 0; i < contracts.size(); i++) {
			Order order = orderService.getById(contracts.get(i).getOrderId());
			if (order.isDepositCheck()) {
				Contract contract = convertToContract(contracts.get(i));
				list.add(contract);
			}
		}
		Iterator<Contract> iterator = list.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			iterator.next();
			if (count >= rb.getOffset()
					&& count < (rb.getOffset() + rb.getLimit())) {
			} else {
				iterator.remove();
			}
			count++;
		}

		return list;
	}

	public Contract getById(int id) {
		Contract contract = contractMapper.getById(id);
		return convertToContract(contract);
	}

	private Contract convertToContract(final Contract contract) {
		Order order = orderService.getById(contract.getOrderId());
		if (order.getRentPersonType() == 0) {
			contract.setOrderName("个人：" + order.getRentPersonInfo().getName());
			contract.setCustomerName(order.getRentPersonInfo().getName());
			contract.setOrderProvince(
					order.getRentPersonInfo().getPersonProvince());
			contract.setOrderPerson(order.getRentPersonInfo().getName());
			contract.setOrderMobile(order.getRentPersonInfo().getMobile());
			contract.setLocation(order.getRentPersonInfo().getPersonProvince()
					+ order.getRentPersonInfo().getPersonCity()
					+ order.getRentPersonInfo().getAddress());
		} else {
			contract.setOrderName("企业：" + order.getRentCompanyInfo().getName());
			contract.setCustomerName(order.getRentCompanyInfo().getName());
			contract.setOrderProvince(
					order.getRentCompanyInfo().getCompanyProvince());
			contract.setOrderPerson(order.getRentCompanyInfo().getLegalName());
			contract.setOrderMobile(
					order.getRentCompanyInfo().getLegalMobile());
			contract.setLocation(order.getRentCompanyInfo().getCompanyProvince()
					+ order.getRentCompanyInfo().getCompanyCity()
					+ order.getRentCompanyInfo().getPostalAddress());
		}
		contract.setOrder(order);
		contract.setProject(projectService.getById(contract.getProjectId()));
		contract.setOrderDeposit(order.getDeposit());
		contract.setOrderLeftDeposit(order.getLeftDeposit());
		return contract;
	}

	public List<Asset> getAllAssets(int contractId) {
		Contract contract = contractMapper.getById(contractId);
		List<SubOrder> subOrders = subOrderMapper.getByOrderId(new RowBounds(),
				contract.getOrderId());
		List<Asset> rets = new ArrayList<Asset>();
		for (SubOrder subOrder : subOrders) {
			AssetCondition condition = new AssetCondition();
			condition.setAssetTypeId(subOrder.getAssetTypeId() + "");
			condition.setRent("true");
			List<Asset> assets = assetService.getAll(condition,
					new RowBounds());
			if (CollectionUtils.isNotEmpty(assets)) {
				rets.addAll(assets);
			}
		}
		return rets;
	}

	public List<AssetManagerRentAsset> getAllRentAsset(int contractId) {
		return assetManagerRentAssetService.getAll(
				contractMapper.getById(contractId).getOrderId() + "",
				new RowBounds());
	}

	public void insert(Contract contract) {
		contract.setOperateDate(DateUtil.currentTimestamp());
		contract.setCreateDate(DateUtil.currentTimestamp());
		contractMapper.insert(contract);
	}

	public void checkin(Contract contract, String selectDate) {
		Contract exist = contractMapper.getById(contract.getId());
		// exist.setStatus("已签收");
		exist.setCheckin(true);
		exist.setCheckinDate(
				DateUtil.string2Timestamp(selectDate, DateUtil.PATTERN_DATE));
		exist.setOperateDate(DateUtil.currentTimestamp());
		contractMapper.update(exist);

		Order order = orderMapper.getById(exist.getOrderId());
		order.setStatus(Constants.ORDER_7_CONTRACT_RECEIVE);
		order.setOperateDate(DateUtil.currentTimestamp());
		orderMapper.update(order);

		DingDingAuthUtil.send(Constants.DINGDING_8_CONTRACT_SIGNOFF);
	}

	public void signoff(Contract contract, String selectDate) {
		Contract exist = contractMapper.getById(contract.getId());
		exist.setStatus("生效");
		exist.setSignoff(true);
		exist.setSignoffDate(
				DateUtil.string2Timestamp(selectDate, DateUtil.PATTERN_DATE));
		exist.setOperateDate(DateUtil.currentTimestamp());
		contractMapper.update(exist);

		Order order = orderMapper.getById(exist.getOrderId());
		order.setStatus(Constants.ORDER_8_CONTRACT_SIGNOFF);
		order.setOperateDate(DateUtil.currentTimestamp());
		orderMapper.update(order);

		DingDingAuthUtil.send(Constants.DINGDING_9_CONTRACT_BILL_CHECK);
	}

	public void abandon(int id, int status) {
		Contract exist = contractMapper.getById(id);
		Order order = orderMapper.getById(exist.getOrderId());
		if (status == 0) {
			exist.setStatus("终止");
			order.setStatus("合同终止");
		} else {
			exist.setStatus("作废");
			order.setStatus("合同作废");
		}
		exist.setOperateDate(DateUtil.currentTimestamp());
		contractMapper.update(exist);

		order.setOperateDate(DateUtil.currentTimestamp());
		orderMapper.update(order);
	}
}
