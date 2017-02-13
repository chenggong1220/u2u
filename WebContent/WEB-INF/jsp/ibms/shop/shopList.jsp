<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<script type="text/javascript"
	src="<c:url value='/styles/js/location.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/styles/js/assetType.js'/>"></script>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="分享店查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>名称：</font>
					<h1>
						<input id="name" name="name" type="text">
					</h1>
				</li>
				<li><font>BU：</font>
					<h1>
						<input id="bid" name="bid" type="text">
					</h1>
				</li>
				<li><font>联系人：</font>
					<h1>
						<input id="contactName" name="contactName" type="text">
					</h1>
				</li>
			</ul>
		</div>
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="auto-querybutton query_list_button">查询</a> <a
				href="#" class="auto-resetbutton query_list_button">重 置</a>
		</div>
	</div>
</form>

<table id="querygrid" class="easyui-datagrid" title="分享店列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/shop/list',
				pageSize:10,
				pageList: [1,2,5,10],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th data-options="field:'name',width:'20%',editor:'text'">名称</th>
			<th data-options="field:'address',width:'20%',editor:'text'">地址</th>
			<th data-options="field:'bname',width:'20%',formatter:function(value,row,index){
                        return row.bu.name;
                    }">BU</th>
			<th data-options="field:'contactName',width:'20%',editor:'text'">联系人</th>
			<th data-options="field:'contactAddress',width:'15%',editor:'text'">联系地址</th>
			<th data-options="field:'email',width:'10%',editor:'text'">电子邮件</th>
			<th data-options="field:'note',width:'12%',editor:'text'">备注</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-addbutton"
		data-options="iconCls:'icon-add',plain:true,url:'${pageContext.request.contextPath}/web/shop/add'">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
		data-options="iconCls:'icon-edit',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/shop/edit'">修改</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-delbutton"
		data-options="iconCls:'icon-remove',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/shop/delete'">删除</a>
</div>
<script type="text/javascript">
$('#bid').combobox({
	editable:false,
    url: WEB_APP + '/auth/bu/json',
    valueField:'id',
    textField:'name',
    method:'get'
});
</script>