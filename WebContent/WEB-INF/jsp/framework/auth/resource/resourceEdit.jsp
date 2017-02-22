<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>
<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<c:url value='/auth/resource/js/resourceAdd.js'/>"></script>

<div style="margin-bottom: 10px">${parentsResourcePath}</div>

<form id="editMenuResources">
	<input type="hidden" name="id" value="${resource.id}"> <input
		type="hidden" name="parentsResource"
		value="${resource.parentsResource}">
	<div class="ibms_form_default"
		style="padding-top: 5px; border-bottom: 1px dotted #ccc; background: #f4f4f4;">
		<ul>
			<li><font>资源名称：</font>
				<h1>
					<input id="resourceName" name="resourceName" type="text"
						value="${resource.resourceName }" class="easyui-validatebox"
						data-options="required:true,validType:'minLength[1]'">
				</h1></li>
			<li><font>资源优先级：</font>
				<h1>
					<input id="priority" name="priority" type="text"
						value="${resource.priority }" class="easyui-validatebox"
						data-options="required:true,validType:'number'">
				</h1></li>
			<li><font>URL：</font>
				<h1>
					<input id="link" name="link" type="text" value="${resource.link}">
				</h1></li>
			<li><font>资源图片：</font>
				<h1>
					<input id="imageUrl" name="imageUrl" type="text"
						value="${resource.imageUrl}">
				</h1></li>
			<li><font></font>
				<h1>
					<b><input id="validate" name="validate" type="checkbox"
						tvalue="${resource.validate}">是否可用</b>
				</h1></li>
		</ul>
		<div class="ibms_clear"></div>
	</div>

	<div class="ibms_form_btn">
		<a href="#" class="resource_save_button"
			data-options="plain:true,url:'${pageContext.request.contextPath}/auth/resource/update'">保存</a>
		<a href="#" class="resource-editbutton auto-resetbutton"
			data-options="plain:true,url:'${pageContext.request.contextPath}/auth/resource/edit'">重置</a>
	</div>
</form>

<script>
$(function(){
	
	$('#resourceType').combobox('setValue', $("#resourceType").attr("tvalue"));
	
	var node = $("#validate");
	if(node.attr("tvalue")=='true'){
		node.attr("checked", "checked");
	}else{
		node.removeAttr("checked");
	}
});
</script>