<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm" >
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
	        <ul>
	        	<li><font>租赁类型：</font><h1>
		           <select id="rentType" name="rentType" class="easyui-combobox">
		           		<option value="0">分时租赁</option>
		           		<option value="1">分月租赁</option>
		           </select>
	           </h1></li>
	           <li><font>明星类型：</font><h1><select id="starProduct" name="starProduct" class="easyui-combobox">
	           		<option value="false">否</option>
		           	<option value="true">是</option>
	           </select>
	           </h1></li>
	        	<li><font>品牌：</font><h1><input id="brand" name="brand"  type="text" class="easyui-combobox"></h1></li>
	           <li><font>型号：</font><h1><input id="assetType" name="assetTypeId"  type="text" class="easyui-combobox"></h1></li>
	           <li><font>名称：</font><h1><input name="name"  type="text" ></h1></li>
	           <li><font>费率：</font><h1><input name="amount"  type="text" ></h1></li>
	           <li><font>最小时长：</font><h1><input name="minimumUseTime"  type="text" ></h1></li>
	        </ul>
        </div>
        <div class="ibms_clear"></div>
	    <div class="ibms_form_default_textarea">
        	<font>计费规则：</font><h1><textarea id="ck_editor" name="amountRule"></textarea></h1>
        </div>
        <div class="ibms_clear"></div>
	    <div class="ibms_form_default_textarea">
        	<font>注意事项：</font><h1><textarea name="notice"></textarea></h1>
        </div>
        <div class="ibms_clear"></div>
	    <div class="ibms_form_btn">
	        <a href="#" class="query_list_button auto-savebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/asset/combo/save',autoclose:true">保存</a>
	        <a href="#" class="query_list_button auto-resetbutton">重 置</a>
        </div>
	</div>
</form>

<script type="text/javascript">
$("#brand").combobox({
	editable:false,
    url: WEB_APP + '/web/asset/brand/json',
    valueField:'id',
    textField:'name',
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