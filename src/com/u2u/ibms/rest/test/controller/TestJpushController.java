package com.u2u.ibms.rest.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.common.component.jpush.JPushService;
import com.u2u.framework.base.BaseRestController;
import com.u2u.framework.beans.MobileResponse;

@Controller
@RequestMapping("/mobile/test")
public class TestJpushController extends BaseRestController {

	@Autowired
	private JPushService jPushService;


	@RequestMapping(value = "/testJpush", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse testJpush() throws Exception {
		jPushService.push("1233211234567");
		return success("");
	}
}
