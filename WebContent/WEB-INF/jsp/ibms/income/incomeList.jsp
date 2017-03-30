<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="财务查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>客户：</font>
					<h1>
						<input id="customer" name="customer" type="text">
					</h1></li>
				<li><font>合同编码：</font>
					<h1>
						<input id="contractId" name="contractId" type="text">
					</h1></li>
				<li><font>款类型：</font>
					<h1>
						<select id="type" name="type" class="easyui-combobox"
							data-options="editable:false">
							<option value="">全部</option>
							<option value="0">会员费</option>
							<option value="1">保证金</option>
							<option value="2">租金</option>
						</select>
					</h1></li>
				<li><font>开票状态：</font>
					<h1>
						<select id="invoiceStatus" name="invoiceStatus"
							class="easyui-combobox" data-options="editable:false">
							<option value="">全部</option>
							<option value="0">未开</option>
							<option value="1">已开</option>
						</select>
					</h1></li>
				<li><font>开票日期(始)：</font>
					<h1>
						<input id="startDate" name="startDate" type="text"
							class="easyui-datebox">
					</h1></li>
				<li><font>开票日期(终)：</font>
					<h1>
						<input id="endDate" name="endDate" type="text"
							class="easyui-datebox">
					</h1></li>
			</ul>
		</div>
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="auto-querybutton query_list_button">查 询</a> <a
				href="#" class="auto-resetbutton query_list_button">重 置</a>
		</div>
	</div>
</form>

<table id="querygrid" class="easyui-datagrid" title="财务列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/income/list',
				pageSize:10,
				pageList: [10,20,50],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th
				data-options="field:'contractId',width:'10%',formatter:function(value,rec){return '<a target=\'_blank\' href=\'${pageContext.request.contextPath}'+rec.contract.saveLocation +'\'>'
				+ rec.contract.contractId+'</a>' }">合同号
			</th>
			<th data-options="field:'customer',width:'10%'">客户</th>
			<th
				data-options="field:'type',width:'10%',formatter:function(value,rec){if(rec.type==0){return '会员费';}else if(rec.type==1){return '保证金';}else{return '租金';}}">类型</th>
			<th data-options="field:'viewDate',width:'10%'">时间</th>
			<th data-options="field:'amount',width:'10%'">应收</th>
			<th data-options="field:'payAmount',width:'10%'">实收</th>
			<th
				data-options="field:'invoice',width:'10%',formatter:function(value,rec){if(rec.invoice==true){return '是';}else{return '否';}}">申请开票</th>
			<th
				data-options="field:'invoiceStatus',width:'10%',formatter:function(value,rec){if(rec.invoiceStatus==true){return '已开';}else{return '未开';}}">开票状态</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
		data-options="iconCls:'icon-add',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/income/edit'">开票登记</a>
</div>
