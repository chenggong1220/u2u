<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search'" title="用户查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>用户名：</font>
					<h1>
						<input id="userName" name="userName" type="text">
					</h1></li>
				<li><font>分享店：</font>
					<h1>
						<input id="userShopSearch" name="userShopId" type="text" 
							class="easyui-combobox">
					</h1>
				</li>
				<li><font>BU：</font>
					<h1>
						<input id="userBUSearch" name="userBUId" type="text" 
							class="easyui-combobox">
					</h1>
				</li>
				<li><font>角色：</font>
					<h1>
						<input id="userRoleSearch" name="userRoleId" type="text" 
							class="easyui-combobox">
					</h1>
				</li>				
			</ul>
			<div class="ibms_clear"></div>
		</div>
		<div class="ibms_form_btn">
			<a href="#" class="auto-querybutton query_list_button">查 询</a> <a
				href="#" class="query_list_button auto-resetbutton">重 置</a>
		</div>
	</div>
</form>

<table id="querygrid" class="easyui-datagrid" title="用户列表"
	data-options="
			iconCls: 'icon-edit',
			singleSelect: true,
			toolbar: '#tb',
			pagination:true,
			rownumbers:true,
			 fitColumns: true,
			singleSelect:false,
			url: '${pageContext.request.contextPath}/auth/user/list',
			method: 'get'
		">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">ID</th>
			<th data-options="field:'username',width:'10%'">用户名</th>
			<th data-options="field:'realname',width:'10%'">真实姓名</th>
			<th data-options="field:'mobile',width:'15%'">手机号</th>
			<th data-options="field:'dingding',width:'10%'">钉钉</th>
			<th data-options="field:'bu',width:'10%',formatter:function(value,rec){return rec.bu.name;}">BU</th>
			<th data-options="field:'shop',width:'20%',formatter:function(value,rec){return rec.shop.name;}">分享店</th>
			<th data-options="field:'roles',width:'15%',formatter:function(value,rec){
				return rec.roles[0].roleName;
			}">角色</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-addbutton"
		data-options="iconCls:'icon-add',plain:true,url:'${pageContext.request.contextPath}/auth/user/add'">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
		data-options="iconCls:'icon-edit',plain:true,index:'username',url:'${pageContext.request.contextPath}/auth/user/edit'">编辑</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-delbutton"
		data-options="iconCls:'icon-remove',plain:true,index:'id',url:'${pageContext.request.contextPath}/auth/user/delete'">删除</a>
</div>

<script type="text/javascript">
//Start: Load Shop Info, SUNZHE, 2017-06-19
$('#userShopSearch').combobox({
	editable:false,
    url: WEB_APP + '/web/shop/json',
    valueField:'id',
    textField:'name'
});

$('#userBUSearch').combobox({
	editable:false,
    url: WEB_APP + '/auth/bu/json',
    valueField:'id',
    textField:'name'
});

$('#userRoleSearch').combobox({
	editable:false,
    url: WEB_APP + '/auth/role/getRoles',
    valueField:'id',
    textField:'roleName'
});
//Start: Load Shop Info, SUNZHE, 2017-06-19
