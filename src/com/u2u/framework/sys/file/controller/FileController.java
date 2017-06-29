package com.u2u.framework.sys.file.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.util.LogUtil;
import com.u2u.ibms.common.ImageResizer;

@Controller("FileController")
@RequestMapping("/frame/file")
public class FileController extends BaseController {

	private static final Log log = LogFactory.getLog(FileController.class);

	@RequestMapping("/upload")
	@ResponseBody
	public FileResponse upload(HttpServletRequest request)
			throws IllegalStateException, IOException {
		FileResponse response = new FileResponse();
		try {
			//System.out.println(request.getParameter("fileName"));
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			/** 构建图片保存的目录 **/
			String logoPathDir = "/styles/backend/"
					+ dateformat.format(new Date());
			/** 得到图片保存目录的真实路径 **/
			String logoRealPathDir = request.getSession().getServletContext()
					.getRealPath(logoPathDir);
			/** 根据真实路径创建目录 **/
			File logoSaveFile = new File(logoRealPathDir);
			if (!logoSaveFile.exists())
				logoSaveFile.mkdirs();
			/** 页面控件的文件流 **/
			MultipartFile multipartFile = multipartRequest.getFile(request
					.getParameter("fileName"));

			// 构建文件名称
			String logImageName = UUID.randomUUID().toString() + "."
					+ getOriginalFileName(multipartFile.getOriginalFilename());

			/** 拼成完整的文件保存路径加文件 **/
			String fileName = logoRealPathDir + "/" + logImageName;
			LogUtil.info(log, "上传文件保存在[" + fileName + "]");
			// ---------------------------------------------------------

			File file = new File(fileName);

			multipartFile.transferTo(file);
			System.out.println(logoPathDir + "/" + logImageName);

			// 上传文件同时备份设备类型200*200的图片
			ExecutorService threadPool = Executors.newSingleThreadExecutor();
			MyTask t = new MyTask(logoRealPathDir, logImageName, fileName);
			threadPool.execute(t);

			response.setError("false");
			response.setMsg(logoPathDir + "/" + logImageName);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setError("true");
			response.setMsg(e.getMessage());
			return response;
		}
	}

	class MyTask implements Runnable {
		private String logoRealPathDir;
		private String logImageName;
		private String fileName;

		public MyTask(String logoRealPathDir, String logImageName,
				String fileName) {
			this.logoRealPathDir = logoRealPathDir;
			this.logImageName = logImageName;
			this.fileName = fileName;
		}

		public void run() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			File smallSaveFile = new File(logoRealPathDir + File.separator
					+ "deviceTypeImgs");
			//System.out.println("====" + logoRealPathDir + File.separator
			//		+ "deviceTypeImgs");
			if (!smallSaveFile.exists()) {
				smallSaveFile.mkdirs();
			}
			String smallFileName = logoRealPathDir + File.separator
					+ "deviceTypeImgs" + File.separator + logImageName;
			//System.out.println(smallFileName);
			try {
				//add "if" sentence because some are excel files, resizeImage doesn't work on them, SUNZHE, 2017-05-25
				if(fileName.indexOf("jpg")>0 || fileName.indexOf("jpeg")>0 || fileName.indexOf("png")>0){
					ImageResizer.resizeImage(fileName, smallFileName, 200, 200);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getOriginalFileName(String origin) {
		if (StringUtils.isNotEmpty(origin)) {
			origin = origin.replace(" ", "_");
			if (origin.contains(".")) {
				origin = origin.substring(origin.lastIndexOf("."));
			}
		}
		if (StringUtils.isEmpty(origin)) {
			origin = "";
		} else {
			if (origin.startsWith(".")) {
				origin = origin.substring(1);
			}
		}
		return origin;
	}
	
	//Start: Upload credit file, SUNZHE, 2017-05-27
	@RequestMapping("/uploadCreditFile")
	@ResponseBody
	public FileResponse uploadCreditFile(HttpServletRequest request)
			throws IllegalStateException, IOException {
		FileResponse response = new FileResponse();
		try {
			//System.out.println(request.getParameter("fileName"));
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			/** 构建文件保存的目录 **/
			String filePathDir = "/styles/credit";
			/** 得到文件保存目录的真实路径 **/
			String logoRealPathDir = request.getSession().getServletContext()
					.getRealPath(filePathDir);
			/** 根据真实路径创建目录 **/
			File logoSaveFile = new File(logoRealPathDir);
			if (!logoSaveFile.exists())
				logoSaveFile.mkdirs();
			/** 页面控件的文件流 **/
			MultipartFile multipartFile = multipartRequest.getFile(request
					.getParameter("fileName"));

			// 构建文件名称, removed by SUNZHE, to use new logic only for Credit File
			//String logImageName = UUID.randomUUID().toString() + "." + getOriginalFileName(multipartFile.getOriginalFilename());
			String creditFileName = "Credit_" + request.getParameter("projectID") + 
					"." + getOriginalFileName(multipartFile.getOriginalFilename());

			/** 拼成完整的文件保存路径加文件 **/
			String fileName = logoRealPathDir + "/" + creditFileName;
			LogUtil.info(log, "上传文件保存在[" + fileName + "]");
			// ---------------------------------------------------------

			File file = new File(fileName);

			multipartFile.transferTo(file);
			//System.out.println(filePathDir + "/" + creditFileName);

			response.setError("false");
			response.setMsg(filePathDir + "/" + creditFileName);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setError("true");
			response.setMsg(e.getMessage());
			return response;
		}
	}	
	
	//End: Upload credit file, SUNZHE, 2017-05-27
}

class FileResponse {
	private String error;
	private String msg;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}