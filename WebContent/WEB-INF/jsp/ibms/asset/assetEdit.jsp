<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<input type="hidden" name="id" value="${asset.id }">
			<ul>
				<li><font>省：</font>
				<h1>
						<input id="province" name="provinceId" type="text"
							class="easyui-combobox">
					</h1></li>
				<li><font>市：</font>
				<h1>
						<input id="city" name="cityId" type="text" class="easyui-combobox">
					</h1></li>
				<li><font>品牌：</font>
				<h1>
						<input id="brandId" name="brandId" type="text"
							class="easyui-combobox">
					</h1></li>
				<li><font>型号：</font>
				<h1>
						<input id="assetType" name="assetTypeId" type="text"
							class="easyui-combobox">
					</h1></li>
				<li><font>分享店：</font>
				<h1>
						<input id="shopId" name="shopId" type="text"
							class="easyui-combobox">
					</h1></li>
				<li><font>编码：</font>
				<h1>
						<input name="code" type="text" value="${asset.code }">
					</h1></li>
				<li><font>使用时长：</font>
				<h1>
						<input name="useTime" type="text" value="${asset.useTime }">
					</h1></li>
				<li><font>出租状态：</font>
				<h1>
						<select id="rent" name="rent" class="easyui-combobox"
							data-options="editable:false">
						</select>
					</h1></li>
				<li><font>所在位置：</font>
				<h1>
						<select id="location" name="location" class="easyui-combobox"
							data-options="editable:false">
						</select>
					</h1></li>
				<li><font>加密狗：</font>
				<h1>
						<input name="softDog" type="text" value="${asset.softDog }">
					</h1></li>
				<li><font>是否保险：</font>
				<h1>
						<select id="insurance" name="insurance" class="easyui-combobox"
							data-options="editable:false">
						</select>
					</h1></li>
				<li><font>保险单号：</font>
				<h1>
						<input name="insuranceId" type="text"
							value="${asset.insuranceId }">
					</h1></li>
				<li><font>保险金额：</font>
				<h1>
						<input name="insuranceAmount" type="text"
							value="${asset.insuranceAmount }">
					</h1></li>
			</ul>
		</div>
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="query_list_button auto-savebutton"
				data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/asset/update',autoclose:true">保存</a>
			<a href="#" class="query_list_button auto-resetbutton">重 置</a>
		</div>
	</div>
</form>

<script type="text/javascript">
$('#shopId').combobox({
	editable:false,
    url: WEB_APP + '/web/shop/json',
    valueField:'id',
    textField:'name',
    onLoadSuccess:function(){$(this).combobox('setValue', '${asset.shopId }');}
});
var rentData = [];
rentData.push({ "value": "已租", "id": '0' });
rentData.push({ "value": "空闲", "id": '1' });
rentData.push({ "value": "检修", "id": '2' });
$("#rent").combobox({
	editable:false,
    data:rentData,
	valueField:'id',
	textField:'value',
	onLoadSuccess:function(){$(this).combobox('setValue', '${asset.rent }');}
});

var locationData = [];
locationData.push({ "value": "客户端", "id": "客户端" });
locationData.push({ "value": "沈阳", "id": "沈阳" });
locationData.push({ "value": "宁波配送中心", "id": "宁波配送中心" });
locationData.push({ "value": "常州机电展厅", "id": "常州机电展厅" });
locationData.push({ "value": "洛阳亚广4S店", "id": "洛阳亚广4S店" });
locationData.push({ "value": "石家庄配送中心", "id": "石家庄配送中心" });
locationData.push({ "value": "深圳鹏创4s店", "id": "深圳鹏创4s店" });
locationData.push({ "value": "寄存南方机床", "id": "寄存南方机床" });
locationData.push({ "value": "U2U", "id": "U2U" });
locationData.push({ "value": "厂商", "id": "厂商" });

$("#location").combobox({
	editable:false,
    data:locationData,
	valueField:'id',
	textField:'value',
	onLoadSuccess:function(){$(this).combobox('setValue', '${asset.location }');console.log('${asset.location }');}
});

var insuranceData = [];
insuranceData.push({ "value": "是", "id": 'true' });
insuranceData.push({ "value": "否", "id": 'false' });
$("#insurance").combobox({
	editable:false,
    data:insuranceData,
	valueField:'id',
	textField:'value',
	onLoadSuccess:function(){$(this).combobox('setValue', '${asset.insurance }');}
});

$('#province').combobox({
	editable:false,
    url: WEB_APP + '/web/location/getProvinces',
    valueField:'id',
    textField:'name',
    onLoadSuccess:function(){
    	$(this).combobox('setValue', '${asset.provinceId }');
    	$('#city').combobox({
			editable:false,
		    url: WEB_APP + '/web/location/getCities?provinceId=${asset.provinceId }',
		    valueField:'id',   
		    textField:'name',
		    onLoadSuccess:function(){
		    	$(this).combobox('setValue', '${asset.cityId }');
		    }
		});
    },
    onSelect: function (record) {
    	$('#city').combobox({
			editable:false,
		    url: WEB_APP + '/web/location/getCities?provinceId='+record.id,
		    valueField:'id',   
		    textField:'name'
		});
    }
});

$("#brandId").combobox({
	editable:false,
    url: WEB_APP + '/web/asset/brand/json',
    valueField:'id',
    textField:'name',
    onLoadSuccess:function(){
    	$.post(WEB_APP+'/web/asset/type/json/get?assetTypeId=${asset.assetTypeId}',function(result){
    		$("#brandId").combobox('setValue', result.brandId);
    		$("#assetType").combobox({
    			editable:false,
    		    url: WEB_APP + '/web/asset/type/json?brandId='+result.brandId,
    		    valueField:'id',
    		    textField:'model',
    		    onLoadSuccess:function(){
    		    	$("#assetType").combobox('setValue', '${asset.assetTypeId}');
    		    }
    		});
		});
    },
    onSelect: function (record) {
    	$("#assetType").combobox({
			editable:false,
		    url: WEB_APP + '/web/asset/type/json?brandId='+record.id,
		    valueField:'id',
		    textField:'model'
		});
    }
});

</script>