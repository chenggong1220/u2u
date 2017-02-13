package com.u2u.ibms.web.rent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.framework.base.BaseController;
import com.u2u.ibms.common.beans.RentType;
import com.u2u.ibms.web.rent.service.RentTypeService;

@Controller
@RequestMapping("/web/rent/type")
public class RentTypeController extends BaseController {

	@Autowired
	private RentTypeService rentTypeService;

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list() {
		List<RentType> list = rentTypeService.get(new RowBounds());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", list.size());
		result.put("rows", list);
		return result;
	}
}
