package com.u2u.ibms.web.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
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
import com.u2u.ibms.common.beans.IdentifyCertification;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.Project;
import com.u2u.ibms.common.beans.ProjectHandle;
import com.u2u.ibms.common.beans.ProjectPicture;
import com.u2u.ibms.common.mapper.IdentifyCertificationMapper;
import com.u2u.ibms.web.order.service.OrderService;
import com.u2u.ibms.web.project.condition.ProjectCondition;
import com.u2u.ibms.web.project.service.ProjectPictureService;
import com.u2u.ibms.web.project.service.ProjectService;

@Controller
@RequestMapping("/web/project")
public class ProjectController extends BaseController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProjectPictureService projectPictureService;

	@Autowired
	private IdentifyCertificationMapper identifyCertificationMapper;

	@Autowired
	private AuthorizeService authorizeService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/project/projectList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			ProjectCondition condition) throws Exception {

		User user = authorizeService.getUser(SecurityContextUtil.getUserName());

		for (Role role : user.getRoles()) {
			if (role.getRoleName().equals(Constants.ROLE_CUSTOMER_OPERATOR)) {
				condition.setOperatorId(user.getId() + "");
			}
		}

		List<Project> projects = projectService.getAll(
				buildRowBounds(baseRequest), condition);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", projectService.getAll(buildRowBounds(), condition)
				.size());
		result.put("rows", projects);
		return result;
	}

	@RequestMapping("/verify")
	public ModelAndView verify(int id) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/project/projectVerify");
		Project project = projectService.getById(id);
		mav.addObject("project", project);

		// User user =
		// authorizeService.getUser(SecurityContextUtil.getUserName());
		Order order = orderService.getById(project.getOrderId());
		// order.setOperator(user.getRealname());
		// order.setOperatorMobile(user.getMobile());
		mav.addObject("order", order);
		mav.addObject("orderStatistics",
				orderService.getOrderStatistics(project.getOrderId()));

		String name = null;
		if (order.getRentPersonType() == 0) {
			name = order.getRentPersonInfo().getName();
		} else {
			name = order.getRentCompanyInfo().getLegalName();
		}
		IdentifyCertification identifyCertification = identifyCertificationMapper
				.getByNameAndIdcard(name, order.getIdCard());
		mav.addObject("realpicture", identifyCertification);

		List<ProjectPicture> projectPictures = projectPictureService.getAll(
				new RowBounds(), project.getId());
		mav.addObject("projectPictures", projectPictures);

		mav.addObject("assetSaveLocation", order.getProvince() + order.getCity() + order.getDetailLocation());
		
		if (CollectionUtils.isNotEmpty(projectPictures)) {
			mav.addObject("projectPicturesStartIndex",
					projectPictures.get(projectPictures.size() - 1)
							.getPictureIndex() + 1);
		} else {
			mav.addObject("projectPicturesStartIndex", 0);
		}

		if (orderService.validateProjectIsDenied(order)) {
			mav.setViewName("/ibms/order/orderDenied");
			return mav;
		}

		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(Project project, Order order,
			HttpServletRequest request) {
		projectService.save(project, order, request);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/multicheck")
	public ModelAndView multicheck(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/project/projectMultiCheck");
		Project project = projectService.getById(id);
		mav.addObject("project", project);
		Order order = orderService.getById(project.getOrderId());
		mav.addObject("order", orderService.getById(project.getOrderId()));
		mav.addObject("orderStatistics",
				orderService.getOrderStatistics(project.getOrderId()));
		mav.addObject("projectPictures",
				projectPictureService.getAll(new RowBounds(), project.getId()));
		mav.addObject(
				"projectHandle",
				projectService.getProjectHandlerByProjectIdAndLevel(
						project.getId(), 2));
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

	@RequestMapping("/multicheck/save")
	@ResponseBody
	public AjaxDone multicheckSave(Project project, ProjectHandle projectHandle) {
		projectService.multicheckSave(project, projectHandle);
		return ajaxDoneSuccess(null);
	}
}
