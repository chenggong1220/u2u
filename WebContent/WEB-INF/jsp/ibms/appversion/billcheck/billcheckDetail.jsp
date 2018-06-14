<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<ul>
				<li><font>合同编号：</font>
				<h1>
						<input name="deposit" type="text"
							value="${billcheck.contract.contractId }" disabled="disabled">
					</h1></li>
				<li><font>账单期数：</font>
				<h1>
						<input name="deposit" type="text"
							value="${billcheck.currentTerm }" disabled="disabled">
					</h1></li>
				<li><font>出账日期：</font>
				<h1>
						<input name="deposit" type="text"
							value="${billcheck.outdateView }" disabled="disabled">
					</h1></li>
				<li><font>付款截止日期：</font>
				<h1>
						<input name="deposit" type="text"
							value="${billcheck.enddateView }" disabled="disabled">
					</h1></li>
				<li><font>计费时长：</font>
				<h1>
						<input name="deposit" type="text"
							value="${billcheck.useDuration }" disabled="disabled">
					</h1></li>
				<li><font>当期总费用：</font>
				<h1>
						<input name="deposit" type="text" value="${billcheck.allAmount }"
							disabled="disabled">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
		</div>
		<div class="ibms_form_default">
			<table id="querygrid" class="easyui-datagrid" title="设备清单"
				data-options="
						iconCls: 'icon-edit',
						singleSelect: true,
						toolbar: '#tb_2',
						pagination:true,
						rownumbers:true,
						fitColumns: true,
						singleSelect:true,
						url: '${pageContext.request.contextPath}/web/billcheck/detail/list?contractId=${billcheck.contractId }',
						pageSize:100,
						pageList: [100],
						collapsible:true,
						method: 'post'
					">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true">序列号</th>
						<th data-options="field:'model',width:'10%'">型号</th>
						<th data-options="field:'assetId',width:'15%'">设备序号</th>
						<th data-options="field:'deviceDate',width:'15%'">日期</th>
						<th data-options="field:'useTime',width:'15%'">累计计费时长(小时)</th>
						<th data-options="field:'repairTime',width:'15%'">不计费时长(小时)</th>
						<th data-options="field:'amount',width:'20%'">费用(分时租赁)</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</form>
