<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="反馈管理">
		<div class="ibms_form_default">
			<ul>
				<li><font>电话：</font>
					<h1>
						<input id="phone" name="phone" type="text">
					</h1></li>
				<li><font>状态：</font>
					<h1>
						<select id="status" name="status" type="select" class="easyui-combobox" data-options="required:true,editable:false">
	                        <option value="">全部</option>
	                        <option value="1">已处理</option>
	                        <option value="0">未处理</option>
                        </select>
				</h1></li>
				<li><font>开始时间：</font>
					<h1>
						<input id="startDate" name="startDate" type="text" class="easyui-datebox">
					</h1></li>
				<li><font>结束时间：</font>
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

<table id="querygrid" class="easyui-datagrid" title="反馈列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/feedback/list',
				pageSize:10,
				pageList: [10,20,50],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th data-options="field:'phone',width:'10%',editor:'text'">电话</th>
			<th data-options="field:'feedback',width:'20%'">内容</th>
			<th data-options="field:'status',width:'10%',formatter:function(value,rec){if(value=='1'){return '已处理' ;}else{return '未处理';}}">状态</th>		
			<th data-options="field:'viewDate',width:'20%'">提交时间</th>
			<th data-options="field:'handlePerson',width:'15%'">处理人</th>
			<th data-options="field:'operateViewDate',width:'15%'">处理时间</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
		data-options="iconCls:'icon-edit',plain:true,index:'id',title:'查看详情',url:'${pageContext.request.contextPath}/web/feedback/edit'">处理</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-delbutton"
		data-options="iconCls:'icon-remove',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/feedback/delete'">删除</a>
</div>

