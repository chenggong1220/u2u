<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm" >
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<input type="hidden" name="rentAssetId" value="${asset.id }">
			<input type="hidden" name="contractId" value="${contractId }">
	        <ul>
	           <li><font>设备编号：</font><h1><input name="code"  type="text" value="${asset.asset.code }" disabled="disabled"></h1></li>
	           <li><font>收货时间：</font><h1><input name="selectDate"  type="text" value="${asset.receiveDateView }" class="easyui-datebox"  data-options="required:true"></h1></li>
	        </ul>
			<div class="ibms_clear"></div>
        </div>
        <div class="ibms_form_btn">
	        <a href="#" class="query_list_button auto-savebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/send/manager/send/checkin/save',autoclose:true">保存</a>
	        <a href="#" class="query_list_button auto-resetbutton">重 置</a>
        </div>
	</div>
</form>
