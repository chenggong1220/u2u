package com.u2u.ibms.web.message.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.base.BaseRequest;
import com.u2u.framework.beans.AjaxDone;
import com.u2u.framework.exception.ServiceAuthorizeException;
import com.u2u.ibms.common.beans.SysMessage;
import com.u2u.ibms.web.message.service.SysMessageService;
import com.u2u.ibms.web.message.vo.SysMessageCondition;

@Controller
@RequestMapping("/web/sysmessage")
public class SysMessageController extends BaseController {

	@Autowired
	private SysMessageService sysMessageService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/message/sysmessage/sysMessageList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest request,
			SysMessageCondition condition) throws ServiceAuthorizeException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", sysMessageService.get(buildRowBounds(), condition)
				.size());
		result.put("rows",
				sysMessageService.get(buildRowBounds(request), condition));
		return result;
	}

	@RequestMapping("/view")
	public ModelAndView view(int id) throws ServiceAuthorizeException {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/message/sysmessage/sysMessageView");
		mav.addObject("sysmessage", sysMessageService.getById(id));
		return mav;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/message/sysmessage/sysMessageAdd");
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(SysMessage sysMessage) throws Exception {
		sysMessageService.insert(sysMessage);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxDone delete(int id) {
		sysMessageService.delete(id);
		return ajaxDoneSuccess(null);
	}
}
