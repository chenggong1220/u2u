<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<script type="text/javascript"
	src="<c:url value='/styles/js/location.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/styles/js/assetType.js'/>"></script>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="订单审核">
		<div class="ibms_form_default">
			<ul>
				<li><font>订单号：</font>
					<h1>
						<input id="code" name="code" type="text">
					</h1></li>
				<li><font>创建来源：</font>
					<h1>
						<select id="createSource" name="createSource"
							class="easyui-combobox" data-options="editable:false">
							<option value="">全部</option>
							<option value="0">手机平台</option>
							<option value="1">后台创建</option>
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
				<li><font>租赁类型：</font>
					<h1>
						<select id="rentType" name="rentType" class="easyui-combobox"
							data-options="editable:false">
							<option value="">全部</option>
							<option value="0">分时租赁</option>
							<option value="1">分月租赁</option>
						</select>
					</h1></li>
				<li><font>订单状态：</font>
					<h1>
						<select id="status" name="status" class="easyui-combobox"
							data-options="editable:false">
							<option value="">全部</option>
							<option value="租赁申请">租赁申请</option>
							<option value="订单处理">订单处理</option>
							<option value="项目处理">项目处理</option>
							<option value="项目复核">项目复核</option>
							<option value="信审">信审</option>
							<option value="信审复核">信审复核</option>
							<option value="合同到司确认">合同到司确认</option>
							<option value="合同签约">合同签约</option>
							<option value="押金核销">押金核销</option>
							<option value="发货">发货</option>
							<option value="到货确认">到货确认</option>
						</select>
					</h1></li>
				<li><font>客户姓名：</font>
					<h1>
						<input id="customerName" name="customerName" type="text">
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

<table id="querygrid" class="easyui-datagrid" title="订单列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/order/verify/list',
				pageSize:10,
				pageList: [1,2,5,10],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th data-options="field:'code',width:'10%',editor:'text'">订单编号</th>
			<th data-options="field:'status',width:'6%'">状态</th>
			<th
				data-options="field:'name',width:'15%',formatter:function(value,rec){if(rec.rentPersonType=='0'){return rec.rentPersonInfo.name;}else{return rec.rentCompanyInfo.name;}}">客户姓名</th>
			<th
				data-options="field:'mobile',width:'8%',formatter:function(value,rec){if(rec.rentPersonType=='0'){return rec.rentPersonInfo.mobile;}else{return rec.rentCompanyInfo.legalMobile}}">联系电话</th>
			<th data-options="field:'assetFullAdress',width:'15%'">放置地</th>
			<th
				data-options="field:'rentType',width:'8%',formatter:function(value,rec){if(value=='0'){return '分时租赁' ;}else{return '分月租赁';}}">租赁类型</th>
			<th data-options="field:'deposit',width:'6%'">保证金</th>
			<th
				data-options="field:'needInvoices',width:'6%',formatter:function(value,rec){if(value==false){return '不需要' ;}else{return '需要';}}">需要发票</th>
			<th data-options="field:'startDatetime',width:'8%'">起始时间</th>
			<th data-options="field:'endDatetime',width:'8%'">结束时间</th>
			<th data-options="field:'createTime',width:'15%'">创建时间</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
		data-options="iconCls:'cheked-all',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/order/detail'">订单详情</a>
	<a href="javascript:void(0)"
		class="easyui-linkbutton auto-verifybutton"
		data-options="iconCls:'icon-save',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/order/verify/edit'">审核</a>
		
	<!-- Start: 增加订单修改功能，by SUNZHE, 2017-06-22 -->

	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
		data-options="iconCls:'icon-edit',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/order/modify'">修改</a>

	<!-- End: 增加订单修改功能，by SUNZHE, 2017-06-22 -->		

		
	<!-- Start: 增加订单审核时删除功能，by SUNZHE, 2017-02-11 -->
	<a href="javascript:void(0)" class="easyui-linkbutton delorderbutton"
		data-options="iconCls:'icon-remove',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/order/delete'">删除</a>
	<!-- End: 增加订单审核时删除功能，by SUNZHE, 2017-02-11 -->
</div>
