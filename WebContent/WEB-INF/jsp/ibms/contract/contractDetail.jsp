<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<input type="hidden" name="id" value="${contract.id }">
			<ul>
				<li><font>合同号：</font>
				<h1>
						<input name="contractId" type="text"
							value="${contract.contractId }" disabled="disabled">
					</h1></li>
				<li><font>合同详情：</font>
				<h1>
						<a
							href="${pageContext.request.contextPath}${contract.saveLocation}"
							class="query_list_button" target="_blank">合同详情</a>
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
		</div>
	</div>
</form>
