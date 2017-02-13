package com.u2u.ibms.common.util.contract;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.u2u.ibms.common.util.contract.vo.ContractFileVo;

public class HeaderFooter extends PdfPageEventHelper {

	private PdfTemplate total;
	private ContractFileVo contractVo;
	private BaseFont bfChinese;
	private int fontSize;  

	public HeaderFooter(ContractFileVo contractVo) {
		this.contractVo = contractVo;
		
		try {
			bfChinese = BaseFont.createFont(contractVo.getZiti()
					.getAbsolutePath(), BaseContract.FONT_ENCODE,
					BaseContract.FONT_EMBEDED);
			fontSize = 7;
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void onOpenDocument(PdfWriter writer, Document document) {
		total = writer.getDirectContent().createTemplate(30, 16);
	}
	

	public void onStartPage(PdfWriter writer, Document document) {	
		PdfPTable table = new PdfPTable(3);
		try {
			//Header
			table.setWidths(new int[] { 24, 2, 24 });
			table.setTotalWidth(527);
			table.setLockedWidth(true);
			table.getDefaultCell().setFixedHeight(20);
			table.getDefaultCell().setBorder(Rectangle.BOTTOM);
			table.addCell(Image.getInstance(contractVo.getLogo()
					.getAbsolutePath()));

			Font subBoldFontChinese = new Font(bfChinese, fontSize);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
			table.addCell(new Paragraph("", subBoldFontChinese));			

			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
			//Font subBoldFontChinese = new Font(bfChinese, fontSize);
			table.addCell(new Paragraph("合同编号："
					+ contractVo.getContractId().replace("_", "") + "-OL",
					subBoldFontChinese));		
			
			
			PdfPCell cell = new PdfPCell(Image.getInstance(total));
			cell.setBorder(Rectangle.BOTTOM);
			table.addCell(cell);
			
			table.writeSelectedRows(0, -1, 34, 820, writer.getDirectContent());	
		} catch (Exception de) {
			throw new ExceptionConverter(de);
		}
	}
	

	//Add Footer, by SUNZHE, 2017-01-14 
	public void onEndPage(PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent();
		cb.saveState();
		cb.beginText();
		
		cb.setFontAndSize(bfChinese, fontSize);
		float y = document.bottom(-20);

		//左
		//cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"F-Left",document.left(), y, 0);
		//中
		cb.showTextAligned(PdfContentByte.ALIGN_CENTER, writer.getPageNumber()+ " / 3" ,(document.right() + document.left())/2,y, 0);
		//右
		//cb.showTextAligned(PdfContentByte.ALIGN_RIGHT,"F-Right",document.right(), y, 0);

		cb.addTemplate(total, (document.rightMargin() + document.right() + 
							   document.leftMargin() - document.left()) / 2.0F + 20F, 
				               document.bottom() - 20); // 调节模版显示的位置
		
		cb.endText();
		cb.restoreState();
	}
	
	
/*	
    public void onCloseDocument(PdfWriter writer, Document document) {  
    	//动态获得总页数, Added by SUNZHE,  2017-01-14 
        total.beginText();  
        total.setFontAndSize(bfChinese, fontSize);	//生成的模版的字体、颜色  
        String totalPageNum = (writer.getPageNumber() - 1) + " ";  
        total.showText(totalPageNum);				//总页数  
        total.endText();  
        total.closePath();  
    }  
*/    
}