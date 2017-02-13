<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm" >
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
		<input type="hidden" id="id" name="id" value="${shop.id }" />
		  <ul>
	           <li><font>名称：</font><h1><input id="name" name="name"  type="text" value="${shop.name }"></h1></li>
	        </ul>
	        <ul>
	           <li><font>地址：</font><h1><input id="address" name="address"  type="text" value="${shop.address }"></h1></li>
	        </ul>   
	       	<ul>
	           <li><font>BU：</font><h1><input id="bid_edit" name="bid"  type="text" class="easyui-combobox" data-options="required:true" value="${shop.bid}"></h1></li>
	        </ul>      
	        <ul>
	           <li><font>联系人：</font><h1><input id="contactName" name="contactName"  type="text" value="${shop.contactName }"></h1></li>
	        </ul>
	        <ul>
	           <li><font>联系地址：</font><h1><input name="contactAddress"  type="text" value="${shop.contactAddress }"></h1></li>
	        </ul>
	        <ul>
	           <li><font>电子邮件：</font><h1><input name="email"  type="text" value="${shop.email }"></h1></li>
	        </ul>
	        <ul>
	           <li><font>备注：</font><h1><input name="note"  type="text" value="${shop.note }"></h1></li>
	        </ul>
        </div>
        <div class="ibms_clear"></div>
	    <div class="ibms_form_btn">
	        <a href="#" class="query_list_button auto-savebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/shop/update',autoclose:true">保存</a>
	        <a href="#" class="query_list_button auto-resetbutton">重 置</a>
        </div>
	</div>
</form>

<script type="text/javascript">
$('#bid_edit').combobox({
	editable:false,
    url: WEB_APP + '/auth/bu/json',
    valueField:'id',
    textField:'name',
    method:'get',
    onLoadSuccess:function(){$(this).combobox('setValue', '${shop.bid}');}
});
</script>