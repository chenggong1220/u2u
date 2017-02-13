package com.u2u.ibms.web.bill.controller;

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
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.u2u.ibms.web.bill.bean.Bill;
import com.u2u.ibms.web.bill.condition.BillCondition;
import com.u2u.ibms.web.bill.service.BillService;

@Controller
@RequestMapping("/web/bill")
public class BillController extends BaseController {

	@Autowired
	private BillService billService;

	@RequestMapping("/index")
	public String index() {
		return "/ibms/bill/billList";
	}

	@RequestMapping("/billImport")
	public String importBill() {
		return "/ibms/bill/billImport";
	}
	@RequestMapping("/billList")
	public String assetList() {
		return "/ibms/bill/billList";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(BaseRequest baseRequest,
			BillCondition condition) {
		List<Bill> bills = billService.getAll(condition,
				buildRowBounds(baseRequest));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total",
				billService.getAll(condition, buildRowBounds()).size());
		result.put("rows", bills);
		return result;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/bill/billAdd");
		return mav;
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDone save(Bill bill) {
		billService.insert(bill);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ibms/bill/billEdit");
		mav.addObject("bill", billService.getById(id));
		return mav;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxDone update(Bill bill) {
		billService.update(bill);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxDone delete(int id) {
		billService.delete(id);
		return ajaxDoneSuccess(null);
	}

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public void uploadFile(HttpServletRequest request,
			@RequestParam("filedata") MultipartFile multipartFile,
			Integer taskType) throws Exception {
		String uuid = UUIDUtil.generateUUID();
		String pathUrl = "/styles/bill/" + uuid;

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

	private void importExcel(File file)
			throws InvalidFormatException, IOException {
		Workbook wb = WorkbookFactory.create(file);
		Sheet sheet = wb.getSheetAt(0);
		for (int r = 1; r < sheet.getLastRowNum() + 1; r++) {
			Row row = sheet.getRow(r);
			Cell cellId = row.getCell(0);
			if (cellId == null) {
				continue;
			}
			Bill bill = new Bill();
			bill.setAccountNum(row.getCell(0).toString());
			bill.setAccountName(row.getCell(1).toString());
			bill.setCusName(row.getCell(2).toString());
			bill.setCusNum(row.getCell(3).toString());
			
			bill.setDealDate(row.getCell(4).toString());
			
			//Change Field Type from Date to String directly, by SUNZHE, 2017-01-19
			//bill.setDealDate(DateUtil.date2String(
			//		row.getCell(4).getDateCellValue(), DateUtil.PATTERN_DATE));
			
			bill.setAmount(row.getCell(5).toString());
			
			bill.setBillingDate(row.getCell(6).toString());
			//Change Field Type from Date to String directly, by SUNZHE, 2017-01-19
			//bill.setBillingDate(DateUtil.date2String(
			//		row.getCell(6).getDateCellValue(), DateUtil.PATTERN_DATE));
			bill.setFinancialNum(row.getCell(7).toString());
			bill.setBankSerialNumber(row.getCell(8).toString());
			bill.setCreateDate(DateUtil.currentTimestamp());
			bill.setOperateDate(DateUtil.currentTimestamp());
			String bankSerialNumber = row.getCell(8).toString();
			bill.setRemark(row.getCell(9).toString());
			
			Bill existBill = billService.getOne(bankSerialNumber);
			if(existBill != null)
			{
				bill.setId(existBill.getId());
				billService.updateBill(bill);
			}else{
				billService.insert(bill);
			}

		}
	}

	@RequestMapping(value = "/download")
	public ModelAndView download(HttpServletRequest request,
			HttpServletResponse response) {
		OutputStream bos = null;
		// 返回下载文件名称
		String path = request.getSession().getServletContext().getRealPath("");
		String downLoadName = "bill-import.xls";
		// 设置文件输出类型
		File oriFile = new File(path + File.separator + "styles"
				+ File.separator + "bill" + File.separator + downLoadName);
		response.setContentType("application/x-xls");
		try {
			downLoadName = getDownLoadName(downLoadName,
					request.getHeader("USER-AGENT"));
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		response.setHeader("Content-disposition",
				"attachment; filename=" + downLoadName);
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
