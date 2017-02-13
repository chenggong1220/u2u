<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<script type="text/javascript" src="<c:url value='/styles/js/location.js'/>"></script>

<script type="text/javascript">
$('#brandIdSearch').combobox({
	editable:false,
    url: WEB_APP + '/web/asset/brand/json',
    valueField:'id',
    textField:'name',
    onSelect: function (record) {
    	$('#assetTypeIdSearch').combobox({
    		editable:false,
    	    url: WEB_APP + '/web/asset/type/json?brandId=' + record.id,
    	    valueField:'id',
    	    textField:'model'
    	});
    }
});
</script>
<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="租赁物查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>品牌：</font>
					<h1>
						<input id="brandIdSearch" name="brandId" type="text" class="easyui-combobox">
					</h1></li>
				<li><font>设备类型：</font>
					<h1>
						<input id="assetTypeIdSearch" name="assetTypeId" type="text" class="easyui-combobox">
					</h1></li>
				<li><font>省：</font>
					<h1>
						<input id="provinceId" name="provinceId" type="text" class="easyui-combobox">
					</h1></li>
				<li><font>市：</font>
					<h1>
						<input id="cityId" name="cityId" type="text" class="easyui-combobox">
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

<table id="querygrid" class="easyui-datagrid" title="租赁物列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/asset/list',
				pageSize:10,
				pageList: [1,2,5,10],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th data-options="field:'brand',width:'20%',editor:'text',formatter:function(value,rec){return rec.assetType.brand;}">设备品牌</th>
			<th data-options="field:'model',width:'20%',editor:'text',formatter:function(value,rec){return rec.assetType.model;}">设备型号</th>
			<th data-options="field:'code',width:'20%',editor:'text'">设备编号</th>
			<th data-options="field:'rent',width:'20%',editor:'text',formatter:function(value,rec){if(value==0){return '已租'}else if(value==1){return '空闲'}else{return '检修'}}">状态</th>
			<th data-options="field:'province',width:'20%'">省</th>
			<th data-options="field:'city',width:'20%'">市</th>
			<th data-options="field:'createTime',width:'20%'">创建时间</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-addbutton"
		data-options="iconCls:'icon-add',plain:true,url:'${pageContext.request.contextPath}/web/asset/add'">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
		data-options="iconCls:'icon-edit',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/asset/edit'">修改</a>
	<!--  
	<a href="javascript:void(0)" class="easyui-linkbutton auto-delbutton"
		data-options="iconCls:'icon-remove',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/asset/delete'">删除</a>
-->
</div>