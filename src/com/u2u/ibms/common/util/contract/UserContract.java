package com.u2u.ibms.common.util.contract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.u2u.ibms.common.util.contract.vo.ContractFileVo;

/**
 * Hello world!
 * 
 */
public class UserContract extends BaseContract {

	private ContractFileVo contractVo;

	public static String FONT_NAME;

	public UserContract(ContractFileVo contractVo) {
		this.contractVo = contractVo;
		FONT_NAME = contractVo.getZiti().getAbsolutePath();
	}

	public void generateFile(File file) throws Exception {

		Document document = getDocument(file, this.contractVo);

		document.add(getTitle("设备租赁合同"));
		document.add(generateFirstTable());
		document.add(generateSecondTable());
		document.add(new Paragraph());

		document.add(generateSignoff1());
		// document.add(img);
		document.add(new Paragraph());
		document.add(new Paragraph());
		document.add(generateSignoffDate());
		document.add(new Paragraph());
		document.add(getFooter());
		document.newPage();
		document.add(new ContractGenerationsUtil().getContractGenerator());
		document.add(new Paragraph());
		document.add(new Paragraph());
		document.add(generateSignoff2());
		document.close();

		Image img = Image.getInstance(this.contractVo.getSealFile()
				.getAbsolutePath());
		img.scaleAbsolute(120, 120);
		img.setAbsolutePosition(160, 120);
		//img.setAbsolutePosition(180, 190);
		
		//Start: Added GM Signature by SUNZHE, 2017-01-11
		Image imgSignature = Image.getInstance(this.contractVo.getSignatureFile()
				.getAbsolutePath());
		imgSignature.scaleAbsolute(100, 50);
		imgSignature.setAbsolutePosition(120, 150);		
		//Start: Updated by SUNZHE, 2017-01-11

		File tmp = new File(file.getAbsolutePath() + ".tmp");
		PdfReader reader = null;
		PdfStamper stamp = null;
		try {
			reader = new PdfReader(new FileInputStream(file));
			stamp = new PdfStamper(reader, new FileOutputStream(tmp));
			stamp.getOverContent(1).addImage(imgSignature);			
			stamp.getOverContent(1).addImage(img);
		} finally {
			reader.close();
			stamp.close();
		}

		OutputStream os = null;
		InputStream is = null;
		try {
			os = new FileOutputStream(file);
			is = new FileInputStream(tmp);
			byte[] buffer = new byte[1024];

			int index = -1;
			while ((index = is.read(buffer, 0, buffer.length)) != -1) {
				os.write(buffer, 0, index);
			}
		} finally {
			os.close();
			is.close();
		}
	}

	private PdfPTable generateFirstTable() throws Exception {
		final PdfPTable table = new PdfPTable(2);// 建立一个pdf表格
		table.setSpacingBefore(20f);
		table.setWidthPercentage(100);// 设置表格宽度为100%
		addFirstTeablJiafang(table, this.contractVo);
		table.addCell(getCell(""));
		table.addCell(getCell(""));
		table.addCell(getCell(""));
		table.addCell(getCell(""));
		table.addCell(getCell("乙方(承租人)："
				+ contractVo.getContractYifang().getName()));
		table.addCell(getCell("地址："
				+ getNonNullTxt(contractVo.getContractYifang().getAddress())));
		table.addCell(getCell("法定代表人："
				+ contractVo.getContractYifang().getLegalPerson()));
		table.addCell(getCell("邮编："
				+ getNonNullTxt(contractVo.getContractYifang().getPostcode())));
		table.addCell(getCell("手机号码："
				+ getNonNullTxt(contractVo.getContractYifang().getMobile())));
		table.addCell(getCell("电子邮箱："
				+ getNonNullTxt(contractVo.getContractYifang().getEmail())));
		table.addCell(getCell("证件号码："
				+ getNonNullTxt(contractVo.getContractYifang().getIdentifier())));
		
		String emergencyContact = getNonNullTxt(contractVo.getContractYifang().getEmergency()) + "("
				+ getNonNullTxt(contractVo.getContractYifang().getEmergencyRelation()) + ")"
				+ getNonNullTxt(contractVo.getContractYifang().getEmergencyMobile());
		if(emergencyContact.equals("()")){	emergencyContact = "";	}
		table.addCell(getCell("经办人/紧急联系人及联系方式：" + emergencyContact));
//		+ contractVo.getContractYifang().getEmergency() + "("
//		+ contractVo.getContractYifang().getEmergencyRelation() + ")"
//		+ contractVo.getContractYifang().getEmergencyMobile())
		table.addCell(getCell(""));
		table.addCell(getCell(""));
		table.addCell(getCell(""));
		table.addCell(getCell(""));
		//addFirstTeablBingfang(table);		//屏蔽丙方信息，by SUNZHE, 2017-01-10
		return table;
	}

	private PdfPTable generateSecondTable() throws Exception {
		PdfPTable table = new PdfPTable(1);// 建立一个pdf表格
		table.setSpacingBefore(20f);
		table.setWidthPercentage(100);// 设置表格宽度为100%
		table.addCell(generateSecondTableHeader());
		table.addCell(generateSecondTableAssetList(this.contractVo));
		table.addCell(generateSecondTableLocation(this.contractVo));
		table.addCell(generateSecondTableConstructure());
		table.addCell(generateSecondTableDate());
		table.addCell(generateSecondTableFirstPay(this.contractVo));
		table.addCell(generateSecondTableJiaofuCondition(this.contractVo));
		table.addCell(generateSecondTablePayInfo());
		return table;
	}

	private PdfPTable generateSignoff1() throws Exception {
		PdfPTable table = new PdfPTable(12);// 建立一个pdf表格
		table.setSpacingBefore(20f);
		table.setWidthPercentage(100);// 设置表格宽度为100%

		PdfPCell cell = getCell("甲方(出租人)：", true);
		cell.setColspan(2);
		table.addCell(cell);

		cell = getCell("优尼斯融资租赁(上海)有限公司  (盖章)");
		cell.setColspan(4);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);

		cell = getCell("乙方(承租人)：", true);
		cell.setColspan(2);
		table.addCell(cell);

		cell = getCell(contractVo.getContractYifang().getName());
		cell.setColspan(4);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);

		cell = getCell("(企业盖公章/个人按手印)");
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);
		
		cell = getCell("法定代表人签字：", true);
		cell.setColspan(6);
		table.addCell(cell);

		cell = getCell("法定代表人(企业)/本人(个人)签字：", true);
		cell.setColspan(6);
		table.addCell(cell);
		
		return table;
	}

	private PdfPTable generateSignoff2() throws Exception {
		PdfPTable table = new PdfPTable(6);// 建立一个pdf表格
		table.setSpacingBefore(20f);
		table.setWidthPercentage(100);// 设置表格宽度为100%

		PdfPCell cell = getCell("承租人："
				+ this.contractVo.getContractYifang().getName(), true);
		cell.setColspan(3);
		table.addCell(cell);

		cell = getCell("（企业盖公章/个人按手印）");
		cell.setColspan(3);
		table.addCell(cell);

		cell = getCell("法定代表人（企业）/本人（个人）签字：", true);
		cell.setColspan(6);
		table.addCell(cell);
		return table;
	}

	private PdfPTable generateSecondTableConstructure() throws Exception {
		PdfPTable table = new PdfPTable(15);// 建立一个pdf表格
		table.setWidthPercentage(100);// 设置表格宽度为100%
		PdfPCell cell = getBorderCell("交易结构", true);
		cell.setRowspan(3);
		cell.setColspan(3);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("押金", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell(getDecimalFormat(contractVo
				.getContractConstructor().getDeposit()) + " 元/台", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("租赁期限", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell(contractVo.getContractConstructor().getTotalDate()
				+ " 个月", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("租金结算日", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("每月1日", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("提取租赁物方式", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("服务商送机上门", false);		//由"丙方送机上门"改成"服务商送机上门" ,by SUNZHE, 2017-01-10
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("归还租赁物方式", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("服务商上门取机", false);			//由"丙方送机取机"改成"服务商上门取机" ,by SUNZHE, 2017-01-10
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("租金支付日", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("每月5日", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("租金支付方式", false);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = getBorderCell("按月支付,电汇或线上支付", false);	//由"一月支付一次,线上支付"改成"按月支付,电汇或线上支付" ,by SUNZHE, 2017-01-10
		cell.setColspan(10);
		table.addCell(cell);
		return table;
	}

}
