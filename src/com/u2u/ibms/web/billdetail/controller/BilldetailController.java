package com.u2u.ibms.web.billdetail.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.base.BaseRequest;
import com.u2u.framework.beans.AjaxDone;
import com.u2u.framework.util.DateUtil;
import com.u2u.framework.util.UUIDUtil;
import com.u2u.ibms.web.billdetail.bean.Billdetail;
import com.u2u.ibms.web.billdetail.condition.BilldetailCondition;
import com.u2u.ibms.web.billdetail.service.BilldetailService;

@Controller
@RequestMapping("/web/billdetail")
public class BilldetailController extends BaseController {

	@Autowired
	private BilldetailService billdetailService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/billdetail/billdetailList";
	}

	@RequestMapping("/billdetailImport")
	public String importBilldetail() {
		return "/ibms/billdetail/billdetailImport";
	}

	@RequestMapping("/billdetailList")
	public String assetList() {
		return "/ibms/billdetail/billdetailList";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			BilldetailCondition condition) {
		List<Billdetail> billdetails = billdetailService.getAll(condition,
				buildRowBounds(baseRequest));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total",
				billdetailService.getAll(condition, buildRowBounds()).size());
		result.put("rows", billdetails);
		return result;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/billdetail/billdetailAdd");
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(Billdetail billdetail) {
		billdetailService.insert(billdetail);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/billdetail/billdetailEdit");
		mav.addObject("billdetail", billdetailService.getById(id));
		return mav;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxDone update(Billdetail billdetail) {
		billdetailService.update(billdetail);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxDone delete(int id) {
		billdetailService.delete(id);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public void uploadFile(HttpServletRequest request,
			@RequestParam("filedata") MultipartFile multipartFile,
			Integer taskType) throws Exception {
		String uuid = UUIDUtil.generateUUID();
		String pathUrl = "/styles/billdetail/" + uuid;

		// 构建文件名称
		String fileName = multipartFile.getOriginalFilename();

		/** 拼成完整的文件保存路径加文件 **/
		String saveUrl = pathUrl + "/" + fileName;

		/** 得到图片保存目录的真实路径 **/
		String logoRealPathDir = request.getSession().getServletContext()
				.getRealPath(saveUrl);
		/** 根据真实路径创建目录 **/
		File logoSaveFile = new File(logoRealPathDir);
		if (!logoSaveFile.exists())
			logoSaveFile.mkdirs();

		// 文件上传
		multipartFile.transferTo(logoSaveFile);
		importExcel(logoSaveFile);
	}

	@Transactional
	private void importExcel(File file)
			throws InvalidFormatException, IOException {
		List<String> dataList = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(
					new InputStreamReader(new FileInputStream(file), "GBK"));
			String line = "";
			while ((line = br.readLine()) != null) {
				dataList.add(line);
			}
		} catch (Exception e) {
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
			for (int i = 1; i < dataList.size(); i++) {
				String[] oriData = dataList.get(i).split(",");
				//System.out.println(oriData.length);
				if (oriData.length != 9) {
					continue;
				}
				List<Billdetail> billDetails = billdetailService
						.getByDeviceNoAndDate(oriData[3], oriData[5]);
				Billdetail billdetail = null;
				int opeFlag = 0;
				if (CollectionUtils.isNotEmpty(billDetails)) {
					billdetail = billDetails.get(0);
					opeFlag = 1;
				} else {
					billdetail = new Billdetail();
					opeFlag = 0;
				}

				billdetail.setCustomer(oriData[0]);
				billdetail.setContact(oriData[1]);
				billdetail.setContract(oriData[2]);
				billdetail.setDeviceno(oriData[3]);
				billdetail.setType(oriData[4]);
				billdetail.setDevicedate(DateUtil.date2String(
						DateUtil.string2Date(oriData[5], "yyyy-MM-dd"),
						DateUtil.PATTERN_DATE));
				billdetail.setRunningtime(oriData[6]);
				billdetail.setNochargingtime(oriData[7]);
				billdetail.setChargingtime(oriData[8]);
				billdetail.setCreateDate(DateUtil.currentTimestamp());
				billdetail.setOperateDate(DateUtil.currentTimestamp());
				if (opeFlag == 0) {
					billdetailService.insert(billdetail);
				} else {
					billdetailService.update(billdetail);
				}

			}
	}

	@RequestMapping(value = "/download")
	public ModelAndView download(HttpServletRequest request,
			HttpServletResponse response) {
		OutputStream bos = null;
		// 返回下载文件名称
		String path = request.getContextPath();
		String downLoadName = "billdetail.csv";
		// 设置文件输出类型
		try {
			response.sendRedirect(
					path + "/styles/billdetail/" + getDownLoadName(downLoadName,
							request.getHeader("USER-AGENT")));
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		// File oriFile = new File(
		// path + File.separator + "styles" + File.separator + "billdetail" +
		// File.separator + downLoadName);
		//
		// response.setContentType("text/csv");
		// try {
		// downLoadName = getDownLoadName(downLoadName,
		// request.getHeader("USER-AGENT"));
		// } catch (Exception e2) {
		// // TODO Auto-generated catch block
		// e2.printStackTrace();
		// }
		// response.setHeader("Content-disposition", "attachment; filename=" +
		// downLoadName);
		// // 输出流
		// try {
		// bos = new BufferedOutputStream(response.getOutputStream());
		// } catch (IOException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		//
		// byte[] buff = null;
		// try {
		// buff = FileUtils.readFileToByteArray(oriFile);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// try {
		// bos.write(buff);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return null;
	}

	public static String getDownLoadName(String filename, String agent)
			throws Exception {
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
