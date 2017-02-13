package com.u2u.ibms.web.message.controller;

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
import com.u2u.ibms.common.beans.Feedback;
import com.u2u.ibms.web.message.condition.FeedbackCondition;
import com.u2u.ibms.web.message.service.FeedbackService;

/**
 * Title: [From Dean] <br>
 * Description:[反馈管理] <br>
 * Date: 2016年11月5日 <br>
 * Copyright (c) 2016 <br>
 * 
 * @author dongming
 */
@Controller
@RequestMapping("/web/feedback")
public class FeedbackController extends BaseController {

	@Autowired
	private FeedbackService feedbackService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/message/feedback/feedbackList";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(FeedbackCondition conditoin,
			BaseRequest request) {

		List<Feedback> feedbacks = feedbackService.getAll(conditoin,
				buildRowBounds(request));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", feedbackService.getAll(conditoin,
				buildRowBounds()).size());
		result.put("rows", feedbacks);
		return result;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/message/feedback/feedbackEdit");
		mav.addObject("feedback", feedbackService.getById(id));
		return mav;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxDone update(Feedback feedback) {
		feedbackService.update(feedback);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxDone delete(int id) {
		feedbackService.delete(id);
		return ajaxDoneSuccess(null);
	}
}
