package com.u2u.ibms.web.shop.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.base.BaseRequest;
import com.u2u.framework.beans.AjaxDone;
import com.u2u.ibms.web.shop.bean.Shop;
import com.u2u.ibms.web.shop.condition.ShopCondition;
import com.u2u.ibms.web.shop.service.ShopService;

@Controller
@RequestMapping("/web/shop")
public class ShopController extends BaseController {

	@Autowired
	private ShopService shopService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/shop/shopList";
	}

	@RequestMapping("/shopImport")
	public String importShop() {
		return "/ibms/shop/shopImport";
	}
	@RequestMapping("/json")
	@ResponseBody
	public List<Shop> json() {
		ShopCondition condition = new ShopCondition();
		return shopService.getAll(condition, buildRowBounds());
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest, ShopCondition condition) {
		List<Shop> shops = shopService.getAll(condition, buildRowBounds(baseRequest));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", shopService.getAll(condition, buildRowBounds()).size());
		result.put("rows", shops);
		return result;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/shop/shopAdd");
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(Shop shop) {
		shopService.insert(shop);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/shop/shopEdit");
		mav.addObject("shop", shopService.getById(id));
		return mav;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxDone update(Shop shop) {
		shopService.update(shop);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxDone delete(int id) {
		shopService.delete(id);
		return ajaxDoneSuccess(null);
	}


	@RequestMapping(value = "/download")
	public ModelAndView download(HttpServletRequest request, HttpServletResponse response) {
		OutputStream bos = null;
		// 返回下载文件名称
		String path = request.getSession().getServletContext().getRealPath("");
		String downLoadName = "账单导入模板.xlsx";
		// 设置文件输出类型
		File oriFile = new File(
				path + File.separator + "styles" + File.separator + "shop" + File.separator + downLoadName);
		response.setContentType("application/octet-stream");
		try {
			downLoadName = getDownLoadName(downLoadName, request.getHeader("USER-AGENT"));
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		response.setHeader("Content-disposition", "attachment; filename=" + downLoadName);
		// 输出流
		try {
			bos = new BufferedOutputStream(response.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		byte[] buff = null;
		try {
			buff = FileUtils.readFileToByteArray(oriFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bos.write(buff);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getDownLoadName(String filename, String agent) throws Exception {
		String downLoadName = null;
		if (StringUtils.isEmpty(agent)) // null
		{
			downLoadName = java.net.URLEncoder.encode(filename, "UTF-8");
		} else if (-1 != agent.toUpperCase().indexOf("MSIE")) // IE 9,10
		{
			downLoadName = java.net.URLEncoder.encode(filename, "UTF-8");
		} else if (-1 != agent.toUpperCase().indexOf("CHROME")) // Chrome
		{
			downLoadName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
		} else if (-1 != agent.toUpperCase().indexOf("FIREFOX")) // Firefox
		{
			downLoadName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
		} else {
			downLoadName = java.net.URLEncoder.encode(filename, "UTF-8");
		}
		return downLoadName;
	}
}
