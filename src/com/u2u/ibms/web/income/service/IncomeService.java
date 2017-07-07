package com.u2u.ibms.web.income.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.Contract;
import com.u2u.ibms.common.beans.Income;
import com.u2u.ibms.common.beans.IncomeTicket;
import com.u2u.ibms.common.mapper.ContractMapper;
import com.u2u.ibms.common.mapper.IncomeMapper;
import com.u2u.ibms.common.mapper.IncomeTicketMapper;
import com.u2u.ibms.web.income.condition.IncomeCondition;

@Service
public class IncomeService extends BaseService {

	@Autowired
	private IncomeMapper incomeMapper;

	@Autowired
	private IncomeTicketMapper incomeTicketMapper;

	@Autowired
	private ContractMapper contractMapper;

	public List<Income> getAll(RowBounds rb, IncomeCondition condition) {
		List<Income> incomes = incomeMapper.getAll(rb,
				getStringCondition(condition.getCustomer()),
				getStringCondition(condition.getContractId()),
				getStringCondition(condition.getType()),
				getStringCondition(condition.getInvoiceStatus()),
				getStartDate(condition), getEndDate(condition));
		for (final Income income : incomes) {
			this.convertToIncome(income);
		}
		return incomes;
	}

	public Income getById(int id) {
		return convertToIncome(incomeMapper.getById(id));
	}

	private Income convertToIncome(final Income income) {
		Contract contract = contractMapper.getByOrderId(income.getOrderId());
		income.setContract(contract);
		return income;
	}

	public IncomeTicket getByIncomeId(int incomeId) {
		return incomeTicketMapper.getByIncomeId(incomeId);
	}

	public void save(String selectDate, String incomeId,
			IncomeTicket incomeTicket) {
		IncomeTicket exist = incomeTicketMapper
				.getByIncomeId(getIntegerCondition(incomeId));
		if (exist == null) {
			incomeTicket.setOpenDate(DateUtil.string2Timestamp(selectDate,
					DateUtil.PATTERN_DATE));
			incomeTicket.setCreateDate(DateUtil.currentTimestamp());
			incomeTicket.setOperateDate(DateUtil.currentTimestamp());
			incomeTicketMapper.insert(incomeTicket);
		} else {
			exist.setContractId(incomeTicket.getContractId());
			exist.setOpenDate(DateUtil.string2Timestamp(selectDate,
					DateUtil.PATTERN_DATE));
			exist.setProject(incomeTicket.getProject());
			exist.setType(incomeTicket.getType());
			exist.setTicketId(incomeTicket.getTicketId());
			exist.setAmount(incomeTicket.getAmount());
			exist.setRealAmount(incomeTicket.getRealAmount());
			exist.setOperateDate(DateUtil.currentTimestamp());
			incomeTicketMapper.update(exist);
		}
		Income income = incomeMapper.getById(getIntegerCondition(incomeId));
		income.setInvoiceStatus(true);
		income.setType(incomeTicket.getProject());
		income.setOperateDate(DateUtil.currentTimestamp());
		income.setPayAmount(incomeTicket.getRealAmount());
		incomeMapper.update(income);
	}
}
