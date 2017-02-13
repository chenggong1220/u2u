<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<div class="easyui-panel ibms_form_panel" data-options="iconCls:'icon-search'" title="系统日志查询">
	<form action="">
		<div class="ibms_form_default">
	       <ul class="ibms_clear">
	          <li><font>操作人：</font><h1><input name="username" id="username" class="textbox test" /></h1></li>
	          <li><font>起始时间：</font><h1><input name="startDate" id="startDate" class="easyui-datebox textbox" /></h1></li>
	          <li><font>结束时间：</font><h1><input name="endDate" id="endDate" class="easyui-datebox textbox" /></h1></li>
	       </ul>
	    </div>
	    <div class="ibms_form_btn">
	       <a href="#" class="auto-querybutton query_list_button">查 询</a>
	       <a href="#" class="auto-resetbutton query_list_button">重 置</a>
       </div>
    </form>
</div>
	
<table id="dg" class="easyui-datagrid" title="系统日志记录"
		data-options="
			iconCls: 'icon-edit',
			singleSelect: true,
			toolbar: '#tb',
			pagination:true,
			rownumbers:true,
			fitColumns: true,
			singleSelect:false,
			url: '${pageContext.request.contextPath}/ibms/log/page',
			method: 'get'
		">
	
	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">ID</th>
			<th data-options="field:'username',width:'16%'">操作人</th>
			<th data-options="field:'operateObject',width:'17%'">操作对象</th>
			<th data-options="field:'operateDesc',width:'20%'">操作描述</th>
			<th data-options="field:'operateTime',width:'20%',sortable:true">操作时间</th>
			<th data-options="field:'operateIp',width:'20%'">操作IP</th>
		</tr>
	</thead>
</table>
