<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm">
	<input type="hidden" name="id" value="${bu.id }">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<ul>
				<li><font>部门名称：</font>
				<h1>
						<input id="name" name="name" type="text" value="${bu.name}">
					</h1></li>
			</ul>
		</div>
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="query_list_button auto-savebutton"
				data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/auth/bu/update',autoclose:true">提交</a>
			<a href="#" class="query_list_button auto-resetbutton">重 置</a>
		</div>
	</div>
</form>