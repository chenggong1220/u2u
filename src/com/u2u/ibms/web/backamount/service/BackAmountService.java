package com.u2u.ibms.web.backamount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.BackAmount;
import com.u2u.ibms.common.mapper.BackAmountMapper;

@Service
public class BackAmountService extends BaseService {

	@Autowired
	private BackAmountMapper backAmountMapper;

	public BackAmount getByContractId(int contractId) {
		return backAmountMapper.getByContractId(contractId);
	}

	public void insert(BackAmount backAmount) {
		BackAmount exist = backAmountMapper.getByContractId(backAmount
				.getContractId());
		if (exist != null) {
			exist.setAmount(backAmount.getAmount());
			exist.setRemark(backAmount.getRemark());
			exist.setOperateDate(DateUtil.currentTimestamp());
			backAmountMapper.update(exist);
		} else {
			backAmount.setCreateDate(DateUtil.currentTimestamp());
			backAmount.setOperateDate(DateUtil.currentTimestamp());
			backAmountMapper.insert(backAmount);
		}
	}
}
