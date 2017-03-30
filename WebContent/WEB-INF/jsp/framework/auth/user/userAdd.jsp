<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript"
	src="<c:url value='/styles/js/location.js'/>"></script>

<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<ul>
				<li><font>用户名：</font>
				<h1>
						<input id="username" name="username" type="text"
							class="easyui-validatebox"
							data-options="required:true,validType:'minLength[1]'">
					</h1></li>
				<li><font>密码：</font>
				<h1>
						<input id="password" name="password"
							onfocus="this.type='password'" class="easyui-validatebox"
							data-options="required:true,validType:'minLength[8]'">
					</h1></li>
				<li><font>角色： </font>
					<h1>
						<input id="cc" class="easyui-combobox" name="roles" />
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<ul>
				<li><font>真实姓名：</font>
				<h1>
						<input id="realname" name="realname" type="text"
							class="easyui-validatebox" data-options="required:true">
					</h1></li>
				<li><font>手机号：</font>
				<h1>
						<input id="mobile" name="mobile" type="text"
							class="easyui-validatebox"
							data-options="required:true,validType:'fixLength[11]'">
					</h1></li>
				<li><font>钉钉号：</font>
				<h1>
						<input id="dingding" name="dingding" type="text"
							class="easyui-validatebox" data-options="required:true">
					</h1></li>
				<li><font>邮箱：</font><h1><input id="email" name="email" type="text" class="easyui-validatebox" data-options="required:true"></h1></li>					
			</ul>
			<div class="ibms_clear"></div>
			<ul>
				<li><font>BU：</font>
				<h1>
						<input id="buId" name="buId" type="text" class="easyui-combobox"
							data-options="required:true">
					</h1></li>
				<li><font>分享店：</font>
				<h1>
						<input id="shopId" name="shopId" type="text"
							class="easyui-combobox" data-options="required:true">
					</h1></li>
				<li><font>省：</font>
				<h1>
						<input id="provinceId" name="provinceId" type="text"
							class="easyui-combobox">
					</h1></li>
				<li><font>市：</font>
				<h1>
						<input id="cityId" name="cityId" type="text"
							class="easyui-combobox">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
	        <ul>
	           <li><font>辖区：</font><h1><input id="cities" name="cities" type="text" class="easyui-combobox" data-options="required:true"></h1></li>
	        </ul>
	        <div class="ibms_clear"></div>			
		</div>
		<div class="ibms_form_btn">
			<a href="#" class="query_list_button auto-savebutton"
				data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/auth/user/save',autoclose:true">添加</a>
			<a href="#" class="query_list_button auto-resetbutton">重 置</a>
		</div>
	</div>
</form>

<script type="text/javascript">

$('#shopId').combobox({
	editable:false,
    url: WEB_APP + '/web/shop/json',
    valueField:'id',
    textField:'name'
});

$('#cc').combobox({
	editable:false,
    url: WEB_APP + '/auth/role/getRoles',   
    valueField:'id',
    method:'get',
    textField:'roleName',
    onLoadSuccess:function(){$('#cc').combobox('setValue', '${roleId}');}
});

$('#buId').combobox({
	editable:false,
    url: WEB_APP + '/auth/bu/json',
    valueField:'id',
    textField:'name',
    method:'get'
});

$('#provinceId').combobox({
	editable:false,
    url: WEB_APP + '/web/location/getProvinces',
    valueField:'id',
    textField:'name',
    method:'get',
    onSelect: function (record) {
    	$('#cityId').combobox({
			editable:false,
			method:'get',
		    url: WEB_APP + '/web/location/getCities?provinceId='+record.id,
		    valueField:'id',   
		    textField:'name'
		});
    }
});


$('#cities').combotree({
	editable:false,
    url: WEB_APP + '/web/location/getLocation',
    valueField:'id',
    textField:'name',
    method:'get',
    multiple:true,
    onLoadSuccess: function(node,data){
    	$("#cities").combotree('tree').tree("collapseAll");}    
});
</script>