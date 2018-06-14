package com.u2u.ibms.rest.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.framework.base.BaseRestController;
import com.u2u.framework.beans.MobileResponse;
import com.u2u.ibms.rest.auth.vo.FeedBackRequest;
import com.u2u.ibms.rest.message.service.MobileMessageService;
import com.u2u.ibms.rest.message.vo.MessageRequest;
import com.u2u.ibms.rest.message.vo.MessagesRequest;

@Controller
@RequestMapping("/mobile/message")
public class MobileMessageController extends BaseRestController {

	@Autowired
	private MobileMessageService mobileMessageService;
	/**
	 * 2.1.1获取消息列表
	 * @param username
	 * @param pwd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMessages/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getMessages(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd,
			@RequestBody MessagesRequest request) throws Exception {
		return success(mobileMessageService.getMessages(username, request));
	}
	/**
	 * 2.1.2获取单条消息
	 * @param username
	 * @param pwd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSingleMessage/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getSingleMessage(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd,
			@RequestBody MessageRequest request) throws Exception {
		request.setUsername(username);
		return success(mobileMessageService.getSingleMessage(request));
	}
	/**
	 * 2.2.1反馈信息
	 * @param username
	 * @param pwd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/feedback/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse feedback(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd,
			@RequestBody FeedBackRequest request) throws Exception {
			mobileMessageService.feedback(request);
		return success("");
	}
}
