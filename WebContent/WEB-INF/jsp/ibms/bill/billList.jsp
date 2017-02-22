<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<script type="text/javascript"
	src="<c:url value='/styles/js/location.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/styles/js/assetType.js'/>"></script>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="银行流水查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>银行流水号：</font>
					<h1>
						<input id="bankSerialNumber" name="bankSerialNumber" type="text">
					</h1></li>
				<li><font>对方帐号：</font>
					<h1>
						<input id="accountNum" name="accountNum" type="text">
					</h1></li>
				<li><font>对方户名：</font>
					<h1>
						<input id="accountName" name="accountName" type="text">
					</h1></li>
				<li><font>客户姓名：</font>
					<h1>
						<input id="cusName" name="cusName" type="text">
					</h1></li>
				<li><font>联系电话：</font>
					<h1>
						<input id="cusNum" name="cusNum" type="text">
					</h1></li>
				<li><font>交易日期(始)：</font>
					<h1>
						<input id="startDate" name="startDate" type="text"
							class="easyui-datebox">
					</h1></li>
				<li><font>交易日期(终)：</font>
					<h1>
						<input id="endDate" name="endDate" type="text"
							class="easyui-datebox">
					</h1></li>
			</ul>
		</div>
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="auto-querybutton query_list_button">查询</a> <a
				href="#" class="auto-resetbutton query_list_button">重 置</a>
		</div>
	</div>
</form>

<table id="querygrid" class="easyui-datagrid" title="银行流水列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/bill/list',
				pageSize:10,
				pageList: [1,2,5,10],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th data-options="field:'bankSerialNumber',width:'12%',editor:'text'">银行流水号</th>
			<th data-options="field:'accountNum',width:'15%',editor:'text'">对方账号</th>
			<th data-options="field:'accountName',width:'8%',editor:'text'">对方户名</th>
			<th data-options="field:'cusName',width:'8%',editor:'text'">客户姓名</th>
			<th data-options="field:'cusNum',width:'10%',editor:'text'">联系电话</th>
			<th data-options="field:'dealDate',width:'10%',editor:'text'">交易日期</th>
			<th data-options="field:'amount',width:'10%',editor:'text'">借方发生额</th>
			<th data-options="field:'billingDate',width:'10%',editor:'text'">已开票日期</th>
			<th data-options="field:'financialNum',width:'12%',editor:'text'">财务凭证号</th>
			<th data-options="field:'remark',width:'12%',editor:'text'">备注</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-delbutton"
		data-options="iconCls:'icon-remove',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/bill/delete'">删除</a>
	<a href="javascript:openDialog('#importFile')"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-excel',plain:true">批量导入</a>
</div>
<div id="importFile"
	data-options="title: '批量导入',   
	    width: '500',   
	    height: '320', 
	    url: '${pageContext.request.contextPath}/web/bill/billImport',
	    onClose:function(){$('#dcim_tmp_dialogId').remove();$('.auto-querybutton').click();}">
</div>