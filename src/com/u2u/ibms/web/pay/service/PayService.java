package com.u2u.ibms.web.pay.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.Pays;
import com.u2u.ibms.common.beans.UserInfo;
import com.u2u.ibms.common.mapper.ContractMapper;
import com.u2u.ibms.common.mapper.IncomeMapper;
import com.u2u.ibms.common.mapper.OrderMapper;
import com.u2u.ibms.common.mapper.PaysMapper;
import com.u2u.ibms.common.mapper.UserInfoMapper;
import com.u2u.ibms.web.order.service.OrderService;
import com.u2u.ibms.web.pay.condition.PayCondition;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PayService extends BaseService {

	@Autowired
	private PaysMapper billMapper;

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
		billMapper.insert(asset);
	}

	public Pays getById(int id) {
		return billMapper.getById(id);
	}


	public List<Pays> getAll(PayCondition condition, RowBounds rb) {
		UserInfo userInfo = userInfoMapper.getUserByUserName(condition.getUserName());
		if(userInfo!=null){
			System.out.println("111111111111111111======" + userInfo.getId());
			condition.setUserId(userInfo.getId());
		}
		List<Pays> pays = billMapper.getAllWithUser(
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
}
