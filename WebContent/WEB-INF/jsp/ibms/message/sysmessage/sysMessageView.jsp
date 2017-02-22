<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<ul>
				<li><font>标 题：</font>
				<h1>
						<input name="title" type="text" value="${sysmessage.title }"
							disabled="disabled">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<ul>
				<li><font>类 型：</font>
				<h1>
						<input name="author" type="text" value="${sysmessage.type }"
							disabled="disabled">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<ul>
				<li><font>发送对象：</font>
				<h1>
						<input name="author" type="text"
							value="${sysmessage.user.realname}" disabled="disabled">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<ul>
				<li><font>时 间：</font>
				<h1>
						<input name="viewDate" type="text" value="${sysmessage.viewDate}"
							disabled="disabled">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<div class="ibms_form_default_textarea">
				<font>内 容：</font>
				<h1>
					<textarea name="content" disabled="disabled">${sysmessage.content }</textarea>
				</h1>
			</div>
		</div>
	</div>
</form>
