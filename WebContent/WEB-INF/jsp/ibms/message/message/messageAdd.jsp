<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm" >
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
	        <ul>
	           <li><font>标 题：</font><h1><input name="title"  type="text" ></h1></li>
	        </ul>
	        <div class="ibms_clear"></div>
	        <ul>
	        	<li><font>消息类型：</font><h1>
		           <select id="type" name="type" class="easyui-combobox" data-options="editable:false">
	                 <option value="APP">APP</option>
	                 <option value="短信">短信</option>
	               </select>
	               </h1>
               </li>
	        </ul>      
	        <div class="ibms_clear"></div>
	        <ul>
	        	<li><font>对象类型：</font><h1>
		           <select id="msgType" name="msgType" class="easyui-combobox" data-options="editable:false">
	                 <option value="系统消息">系统消息</option>
	                 <option value="个人消息">个人消息</option>
	               </select>
	               </h1>
               </li>
	        </ul>
	        <div class="ibms_clear"></div>
	        <ul id="personMessage" hidden="true">
	        	<li><font>发送对象：</font><h1><input id="userId" name="userId"  type="text" class="easyui-combobox"></h1></li>
	        </ul>
	        <div class="ibms_clear"></div>
	        <div class="ibms_form_default_textarea">
	        	<font>内 容：</font>
	        	<h1><textarea name="content" ></textarea></h1>
	        </div>
        </div>
        <div class="ibms_clear"></div>
	    <div class="ibms_form_btn">
	        <a href="#" class="query_list_button auto-savebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/message/save',autoclose:true">保存</a>
	        <a href="#" class="query_list_button auto-resetbutton">重 置</a>
        </div>
	</div>
</form>

<script type="text/javascript">
var msgTypeData = [];
msgTypeData.push({ "value": "系统消息", "id": "系统消息" });
msgTypeData.push({ "value": "个人消息", "id": "个人消息" });
$("#msgType").combobox({
	editable:false,
	data:msgTypeData,
	valueField:'id',
	textField:'value',
	onSelect: function (record) {
		if(record.id=="个人消息"){
			$("#personMessage").show();
			$('#userId').combobox({
				editable:false,
				required:true,
				multiple:true,
			    url: WEB_APP + '/web/userinfo/json',
			    valueField:'id',
			    textField:'username'
			});
		}else{
			$("#personMessage").hide();
		}
    }
});
</script>