<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<script type="text/javascript"
	src="<c:url value='/styles/js/location.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/styles/js/assetType.js'/>"></script>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="设备使用情况查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>客户名：</font><h1><input id="customer" name="customer" type="text"></h1></li>
				<li><font>设备序列号：</font><h1><input id="deviceno" name="deviceno" type="text"></h1></li>
				<li><font>开始时间：</font><h1><input id="startDate" name="startDate" type="text" class="easyui-datebox"></h1></li>
				<li><font>结束时间：</font><h1><input id="endDate" name="endDate" type="text" class="easyui-datebox"></h1></li>
				<li><font>设备型号：</font><h1><input id="type" name="type" type="text"></h1></li>
			</ul>
		</div>
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="auto-querybutton query_list_button">查询</a> <a
				href="#" class="auto-resetbutton query_list_button">重 置</a>
		</div>
	</div>
</form>

<table id="querygrid" class="easyui-datagrid" title="设备使用情况列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/billdetail/list',
				pageSize:10,
				pageList: [1,2,5,10],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th data-options="field:'customer',width:'10%',editor:'text'">客户</th>
			<th data-options="field:'contact',width:'15%',editor:'text'">联系方式</th>
			<th data-options="field:'deviceno',width:'10%',editor:'text'">设备序列号</th>
			<th data-options="field:'type',width:'10%',editor:'text'">型号</th>
			<th data-options="field:'devicedate',width:'15%',editor:'text'">日期</th>
			<th data-options="field:'runningtime',width:'10%',editor:'text'">开机时长</th>
			<th data-options="field:'nochargingtime',width:'13%',editor:'text'">不计费时长</th>
			<th data-options="field:'chargingtime',width:'13%',editor:'text'">计费时长</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
<!-- 	<a href="javascript:void(0)" class="easyui-linkbutton auto-addbutton" -->
<%-- 		data-options="iconCls:'icon-add',plain:true,url:'${pageContext.request.contextPath}/web/billdetail/add'">添加</a> --%>
<!-- 	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton" -->
<%-- 		data-options="iconCls:'icon-edit',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/billdetail/edit'">修改</a> --%>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-delbutton"
		data-options="iconCls:'icon-remove',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/billdetail/delete'">删除</a>
	<a href="javascript:openDialog('#importFile')"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-excel',plain:true">批量导入</a>
</div>
<div id="importFile"
	data-options="title: '批量导入',   
	    width: '500',   
	    height: '320', 
	    url: '${pageContext.request.contextPath}/web/billdetail/billdetailImport',
	    onClose:function(){$('#dcim_tmp_dialogId').remove();$('.auto-querybutton').click();}">
</div>