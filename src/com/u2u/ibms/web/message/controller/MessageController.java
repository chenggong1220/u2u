package com.u2u.ibms.web.message.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.u2u.common.component.sms.AlidayuSmsMessage;
import com.u2u.common.component.sms.ThridPartySmsTemplate;
import com.u2u.framework.base.BaseCondition;
import com.u2u.framework.base.BaseController;
import com.u2u.framework.beans.AjaxDone;
import com.u2u.ibms.common.beans.Message;
import com.u2u.ibms.web.message.service.MessageService;
import com.u2u.ibms.web.message.vo.MessageVo;

@Controller
@RequestMapping("/web/message")
public class MessageController extends BaseController {

	@Autowired
	private MessageService messageService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/message/message/messageList";
	}

	@Autowired
	private ThridPartySmsTemplate smsTemplate;

	@RequestMapping("/alidayusms")
	public String alidayusmssend() throws Exception {
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("AppName", "优尼斯租赁");
		maps.put("VerifyCode", "测试111qweqweq");
		AlidayuSmsMessage message = new AlidayuSmsMessage();
		message.setSignName("zhuce");
		message.setTemplateCode("zhuce");
		message.setPhoneNum("18342262221");
		message.bulidContext(maps);

		smsTemplate.sendMessage(message);
		return "/dcim/test/fsuAbstract";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(MessageVo request, BaseCondition condition) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total",
				messageService.get(buildRowBounds(), request, condition).size());
		result.put("rows",
				messageService.get(buildRowBounds(request), request, condition));
		return result;
	}

	@RequestMapping("/view")
	public ModelAndView view(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/message/message/messageView");
		mav.addObject("message", messageService.getById(id));
		return mav;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/message/message/messageAdd");
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(Message message,
			@RequestParam("userId") String[] userIds) {
		messageService.insert(message, userIds);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxDone delete(int id) {
		messageService.delete(id);
		return ajaxDoneSuccess(null);
	}
}
