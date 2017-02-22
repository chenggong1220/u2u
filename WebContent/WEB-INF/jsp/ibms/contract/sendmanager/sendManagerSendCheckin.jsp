<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ include
	file="/WEB-INF/jsp/framework/component/include_for_second_diaglog.jsp"%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<input type="hidden" name="id" value="${contract.id }">
			<ul>
				<li><font>合同号：</font>
				<h1>
						<input name="contractId" type="text"
							value="${contract.contractId }" disabled="disabled">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<table id="querygrid_2" class="easyui-datagrid easyui-datagrid_2"
				title="设备列表"
				data-options="
							iconCls: 'icon-edit',
							singleSelect: true,
							toolbar: '#tb_sendManager',
							pagination:true,
							rownumbers:true,
							fitColumns: true,
							singleSelect:true,
							url: '${pageContext.request.contextPath}/web/send/manager/send/checkin/json?contractId=${contract.id }',
							pageSize:10,
							pageList: [1,2,5,10],
							collapsible:true,
							method: 'post'
						">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true">序列号</th>
						<th
							data-options="field:'brand',width:'20%',editor:'text',formatter:function(value,rec){return rec.asset.assetType.brand;}">设备品牌</th>
						<th
							data-options="field:'model',width:'10%',editor:'text',formatter:function(value,rec){return rec.asset.assetType.model;}">设备型号</th>
						<th
							data-options="field:'code',width:'10%',editor:'text',formatter:function(value,rec){return rec.asset.code;}">设备编号</th>
						<th
							data-options="field:'receiveStatus',width:'20%',formatter:function(value,rec){if(rec.receiveStatus==false){return '未收货'}else{return '已收货'}}">状态</th>
						<th
							data-options="field:'receiveDate',width:'20%',formatter:function(value,rec){return rec.receiveDateView;}">收货时间</th>
					</tr>
				</thead>
			</table>
			<div class="ibms_clear"></div>
		</div>
		<!--         <div class="ibms_form_btn"> -->
		<%-- 	        <a href="#" class="query_list_button auto-savebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/send/manager/send/checkin/save',autoclose:true">保存</a> --%>
		<!-- 	        <a href="#" class="query_list_button auto-resetbutton">重 置</a> -->
		<!--         </div> -->
	</div>
</form>

<div id="tb_sendManager" style="height: auto">
	<a href="javascript:void(0)"
		class="easyui-linkbutton auto-editbutton_2"
		data-options="iconCls:'icon-add',plain:true,d_width:'40%',d_height:'40%',index:'id',url:'${pageContext.request.contextPath}/web/send/manager/send/checkin/edit?contractId=${contract.id }'">到货确认</a>
</div>