package com.u2u.ibms.web.bill.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.common.component.dingding.DingDingAuthUtil;
import com.u2u.framework.base.BaseService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.Constants;
import com.u2u.ibms.common.beans.BillCheck;
import com.u2u.ibms.common.beans.Contract;
import com.u2u.ibms.common.beans.Income;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.Pays;
import com.u2u.ibms.common.mapper.BillCheckMapper;
import com.u2u.ibms.common.mapper.ContractMapper;
import com.u2u.ibms.common.mapper.IncomeMapper;
import com.u2u.ibms.common.mapper.OrderMapper;
import com.u2u.ibms.common.mapper.PaysMapper;
import com.u2u.ibms.common.util.CommonIdGenerator;
import com.u2u.ibms.web.bill.bean.Bill;
import com.u2u.ibms.web.bill.condition.BillCondition;
import com.u2u.ibms.web.bill.mapper.BillMapper;
import com.u2u.ibms.web.order.service.OrderService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class BillService extends BaseService {

	@Autowired
	private BillMapper billMapper;

	@Autowired
	private ContractMapper contractMapper;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private BillCheckMapper billCheckMapper;

	@Autowired
	private IncomeMapper incomeMapper;

	@Autowired
	private PaysMapper paysMapper;

	public void insert(Bill asset) {
		billMapper.insert(asset);
	}

	public Bill getById(int id) {
		return billMapper.getById(id);
	}

	public void update(Bill bill) {
		Contract contract = contractMapper.getById(bill.getContractId());
		if (contract == null) {
			throw new RuntimeException("没有选择合同信息!");
		}
		
		Bill exist = billMapper.getById(bill.getId());
		exist.setBillingStatus(bill.isBillingStatus());
		exist.setStatus(true);
		exist.setContractId(bill.getContractId());
		exist.setBillCheckId(bill.getBillCheckId());
		exist.setDeposit(bill.getDeposit());
		System.out.println("getDeposit(): " + bill.getDeposit());
		exist.setOperateDate(DateUtil.currentTimestamp());
		billMapper.update(exist);

		Order order = orderService.getById(contract.getOrderId());
		
		BillCheck billCheck = billCheckMapper.getById(bill.getBillCheckId());

		Income income = incomeMapper.getByBillId(bill.getId());
		int incomeOpe = 0;
		if (income == null) {
			incomeOpe = 0;
			income = new Income();
			income.setCreateDate(DateUtil.currentTimestamp());
		} else {
			incomeOpe = 1;
		}
		if (bill.getDeposit() < 2) {
			income.setType(0);
			income.setAmount(order.getDeposit());
			order.setLeftDeposit(Float.valueOf(exist.getAmount()));
			order.setDepositCheck(true);
			order.setStatus(Constants.ORDER_9_CONTRACT_BILL_CHECK);
			order.setOperateDate(DateUtil.currentTimestamp());
			orderMapper.update(order);
			DingDingAuthUtil.send(Constants.DINGDING_10_ASSET_SEND);
		} else {
			if (billCheck == null) {
				throw new RuntimeException("没有选择账单信息!");
			}
			income.setType(1);
			income.setAmount(billCheck.getAllAmount());
			billCheck.setPayAmount(Float.valueOf(exist.getAmount()));
			billCheck.setPayStatus(1);
			billCheck.setOperateDate(DateUtil.currentTimestamp());
			billCheckMapper.update(billCheck);
		}

		income.setOrderId(order.getId());
		
		//Debug, modified RentType to RentPersonType
		if (order.getRentPersonType() == 0) {
			income.setCustomer(order.getRentPersonInfo().getName());
		} else {
			income.setCustomer(order.getRentCompanyInfo().getLegalName());
		}
		income.setBillId(exist.getId());
		income.setPayAmount(Float.valueOf(exist.getAmount()));
		income.setInvoice(true);
		income.setInvoiceStatus(exist.isBillingStatus());
		income.setOperateDate(DateUtil.currentTimestamp());
		if (incomeOpe == 0) {
			incomeMapper.insert(income);
		} else {
			incomeMapper.update(income);
		}

		Pays pay = new Pays();
		pay.setPayId(CommonIdGenerator.generatePayId());
		pay.setPaySource(1);
		if (bill.getDeposit() < 2) {
			pay.setType(1);
		} else {
			pay.setType(2);
			pay.setBillCheckId(exist.getBillCheckId());
		}
		pay.setOrderId(order.getId());
		pay.setUserId(0);
		pay.setAmount(Float.valueOf(exist.getAmount()));
		pay.setCreateDate(DateUtil.currentTimestamp());
		pay.setOperateDate(DateUtil.currentTimestamp());
		paysMapper.insert(pay);

		DingDingAuthUtil.send(Constants.DINGDING_3_PROJECT_HANDLE);
	}

	public void delete(int id) {
		billMapper.delete(id);
	}

	public List<Bill> getAll(BillCondition condition, RowBounds rb) {
		return billMapper.getAll(
				getStringCondition(condition.getBankSerialNumber()),
				getStringCondition(condition.getAccountNum()),
				getStringCondition(condition.getAccountName()),
				getStringCondition(condition.getCusName()),
				getBooleanCondition(condition.getStatusId()),
				getBooleanCondition(condition.getBillingStatusId()), rb,
				getStringCondition(condition.getCusNum()),
				getStartDate(condition), getEndDate(condition));
	}
	
	public Bill getOne(String BankSerialNumber) {
		return billMapper.getOne(BankSerialNumber);
	}	

	public void updateBill(Bill bill) {
		billMapper.update(bill);
	}
}
