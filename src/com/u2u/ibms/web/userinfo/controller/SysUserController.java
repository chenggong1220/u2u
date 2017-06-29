package com.u2u.ibms.web.userinfo.controller;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.service.AuthorizeService;

@Controller
@RequestMapping("/web/sys/user")
public class SysUserController extends BaseController {

	@Autowired
	private AuthorizeService authorizeService;

	@RequestMapping("/operators")
	@ResponseBody
	public List<User> operators() {
		return authorizeService.getAllUsers(null, new RowBounds());
	}

}
