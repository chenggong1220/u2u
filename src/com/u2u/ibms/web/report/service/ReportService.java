package com.u2u.ibms.web.report.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.mapper.AuthorizeMapper;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.AssetType;
import com.u2u.ibms.common.beans.Combo;
import com.u2u.ibms.common.beans.Contract;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.Pays;
import com.u2u.ibms.common.beans.Project;
import com.u2u.ibms.common.beans.RentCompanyInfo;
import com.u2u.ibms.common.beans.RentPersonInfo;
import com.u2u.ibms.common.beans.SubOrder;
import com.u2u.ibms.common.mapper.AssetTypeMapper;
import com.u2u.ibms.common.mapper.ComboMapper;
import com.u2u.ibms.common.mapper.ContractMapper;
import com.u2u.ibms.common.mapper.PaysMapper;
import com.u2u.ibms.common.mapper.ProjectMapper;
import com.u2u.ibms.common.mapper.SubOrderMapper;
import com.u2u.ibms.web.order.service.OrderService;
import com.u2u.ibms.web.report.bean.HasPay;
import com.u2u.ibms.web.report.bean.HasRent;
import com.u2u.ibms.web.report.bean.RentDetail;
import com.u2u.ibms.web.report.condition.ReportCondition;
import com.u2u.ibms.web.shop.mapper.ShopMapper;

@Service
@Transactional
public class ReportService extends BaseService {

	@Autowired
	private ContractMapper contractMapper;
	@Autowired
	private SubOrderMapper subOrderMapper;
	@Autowired
	private AssetTypeMapper assetTypeMapper;
	@Autowired
	private PaysMapper paysMapper;
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private OrderService orderService;
	@Autowired
	private AuthorizeMapper authorizeMapper;
	@Autowired
	private ComboMapper comboMapper;
	@Autowired
	private ShopMapper shopMapper;

	public List<HasRent> getHasRent(ReportCondition condition, RowBounds rb) {
		List<Contract> contracts = contractMapper.getAll(rb, null, null, null,
				null, null, null, getStartDate(condition),
				getEndDate(condition),null,null,null,null,null,null);
		Map<AssetType, Integer> map = new HashMap<>();
		for (Contract contract : contracts) {
			List<SubOrder> sorders = subOrderMapper.getByOrderId(
					new RowBounds(), contract.getOrderId());
			for (SubOrder subOrder : sorders) {
				AssetType atype = assetTypeMapper.getById(subOrder
						.getAssetTypeId());
				if (map.containsKey(atype)) {
					Integer oriCount = map.get(atype);
					map.put(atype, oriCount + subOrder.getCount());
				} else {
					map.put(atype, subOrder.getCount());
				}
			}
		}

		List<HasRent> hasrents = new ArrayList<>();
		for (AssetType assetType : map.keySet()) {
			HasRent hasrent = new HasRent();
			hasrent.setName(assetType.getModel());
			hasrent.setValue(map.get(assetType));
			hasrents.add(hasrent);
		}

		return hasrents;
	}

	public List<HasPay> getHasPay(ReportCondition condition, RowBounds rb) {
		List<Pays> pays = paysMapper.getAll(rb, null, getStartDate(condition),
				getEndDate(condition));
		Map<String, Float> map = new HashMap<>();
		map.put("会员费", 0f);
		map.put("保证金", 0f);
		map.put("租金", 0f);
		for (Pays pay : pays) {

			switch (pay.getType()) {
			case 0:
				Float oriCount = map.get("会员费");
				map.put("会员费", oriCount + pay.getAmount());
				break;
			case 1:
				Float oriCount1 = map.get("保证金");
				map.put("保证金", oriCount1 + pay.getAmount());
				break;
			case 2:
				Float oriCount2 = map.get("租金");
				map.put("租金", oriCount2 + pay.getAmount());
				break;

			default:
				break;
			}
		}

		List<HasPay> hasrents = new ArrayList<>();
		for (String assetType : map.keySet()) {
			HasPay hasrent = new HasPay();
			hasrent.setName(assetType);
			hasrent.setValue(map.get(assetType));
			hasrents.add(hasrent);
		}

		return hasrents;
	}

	public List<RentDetail> getAllRent(ReportCondition condition, RowBounds rb) {
		List<RentDetail> rdetail = new ArrayList<>();
		List<Contract> contracts = contractMapper.getAll(rb, null, null, null,
				null, null, null, getStartDate(condition),
				getEndDate(condition),null,null,null,null,null,null);
		int id = 1;
		for (Contract contract : contracts) {
			RentDetail rentDetail = new RentDetail();
			rentDetail.setId(id++);
			rentDetail.setContractNo(contract.getContractId());
			Project project = projectMapper.getById(contract.getProjectId());
			rentDetail.setProjectNo(project.getProjectId());

			Order order = orderService.getById(contract.getOrderId());

			rentDetail.setStartDate(DateUtil.timestamp2String(
					order.getStartDate(), DateUtil.PATTERN_DATE));

			rentDetail.setRentDate(order.getRentDate() + "月");

			Float deposit = order.getDeposit();
			String operator = order.getOperator();
			// // authorizeMapper.get
			// UserInfo userInfo = userInfoMapper.getById(
			// // order.getOperatorId());
			User user = authorizeMapper.getUserById(order.getOperatorId());
			rentDetail.setShop(shopMapper.getById(user.getShopId()).getName());
			List<SubOrder> suborders = subOrderMapper.getByOrderId(
					new RowBounds(), contract.getOrderId());
			Float sr = 0f;
			for (SubOrder subOrder : suborders) {
				Combo combo = comboMapper.getById(subOrder.getComboId());
				sr += combo.getAmount() * subOrder.getCount();
			}

			rentDetail.setShouldRev(sr + "");
			List<Pays> pays = paysMapper.getAll(new RowBounds(),
					contract.getOrderId(), null, null);
			Float allPaid = 0f;

			for (Pays pays2 : pays) {
				allPaid += pays2.getAmount();
			}

			if (order.getRentPersonType() == 0) {
				RentPersonInfo rentPersonInfo = order.getRentPersonInfo();
				rentDetail.setUsername(rentPersonInfo.getName());
				rentDetail.setMobile(rentPersonInfo.getMobile());
			} else {
				RentCompanyInfo rentCompanyInfo = order.getRentCompanyInfo();
				rentDetail.setUsername(rentCompanyInfo.getLegalName());
				rentDetail.setMobile(rentCompanyInfo.getLegalMobile());
			}

			rentDetail.setHasRev(allPaid + "");
			rentDetail.setDeposit(deposit + "");
			rentDetail.setManager(operator);
			rdetail.add(rentDetail);
		}
		return rdetail;
	}
}
