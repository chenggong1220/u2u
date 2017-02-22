<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>
<%@ taglib prefix="sec-ext"
	uri="http://www.springframework.org/securitya/ext-tags"%>
<script type="text/javascript"
	src="<c:url value='/styles/js/location.js'/>"></script>
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
							class="easyui-combobox" data-options="editable:false">
							<option value="">全部</option>
							<option value="true">已签收</option>
							<option value="false">未签收</option>
						</select>
					</h1></li>
				<li><font>签约状态：</font>
					<h1>
						<select id="signoffStatus" name="signoffStatus"
							class="easyui-combobox" data-options="editable:false">
							<option value="">全部</option>
							<option value="true">已签约</option>
							<option value="false">未签约</option>
						</select>
					</h1></li>
				<li><font>合同类型：</font>
					<h1>
						<select id="contractType" name="contractType"
							class="easyui-combobox" data-options="editable:false">
							<option value="">全部</option>
							<option value="0">个人</option>
							<option value="1">企业</option>
						</select>
					</h1></li>
				<li><font>合同状态：</font>
					<h1>
						<select id="status" name="status" class="easyui-combobox"
							data-options="editable:false">
							<option value="">全部</option>
							<option value="待审">待审</option>
							<option value="生效">生效</option>
							<option value="作废">作废</option>
						</select>
					</h1></li>
				<li><font>收货人：</font>
					<h1>
						<input id="orderPerson" name="orderPerson" type="text">
					</h1></li>
				<li><font>发货状态：</font>
					<h1>
						<select id="sendStatus" name="sendStatus" class="easyui-combobox"
							data-options="editable:false">
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
				url: '${pageContext.request.contextPath}/web/contract/list',
				pageSize:10,
				pageList: [1,2,5,10],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th
				data-options="field:'contractId',width:'10%',formatter:function(value,rec){return '<a target=\'_blank\' 
				href=\'${pageContext.request.contextPath}'+
				rec.saveLocation +'\'>'
				+ value+'</a>' }">合同号
			</th>
			<th data-options="field:'orderName',width:'22%'">合同类型</th>
			<!-- 
			<th data-options="field:'orderProvince',width:'10%'">省</th>
			-->
			<th data-options="field:'orderPerson',width:'10%'">收货人</th>
			<th data-options="field:'orderMobile',width:'10%'">手机号</th>
			<th
				data-options="field:'sendStatus',width:'8%',formatter:function(value,rec){if(value==false){return '未发货' ;}else{return '已发货';}}">发货状态</th>
			<th
				data-options="field:'backStatus',width:'8%',formatter:function(value,rec){if(value==false){return '未归还' ;}else{return '已归还';}}">归还状态</th>
			<th data-options="field:'checkinDateView',width:'8%'">签收时间</th>
			<th data-options="field:'signoffDateView',width:'8%'">签约时间</th>
			<th data-options="field:'status',width:'6%'">状态</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<sec-ext:authenticated id="439">
		<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
			data-options="iconCls:'icon-add',d_width:'40%',d_height:'40%',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/contract/checkin'">签收</a>
	</sec-ext:authenticated>
	<sec-ext:authenticated id="440">
		<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
			data-options="iconCls:'icon-edit',d_width:'40%',d_height:'40%',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/contract/signoff'">签约</a>
	</sec-ext:authenticated>
	<sec-ext:authenticated id="441">
		<a href="javascript:void(0)" class="easyui-linkbutton auto-delbutton"
			data-options="iconCls:'icon-remove',plain:true,index:'id',alarmInfo:'确定要终止吗？',url:'${pageContext.request.contextPath}/web/contract/abandon?status=0'">终止</a>
	</sec-ext:authenticated>
	<sec-ext:authenticated id="442">
		<a href="javascript:void(0)" class="easyui-linkbutton auto-delbutton"
			data-options="iconCls:'icon-remove',plain:true,index:'id',alarmInfo:'确定要作废吗？',url:'${pageContext.request.contextPath}/web/contract/abandon?status=1'">作废</a>
	</sec-ext:authenticated>
	<!-- 	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"  -->
	<%-- 		data-options="iconCls:'icon-save',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/contract/detail'">合同详情</a> --%>
</div>
