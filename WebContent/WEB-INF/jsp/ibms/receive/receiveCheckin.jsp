<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<input type="hidden" name="orderId" value="${order.id }">
			<ul>
				<li><font>应交保证金：</font>
				<h1>
						<input name="deposit" type="text" value="${order.deposit }"
							disabled="disabled">
					</h1></li>
				<li><font>保证金：</font>
				<h1>
						<input name="leftDeposit" type="text"
							value="${order.leftDeposit }">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
		</div>
		<div class="easyui-panel ibms_form_panel">
			<div class="project-record">
				<div class="project-table">
					<table cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<c:forEach var="billcheck" items="${billChecks}" varStatus="vs">
									<input type="hidden" name="billcheck_id_${vs.count }"
										value="${billcheck.id }">
									<td>租期：<span>${billcheck.outdateView==''?'未出账':billcheck.outdateView }</span></td>
									<td>应交租金：<span>${billcheck.allAmount }</span></td>
									<td>租金：<span><input type="text"
											name="billcheck_value_${vs.count }"
											value="${billcheck.payAmount }"></span></td>
								</c:forEach>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="ibms_form_btn">
			<a href="#" class="query_list_button auto-savebutton"
				data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/receive/checkin/save',autoclose:true">保存</a>
			<a href="#" class="query_list_button auto-resetbutton">重 置</a>
		</div>
	</div>
</form>
