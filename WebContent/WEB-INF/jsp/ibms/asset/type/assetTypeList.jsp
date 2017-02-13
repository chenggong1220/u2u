<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<script type="text/javascript" src="<c:url value='/styles/js/location.js'/>"></script>
<script type="text/javascript" src="<c:url value='/styles/js/assetType.js'/>"></script>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="型号查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>品牌：</font>
					<h1>
						<input id="brandIdSearch" name="brandId" type="text">
					</h1></li>
				<li><font>型号：</font>
					<h1>
						<input id="model" name="model" type="text">
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

<script type="text/javascript">
$('#brandIdSearch').combobox({
	editable:false,
    url: WEB_APP + '/web/asset/brand/json',
    valueField:'id',
    textField:'name'
});
</script>

<table id="querygrid" class="easyui-datagrid" title="型号列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/asset/type/list',
				pageSize:10,
				pageList: [1,2,5,10],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th data-options="field:'brand',width:'15%'">品牌</th>
			<th data-options="field:'model',width:'15%'">型号</th>
			<th data-options="field:'deposit',width:'10%'">保证金</th>
			<th data-options="field:'amount',width:'10%'">净值</th>
			<th data-options="field:'insuranceAmount',width:'10%'">保险金</th>
			<th data-options="field:'createTime',width:'20%'">创建时间</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-addbutton"
		data-options="iconCls:'icon-add',plain:true,url:'${pageContext.request.contextPath}/web/asset/type/add'">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
		data-options="iconCls:'icon-edit',plain:true,url:'${pageContext.request.contextPath}/web/asset/type/edit'">修改</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-delbutton"
		data-options="iconCls:'icon-remove',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/asset/type/delete'">删除</a>
</div>
