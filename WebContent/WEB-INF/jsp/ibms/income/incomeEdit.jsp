<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<input type="hidden" name="incomeId" value="${income.id }"> <input
				type="hidden" name="contractId" value="${income.contract.id }">
			<ul>
				<li><font>合同编号：</font>
				<h1>
						<input name="contract" type="text"
							value="${income.contract.contractId }" disabled="disabled">
					</h1></li>
				<li><font>开票时间：</font>
				<h1>
						<input name="selectDate" type="text"
							value="${incomeTicket.openDateView }" class="easyui-datebox">
					</h1></li>
				<li><font>项目：</font>
				<h1>
						<input id="project" name="project" type="text"
							value="${billcheck.outdateView }" class="easyui-combobox">
					</h1></li>
				<li><font>票据种类：</font>
				<h1>
						<input id="type" name="type" type="text"
							value="${billcheck.enddateView }" class="easyui-combobox">
					</h1></li>
				<li><font>票据编号：</font>
				<h1>
						<input name="ticketId" type="text"
							value="${incomeTicket.ticketId }">
					</h1></li>
				<li><font>应开金额：</font>
				<h1>
						<input name="amount" type="text" value="${income.payAmount }">
					</h1></li>
				<li><font>实开金额：</font>
				<h1>
						<input name="realAmount" type="text"
							value="${incomeTicket.realAmount==null?income.payAmount:incomeTicket.realAmount }">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
		</div>

	</div>
	<div class="ibms_form_btn">
		<a href="#" class="query_list_button auto-savebutton"
			data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/income/update',autoclose:true">保存</a>
		<a href="#" class="query_list_button auto-resetbutton">重 置</a>
	</div>
</form>
<script type="text/javascript">
var projectData = [];
projectData.push({ "value": "保证金", "id": "保证金" });
projectData.push({ "value": "租金", "id": "租金" });
projectData.push({ "value": "手续费", "id": "手续费" });
projectData.push({ "value": "服务费", "id": "服务费" });
projectData.push({ "value": "违约金", "id": "违约金" });
projectData.push({ "value": "滞纳金", "id": "滞纳金" });
projectData.push({ "value": "会员费", "id": "会员费" });
$("#project").combobox({
	editable:false,
	data:projectData,
	valueField:'id',
	textField:'value',
	onLoadSuccess:function(){$(this).combobox('setValue', "${incomeTicket.project}");}
});

var typeData = [];
typeData.push({ "value": "普票", "id": "普票" });
typeData.push({ "value": "专票", "id": "专票" });
typeData.push({ "value": "收据", "id": "收据" });
$("#type").combobox({
	editable:false,
	data:typeData,
	valueField:'id',
	textField:'value',
	onLoadSuccess:function(){$(this).combobox('setValue', "${incomeTicket.type}");}
});
</script>