<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<input type="hidden" id="id" name="id" value="${userinfo.id }" />
		<div class="ibms_form_default">
			<ul>
				<li><font>用户名：</font>
				<h1>
						<input name="username" type="text" value="${userinfo.username }"
							disabled="disabled">
					</h1></li>
				<li><font>手机：</font>
				<h1>
						<input name="mobile" type="text" value="${userinfo.mobile }"
							disabled="disabled">
					</h1></li>
				<li><font>昵称：</font>
				<h1>
						<input name="nickname" type="text" value="${userinfo.nickname }"
							disabled="disabled">
					</h1></li>
				<li><font>邮箱：</font>
				<h1>
						<input name="email" type="text" value="${userinfo.email }"
							disabled="disabled">
					</h1></li>
				<li><font>省：</font>
				<h1>
						<input name="province" type="text" value="${userinfo.province }"
							disabled="disabled">
					</h1></li>
				<li><font>市：</font>
				<h1>
						<input name="city" type="text" value="${userinfo.city }"
							disabled="disabled">
					</h1></li>
				<li><font>行业：</font>
				<h1>
						<input name="industry" type="text" value="${userinfo.industry }"
							disabled="disabled">
					</h1></li>
				<li><font>用户类型：</font>
				<h1>
						<input name="userType" type="text"
							value="${userinfo.userType=='0'?'个人用户':'企业用户' }"
							disabled="disabled">
					</h1></li>
				<li><font>缴纳会员费：</font>
				<h1>
						<input name="userType" type="text"
							value="${userinfo.hasDeposited=='false'?'否':'是' }"
							disabled="disabled">
					</h1></li>
				<li><font>会员费：</font>
				<h1>
						<input name="userType" type="text" value="${userinfo.deposit }"
							disabled="disabled">
					</h1></li>
				<li><font>余额：</font>
				<h1>
						<input name="userType" type="text" value="${userinfo.amount }"
							disabled="disabled">
					</h1></li>
				<li><font>状态：</font>
				<h1>
						<input name="userType" type="text"
							value="${userinfo.status=='true'?'正常':'锁定' }" disabled="disabled">
					</h1></li>
				<li><font>推送消息：</font>
				<h1>
						<input name="userType" type="text"
							value="${userinfo.pushMessage=='true'?'推送':'不推送' }"
							disabled="disabled">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<ul>
				<li><font>真实姓名：</font>
				<h1>
						<input name="realname" type="text" value="${userinfo.realname }"
							disabled="disabled">
					</h1></li>
				<li><font>身份证号：</font>
				<h1>
						<input name="realname" type="text" value="${userinfo.identify }"
							disabled="disabled">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<div class="ibms_form_default_textarea">
				<font>正面照：</font>
				<h1 style="width: 90%;">
					<b> <img width="300" height="200"
						src="${pageContext.request.contextPath}${userinfo.identifyFrontImg }"
						name="identifyFrontImg" />
					</b>
				</h1>
			</div>
			<div class="ibms_clear"></div>
			<div class="ibms_form_default_textarea">
				<font>反面照：</font>
				<h1 style="width: 90%;">
					<b> <img width="300" height="200"
						src="${pageContext.request.contextPath}${userinfo.identifyBackendImg }"
						name="identifyBackendImg" />
					</b>
				</h1>
			</div>
			<div class="ibms_clear"></div>
			<ul>
				<li><font>审核：</font>
					<h1>
						<input id="realnameVerify" name="realnameVerify"
							class="easyui-combobox" type="text" />
						<script type="text/javascript">
	                	var data= [];
    	                data.push({ "text": "未审核", "value": '0'});
        	            data.push({ "text": "通过", "value": '1'});
	                    $('#realnameVerify').combobox({
	                    	editable : false,
	                    	required : true,
	                    	data : data,
	                        onLoadSuccess:function(){
	                        	$(this).combobox('setValue', '${userinfo.realnameVerify}');	
	                        }
	                    });
                    </script>
					</h1></li>
			</ul>
		</div>
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="query_list_button auto-savebutton"
				data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/userinfo/update',autoclose:true">保存</a>
			<a href="#" class="query_list_button auto-resetbutton">重 置</a>
		</div>
	</div>
</form>
