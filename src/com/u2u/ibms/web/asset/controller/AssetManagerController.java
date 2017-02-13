package com.u2u.ibms.web.asset.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.base.BaseRequest;
import com.u2u.framework.beans.AjaxDone;
import com.u2u.framework.exception.ServiceAuthorizeException;
import com.u2u.framework.exception.ServiceBusinessException;
import com.u2u.ibms.common.beans.AssetManagerBack;
import com.u2u.ibms.common.beans.AssetManagerDelay;
import com.u2u.ibms.common.beans.AssetManagerFandan;
import com.u2u.ibms.common.beans.AssetManagerRentAsset;
import com.u2u.ibms.common.beans.AssetManagerRisk;
import com.u2u.ibms.common.beans.Contract;
import com.u2u.ibms.web.asset.service.AssetManagerBackService;
import com.u2u.ibms.web.asset.service.AssetManagerDelayService;
import com.u2u.ibms.web.asset.service.AssetManagerFandanService;
import com.u2u.ibms.web.asset.service.AssetManagerRentAssetService;
import com.u2u.ibms.web.asset.service.AssetManagerRiskService;
import com.u2u.ibms.web.contract.condition.ContractCondition;
import com.u2u.ibms.web.contract.service.ContractService;
import com.u2u.ibms.web.order.service.OrderService;
import com.u2u.ibms.web.project.service.ProjectService;

@Controller
@RequestMapping("/web/asset/manager")
public class AssetManagerController extends BaseController {

	@Autowired
	private ContractService contractService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private AssetManagerRentAssetService assetManagerRentAssetService;

	@Autowired
	private AssetManagerFandanService assetManagerFandanService;

	@Autowired
	private AssetManagerBackService assetManagerBackService;

	@Autowired
	private AssetManagerDelayService assetManagerDelayService;

	@Autowired
	private AssetManagerRiskService assetManagerRiskService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/asset/manager/assetManagerList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			ContractCondition condition) {
		List<Contract> contracts = contractService.getAll(
				buildRowBounds(baseRequest), condition);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", contractService.getAll(buildRowBounds(), condition)
				.size());
		result.put("rows", contracts);
		return result;
	}

	@RequestMapping("/rent")
	public ModelAndView rent(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/asset/manager/assetManagerRent");
		Contract contract = contractService.getById(id);
		mav.addObject("contract", contract);
		mav.addObject("order", orderService.getById(contract.getOrderId()));
		mav.addObject("orderStatistics",
				orderService.getOrderStatistics(contract.getOrderId()));
		mav.addObject("project",
				projectService.getById(contract.getProjectId()));
		return mav;
	}

	@RequestMapping("/rent/list")
	@ResponseBody
	public Map<String, Object> rentList(BaseRequest baseRequest, String orderId) {
		List<AssetManagerRentAsset> assetManagerRentAssets = assetManagerRentAssetService
				.getAll(orderId, buildRowBounds(baseRequest));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total",
				assetManagerRentAssetService.getAll(orderId, buildRowBounds())
						.size());
		result.put("rows", assetManagerRentAssets);
		return result;
	}

	@RequestMapping("/rent/add")
	public ModelAndView rentAdd(int orderId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/asset/manager/assetManagerRentAdd");
		mav.addObject("order", orderService.getById(orderId));
		return mav;
	}

	@RequestMapping("/rent/save")
	@ResponseBody
	public AjaxDone rentSave(AssetManagerRentAsset assetManagerRentAsset,
			@RequestParam("selectDate") String selectDate)
			throws ServiceBusinessException {
		assetManagerRentAssetService.insert(assetManagerRentAsset, selectDate);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/rent/delete")
	@ResponseBody
	public AjaxDone rentDelete(int id) {
		assetManagerRentAssetService.delete(id);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/fandan")
	public ModelAndView fandan(Integer id) {
		Contract contract = contractService.getById(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/asset/manager/assetManagerFandan");
		mav.addObject("contract", contract);
		mav.addObject("order", orderService.getById(contract.getOrderId()));
		mav.addObject("orderStatistics",
				orderService.getOrderStatistics(contract.getOrderId()));
		mav.addObject("project",
				projectService.getById(contract.getProjectId()));
		mav.addObject("fandans", assetManagerFandanService.getAll(
				contract.getOrderId() + "", new RowBounds()));
		return mav;
	}

	@RequestMapping("/fandan/save")
	@ResponseBody
	public AjaxDone fandanSave(AssetManagerFandan assetManagerFandan) {
		assetManagerFandanService.insert(assetManagerFandan);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/back")
	public ModelAndView back(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/asset/manager/assetManagerBack");
		Contract contract = contractService.getById(id);
		mav.addObject("contract", contract);
		mav.addObject("order", orderService.getById(contract.getOrderId()));
		mav.addObject("orderStatistics",
				orderService.getOrderStatistics(contract.getOrderId()));
		mav.addObject("project",
				projectService.getById(contract.getProjectId()));
		return mav;
	}

	@RequestMapping("/back/list")
	@ResponseBody
	public Map<String, Object> backList(BaseRequest baseRequest, String orderId) {
		List<AssetManagerRentAsset> assetManagerRentAssets = assetManagerRentAssetService
				.getAll(orderId, buildRowBounds(baseRequest));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total",
				assetManagerRentAssetService.getAll(orderId, buildRowBounds())
						.size());
		result.put("rows", assetManagerRentAssets);
		return result;
	}

	@RequestMapping("/back/edit")
	public ModelAndView backEdit(int orderId, int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/asset/manager/assetManagerBackEdit");
		mav.addObject("order", orderService.getById(orderId));
		mav.addObject("asset", assetManagerRentAssetService.getById(id));
		return mav;
	}

	@RequestMapping("/back/save")
	@ResponseBody
	public AjaxDone backSave(AssetManagerBack assetManagerBack,
			@RequestParam("rentStatus") String rentStatus) {
		assetManagerBackService.insert(assetManagerBack, rentStatus);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/risk")
	public ModelAndView risk(Integer id) {
		Contract contract = contractService.getById(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/asset/manager/assetManagerRisk");
		mav.addObject("contract", contract);
		mav.addObject("order", orderService.getById(contract.getOrderId()));
		mav.addObject("orderStatistics",
				orderService.getOrderStatistics(contract.getOrderId()));
		mav.addObject("project",
				projectService.getById(contract.getProjectId()));
		mav.addObject("risks", assetManagerRiskService.getAll(
				contract.getOrderId() + "", new RowBounds()));
		return mav;
	}

	@RequestMapping("/risk/save")
	@ResponseBody
	public AjaxDone riskSave(AssetManagerRisk assetManagerRisk) {
		assetManagerRiskService.insert(assetManagerRisk);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delay")
	public ModelAndView delay(Integer id) {
		Contract contract = contractService.getById(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/asset/manager/assetManagerDelay");
		mav.addObject("contract", contract);
		mav.addObject("order", orderService.getById(contract.getOrderId()));
		return mav;
	}

	@RequestMapping("/delay/save")
	@ResponseBody
	public AjaxDone delaySave(AssetManagerDelay assetManagerDelay,
			String selectDate) throws ServiceAuthorizeException {
		assetManagerDelayService.insert(assetManagerDelay, selectDate);
		return ajaxDoneSuccess(null);
	}
}
