package com.u2u.ibms.web.contract.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.exception.ServiceAuthorizeException;
import com.u2u.framework.sys.authorize.beans.Role;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.security.SecurityContextUtil;
import com.u2u.framework.sys.authorize.service.AuthorizeService;
import com.u2u.ibms.common.Constants;
import com.u2u.ibms.common.beans.MailHistory;
import com.u2u.ibms.common.mapper.ContractMapper;
import com.u2u.ibms.common.mapper.MailHistoryMapper;
import com.u2u.ibms.web.contract.condition.MailHistoryCondition;

@Service
public class MailHistoryService extends BaseService {

	@Autowired
	private MailHistoryMapper mailHistoryMapper;

	@Autowired
	private AuthorizeService authorizeService;

	@Autowired
	private ContractMapper contractMapper;

	public List<MailHistory> getAll(RowBounds rb, MailHistoryCondition condition)
			throws ServiceAuthorizeException {

		User user = authorizeService.getUser(SecurityContextUtil.getUserName());
		for (Role role : user.getRoles()) {
			if (role.getRoleName().equals(Constants.ROLE_CUSTOMER_OPERATOR)) {
				condition.setOperatorId(user.getId() + "");
			}
		}

		final List<MailHistory> mailHistories = mailHistoryMapper.getAll(rb,
				getIntegerCondition(condition.getOperatorId()));
		for (final MailHistory mailHistory : mailHistories) {
			this.convert(mailHistory);
		}

		return mailHistories;
	}

	public MailHistory convert(final MailHistory mailHistory) {
		mailHistory.setContract(contractMapper.getById(mailHistory
				.getContractId()));
		return mailHistory;
	}

}
