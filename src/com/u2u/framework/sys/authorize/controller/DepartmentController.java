package com.u2u.framework.sys.authorize.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.base.BaseRequest;
import com.u2u.framework.beans.AjaxDone;
import com.u2u.framework.sys.authorize.beans.Department;
import com.u2u.framework.sys.authorize.model.DepartmentCondition;
import com.u2u.framework.sys.authorize.service.DepartmentService;

@Controller
@RequestMapping("/auth/department")
public class DepartmentController extends BaseController {

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("/index")
	public String index() {
		return "/framework/auth/department/departmentList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			DepartmentCondition condition) {
		List<Department> departments = departmentService.getAll(
				buildRowBounds(baseRequest), condition);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total",
				departmentService.getAll(buildRowBounds(), condition).size());
		result.put("rows", departments);
		return result;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/framework/auth/department/departmentAdd");
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(Department department) {
		departmentService.insert(department);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/framework/auth/department/departmentEdit");
		mav.addObject("department", departmentService.getById(id));
		return mav;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxDone update(Department department) {
		departmentService.update(department);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxDone delete(int id) {
		departmentService.delete(id);
		return ajaxDoneSuccess(null);
	}
}
