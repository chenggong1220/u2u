<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<form id="queryForm">
    <a href="#" class="auto-querybutton query_list_button"></a>
</form>

<table id="querygrid" class="easyui-datagrid" title="角色列表"
		data-options="
			iconCls: 'icon-edit',
			singleSelect: true,
			toolbar: '#tb',
			pagination:true,
			rownumbers:true,
			 fitColumns: true,
			singleSelect:false,
			url: '${pageContext.request.contextPath}/auth/role/list',
			method: 'get'
		">
	
	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">ID</th>
			<th data-options="field:'roleName',width:'96%'">角色</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height:auto">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-addbutton" data-options="iconCls:'icon-add',plain:true,url:'${pageContext.request.contextPath}/auth/role/add'" >添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton" data-options="iconCls:'icon-edit',plain:true,url:'${pageContext.request.contextPath}/auth/role/edit'" >编辑</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-delbutton" data-options="iconCls:'icon-remove',plain:true,index:'id',url:'${pageContext.request.contextPath}/auth/role/delete'">删除</a>
</div>
