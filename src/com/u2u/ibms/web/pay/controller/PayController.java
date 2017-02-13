package com.u2u.ibms.web.pay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.base.BaseRequest;
import com.u2u.ibms.common.beans.Pays;
import com.u2u.ibms.web.pay.condition.PayCondition;
import com.u2u.ibms.web.pay.service.PayService;

@Controller
@RequestMapping("/web/pays")
public class PayController extends BaseController {

	@Autowired
	private PayService payservice;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/pays/paysList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest, PayCondition condition) {
		List<Pays> pays = payservice.getAll(condition, buildRowBounds(baseRequest));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", payservice.getAll(condition, buildRowBounds()).size());
		result.put("rows", pays);
		return result;
	}

}
