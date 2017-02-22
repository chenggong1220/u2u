<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<form id="saveForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search'" title="修改密码">
		<div class="ibms_form_default">
			<ul>
				<li><font>用户名：</font>
					<h1>
						<lable>
						<span>${user.username }</span></lable>
					</h1> <input name="username" type="hidden" value="${user.username }">
				</li>
			</ul>
			<div class="ibms_clear"></div>
			<ul>
				<li><font>密码：</font>
					<h1>
						<input type="password" id="oldPassword" name="oldPassword"
							class="easyui-validatebox" data-options="required:true">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<ul>
				<li><font>新密码：</font>
					<h1>
						<input type="password" id="newPassword" name="newPassword"
							class="easyui-validatebox" value=""
							data-options="required:true,validType:'minLength[8]'">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<ul>
				<li><font>再次输入：</font>
					<h1>
						<input type="password" id="newPasswordAgain"
							name="newPasswordAgain" class="easyui-validatebox"
							data-options="required:true" validType="equalTo['#newPassword']"
							invalidMessage="两次输入密码不匹配">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
		</div>
		<div class="ibms_form_btn">
			<a href="#" id="save_self_user" class="auto-querybutton"
				data-options="url:'${pageContext.request.contextPath}/auth/user/self/save'">保
				存</a> <a href="#" class="query_list_button auto-resetbutton">重 置</a> <a
				href="#" id="cancel_eidt" class="query_list_button">取 消</a>
		</div>
	</div>
</form>

<script>
$.extend($.fn.validatebox.defaults.rules, {
    /*必须和某个字段相等*/
    equalTo: {
    	validator: function (value, param) {
    		return $(param[0]).val() == value;
    	},
    	message: '两次输入密码不一致'
    }
});

$("#save_self_user").click(function(){
	var form = $(this).parents("form");
	
	if(!form.form('validate')){
		return;
	}
	var dataOption = parseOption(this);

	if(dataOption){
    	IBMS(form).post(dataOption.url,function(data){
    		if (data.message) {
    			IBMS.messager.alert("提示消息", data.message, "info", function() {
    				if (data.statusCode =="200") {
    					$("#ibms_main_content_01").load( WEB_APP + data.returnUrl);
    				}else{
    					$("#oldPassword").val("");
    				}
    			});
    		}
    	});
	}
});

$("#cancel_eidt").click(function(){
	$("#ibms_main_content_01").load( WEB_APP + "/auth/user/self");
});
</script>