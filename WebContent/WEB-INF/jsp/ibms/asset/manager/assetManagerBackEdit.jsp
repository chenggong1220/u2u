<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm">
	<div id="infopointAccordion" class="easyui-accordion"
		style="width: 100%;" data-options="multiple:true">
		<div title="原租赁物" class="dcim_form_panel" data-options="selected:true">
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
					<input type="hidden" name="orderId" value="${order.id }"> <input
						type="hidden" name="oldAssetId" value="${asset.asset.id }">
					<input type="hidden" name="managerRentAssetId" value="${asset.id }">
					<ul>
						<li><font>编号：</font>
						<h1>
								<input id="assetCode" name="assetCode"
									value="${asset.asset.code }" type="text">
							</h1></li>
						<li><font>状态：</font>
							<h1>
								<input id="rentStatus" name="rentStatus" type="text"
									class="easyui-combobox">
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
						<li><font>地址：</font>
						<h1>
								<input id="address" name="address" type="text">
							</h1></li>
					</ul>
				</div>
			</div>
		</div>
		<div title="更换后租赁物" class="dcim_form_panel">
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
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
								<input id="newAssetId" name="newAssetId" type="text">
							</h1></li>
					</ul>
					<div class="ibms_clear"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="ibms_form_btn">
		<a href="#" class="query_list_button auto-savebutton"
			data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/asset/manager/back/save',autoclose:true">保存</a>
		<a href="#" class="query_list_button auto-resetbutton">重 置</a>
	</div>
</form>

<script type="text/javascript">
var data = [];
data.push({ "value": "已租", "id": "0" });
data.push({ "value": "空闲", "id": "1" });
data.push({ "value": "检修", "id": "2" });
$("#rentStatus").combobox({
	editable:false,
    data:data,
	valueField:'id',
	textField:'value',
	onLoadSuccess:function(){$(this).combobox('setValue', '${asset.asset.rent}');}
});

$('#provinceId').combobox({
	editable:false,
    url: WEB_APP + '/web/location/getProvinces',
    valueField:'id',
    textField:'name',
    onSelect: function (record) {
    	$('#cityId').combobox({
			editable:false,
		    url: WEB_APP + '/web/location/getCities?provinceId='+record.id,
		    valueField:'id',   
		    textField:'name'
		});
    }
});

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
   	        	$('#newAssetId').combobox({
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