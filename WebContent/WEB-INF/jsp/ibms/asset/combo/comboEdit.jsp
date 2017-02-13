<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm" >
	<div class="easyui-panel ibms_form_panel">
		<input type="hidden" name="id" value="${combo.id }">
		<div class="ibms_form_default">
	        <ul>
	        	<li><font>租赁类型：</font><h1><input id="rentTypeEdit" name="rentType" type="text" class="easyui-combobox"></h1></li>
	        	<li><font>明星类型：</font><h1><select id="starProduct" name="starProduct" class="easyui-combobox"></select></h1></li>
	         	<li><font>品牌：</font><h1><input id="brand" name="brand"  type="text" class="easyui-combobox"></h1></li>
	           <li><font>型号：</font><h1><input id="assetType" name="assetTypeId"  type="text" class="easyui-combobox"></h1></li>
	           <li><font>名称：</font><h1><input name="name"  type="text" value="${combo.name }"></h1></li>
	           <li><font>费率：</font><h1><input name="amount"  type="text" value="${combo.amount }"></h1></li>
	           <li><font>最小时长：</font><h1><input name="minimumUseTime"  type="text" value="${combo.minimumUseTime }"></h1></li>
	        </ul>
        </div>
        <div class="ibms_clear"></div>
	    <div class="ibms_form_default_textarea">
        	<font>计费规则：</font><h1><textarea name="amountRule">${combo.amountRule }</textarea></h1>
        </div>
        <div class="ibms_clear"></div>
	    <div class="ibms_form_default_textarea">
        	<font>注意事项：</font><h1><textarea name="notice">${combo.notice }</textarea></h1>
        </div>
        <div class="ibms_clear"></div>
	    <div class="ibms_form_btn">
	        <a href="#" class="query_list_button auto-savebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/asset/combo/update',autoclose:true">保存</a>
	        <a href="#" class="query_list_button auto-resetbutton">重 置</a>
        </div>
	</div>
</form>

<script type="text/javascript">
var rentTypeData = [];
rentTypeData.push({ "value": "分时租赁", "id": "0" });
rentTypeData.push({ "value": "分月租赁", "id": "1" });
$("#rentTypeEdit").combobox({
	editable:false,
    data:rentTypeData,
	valueField:'id',
	textField:'value',
	onLoadSuccess:function(){$(this).combobox('setValue', '${combo.rentType }');}
});


var starProductData = [];
starProductData.push({ "value": "是", "id": "true" });
starProductData.push({ "value": "否", "id": "false" });
$("#starProduct").combobox({
	editable:false,
    data:starProductData,
	valueField:'id',
	textField:'value',
	onLoadSuccess:function(){$(this).combobox('setValue', '${combo.starProduct }');}
});

$("#brand").combobox({
	editable:false,
    url: WEB_APP + '/web/asset/brand/json',
    valueField:'id',
    textField:'name',
    onLoadSuccess:function(){
    	$.post(WEB_APP+'/web/asset/type/json/get?assetTypeId=${combo.assetTypeId}',function(result){
    		$("#brand").combobox('setValue', result.brandId);
    		$("#assetType").combobox({
    			editable:false,
    		    url: WEB_APP + '/web/asset/type/json?brandId='+result.brandId,
    		    valueField:'id',
    		    textField:'model',
    		    onLoadSuccess:function(){
    		    	$("#assetType").combobox('setValue', '${combo.assetTypeId}');
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