<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<input type="hidden" name="id" value="${appversion.id }">
			<ul>
				<li><font>版本号：</font>
				<h1>
						<input id="version" name="version" value="${appversion.version }"
							type="text">
					</h1></li>
				<li><font>是否强制更新：</font>
				<h1>
						<select id="enforce" name="enforce" class="easyui-combobox">
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
		</div>
		<div class="ibms_form_btn">
			<a href="#" class="query_list_button auto-savebutton"
				data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/appversion/update',autoclose:true">保存</a>
			<a href="#" class="query_list_button auto-resetbutton">重 置</a>
		</div>
	</div>
</form>

<script type="text/javascript">
var data = [];
data.push({ "value": "是", "id": "true" });
data.push({ "value": "否", "id": "false" });
$("#enforce").combobox({
	editable:false,
    data:data,
	valueField:'id',
	textField:'value',
	onLoadSuccess:function(){$(this).combobox('setValue', '${appversion.enforce}');}
});

</script>