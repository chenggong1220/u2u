<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>
<%@ taglib prefix="sec-ext"
	uri="http://www.springframework.org/securitya/ext-tags"%>
<script type="text/javascript"
	src="<c:url value='/styles/js/location.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/styles/js/assetType.js'/>"></script>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="信审项目查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>项目号：</font>
					<h1>
						<input id="projectId" name="projectId" type="text">
					</h1></li>
				<li><font>客户名称：</font>
					<h1>
						<input id="customerName" name="customerName" type="text">
					</h1></li>		
				<li><font>证件号码：</font>
					<h1>
						<input id="IDNo" name="IDNo" type="text">
					</h1></li>									
				<li><font>信审状态：</font>
					<h1>
						<select id="creditResult" name="creditResult"
							class="easyui-combobox" data-options="editable:false">
							<option value="">全部</option>
							<option value="0">未审核</option>
							<option value="1">待复核</option>
							<option value="2">复核通过</option>
							<option value="3">信审拒绝</option>
						</select>
					</h1></li>
				<li><font>创建时间(始)：</font>
					<h1>
						<input id="startDate" name="startDate" type="text"
							class="easyui-datebox">
					</h1></li>
				<li><font>创建时间(终)：</font>
					<h1>
						<input id="endDate" name="endDate" type="text"
							class="easyui-datebox">
					</h1></li>
				<li><font>订单编号：</font>
					<h1>
						<input id="orderCode" name="orderCode" type="text">
					</h1></li>			
				<li><font>客户专员：</font>
					<h1>
						<input id="operator" name="operator" type="text">
					</h1></li>
			</ul>
		</div>
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="auto-querybutton query_list_button">查 询</a> <a
				href="#" class="auto-resetbutton query_list_button">重 置</a>
		</div>
	</div>
</form>

<table id="querygrid" class="easyui-datagrid" title="信审项目列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/credit/list',
				pageSize:10,
				pageList: [1,2,5,10],
				collapsible:true,
				method: 'post'
			">
	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th data-options="field:'projectId',width:'10%'">项目编号</th>
			<th
				data-options="field:'order',width:'10%',formatter:function(value,rec){return rec.order.code;}">订单编号</th>
			<th
				data-options="field:'customerName',width:'25%',formatter:function(value,rec){return rec.order.customerName;}">客户姓名</th>
			<th
				data-options="field:'operator',width:'10%',formatter:function(value,rec){return rec.order.manager.realname;}">客户专员</th>
			<th
				data-options="field:'operatorMobile',width:'15%',formatter:function(value,rec){return rec.order.manager.mobile;}">客户专员手机</th>
			<th
				data-options="field:'creditResult',width:'10%',formatter:function(value,rec){if(value==0){return '未审核' ;}else if(value==1){return '待复核';}else if(value==2){return '复核通过';}else{return '信审拒绝';}}">状态</th>
			<th data-options="field:'createTime',width:'15%'">创建时间</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<sec-ext:authenticated id="437">
		<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
			data-options="iconCls:'icon-edit',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/credit/verify'">信用审核</a>
	</sec-ext:authenticated>
	<sec-ext:authenticated id="438">
		<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
			data-options="iconCls:'icon-edit',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/credit/multicheck'">信用复核</a>
	</sec-ext:authenticated>
</div>
