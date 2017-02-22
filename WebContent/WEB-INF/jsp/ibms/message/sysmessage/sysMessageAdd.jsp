<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<ul>
				<li><font>标 题：</font>
				<h1>
						<input name="title" type="text">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<ul>
				<li><font>类型：</font>
				<h1>
						<select id="type" name="type" class="easyui-combobox"
							data-options="editable:false">
							<option value="短信">短信</option>
							<option value="钉钉">钉钉</option>
						</select>
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<ul>
				<li><font>发送对象：</font>
				<h1>
						<input id="userId" name="userId" type="text">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<div class="ibms_form_default_textarea">
				<font>内 容：</font>
				<h1>
					<textarea name="content"></textarea>
				</h1>
			</div>
			<div class="ibms_clear"></div>
		</div>
		<div class="ibms_form_btn">
			<a href="#" class="query_list_button auto-savebutton"
				data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/sysmessage/save',autoclose:true">保存</a>
			<a href="#" class="query_list_button auto-resetbutton">重 置</a>
		</div>
	</div>
</form>

<script type="text/javascript">

$('#userId').combobox({
	editable:false,
    url: WEB_APP + '/auth/user/json',
    valueField:'id',
    textField:'realname'
});

</script>