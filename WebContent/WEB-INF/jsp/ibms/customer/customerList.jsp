<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>
<script type="text/javascript" src="<c:url value='/styles/js/location.js'/>"></script>
<script type="text/javascript" src="<c:url value='/styles/js/assetType.js'/>"></script>
<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="客户查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>客户名称：</font>
					<h1>
						<input id="customerName" name="customerName" type="text">
					</h1></li>
				<li><font>客户类型：</font>
					<h1>
						<select id="custmerType" name="custmerType" class="easyui-combobox" data-options="editable:false">
							<option value="">全部</option>
							<option value="0">个人</option>
							<option value="1">企业</option>
						</select>
					</h1></li>					
				<li><font>创建来源：</font>
					<h1>
						<select id="createSource" name="createSource" class="easyui-combobox" data-options="editable:false">
							<option value="">全部</option>
							<option value="0">手机平台</option>
							<option value="1">后台创建</option>
						</select>
					</h1></li>
				<li><font>客户状态：</font>
					<h1>
						<select id="customerStatus" name="customerStatus" class="easyui-combobox" data-options="editable:false">
							<option value="0">有效</option>
							<option value="1">失效</option>
							<option value="2">潜在</option>
						</select>
					</h1></li>
					<li><font>客户等级：</font>
					<h1>
						<select id="custoerLevel" name="custoerLevel" class="easyui-combobox" data-options="editable:false">
							<option value="">全部</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						    <option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>							
						</select>
					</h1></li>
					<li><font>所在区域：</font>
					<h1>
						<input id="customerAddr" name="customerAddr" type="text">
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

<table id="querygrid" class="easyui-datagrid" title="客户列表"
	data-options="
				iconCls: 'icon-list',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/customer/list',
				pageSize:10,
				pageList: [1,2,5,10],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>	
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th data-options="field:'custType',width:'5%',editor:'text',formatter:function(value,rec){if(value=='0'){return '个人' ;}else{return '企业';}}">客户类型</th>
			<th data-options="field:'custName',width:'15%',editor:'text'">客户名称</th>
			<th data-options="field:'custAddress',width:'30%'">客户地址</th>
			<th data-options="field:'legalName',width:'6%'">联系人</th>
			<th data-options="field:'legalMobile',width:'8%'">联系电话</th>
		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton auto-addbutton"
		data-options="iconCls:'icon-add',plain:true,url:'${pageContext.request.contextPath}/web/customer/add'">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton auto-editbutton"
		data-options="iconCls:'icon-edit',plain:true,index:'id',url:'${pageContext.request.contextPath}/web/customer/update'">修改</a>
</div>
