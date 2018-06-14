package com.u2u.ibms.common.util.contract;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.u2u.ibms.common.util.contract.vo.ContractFileVo;

public class NaturePersonGuaranteeGenerator extends BaseContract {

	private ContractFileVo contractFileVo;

	public NaturePersonGuaranteeGenerator(ContractFileVo contractFileVo) {
		this.contractFileVo = contractFileVo;
	}

	public void generateFile(File file) throws Exception {
		Document document = getDocument(file, this.contractFileVo);
		document.add(getTitle("保证函"));
		document.add(getVendorCheckGenerator());
		document.close();
	}

	public PdfPTable getVendorCheckGenerator() throws Exception {
		final PdfPTable table = new PdfPTable(1);// 建立一个pdf表格
		table.setSpacingBefore(20f);
		table.setWidthPercentage(100);// 设置表格宽度为100%

		getGeneratorCell(table, "致：优尼斯融资租赁（上海）有限公司", true);
		getGeneratorCell(table, "鉴于贵公司与【"
				+ contractFileVo.getContractYifang().getLegalPerson()
				+ "】（下称“承租人”）签订了编号为");
		getGeneratorCell(table, "【" + contractFileVo.getContractId()
				+ "—OL】的《设备租赁合同》（下称“主合同”），现本保证人为承租人履行《设备租");
		getGeneratorCell(table, "赁合同》中所约定的义务提供无条件的﹑不可撤销的连带保证责任担保。根据有关法律、法规的规定，");
		getGeneratorCell(table, "本保证人做出如下保证：");

		getGeneratorCell(table, "第一条 被保证的债权", true);
		getGeneratorCell(table,
				"本保证函项下被保证的债权是贵公司因《设备租赁合同》产生的对承租人的债权,即因《设备租赁合同》承租人需要承担的债务。");
		getGeneratorCell(table, "第二条 保证范围", true);
		getGeneratorCell(table, "各保证人保证担保的范围包括：");
		getGeneratorCell(table,
				"(1)   因《设备租赁合同》，承租人需要向贵公司支付的押金、租金、违约金、赔偿金等全部应付款项。");
		getGeneratorCell(table,
				"(2)   贵公司实现上述债权的费用（包括但不限于诉讼费、仲裁费、财产保全费、差旅费、执行费、评估费、律师费、拍卖费等）。 ");
		getGeneratorCell(table, "第三条 保证方式", true);
		getGeneratorCell(table, "各保证人自愿共同承担连带责任保证，但不影响各保证人单独向贵公司承担保证责任。");
		getGeneratorCell(table,
				"各保证人确认，当承租人未按《设备租赁合同》履行其义务时，贵公司均有权直接要求部分或全部保证人在保证范围内承担保证责任，各保证人均不得以任何理由进行抗辩。");
		getGeneratorCell(table, "第四条 保证期间", true);
		getGeneratorCell(table, "自本保证函生效之日起至《设备租赁合同》中约定的租赁期限届满之日后的两年止。");
		getGeneratorCell(table,
				"如《设备租赁合同》被认定为无效或提前终止，则保证期间为自本保证函生效之日起至《设备租赁合同》被认定为无效或提前终止之日后的两年止。");
		getGeneratorCell(table, "第五条 《设备租赁合同》的签订与变更", true);
		getGeneratorCell(table,
				"有关《设备租赁合同》的租金、期限、违约金、赔偿额计算、当事人的权利义务等事项，由贵公司与承租人在《设备租赁合同》中约定。");
		getGeneratorCell(
				table,
				"各保证人确认，贵公司与承租人协议变更《设备租赁合同》条款的，均无须征得各保证人的同意, 各保证人的保证责任并不因此而减免。但贵公司与承租人协议增加租金总额的，应取得各保证人的同意，否则各保证人对增加的租金部分不承担保证责任。");
		getGeneratorCell(
				table,
				"各保证人确认，在保证期间，贵公司将主合同项下的债权、赔偿请求权等权利转让给第三人的，无须事先通知各保证人以及征得各保证人的同意，各保证人在保证函约定的保证范围内对受让人承担保证责任。");
		getGeneratorCell(table, "第六条 效力的独立性", true);
		getGeneratorCell(
				table,
				"本保证函的效力独立于《设备租赁合同》及其可能的补充协议，《设备租赁合同》全部或部分无效并不影响本保证函的效力。若因承租人的原因造成《设备租赁合同》无效或提前终止，由各保证人按照承租人应承担的赔偿额承担连带保证责任。");
		getGeneratorCell(table,
				"本保证函的有效性不受贵公司与承租人、各保证人之间的任何合同、协议、担保、默契、争议或纠纷而受影响。");
		getGeneratorCell(table,
				"保证期间，承租人、贵公司发生合并、分立、股份制改造、增减资本、合资、联营、更名等情形的，各保证人的保证责任也不因此而减少或免除。");
		getGeneratorCell(table, "第七条 保证", true);
		getGeneratorCell(table,
				"各保证人的财产状况足以保证各保证人有能力履行此项保证；各保证人愿以所拥有的全部财产承担担保责任。");
		getGeneratorCell(table, "第八条 保证的实现", true);
		getGeneratorCell(table, "本保证函项下，保证人履行保证义务时应将应付款项以人民币汇到贵公司指定的以下账户：");
		getGeneratorCell(table, "收  款  人：优尼斯融资租赁（上海）有限公司");
		getGeneratorCell(table, "开户银行：中国民生银行上海曹安支行");
		getGeneratorCell(table, "账       号：694 398 257");
		getGeneratorCell(table, "第九条 补充条款", true);
		getGeneratorCell(table,
				" 1、 保证期间，贵公司有权对各保证人的资金和财产状况进行监督，各保证人应如实提供有关资料；");
		getGeneratorCell(table, " 2、各保证人承诺将对承租人使用设备、支付租金的情况进行监督；");
		getGeneratorCell(table,
				" 3、 各保证人在此授权贵公司，在贵公司认为必要的时候以贵公司的名义处理各保证人到期债权的求偿和追索事宜，由此得到的款项优先清偿对贵公司的债务。");
		getGeneratorCell(table, "第十条 争议解决方式", true);
		getGeneratorCell(table,
				"本保证函在履行过程中如发生争议，可以通过协商解决，协商不成，向贵公司住所地有管辖权的人民法院起诉。");
		getGeneratorCell(table, "在诉讼期间，本保证函不涉及争议部分的条款仍须履行。");
		getGeneratorCell(table, "第十一条 生效", true);
		getGeneratorCell(table, "本保证函自各保证人签字之日起生效。");
		getGeneratorCell(table, "本保证函一式两份，保证人一方和贵公司各执一份。");
		getGeneratorCell(table, "（以下无正文）");
		getGeneratorCell(table, "");
		getGeneratorCell(table, "");
		getGeneratorCell(table, "保证人（签字）：", true);
		getGeneratorCell(table, "保证人身份证号码：", true);
		getGeneratorCell(table, "");
		getGeneratorCell(table, "");
		getGeneratorCell(table, "保证人（签字）：", true);
		getGeneratorCell(table, "保证人身份证号码：", true);
		getGeneratorCell(table, "");
		getGeneratorCell(table, "");
		Date current = new Date();
		getGeneratorCell(table,
				"日期： " + new SimpleDateFormat("yyyy").format(current)
						+ " 年    " + new SimpleDateFormat("MM").format(current)
						+ " 月    " + new SimpleDateFormat("dd").format(current)
						+ " 日    ", true);
		getGeneratorCell(table, "");
		getGeneratorCell(table, "");
		getGeneratorCell(table, "附：保证人身份证复印件（正、反两面）", true);
		return table;
	}

}
