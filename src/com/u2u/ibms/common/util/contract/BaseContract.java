package com.u2u.ibms.common.util.contract;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.u2u.ibms.common.util.contract.vo.ContractFileVo;
import com.u2u.ibms.common.util.contract.vo.ContractRentAsset;

public class BaseContract {

	protected Document getDocument(File file, ContractFileVo contractFile)
			throws Exception {
		Rectangle rect = new Rectangle(PageSize.A4);// 设置页面大小
		rect.setBackgroundColor(BaseColor.WHITE);// 页面背景色
		Document document = new Document(rect);// 创建一个Document
		// 页边空白
		document.setMargins(40, 40, 80, 40); // 左，右，上，下
		// 创建书写器(Writer)与document对象关联，通过书写器可以将文档写入磁盘中
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream(file));
		writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);// 设置PDF版本（默认1.4）
		writer.setBoxSize("art", rect);
		writer.setPageEvent(new HeaderFooter(contractFile));

		// 文档属性
		document.addTitle("Unis合同模版"); // 标题
		document.addAuthor("Unis");// 作者
		document.addSubject("Unis");// 主题
		document.addKeywords("Unis");// 关键字
		document.addCreator("Unis");// 创建者
		document.open();// 打开文档

		return document;
	}

	protected void addFirstTeablJiafang(final PdfPTable table,
			ContractFileVo contractFile) throws Exception {
		table.addCell(getCell("甲方(出租人)：优尼斯融资租赁(上海)有限公司"));
		table.addCell(getCell("地址：上海市普陀区大渡河路388弄5号楼 国盛中心23层"));
		table.addCell(getCell("法定代表人：董彦宏"));
		table.addCell(getCell("邮编：200062"));
		table.addCell(getCell("电话：021-61005690"));
		table.addCell(getCell("邮箱：u2uorders@unislease.com"));
		table.addCell(getCell("经办人：" + contractFile.getOperator()));
		table.addCell(getCell("经办人联系电话：" + contractFile.getOperatorMobile()));
	}

	protected void addFirstTeablBingfang(final PdfPTable table)
			throws Exception {
		table.addCell(getCell("丙方(服务商)：上海优尼斯工业设备销售有限公司"));
		table.addCell(getCell("地址：沈阳市经济技术开发区 开发大路17甲1号"));
		table.addCell(getCell("电话：024-25191904"));
		table.addCell(getCell("报修热线：400-615-9999"));
		table.addCell(getCell("经办人："));
		table.addCell(getCell("经办人联系电话：024-25191904"));
	}

	protected PdfPTable generateSecondTableAssetList(ContractFileVo contractFile)
			throws Exception {
		PdfPTable table = new PdfPTable(5);// 建立一个pdf表格
		table.setWidthPercentage(100);// 设置表格宽度为100%

		PdfPCell cell = getBorderCell("租赁物名称", true);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("规格型号", true);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("租金费率（人民币）", true);		//由"租金"改成"租金费率" ,by SUNZHE, 2017-01-10
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("数量", true);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("计费方式", true);				//由"备注（产品配置）"改成"计费方式" ,by SUNZHE, 2017-01-10
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		for (ContractRentAsset contractRentAsset : contractFile.getAssets()) {

			cell = getBorderCell(contractRentAsset.getBrand(), false);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = getBorderCell(contractRentAsset.getModel(), false);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			if (contractFile.getRentType() == 0) {
				cell = getBorderCell(
						getDecimalFormat(contractRentAsset.getAmount())
								+ " 元/台/小时", false);
			} else {
				cell = getBorderCell(
						getDecimalFormat(contractRentAsset.getAmount())
								+ " 元/台/月", false);
			}
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = getBorderCell(contractRentAsset.getCount() + " 台", false);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			
			//由"标配"改成计费方式值 ,by SUNZHE, 2017-01-10
			if (contractFile.getRentType() == 0) {
				cell = getBorderCell("分时计费", false);
			} else {
				cell = getBorderCell("包月计费", false);
			}
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
		}
		return table;
	}

	protected PdfPTable generateSecondTableLocation(ContractFileVo contractFile)
			throws Exception {
		PdfPTable table = new PdfPTable(15);// 建立一个pdf表格
		table.setWidthPercentage(100);// 设置表格宽度为100%
		PdfPCell cell = getBorderCell("租赁物交付地点", true);
		cell.setColspan(3);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell(contractFile.getTransforAddress(), false);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);

		cell = getBorderCell("租赁物使用地点", true);
		cell.setColspan(3);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell(contractFile.getUseAddress(), false);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);

		cell = getBorderCell("租赁物归还地点", true);
		cell.setColspan(3);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell(" 甲方指定地点", false);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);

		return table;
	}

	protected PdfPTable generateSecondTableDate() throws Exception {
		PdfPTable table = new PdfPTable(15);// 建立一个pdf表格
		table.setWidthPercentage(100);// 设置表格宽度为100%
		PdfPCell cell = getBorderCell("起租时间", true);
		cell.setColspan(3);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		// cell =
		// getBorderCell("租赁物的“起租时间”为该租赁物的提货验收时间(以乙丙双方签字的《租赁物交付确认单》所载时间为准)",
		// false);
		cell = getBorderCell("“起租时间”为乙方确认或被视为确认租赁物合格的时间", false);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);

		cell = getBorderCell("归还时间", true);
		cell.setColspan(3);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("“归还时间”为租赁物的归还验收时间", false);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);

		return table;
	}

	protected PdfPTable generateSecondTableFirstPay(ContractFileVo contractFile)
			throws Exception {
		PdfPTable table = new PdfPTable(15);// 建立一个pdf表格
		table.setWidthPercentage(100);// 设置表格宽度为100%
		PdfPCell cell = getBorderCell("首期款项明细", true);
		cell.setRowspan(3);
		cell.setColspan(3);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("预付租金", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("￥0.00", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("押金合计", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("￥" + getDecimalFormat(contractFile.getDeposit())
				+ " 元", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("保险服务费", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell(
				"￥" + getDecimalFormat(contractFile.getInsuranceServeAmount()),
				false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("交机服务费", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("￥0.00", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("还机服务费", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("￥0.00", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("首期款支付期限", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("签约后3个工作日内", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("费用合计", true);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell(
				"￥" + getDecimalFormat(contractFile.getAmountTotal()), true);
		cell.setColspan(4);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("( 大写：人民币  " + ConvertNum.NumToChinese(Double.valueOf(contractFile
				.getAmountTotal())) + " )", true);
		cell.setColspan(6);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

//Start: Soft Deleted by SUNZHE, 2017-01-11	
//		cell = getBorderCell(
//				ConvertNum.NumToChinese(Double.valueOf(contractFile
//						.getAmountTotal())) + " )", true);
//		cell.setColspan(4);
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		table.addCell(cell);
//End: Soft Deleted by SUNZHE, 2017-01-11		

		return table;
	}

	protected PdfPTable generateSecondTableJiaofuCondition(
			ContractFileVo contractFileVo) throws Exception {
		PdfPTable table = new PdfPTable(15);// 建立一个pdf表格
		table.setWidthPercentage(100);// 设置表格宽度为100%

		PdfPCell cell = getBorderCell("通 知 方 式", true);
		cell.setColspan(3);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("应用软件系统发送", false);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);

		cell = getBorderCell("设备交付前提", true);
		cell.setColspan(3);
		cell.setRowspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("(1)本《设备租赁合同》已完成签署并生效", false);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidthBottom(0);
		table.addCell(cell);

		cell = getBorderCell("(2)甲方收到乙方支付的本合同项下的全部首期款项(详见首期款项明细)", false);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidthTop(0);
		table.addCell(cell);

		return table;
	}

	protected PdfPTable generateSecondTablePayInfo() throws Exception {
		PdfPTable table = new PdfPTable(15);// 建立一个pdf表格
		table.setWidthPercentage(100);// 设置表格宽度为100%
		PdfPCell cell = getBorderCell("收款账户", true);
		cell.setColspan(3);
		cell.setRowspan(8);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("乙方应将首期款项及每期租金支付至甲方如下账户：", false);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidthBottom(0);
		table.addCell(cell);

		//Start, Update by SUNZHE, 2017-01-10
		cell = getBorderCell("电汇方式：", false);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidthTop(0);
		cell.setBorderWidthBottom(0);
		table.addCell(cell);	
		
		cell = getBorderCell("    户       名：优尼斯融资租赁(上海)有限公司", false);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidthTop(0);
		cell.setBorderWidthBottom(0);
		table.addCell(cell);

		cell = getBorderCell("    开户银行：中国民生银行上海曹安支行", false);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidthTop(0);
		cell.setBorderWidthBottom(0);
		table.addCell(cell);

		cell = getBorderCell("    帐       号：694 398 257", false);		
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidthTop(0);
		cell.setBorderWidthBottom(0);
		table.addCell(cell);
		
		cell = getBorderCell("线上支付方式：", false);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidthTop(0);
		cell.setBorderWidthBottom(0);
		table.addCell(cell);	
		
		cell = getBorderCell("支付宝账户名称：优尼斯融资租赁（上海）有限公司", false);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidthTop(0);
		cell.setBorderWidthBottom(0);
		table.addCell(cell);	
		
		cell = getBorderCell("支付宝账户：ulzfb@unislease.com", false);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidthTop(0);
		table.addCell(cell);			
		//End, Update by SUNZHE, 2017-01-10		

		return table;
	}

	protected PdfPTable generateSignoffDate() throws Exception {
		PdfPTable table = new PdfPTable(6);// 建立一个pdf表格
		table.setSpacingBefore(20f);
		table.setWidthPercentage(100);// 设置表格宽度为100%

		PdfPCell cell = getCell("");
		cell.setColspan(4);
		table.addCell(cell);

		Date current = new Date(System.currentTimeMillis() + 3 * 24 * 60 * 60
				* 1000);
		cell = getCell("签署日期：" + new SimpleDateFormat("yyyy").format(current)
				+ "年 " + new SimpleDateFormat("MM").format(current) + "月"
				+ new SimpleDateFormat("dd").format(current) + "日", true);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setColspan(2);
		table.addCell(cell);

		return table;
	}

	protected PdfPTable generateSecondTableHeader() throws Exception {
		PdfPTable table = new PdfPTable(1);// 建立一个pdf表格
		table.setWidthPercentage(100);// 设置表格宽度为100%
		PdfPCell cell = getBorderCell("租赁要素表", true);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		return table;
	}

	protected Paragraph getTitle(String titleName) throws Exception {
		BaseFont bfChinese = BaseFont.createFont(UserContract.FONT_NAME,
				FONT_ENCODE, FONT_EMBEDED);
		Font titleChinese = new Font(bfChinese, 20, Font.BOLD);

		Paragraph title = new Paragraph(titleName, titleChinese);// 设置标题
		title.setAlignment(Element.ALIGN_CENTER);// 设置对齐方式
		title.setLeading(1f);// 设置行间距
		return title;
	}

	protected Paragraph getFooter() throws Exception {
		// 设置字体
		BaseFont bfChinese = BaseFont.createFont(UserContract.FONT_NAME,
				FONT_ENCODE, FONT_EMBEDED);
		Font subBoldFontChinese = null;
		//subBoldFontChinese = new Font(bfChinese, 9, Font.BOLD);
		subBoldFontChinese = new Font(bfChinese, 9, Font.NORMAL);		
		return new Paragraph(
				"\n特别提示：《设备租赁合同一般条款》中需要引起特别注意的事项，均以加粗下划线形式作出标记。合同各方确认已详细阅读并完全理解包括《租赁要素表》、一般条款及相关附件在内的本合同全部条款和附件，并自愿遵守合同条款的约定。",
				subBoldFontChinese);

		//
		// 承租人： 0 （企业盖公章/个人按手印）
		// 法定代表人（企业）/本人（个人）签字：

	}

	protected PdfPTable getReadSignoff() throws Exception {
		PdfPTable table = new PdfPTable(2);// 建立一个pdf表格
		table.setSpacingBefore(20f);
		table.setWidthPercentage(100);// 设置表格宽度为100%

		PdfPCell cell = getCell("承租人：", true);
		table.addCell(cell);

		cell = getCell("（企业盖公章/个人按手印）", false);
		table.addCell(cell);

		cell = getCell("法定代表人（企业）/本人（个人）签字：", true);
		table.addCell(cell);

		cell = getCell("", false);
		table.addCell(cell);

		return table;
	}

	public static final String FONT_ENCODE = BaseFont.IDENTITY_H;
	public static final boolean FONT_EMBEDED = BaseFont.EMBEDDED;

	protected PdfPCell getCell(String text, boolean bold) throws Exception {
		// 设置字体
		BaseFont bfChinese = BaseFont.createFont(UserContract.FONT_NAME,
				FONT_ENCODE, FONT_EMBEDED);
		Font subBoldFontChinese = null;
		if (bold) {
			subBoldFontChinese = new Font(bfChinese, 9, Font.BOLD);
		} else {
			subBoldFontChinese = new Font(bfChinese, 9);
		}
		PdfPCell cell = new PdfPCell(new Paragraph(text, subBoldFontChinese));
		cell.setBorder(0);
		return cell;
	}

	protected PdfPCell getCell(String text) throws Exception {
		return getCell(text, false);
	}

	protected PdfPCell getBorderCell(String text, boolean bold)
			throws Exception {
		// 设置字体
		BaseFont bfChinese = BaseFont.createFont(UserContract.FONT_NAME,
				FONT_ENCODE, FONT_EMBEDED);
		Font subBoldFontChinese = null;
		
		//Changed FontSize from 8 to 11, by SUNZHE, 2017-01-12
		if (bold) {
			subBoldFontChinese = new Font(bfChinese, 9, Font.BOLD);	
		} else {
			subBoldFontChinese = new Font(bfChinese, 9);
		}

		return new PdfPCell(new Paragraph(text, subBoldFontChinese));
	}

	protected void getGeneratorCell(final PdfPTable table, String text,
			boolean bold) throws Exception {
		PdfPCell cell = getCell(text, bold);
		table.addCell(cell);
	}

	protected void getGeneratorCell(final PdfPTable table, String text)
			throws Exception {
		getGeneratorCell(table, text, false);
	}

	protected String getLimitedNumber(String number) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);
		return numberFormat.format(Double.valueOf(number));
	}

	
	//Start: Add by SUNZHE, 2017-01-11
	protected String getDecimalFormat(String number) {
		DecimalFormat decimalFormat = new DecimalFormat(".00");	//小数不足2位,会以0补足
		return decimalFormat.format(Double.valueOf(number));						//format 返回的是字符串
	}
	
	protected String getNonNullTxt(String text) {
		if(text == null){	return "";	}
		else{	return text;	}
	}
	
	protected void getTermCell(final PdfPTable table, String text,
			boolean bold) throws Exception {
		//Just for Terms
		// 设置字体
		BaseFont bfChinese = BaseFont.createFont(UserContract.FONT_NAME,
				FONT_ENCODE, FONT_EMBEDED);
		Font subBoldFontChinese = null;
		if (bold) {
			subBoldFontChinese = new Font(bfChinese, 7, Font.BOLD);
		} else {
			subBoldFontChinese = new Font(bfChinese, 7);
		}
		

            // 创建Chunk对象，设置下划线的厚度为0.1
            Chunk txtunderline = new Chunk(text);
            txtunderline.setUnderline(0.1f, -1f);
		
		PdfPCell cell = new PdfPCell(new Paragraph(text, subBoldFontChinese));
		cell.setBorder(0);		
		
		table.addCell(cell);
	}

	protected void getTermCell(final PdfPTable table, String text)
			throws Exception {
		getTermCell(table, text, false, false);
	}	
	
	
	
	
	
	protected void getTermCell(final PdfPTable table, String text,
			boolean bold, boolean underline) throws Exception {
		//Just for Terms
		// 设置字体
		BaseFont bfChinese = BaseFont.createFont(UserContract.FONT_NAME,
				FONT_ENCODE, FONT_EMBEDED);
		Font subBoldFontChinese = null;
		int fontStyle = Font.NORMAL;
		//Font SUBFONT = new Font(Font.getFamily("TIMES_ROMAN"), 12,    Font.BOLD|Font.UNDERLINE);		//for example
		if(bold) {
			if(underline){
				fontStyle = Font.BOLD|Font.UNDERLINE;
			}else{
				fontStyle = Font.BOLD;
			}
		}else{
			if(underline){
				fontStyle = Font.UNDERLINE;
			}else{
				fontStyle = Font.NORMAL;
			}
		}
		
		subBoldFontChinese = new Font(bfChinese, 7, fontStyle);		
		
		PdfPCell cell = new PdfPCell(new Paragraph(text, subBoldFontChinese));
		cell.setBorder(0);		
		
		table.addCell(cell);
	}
	//ENd: Add by SUNZHE, 2017-01-11
}
