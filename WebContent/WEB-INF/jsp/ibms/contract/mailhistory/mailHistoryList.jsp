<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<table id="querygrid" class="easyui-datagrid" title="发送邮件列表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/mailhistory/list',
				pageSize:10,
				pageList: [10,20,50],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true">序列号</th>
			<th
				data-options="field:'contractId',width:'10%',formatter:function(value,rec){return rec.contract.contractId}">合同编号</th>
			<th data-options="field:'title',width:'20%'">邮件标题</th>
			<th data-options="field:'sendTo',width:'15%'">收件人</th>
			<th
				data-options="field:'contractFile',width:'20%',formatter:function(value,rec){return '<a target=\'
				_blank\' href=\'${pageContext.request.contextPath}'+rec.contractFile +'\'>'
				+ rec.contract.contractId+'</a>' }">合同文件
			</th>
			<th data-options="field:'createTime',width:'10%'">发送时间</th>
		</tr>
	</thead>
</table>