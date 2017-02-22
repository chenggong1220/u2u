<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>


<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<input type="hidden" name="orderId" value="${order.id }">
			<ul>
				<li><font>品牌：</font>
				<h1>
						<input id="brandId" name="brandId" type="text">
					</h1></li>
				<li><font>型号：</font>
				<h1>
						<input id="assetTypeId" name="assetTypeId" type="text">
					</h1></li>
				<li><font>设备ID：</font>
				<h1>
						<input id="assetId" name="assetId" type="text">
					</h1></li>
				<li><font>起租时间：</font>
				<h1>
						<input id="selectDate" name="selectDate" type="text"
							class="easyui-datebox">
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
		</div>
		<div class="ibms_form_btn">
			<a href="#" class="query_list_button auto-savebutton"
				data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/asset/manager/rent/save',autoclose:true">保存</a>
			<a href="#" class="query_list_button auto-resetbutton">重 置</a>
		</div>
	</div>
</form>
<script type="text/javascript">
$('#brandId').combobox({
	editable:false,
    url: WEB_APP + '/web/asset/brand/json',
    valueField:'id',
    textField:'name',
    onSelect: function (record) {
    	$('#assetTypeId').combobox({
    		editable:false,
    	    url: WEB_APP + '/web/asset/type/json?brandId=' + record.id,
    	    valueField:'id',
    	    textField:'model',
   	    	onSelect: function (record) {
   	        	$('#assetId').combobox({
   	        		editable:false,
   	        	    url: WEB_APP + '/web/asset/json?assetTypeId=' + record.id,
   	        	    valueField:'id',
   	        	    textField:'code'
   	        	});
   	    	}
    	});
    }
})
</script>