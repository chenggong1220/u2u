<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<ul>
				<li><font>版本号：</font>
				<h1>
						<input id="version" name="version" type="text"
							class="easyui-validatebox" data-options="required:true">
					</h1></li>
				<li><font>是否强制更新：</font>
				<h1>
						<select id="enforce" name="enforce" class="easyui-combobox"
							data-options="required:true">
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
					</h1></li>
			</ul>
		</div>
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="query_list_button auto-savebutton"
				data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/appversion/save',autoclose:true">保存</a>
			<a href="#" class="query_list_button auto-resetbutton">重 置</a>
		</div>
	</div>
</form>
