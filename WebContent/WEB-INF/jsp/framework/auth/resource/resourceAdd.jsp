<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>
<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<c:url value='/auth/resource/js/resourceAdd.js'/>"></script>

<div style="margin-bottom: 10px">${parentsResourcePath}</div>

<form id="addMenuResources">
	<input type="hidden" name="parentsResource" value="${parentsResource}">
	<input type="hidden" name="parentsLevel" value="${parentsLevel}">
	<div class="ibms_form_default"
		style="padding-top: 5px; border-bottom: 1px dotted #ccc; background: #f4f4f4;">
		<ul>
			<li><font>资源名称：</font>
				<h1>
					<input id="resourceName" name="resourceName" type="text" class="easyui-validatebox" data-options="required:true,validType:'minLength[1]'">
				</h1></li>
			<li><font>资源优先级：</font>
				<h1>
					<input id="priority" name="priority" type="text" class="easyui-validatebox" data-options="required:true,validType:'number'">
				</h1></li>
			<li><font>URL：</font>
				<h1>
					<input id="link" name="link" type="text">
				</h1></li>
			<li><font>资源图片：</font>
				<h1>
					<input id="imageUrl" name="imageUrl" type="text">
				</h1></li>
			<li><font></font>
				<h1>
					<b><input id="validate" name="validate" type="checkbox"
						checked="checked">是否可用</b>
				</h1></li>
			</ul>
			<div class="ibms_clear"></div>
	</div>
	<div class="ibms_form_btn">
		<a href="#" class="resource_save_button"
			data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/auth/resource/save'">保存</a>
		<a href="#" class="auto-resetbutton">重置</a>
	</div>
</form>
