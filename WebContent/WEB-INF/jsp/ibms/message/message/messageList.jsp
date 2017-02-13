<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="消息查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>标 题：</font>
					<h1>
						<input id="title" name="title" type="text">
					</h1></li>
				<li><font>创建时间(始)：</font>
					<h1>
						<input id="startDate" name="startDate" type="text" class="easyui-datebox">
					</h1></li>
				<li><font>创建时间(终)：</font>
					<h1>
						<input id="endDate" name="endDate" type="text" class="easyui-datebox">
					</h1></li>
				<li><font>创建类型：</font>
					<h1>
						<select id="type" name="type" class="easyui-combobox" data-options="required:true,editable:false">
	                        <option value="">全部</option>
	                        <option value="0">系统消息</option>
	                        <option value="1">个人消息</option>
                        </select>
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

<table id="querygrid" class="easyui-datagrid" title="消息列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/message/list',
				pageSize:10,
				pageList: [10,20,50],
				onDblClickCell: function(index,field,value){
					console.info(value.typeName);
				},
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th data-options="field:'title',width:'20%',editor:'text'">标题</th>
			<th data-options="field:'content',width:'30%'">内容</th>
			<th data-options="field:'type',width:'20%'">消息类型</th>
			<th data-options="field:'viewDate',width:'10%'">发布日期</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
		data-options="iconCls:'icon-save',plain:true,index:'id',title:'查看详情',url:'${pageContext.request.contextPath}/web/message/view'">查看</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-addbutton"
		data-options="iconCls:'icon-add',plain:true,url:'${pageContext.request.contextPath}/web/message/add'">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-delbutton"
		data-options="iconCls:'icon-remove',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/message/delete'">删除</a>
</div>

