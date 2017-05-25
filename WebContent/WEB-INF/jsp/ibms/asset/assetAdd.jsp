<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
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
						<input name="code" type="text">
					</h1></li>
				<li><font>使用时长：</font>
				<h1>
						<input name="useTime" type="text">
					</h1></li>
				<li><font>出租状态：</font>
				<h1>
						<select name="rent" class="easyui-combobox"
							data-options="editable:false">
							<option value="1">空闲</option>
							<option value="0">已租</option>
							<option value="2">检修</option>
						</select>
					</h1></li>
				<li><font>所在位置：</font>
				<h1>
						<select name="location" class="easyui-combobox"
							data-options="editable:false">
							<option value="沈阳">沈阳</option>
							<option value="客户端">客户端</option>
							<option value="宁波配送中心">宁波配送中心</option>
							<option value="常州机电展厅">常州机电展厅</option>
							<option value="洛阳亚广4S店">洛阳亚广4S店</option>
							<option value="石家庄配送中心">石家庄配送中心</option>
							<option value="深圳鹏创4s店">深圳鹏创4s店</option>
							<option value="寄存南方机床">寄存南方机床</option>
							<option value="U2U">U2U</option>
							<option value="厂商">厂商</option>
						</select>
					</h1></li>
				<li><font>加密狗：</font>
				<h1>
						<input name="softDog" type="text">
					</h1></li>
				<li><font>是否保险：</font>
				<h1>
						<select name="insurance" class="easyui-combobox"
							data-options="editable:false">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
					</h1></li>
				<li><font>保险单号：</font>
				<h1>
						<input name="insuranceId" type="text">
					</h1></li>
				<li><font>保险金额：</font>
				<h1>
						<input name="insuranceAmount" type="text">
					</h1></li>
			</ul>
		</div>
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="query_list_button auto-savebutton"
				data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/asset/save',autoclose:true">保存</a>
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

$('#province').combobox({
	editable:false,
    url: WEB_APP + '/web/location/getProvinces',
    valueField:'id',
    textField:'name',
    onSelect: function (record) {
    	$('#city').combobox({
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
    	$('#assetType').combobox({
    		editable:false,
    	    url: WEB_APP + '/web/asset/type/json?brandId=' + record.id,
    	    valueField:'id',
    	    textField:'model'
    	});
    }
})
</script>