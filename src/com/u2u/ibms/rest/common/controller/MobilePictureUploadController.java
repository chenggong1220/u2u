package com.u2u.ibms.rest.common.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.u2u.framework.base.BaseRestController;
import com.u2u.framework.beans.MobileResponse;

@Controller
@RequestMapping("/mobile/common/statics")
public class MobilePictureUploadController extends BaseRestController {

	private static final Log logger = LogFactory
			.getLog(MobilePictureUploadController.class);

	/**
	 * 移动端文件上传
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse upload(HttpServletRequest request) throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		/** 构建图片保存的目录 **/
		String logoPathDir = "/styles/mobile/static/"
				+ dateformat.format(new Date());
		/** 得到图片保存目录的真实路径 **/
		String logoRealPathDir = request.getSession().getServletContext()
				.getRealPath(logoPathDir);
		/** 根据真实路径创建目录 **/
		File logoSaveFile = new File(logoRealPathDir);
		if (!logoSaveFile.exists())
			logoSaveFile.mkdirs();
		/** 页面控件的文件流 **/
		MultipartFile multipartFile = multipartRequest.getFile("file");
		// 构建文件名称
		String logImageName = UUID.randomUUID().toString() + "."
				+ multipartFile.getOriginalFilename();

		/** 拼成完整的文件保存路径加文件 **/
		String fileName = logoRealPathDir + "/" + logImageName;
		logger.debug("用户信息图片保存在[" + fileName + "]");
		File file = new File(fileName);

		multipartFile.transferTo(file);
		System.out.println(logoPathDir + "/" + logImageName);
		return success(logoPathDir + "/" + logImageName);
	}
}
