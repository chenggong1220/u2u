<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="账单查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>合同号：</font>
					<h1>
						<input id="contract" name="contract" type="text">
					</h1></li>
				<li><font>账单期数：</font>
					<h1>
						<input id="terms" name="terms" type="text">
					</h1></li>
				<li><font>付款状态：</font>
					<h1>
						<select id="payStatus" name="payStatus" class="easyui-combobox"
							data-options="editable:false">
							<option value="">全部</option>
							<option value="0">未支付</option>
							<option value="1">已支付</option>
						</select>
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

<table id="querygrid" class="easyui-datagrid" title="账单列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/billcheck/list',
				pageSize:10,
				pageList: [10,20,50],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>

			<th data-options="field:'contractId',width:'10%',formatter:function(value,rec){return '<a target=\'_blank\' href=\'${pageContext.request.contextPath}'+rec.contract.saveLocation +'\'>' + rec.contract.contractId+'</a>' }">合同号</th>

			<th data-options="field:'currentTerm',width:'6%'">账单期数</th>
			<th data-options="field:'terms',width:'6%'">总期数</th>
			<th data-options="field:'outdateView',width:'10%'">出账日期</th>
			<th data-options="field:'enddateView',width:'10%'">付款截止日</th>
			<th
				data-options="field:'payStatus',width:'6%',formatter:function(value,rec){if(value==0){return '未支付';}else{return '已支付';}}">付款状态</th>
			<th data-options="field:'useDuration',width:'10%'">使用时长(小时)</th>
			<th data-options="field:'repairTime',width:'10%'">维修时长(小时)</th>
			<th data-options="field:'rentAmount',width:'10%'">租金</th>
			<th
				data-options="field:'interest',width:'10%'">罚息</th>
			<th data-options="field:'allAmount',width:'10%'">总费用(元)</th>
			<th
				data-options="field:'status',width:'10%',formatter:function(value,rec){if(rec.status==true){return '已出账' ;}else{return '未出账';}}">出账状态</th>
			<th
				data-options="field:'rentType',width:'10%',formatter:function(value,rec){if(rec.rentType==0){return '分时租赁' ;}else{return '分月租赁';}}">租赁类型</th>
			<!-- 		
			<th data-options="field:'operateViewDate',width:'10%'">操作时间</th>
-->
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-delbutton"
		data-options="iconCls:'icon-edit',plain:true,index:'id',alarmInfo:'确定出账？',url:'${pageContext.request.contextPath}/web/billcheck/out'">出账</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
		data-options="iconCls:'icon-add',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/billcheck/detail'">详情</a>
</div>
