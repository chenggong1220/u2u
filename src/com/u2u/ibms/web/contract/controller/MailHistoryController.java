package com.u2u.ibms.web.contract.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.base.BaseRequest;
import com.u2u.framework.exception.ServiceAuthorizeException;
import com.u2u.ibms.common.beans.MailHistory;
import com.u2u.ibms.web.contract.condition.MailHistoryCondition;
import com.u2u.ibms.web.contract.service.MailHistoryService;

@Controller
@RequestMapping("/web/mailhistory")
public class MailHistoryController extends BaseController {

	@Autowired
	private MailHistoryService mailHistoryService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/contract/mailhistory/mailHistoryList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			MailHistoryCondition condition) throws ServiceAuthorizeException {
		List<MailHistory> mailHistories = mailHistoryService.getAll(
				buildRowBounds(baseRequest), condition);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total",
				mailHistoryService.getAll(buildRowBounds(), condition).size());
		result.put("rows", mailHistories);
		return result;
	}

}
