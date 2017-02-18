<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="租赁查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>创建时间(始)：</font>
					<h1>
						<input id="startDate" name="startDate" type="text" class="easyui-datebox">
					</h1></li>
				<li><font>创建时间(终)：</font>
					<h1>
						<input id="endDate" name="endDate" type="text" class="easyui-datebox">
					</h1></li>
			</ul>
		</div>
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="auto-querybutton query_list_button">查 询</a>
			<a href="#" class="auto-resetbutton query_list_button">重 置</a>
		</div>
	</div>
</form>

<table id="querygrid" class="easyui-datagrid" title="租赁列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/rentDetail/list',
				pageSize:10,
				pageList: [1,2,5,10],
				collapsible:true,
				method: 'post'
			">
 
	<thead>
		<tr>
			<th data-options="field:'id',width:'4%'">序列号</th>
			<th data-options="field:'contractNo',width:'10%'">合同号</th>
			<th data-options="field:'projectNo',width:'10%'">项目号</th>
			<th data-options="field:'username',width:'10%'">客户姓名</th>
			<th data-options="field:'mobile',width:'10%'">联系电话</th>
			<th data-options="field:'startDate',width:'10%'">起租时间</th>
			<th data-options="field:'rentDate',width:'10%'">租期</th>
			<th data-options="field:'shouldRev',width:'9%'">累计应收</th>
			<th data-options="field:'hasRev',width:'9%'">累计实收</th>
			<th data-options="field:'deposit',width:'8%'">总保证金</th>
			<th data-options="field:'shop',width:'12%'">分享店</th>
			<th data-options="field:'manager',width:'6%'">客户经理</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)"
		class="easyui-linkbutton auto-exportbutton"
		data-options="iconCls:'icon-excel',plain:true">导出</a>
</div>
