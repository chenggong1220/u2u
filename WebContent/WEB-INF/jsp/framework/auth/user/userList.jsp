<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel" data-options="iconCls:'icon-search'" title="用户查询">
		<div class="ibms_form_default">
        <ul>
           <li><font>用户名：</font>
               <h1><input id="username" name="username"  type="text"></h1>
           </li>
        </ul>
        <div class="ibms_clear"></div>
        </div>
        <div class="ibms_form_btn">
        <a href="#" class="auto-querybutton query_list_button">查 询</a>
        <a href="#" class="query_list_button auto-resetbutton">重 置</a>
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
			<th data-options="field:'username',width:'20%'">用户名</th>
			<th data-options="field:'realname',width:'20%'">真实姓名</th>
			<th data-options="field:'mobile',width:'10%'">手机号</th>
			<th data-options="field:'dingding',width:'10%'">钉钉</th>
			<th data-options="field:'bu',width:'20%'">BU</th>
			<th data-options="field:'roles',width:'15%'">角色</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height:auto">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-addbutton" data-options="iconCls:'icon-add',plain:true,url:'${pageContext.request.contextPath}/auth/user/add'" >添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton" data-options="iconCls:'icon-edit',plain:true,index:'username',url:'${pageContext.request.contextPath}/auth/user/edit'" >编辑</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-delbutton" data-options="iconCls:'icon-remove',plain:true,index:'id',url:'${pageContext.request.contextPath}/auth/user/delete'">删除</a>
</div>
