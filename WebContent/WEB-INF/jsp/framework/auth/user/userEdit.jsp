<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<input name="id"  type="hidden" value="${user.id}">
	        <input name="username" type="hidden" value="${user.username}">
	        <ul>
	           <li><font>用户名：</font><h1><label><span>${user.username }</span></label></h1></li>
	           <li><font>密码：</font><h1><input id="password" name="password"  type="password"  value="${user.password}" class="easyui-validatebox" data-options="required:true,validType:'minLength[8]'"></h1></li>
	           <c:if test="${self==false}">
		           <li><font>角色：</font>
		           		<h1>
							<input id="cc"  name="roles"  />  
		           		</h1>
		           </li>
	           </c:if>  
	        </ul>
	        <div class="ibms_clear"></div>
	        <div class="ibms_clear"></div>
		        <ul>
		           <li><font>真实姓名：</font><h1><input id="realname" name="realname"  type="text" value="${user.realname }"></h1></li>
		           <li><font>手机号：</font><h1><input id="mobile" name="mobile"  type="text" value="${user.mobile }" class="easyui-validatebox" data-options="required:true,validType:'fixLength[11]'"></h1></li>
		           <li><font>钉钉号：</font><h1><input id="dingding" name="dingding"  type="text" value="${user.dingding }"></h1></li>
		        </ul>
		        <div class="ibms_clear"></div>
		        <ul>
		           <li><font>BU：</font><h1><input id="buId" name="buId" type="text" class="easyui-combobox" data-options="required:true"></h1></li>
		           <li><font>分享店：</font><h1><input id="shopId" name="shopId"  type="text" class="easyui-combobox"></h1></li>
		           <li><font>省：</font><h1><input id="provinceId" name="provinceId"  type="text" class="easyui-combobox"></h1></li>
		           <li><font>市：</font><h1><input id="cityId" name="cityId"  type="text" class="easyui-combobox"></h1></li>
		        </ul>
		        <div class="ibms_clear"></div>
        </div>
        <div class="ibms_form_btn">
	        <a href="#" class="query_list_button auto-savebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/auth/user/update',autoclose:true">修改</a>
	        <a id="resetbutton" href="#" class="query_list_button">重 置</a>
        </div>
	</div>
</form>

<script>
if('${self}' == 'false'){
	function selectBox(){
		$('#cc').combobox({
			editable:false,
		    url: WEB_APP + '/auth/role/getRoles',
		    valueField:'id',   
		    textField:'roleName',
		    onLoadSuccess:function(){$('#cc').combobox('setValue', '${roleid}');}
		});
	}
	
	$(selectBox());
}

$('#shopId').combobox({
	editable:false,
    url: WEB_APP + '/web/shop/json',
    valueField:'id',
    textField:'name',
    onLoadSuccess:function(){$(this).combobox('setValue', '${user.shopId }');}
});

$('#buId').combobox({
	editable:false,
    url: WEB_APP + '/auth/bu/json',
    valueField:'id',
    textField:'name',
    method:'get',
    onLoadSuccess:function(){$(this).combobox('setValue', '${user.buId}');}
});

$('#provinceId').combobox({
	editable:false,
    url: WEB_APP + '/web/location/getProvinces',
    valueField:'id',
    textField:'name',
    method:'get',
    onLoadSuccess:function(){
    	if('${user.provinceId}'!=0){
	    	$(this).combobox('setValue', '${user.provinceId}');
	    	$('#cityId').combobox({
				editable:false,
				method:'get',
			    url: WEB_APP + '/web/location/getCities?provinceId='+$(this).combobox('getValue'),
			    valueField:'id',   
			    textField:'name',
			    onLoadSuccess:function(){$(this).combobox('setValue', '${user.cityId}');}
			});
    	}
    },
    onSelect: function (record) {
    	$('#cityId').combobox({
			editable:false,
			method:'get',
		    url: WEB_APP + '/web/location/getCities?provinceId='+record.id,
		    valueField:'id',   
		    textField:'name',
		    onLoadSuccess:function(){$(this).combobox('setValue', '');}
		});
    }
});

$("#resetbutton").click(function(){
	if('${self}' == 'false'){
		selectBox();
	}
	$(this).parents("form").form("reset");
});
</script>