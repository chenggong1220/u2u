package com.u2u.ibms.web.userinfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.u2u.framework.base.BaseCondition;
import com.u2u.framework.base.BaseController;
import com.u2u.framework.beans.AjaxDone;
import com.u2u.ibms.common.beans.UserInfo;
import com.u2u.ibms.web.userinfo.service.UserinfoService;
import com.u2u.ibms.web.userinfo.vo.UserInfoVo;

@Controller
@RequestMapping("/web/userinfo")
public class UserinfoController extends BaseController {

	@Autowired
	private UserinfoService userinfoService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/userinfo/userinfoList";
	}

	@RequestMapping("/json")
	@ResponseBody
	public List<UserInfo> json() {
		return userinfoService.getAll(buildRowBounds(), new UserInfoVo());
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(UserInfoVo request, BaseCondition condition) {
		List<UserInfo> users = userinfoService.getAll(buildRowBounds(request),
				request);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", userinfoService.getAll(buildRowBounds(), request)
				.size());
		result.put("rows", users);
		return result;
	}

	@RequestMapping("/verify")
	public ModelAndView view(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/userinfo/userinfoVerify");
		mav.addObject("userinfo", userinfoService.getById(id));
		return mav;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxDone update(UserInfo userInfo) {
		userinfoService.update(userInfo);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxDone delete(int id) {
		userinfoService.delete(id);
		return ajaxDoneSuccess(null);
	}
}

class UserRet {
	private int id;
	private String text;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
