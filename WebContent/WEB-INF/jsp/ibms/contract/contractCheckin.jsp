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
				<li><font>签收时间：</font>
				<h1>
						<input name="selectDate" type="text"
							value="${contract.checkinDate }" class="easyui-datebox"
							data-options="required:true">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
		</div>
		<div class="ibms_form_btn">
			<a href="#" class="query_list_button auto-savebutton"
				data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/contract/checkin/save',autoclose:true">保存</a>
			<a href="#" class="query_list_button auto-resetbutton">重 置</a>
		</div>
	</div>
</form>
