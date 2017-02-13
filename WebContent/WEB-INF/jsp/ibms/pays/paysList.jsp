<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<script type="text/javascript"
	src="<c:url value='/styles/js/location.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/styles/js/assetType.js'/>"></script>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="账单查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>用户名：</font>
					<h1>
						<input id="userName" name="userName" type="text">
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
				url: '${pageContext.request.contextPath}/web/pays/list',
				pageSize:10,
				pageList: [1,2,5,10],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th data-options="field:'userName',width:'23%',editor:'text'">用户名</th>
			<th data-options="field:'paySourceStr',width:'25%',editor:'text'">账户来源</th>
			<th data-options="field:'amount',width:'25%',editor:'text'">金额</th>
			<th data-options="field:'operateViewDate',width:'25%',editor:'text'">缴费日期</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
</div>
