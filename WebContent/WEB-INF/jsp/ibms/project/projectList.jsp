<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>
<%@ taglib prefix="sec-ext" uri="http://www.springframework.org/securitya/ext-tags"%>


<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="项目查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>项目号：</font>
					<h1>
						<input id="projectId" name="projectId" type="text">
					</h1></li>
				<li><font>项目状态：</font>
					<h1>
						<select id="result" name="result" class="easyui-combobox" data-options="editable:false">
							<option value="">全部</option>
							<option value="0">未保存</option>
							<option value="1">待复核</option>
							<option value="2">复核通过</option>
							<option value="4">信审拒绝</option>
						</select>
					</h1></li>
				<li><font>创建时间(始)：</font>
					<h1>
						<input id="startDate" name="startDate" type="text" class="easyui-datebox">
					</h1></li>
				<li><font>创建时间(终)：</font>
					<h1>
						<input id="endDate" name="endDate" type="text" class="easyui-datebox">
					</h1></li>
					<li><font>订单编号：</font>
					<h1>
						<input id="orderCode" name="orderCode" type="text">
					</h1></li>
<!-- 					<li><font>操作人：</font> -->
<!-- 					<h1> -->
<!-- 						<input id="operator" name="operator" type="text"> -->
<!-- 					</h1></li> -->
			</ul>
		</div>
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="auto-querybutton query_list_button">查 询</a>
			<a href="#" class="auto-resetbutton query_list_button">重 置</a>
		</div>
	</div>
</form>

<table id="querygrid" class="easyui-datagrid" title="项目列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/project/list',
				pageSize:10,
				pageList: [1,2,5,10],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th data-options="field:'projectId',width:'10%'">项目编号</th>
			<th data-options="field:'order',width:'10%',formatter:function(value,rec){return rec.order.code;}">订单编号</th>
			<th data-options="field:'name',width:'25%',formatter:function(value,rec){return rec.order.customerName;}">客户姓名</th>
			<th data-options="field:'managerRealname',width:'10%',formatter:function(value,rec){return rec.order.manager.realname;}">客户专员</th>
<!-- 			<th data-options="field:'operatorMobile',width:'15%',formatter:function(value,rec){return rec.order.manager.mobile;}">客户专员手机号</th> -->
			<th data-options="field:'result',width:'15%',formatter:function(value,rec){if(value==0){return '未保存' ;}else if(value==1){return '待复核';}else if(value==2){return '复核通过';}else if(value==4){return '信审拒绝';}else{return '项目审核拒绝';}}">状态</th>
			<th data-options="field:'createTime',width:'20%'">创建时间</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
    <sec-ext:authenticated id="435">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
		data-options="iconCls:'icon-edit',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/project/verify'">项目维护</a>
	</sec-ext:authenticated>
	
	<sec-ext:authenticated id="436">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
		data-options="iconCls:'icon-edit',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/project/multicheck'">项目复核</a>
    </sec-ext:authenticated>
</div>
