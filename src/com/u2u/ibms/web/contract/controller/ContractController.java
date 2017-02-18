package com.u2u.ibms.web.contract.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.base.BaseRequest;
import com.u2u.framework.beans.AjaxDone;
import com.u2u.framework.sys.authorize.beans.Role;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.security.SecurityContextUtil;
import com.u2u.framework.sys.authorize.service.AuthorizeService;
import com.u2u.ibms.common.Constants;
import com.u2u.ibms.common.beans.Contract;
import com.u2u.ibms.web.contract.condition.ContractCondition;
import com.u2u.ibms.web.contract.service.ContractService;

@Controller
@RequestMapping("/web/contract")
public class ContractController extends BaseController {

	@Autowired
	private ContractService contractService;
	
	@Autowired
	private AuthorizeService authorizeService;	

	@RequestMapping("/index")
	public String index() {
		return "/ibms/contract/contractList";
	}

	@RequestMapping("/json")
	@ResponseBody
	public List<Contract> json() {
		ContractCondition condition = new ContractCondition();
		// condition.set
		return contractService.getAll(new RowBounds(), condition);
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			ContractCondition condition) throws Exception {
		//针对客户专员加查看权限，只有看到他自己关联的合同, by SUNZHE, 2017-02-12
		User user = authorizeService.getUser(SecurityContextUtil.getUserName());

		for (Role role : user.getRoles()) {
			if (role.getRoleName().equals(Constants.ROLE_CUSTOMER_OPERATOR)) {
				condition.setOperatorId(user.getId() + "");
				//System.out.println("========================Current Operator ID:" + condition.getOperatorId());
			}
		}		
		//针对客户专员加查看权限，只有看到他自己关联的合同, by SUNZHE, 2017-02-12
		
		List<Contract> contracts = contractService.getAll(
				buildRowBounds(baseRequest), condition);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", contractService.getAll(
				buildRowBounds(), condition).size());
		result.put("rows", contracts);
		return result;
	}

	@RequestMapping("/checkin")
	public ModelAndView checkin(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/contract/contractCheckin");
		mav.addObject("contract", contractService.getById(id));
		return mav;
	}

	@RequestMapping("/checkin/save")
	@ResponseBody
	public AjaxDone checkinSave(Contract contract, String selectDate) {
		contractService.checkin(contract, selectDate);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/signoff")
	public ModelAndView signoff(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/contract/contractSignoff");
		mav.addObject("contract", contractService.getById(id));
		return mav;
	}

	@RequestMapping("/signoff/save")
	@ResponseBody
	public AjaxDone signoffSave(Contract contract, String selectDate) {
		contractService.signoff(contract, selectDate);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/abandon")
	@ResponseBody
	public AjaxDone abandon(int id, int status) {
		contractService.abandon(id, status);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/detail")
	public ModelAndView detail(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/contract/contractDetail");
		mav.addObject("contract", contractService.getById(id));
		return mav;
	}

}
