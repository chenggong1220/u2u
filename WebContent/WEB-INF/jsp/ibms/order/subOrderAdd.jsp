<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm" >
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<input type="hidden" name="id" value="${order.id }">
	        <ul>
	           <li><font>订单ID：</font><h1><input id="detailLocation" name="detailLocation" disabled="disabled" type="text" value="${order.code }"></h1></li>
	        </ul>
	        <div class="ibms_clear"></div>
	        <ul>
	           <li><font>分单给：</font>
	           	<h1>
	           		<input id="operatorId" name="operatorId" type="text" class="easyui-combobox" />
	           	</h1></li>
	        </ul>
        </div>
	</div>
	<div class="ibms_form_btn">
       <a href="#" class="query_list_button auto-savebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/suborder/save',autoclose:true">分单</a>
       <a href="#" class="query_list_button auto-resetbutton">重 置</a>
    </div>
</form>

<script type="text/javascript">
$("#operatorId").combobox({
	editable:false,
    url: WEB_APP + '/web/sys/user/operators',
    valueField:'id',
    textField:'username',
    onLoadSuccess:function(){$(this).combobox('setValue', '${order.operatorId}');}
});
</script>