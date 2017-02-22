package com.u2u.ibms.rest.bill.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taobao.api.internal.util.StringUtils;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.BillCheck;
import com.u2u.ibms.common.beans.UserInfo;
import com.u2u.ibms.common.mapper.BillCheckMapper;
import com.u2u.ibms.common.mapper.UserInfoMapper;
import com.u2u.ibms.rest.bill.vo.BillListResponse;
import com.u2u.ibms.rest.bill.vo.HistoryBillRequest;
import com.u2u.ibms.rest.bill.vo.HistoryBillResponse;

@Service
public class MobileBillService {
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private BillCheckMapper billCheckMapper;

	public List<BillListResponse> getBillList(String username) throws Exception {
		if (null == username) {
			throw new Exception("参数非法");
		}
		UserInfo userinfo = userInfoMapper.getUserByUserName(username);
		List<BillCheck> billChecks = billCheckMapper.getLastOrder(userinfo);
		List<BillListResponse> list = new ArrayList<BillListResponse>();
		for (BillCheck billCheck : billChecks) {
			BillListResponse response = new BillListResponse();
			response.setOrderNo(billCheck.getOrderId() + "");
			response.setTotalUsedTime(billCheck.getUseDuration() + "");// 当月累计使用时长(小时)
			response.setCurrentTotalCost(billCheck.getAllAmount() + "");// 当月总费用
			list.add(response);
		}
		return list;
	}

	// public BillResponse getBillByBillNo(BillRequest request) throws Exception
	// {
	// if(null == request.getBillNo()){
	// throw new Exception("参数非法");
	// }
	// BillCheck billCheck =
	// billCheckMapper.getById(Integer.parseInt(request.getBillNo()));
	// BillResponse response =null;
	// response= new BillResponse();
	// response.setAmount(billCheck.getAllAmount()+"");//金额
	// response.setPayStatus(billCheck.getPayStatus()+"");//支付状态
	// response.setAccountPeriod(billCheck.getOutdate()+""); //账期
	// response.setAccountPeriodStatus("");//账期状态(已 、未出账)
	// response.setPayTime(""); //缴费时间
	//
	// return response;
	// }

	public List<HistoryBillResponse> getHistoryBill(HistoryBillRequest request)
			throws Exception {
		if(StringUtils.isEmpty(request.getPage())||StringUtils.isEmpty(request.getPageSize())){
			throw new Exception("参数错误");
		} 
		int currentPate = Integer.parseInt(request.getPage())-1;
		int pageSize = Integer.parseInt(request.getPageSize());
		List<BillCheck> billChecks = billCheckMapper.getAll(new RowBounds(currentPate,pageSize),Integer.parseInt(request.getOrderId()),null,null,null,null);
		HistoryBillResponse historyBillResponse = null;
		List<HistoryBillResponse> list = new ArrayList<HistoryBillResponse>();
		for (BillCheck billCheck : billChecks) {
			historyBillResponse = new HistoryBillResponse();
			historyBillResponse.setId(billCheck.getId());
			historyBillResponse.setAmount(billCheck.getAllAmount() + "");// 金额
			historyBillResponse.setPayStatus(billCheck.getPayStatus() + "");// 支付状态
			historyBillResponse.setAccountPeriod(billCheck.getCurrentTermDate()
					+ ""); // 账期
			if (billCheck.isStatus()) {
				historyBillResponse.setAccountPeriodStatus("已出账");// 账期状态(已出账、未出账)
			} else {
				historyBillResponse.setAccountPeriodStatus("未出账");// 账期状态(已出账、未出账)
			}

			historyBillResponse.setPayTime(DateUtil.timestamp2String(
					billCheck.getOperateDate(), DateUtil.PATTERN_STANDARD));// 缴费时间
			list.add(historyBillResponse);
		}
		return list;

	}
}
