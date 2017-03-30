package com.u2u.ibms.web.pay.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.Pays;
import com.u2u.ibms.common.beans.UserInfo;
import com.u2u.ibms.common.mapper.ContractMapper;
import com.u2u.ibms.common.mapper.IncomeMapper;
import com.u2u.ibms.common.mapper.OrderMapper;
import com.u2u.ibms.common.mapper.PaysMapper;
import com.u2u.ibms.common.mapper.UserInfoMapper;
import com.u2u.ibms.web.bill.bean.Bill;
import com.u2u.ibms.web.bill.mapper.BillMapper;
import com.u2u.ibms.web.bill.service.BillService;
import com.u2u.ibms.web.order.service.OrderService;
import com.u2u.ibms.web.pay.condition.PayCondition;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PayService extends BaseService {

	@Autowired
	private BillMapper billMapper;

	@Autowired
	private ContractMapper contractMapper;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderMapper orderMapper;


	@Autowired
	private IncomeMapper incomeMapper;

	@Autowired
	private PaysMapper paysMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;

	public void insert(Pays asset) {
		paysMapper.insert(asset);
	}

	public Pays getById(int id) {
		return paysMapper.getById(id);
	}


	public List<Pays> getAll(PayCondition condition, RowBounds rb) {
		UserInfo userInfo = userInfoMapper.getUserByUserName(condition.getUserName());
		if(userInfo!=null){
			condition.setUserId(userInfo.getId());
		}
		List<Pays> pays = paysMapper.getAllWithUser(
				rb,
				condition.getUserId(),
				null,
				null);
		for (Pays pays2 : pays) {
			UserInfo tmpUser =	userInfoMapper.getById(pays2.getUserId());
			Order tmpOrder = orderService.getById(pays2.getOrderId());
			if(tmpUser != null){
				pays2.setUserName(tmpUser.getUsername());
				pays2.setPaySourceStr("支付宝");
			}else if(tmpOrder != null){
				//pays2.setUserName(u.getUsername());
				
				if(tmpOrder.getRentPersonType() == 0)
				{
					pays2.setUserName(tmpOrder.getRentPersonInfo().getName());
				}else{
					pays2.setUserName(tmpOrder.getRentCompanyInfo().getLegalName());
				}
				pays2.setPaySourceStr("银行汇款");
			}
		}
	return pays;
	}
	
	//
	/**
	 * 
	 * MethodName: convertToBillCheck <br>
	 * @UpdateDate 2017-03-12 <br>
	 * @Description: Pay记录变成核销项 <br>
	 * @Updated Logic:
	 * 1. 一部分Alipay的记录没有自动核销，通过该方法把记录插入T_BILL作为的核销项
	 * 2. Web的缴费查询界面调用此方法
	 * @author SUNZHE
	 */	
	
	@Autowired
	private BillService billService;

	public void convertToBillCheck(int id){
		Pays pay = paysMapper.getById(id);
		UserInfo userInfo = userInfoMapper.getById(pay.getUserId());
		
		Bill bill = new Bill();
		bill.setAccountNum(userInfo.getNickname() + "'s Alipay Account");
		bill.setAccountName(userInfo.getNickname());
		bill.setCusName(userInfo.getNickname());
		//bill.setCusNum("");
		bill.setDealDate(DateUtil.currentTimestamp2String(DateUtil.PATTERN_DATE));
		bill.setAmount(Float.toString(pay.getAmount()));
		bill.setBillingDate(DateUtil.timestamp2String(pay.getCreateDate(),DateUtil.PATTERN_STANDARD));
		bill.setFinancialNum(pay.getPayId());
		bill.setCreateDate(pay.getCreateDate());
		bill.setOperateDate(pay.getOperateDate());
		
		String bankSerialNumber = pay.getPayId();	//临时使用PayID代替Bank Serial Number, 未来应该用客户支付宝账号
		bill.setBankSerialNumber(bankSerialNumber);
		
		Bill existBill = billService.getOne(bankSerialNumber);
		if(existBill != null)
		{
			bill.setId(existBill.getId());
			billService.updateBill(bill);
		}else{
			billService.insert(bill);
		}
	}
}
