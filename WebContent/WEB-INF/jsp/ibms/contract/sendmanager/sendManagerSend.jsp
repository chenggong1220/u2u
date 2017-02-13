<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm" >
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<input type="hidden" id="contractId" name="contractId" value="${contract.id }">
	        <ul>
	           <li><font>合同号：</font><h1><input name="contractCode"  type="text" value="${contract.contractId }" disabled="disabled"></h1></li>
	        	<li><font>发货时间：</font><h1><input name="selectDate"  type="text" value="${contract.sendDateView }" class="easyui-datebox"  data-options="required:true"></h1></li>
	        </ul>
	        <div class="ibms_clear"></div>
			<table id="querygrid" class="easyui-datagrid easyui-datatablegrid" title="设备列表"
				data-options="
							iconCls: 'icon-edit',
							singleSelect: true,
							toolbar: '#tb_sendManager',
							pagination:true,
							rownumbers:true,
							fitColumns: true,
							singleSelect:false,
							url: '${pageContext.request.contextPath}/web/send/manager/send/json?contractId=${contract.id }',
							pageSize:10,
							pageList: [1,2,5,10],
							collapsible:true,
							method: 'post'
						">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true">序列号</th>
						<th data-options="field:'brand',width:'20%',editor:'text',formatter:function(value,rec){return rec.assetType.brand;}">设备品牌</th>
						<th data-options="field:'model',width:'10%',editor:'text',formatter:function(value,rec){return rec.assetType.model;}">设备型号</th>
						<th data-options="field:'code',width:'10%',editor:'text'">设备编号</th>
						<th data-options="field:'rent',width:'20%',formatter:function(value,rec){if(rec.rent==true){return '空闲';}else{return '已租';}}">状态</th>
						<th data-options="field:'location',width:'20%',formatter:function(value,rec){return rec.province+rec.city;}">位置</th>
					</tr>
				</thead>
			</table>
			<div class="ibms_clear"></div>
        </div>
        <div class="ibms_form_btn">
	        <a href="#" class="query_list_button auto-tablesavebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/send/manager/send/save',autoclose:true">保存</a>
	        <a href="#" class="query_list_button auto-resetbutton">重 置</a>
        </div>
	</div>
</form>
