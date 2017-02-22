<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<ul>
				<li><font>标 题：</font>
				<h1>
						<input name="title" type="text" value="${message.title }"
							disabled="disabled">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<ul>
				<li><font>消息类型：</font>
				<h1>
						<input name="author" type="text" value="${message.type }"
							disabled="disabled">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<ul>
				<li><font>对象类型：</font>
				<h1>
						<input name="author" type="text" value="${message.msgType }"
							disabled="disabled">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<div class="ibms_form_default_textarea">
				<font>发送对象：</font>
				<h1>
					<textarea name="content" disabled="disabled">${message.username }</textarea>
				</h1>
			</div>
			<div class="ibms_clear"></div>
			<ul>
				<li><font>时 间：</font>
				<h1>
						<input name="viewDate" type="text" value="${message.viewDate}"
							disabled="disabled">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<div class="ibms_form_default_textarea">
				<font>内 容：</font>
				<h1>
					<textarea name="content" disabled="disabled">${message.content }</textarea>
				</h1>
			</div>
		</div>
	</div>
</form>
