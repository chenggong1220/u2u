<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="银行流水查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>是否核销：</font>
					<h1>
						<select id="statusId" name="statusId" class="easyui-combobox"
							data-options="editable:false">
							<option value="">全部</option>
							<option value="true">已核销</option>
							<option value="false">未核销</option>
						</select>
					</h1></li>
				<li><font>开票状态：</font>
					<h1>
						<select id="billingStatusId" name="billingStatusId"
							class="easyui-combobox" data-options="editable:false">
							<option value="">全部</option>
							<option value="true">已开票</option>
							<option value="false">未开票</option>
						</select>
					</h1></li>
				<li><font>客户账号：</font>
					<h1>
						<input id="accountNum" name="accountNum" type="text">
					</h1></li>
				<li><font>客户名称：</font>
					<h1>
						<input id="accountName" name="accountName" type="text">
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

<table id="querygrid" class="easyui-datagrid" title="账单列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/bill/verification/list',
				pageSize:10,
				pageList: [10,20,50],
				collapsible:true,
				method: 'post'
			">
	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th data-options="field:'accountNum',width:'20%',editor:'text'">客户账号</th>
			<th data-options="field:'accountName',width:'20%',editor:'text'">客户户名</th>
			<th data-options="field:'dealDate',width:'15%',editor:'text'">交易日期</th>
			<th data-options="field:'amount',width:'10%',editor:'text'">收到金额</th>
			<th
				data-options="field:'billingStatus',width:'8%',editor:'text',formatter:function(value,rec){if(rec.billingStatus==true){return '已开票';}else{return '未开票';}}">开票状态</th>
			<th data-options="field:'deposit',width:'10%',editor:'text',formatter:
				function(value,rec){
					if(rec.status==true){
						if(value==0){
							return '保证金';
						}else if(value == 1){
							return '服务费';
						}else{
							return '租金';
						}
					}
				}				
				
			">核销项目</th>
			<th
				data-options="field:'status',width:'8%',editor:'text',formatter:function(value,rec){if(rec.status==true){return '已核销';}else{return '未核销';}}">状态</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
		data-options="iconCls:'icon-add',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/bill/verification/edit'">核销</a>
</div>
