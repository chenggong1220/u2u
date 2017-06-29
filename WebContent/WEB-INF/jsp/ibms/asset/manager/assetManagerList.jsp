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
				<li><font>签收状态：</font>
					<h1>
						<select id="checkinStatus" name="checkinStatus"
							class="easyui-combobox">
							<option value="">全部</option>
							<option value="true">已发货</option>
							<option value="false">未发货</option>
						</select>
					</h1></li>
				<li><font>签约状态：</font>
					<h1>
						<select id="signoffStatus" name="signoffStatus"
							class="easyui-combobox">
							<option value="">全部</option>
							<option value="true">已归还</option>
							<option value="false">未归还</option>
						</select>
					</h1></li>
				<li><font>项目编号：</font>
					<h1>
						<input id="projectId" name="projectId" type="text">
					</h1></li>
				<li><font>租赁类型：</font>
					<h1>
						<select id="rentType" name="rentType" class="easyui-combobox"
							data-options="editable:false">
							<option value="">全部</option>
							<option value="0">分时租赁</option>
							<option value="1">分月租赁</option>
						</select>
					</h1></li>
				<li><font>客户姓名：</font>
					<h1>
						<input id="orderPerson" name="orderPerson" type="text">
					</h1></li>
				<li><font>租赁物(省)：</font>
					<h1>
						<input id="provinceId" name="assetProvinceId" type="text"
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
				url: '${pageContext.request.contextPath}/web/asset/manager/list',
				pageSize:10,
				pageList: [1,2,5,10],
				collapsible:true,
				method: 'post'
			">
	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th
				data-options="field:'contractId',width:'10%',formatter:function(value,rec){return '<a target=\'_blank\' href=\'${pageContext.request.contextPath}'+rec.saveLocation +'\'>' + value+'</a>' }">合同编号
			</th>
			<th
				data-options="field:'projectId',width:'10%',formatter:function(value,rec){return rec.project.projectId;}">项目编号</th>
			<th
				data-options="field:'rentType',align:'center',width:'8%',formatter:function(value,rec){if(rec.order.rentType==0){return '分时租赁';}else{return '分月租赁';}}">租赁类型</th>
			<th data-options="field:'orderName',width:'20%'">客户</th>
			<th
				data-options="field:'startDateTime',width:'8%',formatter:function(value,rec){return rec.order.startDatetime;}">起租时间</th>
			<th
				data-options="field:'rentDate',align:'center',width:'5%',formatter:function(value,rec){return rec.order.rentDate;}">租期</th>
			<th data-options="field:'location',width:'30%',formatter:function(value,rec){return rec.order.assetFullAdress;}">放置地</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<sec-ext:authenticated id="445">
		<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
			data-options="iconCls:'icon-add',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/asset/manager/rent'">起租</a>
	</sec-ext:authenticated>
	<!-- 	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton" -->
	<%-- 		data-options="iconCls:'icon-edit',d_width:'40%',d_height:'40%',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/asset/manager/hexiao'">核销项目</a> --%>
	<sec-ext:authenticated id="446">
		<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
			data-options="iconCls:'icon-edit',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/asset/manager/fandan'">翻单</a>
	</sec-ext:authenticated>
	<sec-ext:authenticated id="447">
		<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
			data-options="iconCls:'icon-edit',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/asset/manager/back'">退换货</a>
	</sec-ext:authenticated>
	<sec-ext:authenticated id="448">
		<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
			data-options="iconCls:'icon-edit',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/asset/manager/risk'">风险处置</a>
	</sec-ext:authenticated>
	<sec-ext:authenticated id="449">
		<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
			data-options="iconCls:'icon-edit',d_height:'50%',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/asset/manager/delay'">申请延期</a>
	</sec-ext:authenticated>
</div>
