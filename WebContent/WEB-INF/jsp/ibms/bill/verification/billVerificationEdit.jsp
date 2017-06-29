<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<input type="hidden" name="id" value="${bill.id }">
			<ul>
				<li><font>对方帐号：</font>
				<h1>
						<input name="accountNum" type="text" value="${bill.accountNum }"
							disabled="disabled">
					</h1></li>
				<li><font>客户户名：</font>
				<h1>
						<input name="accountName" type="text" value="${bill.accountName }"
							disabled="disabled">
					</h1></li>
				<li><font>交易日期：</font>
				<h1>
						<input name="dealDate" type="text" value="${bill.dealDate }"
							disabled="disabled">
					</h1></li>
				<li><font>收到金额：</font>
				<h1>
						<input name="amount" type="text" value="${bill.amount }"
							disabled="disabled">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<ul>
				<li><font>是否开票：</font>
				<h1>
						<input id="billingStatus" name="billingStatus" type="text"
							class="easyui-combobox">
					</h1></li>
				<li><font>是否核销：</font>
				<h1>
						<input id="status" name="status" type="text"
							class="easyui-combobox">
					</h1></li>
			</ul>
			<ul>
				<li><font>销帐项目：</font>
					<h1>
						<select id="deposit" name="deposit" class="easyui-combobox">
						</select>
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<ul id="depositDiv">
				<li><font>合同编号：</font>
					<h1>
						<input id="contractId" name="contractId" type="text"
							class="easyui-combobox" />
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<ul id="rentAmountDiv" hidden="true">
				<li><font>账单列表：</font>
					<h1>
						<input id="billCheckId" name="billCheckId" type="text"
							class="easyui-combobox" />
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<div class="ibms_form_btn">
				<a href="#" class="query_list_button auto-savebutton"
					data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/bill/verification/update',autoclose:true">保存</a>
				<a href="#" class="query_list_button auto-resetbutton">重 置</a>
			</div>
		</div>
	</div>
</form>

<script type="text/javascript">
var data = [];
data.push({ "value": "已开票", "id": "true" });
data.push({ "value": "未开票", "id": "false" });
$("#billingStatus").combobox({
	editable:false,
    data:data,
	valueField:'id',
	textField:'value',
	onLoadSuccess:function(){$(this).combobox('setValue', '${bill.billingStatus}');}
});

var statusData = [];
statusData.push({ "value": "已核销", "id": "true" });
//statusData.push({ "value": "未核销", "id": "false" });
$("#status").combobox({
	editable:false,
    data:statusData,
	valueField:'id',
	textField:'value',
	onLoadSuccess:function(){$(this).combobox('setValue', '${bill.status}');}
});

var verificationData = [];
verificationData.push({ "value": "保证金", "id": true });
verificationData.push({ "value": "租金", "id": false });
$("#deposit").combobox({
	editable:false,
	data:verificationData,
	valueField:'id',
	textField:'value',
	onLoadSuccess:function(){$(this).combobox('setValue', true);},
	onSelect: function (record) {
		if(record.id==true){
			$("#rentAmountDiv").hide();	
		}else{
			$("#rentAmountDiv").show();
		}
    }
});

$("#contractId").combobox({
	editable:false,
	url: WEB_APP + '/web/contract/json',
	valueField:'id',
	textField:'contractId',
	onSelect: function (record) {
		if($("#rentAmountDiv").is(":hidden")){
		}else{
			$("#billCheckId").combobox({
				editable:false,
				url: WEB_APP + '/web/billcheck/json?contractId='+record.id,
				valueField:'id',
				textField:'outdateView'
			})
		}
    }
});
</script>
