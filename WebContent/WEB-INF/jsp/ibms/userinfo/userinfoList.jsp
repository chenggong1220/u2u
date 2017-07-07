<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<script type="text/javascript"
	src="<c:url value='/styles/js/location.js'/>"></script>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="用户查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>用户名：</font>
					<h1>
						<input id="username" name="username" type="text">
					</h1></li>
				<li><font>手机号：</font>
					<h1>
						<input id="mobile" name="mobile" type="text">
					</h1></li>
				<li><font>省：</font>
					<h1>
						<input id="provinceId" name="provinceId" type="text"
							class="easyui-combobox">
					</h1></li>
				<li><font>市：</font>
					<h1>
						<input id="cityId" name="cityId" type="text"
							class="easyui-combobox">
					</h1></li>
				<li><font>用户类型：</font>
					<h1>
						<select id="userType" name="userType" class="easyui-combobox"
							data-options="editable:false">
							<option value="">全部</option>
							<option value="0">个人用户</option>
							<option value="1">企业用户</option>
						</select>
					</h1></li>
					
				<li><font>注册时间(始)：</font>
					<h1>
						<input id="regStartDate" name="startDate" type="text"
							class="easyui-datebox">
					</h1></li>
				<li><font>注册时间(终)：</font>
					<h1>
						<input id="regEndDate" name="endDate" type="text"
							class="easyui-datebox">
					</h1></li>					
			</ul>
			<script type="text/javascript">
				
			</script>
		</div>
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="auto-querybutton query_list_button">查 询</a> <a
				href="#" class="auto-resetbutton query_list_button">重 置</a>
		</div>
	</div>
</form>

<table id="querygrid" class="easyui-datagrid" title="用户列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/userinfo/list',
				pageSize:10,
				pageList: [1,2,5,10],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th data-options="field:'username',width:'10%',editor:'text'">用户名</th>
			<th data-options="field:'nickname',width:'8%'">昵称</th>
			<th data-options="field:'mobile',width:'10%'">手机号</th>
			<th data-options="field:'email',width:'15%'">电子邮箱</th>
			<th data-options="field:'province',width:'10%'">省</th>
			<th data-options="field:'city',width:'10%'">市</th>
			<th
				data-options="field:'realnameVerify',width:'10%',formatter:function(value,rec){if(value=='0'){return '未实名' ;}else{return '已实名';}}">实名状态</th>
			<th
				data-options="field:'userType',width:'10%',formatter:function(value,rec){if(value=='0'){return '个人用户' ;}else{return '企业用户';}}">用户类型</th>
			<th data-options="field:'viewDate',width:'10%'">注册时间</th>
		</tr>
	</thead>
</table>

<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
		data-options="iconCls:'icon-edit',plain:true,index:'id',title:'审核',url:'${pageContext.request.contextPath}/web/userinfo/verify'">审核</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-delbutton"
		data-options="iconCls:'icon-remove',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/userinfo/delete'">删除</a>
</div>

