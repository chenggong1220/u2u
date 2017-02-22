<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>
<script type="text/javascript"
	src="<c:url value='/styles/js/location.js'/>"></script>
<%@ taglib prefix="sec-ext"
	uri="http://www.springframework.org/securitya/ext-tags"%>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="合同查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>合同号：</font>
					<h1>
						<input id="contractId" name="contractId" type="text">
					</h1></li>
				<li><font>发货状态：</font>
					<h1>
						<select id="sendStatus" name="sendStatus" class="easyui-combobox">
							<option value="">全部</option>
							<option value="true">已发货</option>
							<option value="false">未发货</option>
						</select>
					</h1></li>
				<li><font>归还状态：</font>
					<h1>
						<select id="backStatus" name="backStatus" class="easyui-combobox">
							<option value="">全部</option>
							<option value="true">已归还</option>
							<option value="false">未归还</option>
						</select>
					</h1></li>
				<li><font>合同类型：</font>
					<h1>
						<select id="contractType" name="contractType"
							class="easyui-combobox">
							<option value="">全部</option>
							<option value="0">个人</option>
							<option value="1">企业</option>
						</select>
					</h1></li>
				<li><font>收货人：</font>
					<h1>
						<input id="orderPerson" name="orderPerson" type="text">
					</h1></li>
				<li><font>发货状态：</font>
					<h1>
						<select id="sendStatus" name="sendStatus" class="easyui-combobox">
							<option value="">全部</option>
							<option value="false">未发货</option>
							<option value="true">已发货</option>
						</select>
					</h1></li>
				<li><font>省：</font>
					<h1>
						<input id="provinceId" name="provinceId" type="text"
							class="easyui-combobox">
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

<table id="querygrid" class="easyui-datagrid" title="合同列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/send/manager/list',
				pageSize:10,
				pageList: [1,2,5,10],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th
				data-options="field:'contractId',width:'10%',formatter:function(value,rec){return '<a target=\'
				_blank\' href=\'${pageContext.request.contextPath}'+rec.saveLocation +'\'>'
				+ value+'</a>' }">合同号
			</th>
			<th data-options="field:'orderName',width:'10%'">合同类型</th>
			<th data-options="field:'orderProvince',width:'10%'">省</th>
			<th data-options="field:'orderPerson',width:'10%'">收货人</th>
			<th data-options="field:'orderMobile',width:'10%'">手机号</th>
			<th
				data-options="field:'sendStatus',width:'10%',formatter:function(value,rec){if(value==false){return '未发货' ;}else{return '已发货';}}">发货状态</th>
			<th
				data-options="field:'sendCheckinStatus',width:'10%',formatter:function(value,rec){if(value==false){return '未到货' ;}else{return '已到货';}}">到货状态</th>
			<th
				data-options="field:'backStatus',width:'10%',formatter:function(value,rec){if(value==false){return '未归还' ;}else{return '已归还';}}">归还状态</th>
			<th data-options="field:'status',width:'10%'">状态</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<sec-ext:authenticated id="443">
		<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
			data-options="iconCls:'icon-add',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/send/manager/send'">发货</a>
	</sec-ext:authenticated>
	<sec-ext:authenticated id="444">
		<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
			data-options="iconCls:'icon-edit',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/send/manager/send/checkin'">到货</a>
	</sec-ext:authenticated>
	<!-- 	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton" -->
	<%-- 		data-options="iconCls:'icon-edit',d_width:'40%',d_height:'40%',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/send/manager/back'">打印发货单</a> --%>
</div>
