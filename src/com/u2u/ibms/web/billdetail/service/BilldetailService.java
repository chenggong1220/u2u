package com.u2u.ibms.web.billdetail.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.Asset;
import com.u2u.ibms.common.beans.BillCheck;
import com.u2u.ibms.common.beans.Combo;
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
import com.u2u.ibms.web.billdetail.bean.Billdetail;
import com.u2u.ibms.web.billdetail.condition.BilldetailCondition;
import com.u2u.ibms.web.billdetail.mapper.BilldetailMapper;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class BilldetailService extends BaseService {

	@Autowired
	private BilldetailMapper billdetailMapper;

	@Autowired
	private AssetMapper assetMapper;

	@Autowired
	private ContractMapper contractMapper;

	@Autowired
	private ComboMapper comboMapper;

	@Autowired
	private AssetTypeMapper assetTypeMapper;

	@Autowired
	private SubOrderMapper subOrderMapper;

	@Autowired
	private BillCheckMapper billCheckMapper;

	@Autowired
	private OrderMapper orderMapper;

	public void insert(Billdetail asset) {
		billdetailMapper.insert(asset);
		calculateBills(asset);
	}

	public Billdetail getById(int id) {
		return billdetailMapper.getById(id);
	}

	public void update(Billdetail asset) {
		billdetailMapper.update(asset);
	}

	public void delete(int id) {
		billdetailMapper.delete(id);
	}

	public void deleteByDeviceno(String deviceNo) {
		billdetailMapper.deleteByDeviceNo(deviceNo);
	}

	public List<Billdetail> getAll(BilldetailCondition condition, RowBounds rb) {
		List<Billdetail> billdetails = billdetailMapper.getAll(
				getStringCondition(condition.getCustomer()),
				getStringCondition(condition.getDeviceno()),
				getStartDate(condition), getEndDate(condition), rb,
				getStringCondition(condition.getType()));
		return billdetails;
	}

	public List<Billdetail> getByDeviceNoAndDate(String deviceno,
			String devicedate) {
		return billdetailMapper
				.getByDevicenoAndDevicedate(deviceno, devicedate);
	}

	public void calculateBills(Billdetail billDetail) {
		Asset asset = assetMapper.getByAssetId(billDetail.getDeviceno());
		List<Contract> contracts = contractMapper.getAll(new RowBounds(),
				billDetail.getContract(), null, null, null, null, null, null,
				null, null, null, null, null,null,null);
		if (CollectionUtils.isNotEmpty(contracts)) {
			Contract contract = contracts.get(0);
			Order order = orderMapper.getById(contract.getOrderId());
			List<SubOrder> subOrders = subOrderMapper.getByOrderId(
					new RowBounds(), contract.getOrderId());
			
			for (SubOrder suborder : subOrders) {
				if (suborder.getAssetTypeId() == asset.getAssetTypeId()) {
					Timestamp happenDate = DateUtil.string2Timestamp(
							billDetail.getDevicedate(), DateUtil.PATTERN_DATE);
					int currentTerm = getTerms(order.getStartDate(), happenDate);
					BillCheck billCheck = billCheckMapper
							.getByContractIdAndCurrentTerm(contract.getId(),
									currentTerm);

					if (billCheck == null) { 
						billCheck = new BillCheck();
						billCheck.setOrderId(contract.getOrderId());
						billCheck.setContractId(contract.getId());
						billCheck.setUserId(order.getUserId());

						billCheck.setCurrentTerm(currentTerm);
						billCheck
								.setCurrentTermDate(billDetail.getDevicedate());
						billCheck.setTerms(order.getRentDate());
						billCheck.setOutdate(null);
//						billCheck.setEnddate(new Timestamp(happenDate.getTime()
//								+ 30 * 24 * 60 * 60 * 1000));
						billCheck.setUseTime(Float.valueOf(billDetail
								.getRunningtime()));
						billCheck.setRepairTime(Float.valueOf(billDetail
								.getNochargingtime()));
						billCheck.setInterest(0);
						billCheck.setPayStatus(0);
						billCheck.setPayAmount(0);
						billCheck.setStatus(false);

						Combo combo = comboMapper
								.getById(suborder.getComboId());
						if (order.getRentType() == 0) {
							billCheck.setRentType(0);
							billCheck.setRentAmount(combo.getAmount()
									* Float.valueOf(billDetail
											.getChargingtime()));
						} else {
							//Start: 修改租赁类型Bug,by SUNZHE, 2017-02-06
							//1. 原来是setRentType(0),改为1
							//2. 修改原来按月租赁类型租金为考虑所有设备，原来计算的的只是一台设备的租金
							billCheck.setRentType(1);
							billCheck.setRentAmount(combo.getAmount()*suborder.getCount());
							//End: 修改租赁类型Bug,原来是setRentType(0),by SUNZHE, 2017-02-06
						}
						billCheck.setAllAmount(billCheck.getRentAmount());
						billCheck.setCreateDate(DateUtil.currentTimestamp());
						billCheck.setOperateDate(DateUtil.currentTimestamp());
						billCheckMapper.insert(billCheck);
					} else {
						billCheck.setUseTime(billCheck.getUseTime()
								+ Float.valueOf(billDetail.getRunningtime()));
						billCheck.setRepairTime(billCheck.getRepairTime()
								+ Float.valueOf(billDetail
										.getNochargingtime()));

						Combo combo = comboMapper
								.getById(suborder.getComboId());
						if (order.getRentType() == 0) {
							billCheck.setRentAmount(billCheck.getRentAmount()
									+ combo.getAmount()
									* Float.valueOf(billDetail
											.getChargingtime()));
						} else {
							//Start: 按月租赁不需要重复计算租金，Remove by SUNZHE, 2017-02-06
							//billCheck.setRentAmount(billCheck.getRentAmount()
							//		+ combo.getAmount());
							//End：按月租赁不需要重复计算租金
						}
						billCheck.setAllAmount(billCheck.getRentAmount());
						billCheck.setOperateDate(DateUtil.currentTimestamp());
						billCheckMapper.update(billCheck);
					}
					return;
				}
			}
		}
	}

	public static int getTerms(Timestamp start, Timestamp current) {
		int startYear = getYear(start);
		int startMonth = getMonth(start);
		int currentYear = getYear(current);
		int currentMonth = getMonth(current);

		if (currentYear - startYear == 0) {
			return currentMonth - startMonth + 1;
		} else if (currentYear - startYear == 1) {
			return 12 - startMonth + currentMonth + 1;
		} else {
			return 12 - startMonth + currentMonth + 1 + 12
					* (currentYear - startYear - 1);
		}
	}

	public static int getYear(Timestamp current) {
		return Integer.valueOf(DateUtil.timestamp2String(current, "yyyy"));
	}

	public static int getMonth(Timestamp current) {
		return Integer.valueOf(DateUtil.timestamp2String(current, "MM"));
	}

}
