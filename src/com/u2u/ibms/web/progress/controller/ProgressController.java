package com.u2u.ibms.web.progress.controller;

import java.util.ArrayList;
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
import com.u2u.framework.sys.authorize.beans.Role;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.model.TreeVo;
import com.u2u.framework.sys.authorize.service.AuthorizeService;
import com.u2u.ibms.web.progress.bean.ProgressRole;
import com.u2u.ibms.web.progress.condition.ProgressCondition;
import com.u2u.ibms.web.progress.service.ProgressRoleService;

@Controller
@RequestMapping("/web/progress")
public class ProgressController extends BaseController {

	@Autowired
	private ProgressRoleService progressService;
	@Autowired
	private AuthorizeService authorizeService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/progress/progressList";
	}

	@RequestMapping("/progressImport")
	public String importProgressRole() {
		return "/ibms/progress/progressImport";
	}
	@RequestMapping("/getTree")
	@ResponseBody
	public List<TreeVo> getTree() {
		List<TreeVo> tvs = new ArrayList<>();
		TreeVo tv = new TreeVo();
		tv.setId("root");
		tv.setText("所有权限");;
		tv.setState("open");
		List<Role> roles = authorizeService.getAllRoles(buildRowBounds());
		for (Role role : roles) {
			TreeVo trv = new TreeVo();
			trv.setId(role.getId()+"");
			trv.setText(role.getRoleName());
			trv.getChildren().clear();
			trv.setState("open");
			tv.getChildren().add(trv);
		}
		tvs.add(tv);
		return tvs;
	}
	@RequestMapping("/getRoleByPid")
	@ResponseBody
	public List<Integer> getRoleByPid(ProgressCondition condition) {
		List<ProgressRole> pr = progressService.getById(Integer.parseInt(condition.getPid()));
		List<Integer> roles = new ArrayList<>();
		for (ProgressRole progressRole : pr) {
			roles.add(progressRole.getRid());
		}
		return roles;
	}
	@RequestMapping("/getUserById")
	@ResponseBody
	public List<User> getUserById(int pid) {
		return progressService.getUserById(pid);
	}
	@RequestMapping("/saveRolePid")
	@ResponseBody
	public AjaxDone saveRolePid(BaseRequest baseRequest,ProgressCondition progressRole) {
		progressService.delete(progressRole.getPid());
		progressService.insert(progressRole);
		return ajaxDoneSuccess("");
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/progress/progressAdd");
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(ProgressRole progress) {
		progressService.insert(progress);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/progress/progressEdit");
		return mav;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxDone update(ProgressRole progress) {
		progressService.update(progress);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxDone delete(int id) {
		return ajaxDoneSuccess(null);
	}


}
