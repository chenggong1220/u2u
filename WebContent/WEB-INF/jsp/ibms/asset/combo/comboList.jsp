<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<script type="text/javascript"
	src="<c:url value='/styles/js/location.js'/>"></script>

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
		data-options="iconCls:'icon-search',collapsible:true" title="套餐查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>租赁类型：</font>
					<h1>
						<select id="rentType" name="rentType" class="easyui-combobox">
							<option value="">全部</option>
							<option value="0">分时租赁</option>
							<option value="1">分月租赁</option>
						</select>
					</h1></li>
				<li><font>品牌：</font>
					<h1>
						<input id="brandIdSearch" name="brandId" type="text"
							class="easyui-combobox">
					</h1></li>
				<li><font>型号：</font>
					<h1>
						<input id="assetTypeIdSearch" name="assetTypeId" type="text"
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

<table id="querygrid" class="easyui-datagrid" title="套餐列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/asset/combo/list',
				pageSize:10,
				pageList: [1,2,5,10],
				onDblClickCell: function(index,field,value){
					console.info(value.typeName);
				},
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th
				data-options="field:'rentType',width:'10%',formatter:function(value,rec){if(value=='0'){return '分时租赁' ;}else{return '分月租赁';}}">租赁类型</th>
			<th
				data-options="field:'brand',width:'20%',formatter:function(value,rec){return rec.assetType.brand;}">品牌</th>
			<th
				data-options="field:'model',width:'20%',formatter:function(value,rec){return rec.assetType.model;}">型号</th>
			<th data-options="field:'amount',width:'10%',editor:'text'">费率</th>
			<th data-options="field:'minimumUseTime',width:'20%'">最小使用时长</th>
			<th
				data-options="field:'starProduct',width:'10%',formatter:function(value,rec){if(value==true){return '是' ;}else{return '否';}}">是否明星类型</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-addbutton"
		data-options="iconCls:'icon-add',plain:true,url:'${pageContext.request.contextPath}/web/asset/combo/add'">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
		data-options="iconCls:'icon-edit',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/asset/combo/edit'">修改</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-delbutton"
		data-options="iconCls:'icon-remove',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/asset/combo/delete'">删除</a>
</div>
