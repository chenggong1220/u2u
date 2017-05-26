package com.u2u.ibms.web.credit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.u2u.ibms.common.beans.CreditVerify;
import com.u2u.ibms.common.beans.IdentifyCertification;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.Project;
import com.u2u.ibms.common.beans.ProjectHandle;
import com.u2u.ibms.common.mapper.IdentifyCertificationMapper;
import com.u2u.ibms.web.order.service.OrderService;
import com.u2u.ibms.web.project.condition.ProjectCondition;
import com.u2u.ibms.web.project.service.ProjectPictureService;
import com.u2u.ibms.web.project.service.ProjectService;

@Controller
@RequestMapping("/web/credit")
public class CreditController extends BaseController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProjectPictureService projectPictureService;

	@Autowired
	private IdentifyCertificationMapper identifyCertificationMapper;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/credit/creditList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			ProjectCondition condition) {
		condition.setResult("2");
		List<Project> projects = projectService.getAll(
				buildRowBounds(baseRequest), condition);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", projectService.getAll(buildRowBounds(), condition)
				.size());
		result.put("rows", projects);
		return result;
	}

	@RequestMapping("/verify")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/credit/creditVerify");
		Project project = projectService.getById(id);
		mav.addObject("project", project);
		mav.addObject("projectHandles",
				projectService.getProjectHandleByProjectId(project.getId()));
		Order order = orderService.getById(project.getOrderId());
		mav.addObject("order", orderService.getById(project.getOrderId()));
		mav.addObject("orderStatistics",
				orderService.getOrderStatistics(project.getOrderId()));
		mav.addObject("projectPictures",
				projectPictureService.getAll(new RowBounds(), project.getId()));
		mav.addObject(
				"projectHandle",
				projectService.getProjectHandlerByProjectIdAndLevel(
						project.getId(), 3));
		mav.addObject("projectPictures", projectPictureService.getAll(new RowBounds(), project.getId()));
		mav.addObject("projectHandle", projectService.getProjectHandlerByProjectIdAndLevel(project.getId(), 3));		
		mav.addObject("assetSaveLocation", order.getProvince() + order.getCity() + order.getDetailLocation());
		
		String name = null;
		if (order.getRentPersonType() == 0) {
			name = order.getRentPersonInfo().getName();
		} else {
			name = order.getRentCompanyInfo().getLegalName();
		}
		IdentifyCertification identifyCertification = identifyCertificationMapper
				.getByNameAndIdcard(name, order.getIdCard());
		mav.addObject("realpicture", identifyCertification);

		if (orderService.validateIsDenied(order)) {
			mav.setViewName("/ibms/order/orderDenied");
			return mav;
		}
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(Project project, ProjectHandle projectHandle,
			CreditVerify creditVerify, @RequestParam("creditFile") String creditFile) {
		projectService.creditVerify(project, projectHandle, creditVerify,creditFile);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/multicheck")
	public ModelAndView multicheck(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/credit/creditMulticheck");
		Project project = projectService.getById(id);
		mav.addObject("project", project);
		mav.addObject("projectHandles",
				projectService.getProjectHandleByProjectId(project.getId()));
		Order order = orderService.getById(project.getOrderId());
		mav.addObject("order", orderService.getById(project.getOrderId()));
		mav.addObject("credit",
				projectService.getCreditVerifyByProjectId(project.getId()));
		mav.addObject("orderStatistics",
				orderService.getOrderStatistics(project.getOrderId()));
		mav.addObject("projectPictures",
				projectPictureService.getAll(new RowBounds(), project.getId()));
		mav.addObject(
				"projectHandle",
				projectService.getProjectHandlerByProjectIdAndLevel(
						project.getId(), 4));
		mav.addObject("assetSaveLocation", order.getProvince() + order.getCity() + order.getDetailLocation());
		
		String name = null;
		if (order.getRentPersonType() == 0) {
			name = order.getRentPersonInfo().getName();
		} else {
			name = order.getRentCompanyInfo().getLegalName();
		}
		IdentifyCertification identifyCertification = identifyCertificationMapper
				.getByNameAndIdcard(name, order.getIdCard());
		mav.addObject("realpicture", identifyCertification);

		if (orderService.validateIsDenied(order)) {
			mav.setViewName("/ibms/order/orderDenied");
			return mav;
		}

		if (orderService.validateIsCreditMultichecked(order)) {
		} else {
			
			mav.setViewName("/ibms/credit/creditMulticheck");
			mav.addObject("ifMultiChecked", "yes");
			
			//Marked by SUNZHE, 2017-05-26
			//mav.setViewName("/ibms/order/orderDenied");
			//mav.addObject("message", "订单已经复核通过，不需要再次复核！");
			return mav;
		}
		return mav;
	}

	@RequestMapping("/multicheck/save")
	@ResponseBody
	public AjaxDone multicheckSave(HttpServletRequest request, Project project,
			ProjectHandle projectHandle, CreditVerify creditVerify)
			throws Exception {
		projectService.creditVerifyMulticheck(project, projectHandle, request);
		return ajaxDoneSuccess(null);
	}

}
