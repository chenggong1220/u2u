package com.u2u.ibms.web.contract.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.base.BaseRequest;
import com.u2u.framework.beans.AjaxDone;
import com.u2u.ibms.common.beans.Asset;
import com.u2u.ibms.common.beans.AssetManagerRentAsset;
import com.u2u.ibms.common.beans.Contract;
import com.u2u.ibms.web.asset.service.AssetManagerRentAssetService;
import com.u2u.ibms.web.contract.condition.ContractCondition;
import com.u2u.ibms.web.contract.service.ContractService;
import com.u2u.ibms.web.contract.service.SendManagerService;

@Controller
@RequestMapping("/web/send/manager")
public class SendManagerController extends BaseController {

	@Autowired
	private ContractService contractService;

	@Autowired
	private SendManagerService sendManagerService;

	@Autowired
	private AssetManagerRentAssetService assetManagerRentAssetService;
	@RequestMapping("/index")
	public String index() {
		return "/ibms/contract/sendmanager/sendManagerList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			ContractCondition condition) {
		List<Contract> contracts = contractService.getSendAll(
				buildRowBounds(baseRequest), condition);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", contractService.getSendAll(buildRowBounds(), condition)
				.size());
		result.put("rows", contracts);
		return result;
	}

	@RequestMapping("/send/json")
	@ResponseBody
	public Map<String, Object> sendJson(int contractId) {
		List<Asset> assets = contractService.getAllAssets(contractId);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", contractService.getAllAssets(contractId).size());
		result.put("rows", assets);
		return result;
	}

	@RequestMapping("/send")
	public ModelAndView send(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/contract/sendmanager/sendManagerSend");
		mav.addObject("contract", contractService.getById(id));
		return mav;
	}

	@RequestMapping("/send/save")
	@ResponseBody
	public AjaxDone sendSave(@RequestParam("id") Integer[] ids,
			@RequestParam("contractId") String contractId,
			@RequestParam("selectDate") String selectDate) {
		sendManagerService.send(contractId, ids, selectDate);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/send/checkin")
	public ModelAndView sendCheckin(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/contract/sendmanager/sendManagerSendCheckin");
		mav.addObject("contract", contractService.getById(id));
		return mav;
	}

	@RequestMapping("/send/checkin/json")
	@ResponseBody
	public Map<String, Object> sendCheckinJson(int contractId) {
		List<AssetManagerRentAsset> assets = contractService
				.getAllRentAsset(contractId);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", contractService.getAllRentAsset(contractId).size());
		result.put("rows", assets);
		return result;
	}

	@RequestMapping("/send/checkin/edit")
	public ModelAndView sendCheckinEdit(@RequestParam("id") int id,
			@RequestParam("contractId") String contractId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/contract/sendmanager/sendManagerSendCheckinEdit");
		mav.addObject("asset", assetManagerRentAssetService.getById(id));
		mav.addObject("contractId", contractId);
		return mav;
	}

	@RequestMapping("/send/checkin/save")
	@ResponseBody
	public AjaxDone sendCheckinSave(
			@RequestParam("contractId") String contractId,
			@RequestParam("rentAssetId") String rentAssetId,
			@RequestParam("selectDate") String selectDate) {
		assetManagerRentAssetService.receive(contractId, rentAssetId,
				selectDate);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/back")
	public ModelAndView back(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/contract/sendmanager/sendManagerBack");
		mav.addObject("contract", contractService.getById(id));
		return mav;
	}

	@RequestMapping("/back/save")
	@ResponseBody
	public AjaxDone backSave(Contract contract, String selectDate) {
		sendManagerService.back(contract, selectDate);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/abandon")
	@ResponseBody
	public AjaxDone delete(int id, int status) {
		contractService.abandon(id, status);
		return ajaxDoneSuccess(null);
	}
}
