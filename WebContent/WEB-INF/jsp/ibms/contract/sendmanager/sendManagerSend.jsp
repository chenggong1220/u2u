<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
$('#brandIdSearch').combobox({
	editable:false,
    url: WEB_APP + '/web/asset/brand/json',
    valueField:'id',
    textField:'name',
    onSelect: function (record) {
    	$('#assetTypeIdSearch').combobox({
    		editable:false,
    	    url: WEB_APP + '/web/asset/type/json?brandId=' + record.id,
    	    valueField:'id',
    	    textField:'model'
    	});
    }
});

//Start: Load Shop Info, SUNZHE, 2017-06-19
$('#assetShopSearch').combobox({
	editable:false,
    url: WEB_APP + '/web/shop/json',
    valueField:'id',
    textField:'name'
});
//Start: Load Shop Info, SUNZHE, 2017-06-19


var assetStatus = [];
assetStatus.push({ "value": "已租", "id": '0' });
assetStatus.push({ "value": "空闲", "id": '1' });
assetStatus.push({ "value": "检修", "id": '2' });

$("#assetStatus").combobox({
	editable:false,
    data:assetStatus,
	valueField:'id',
	textField:'value',
	onLoadSuccess:function(){
		//$(this).combobox('setValue', '${asset.rent }');
		$(this).combobox('select', 1);
	}
});


function assetSendSave(){
	//var contraceCode = $("#contractCode").val();
	var contractId = $("#contractId").val();
	var selectDT = $("#selectDate").val();
	
	var assetIDs = [];
	var rows = $('#querygrid').datagrid('getSelections');
	//alert("Selected Rows: " + rows.length);	
	for(var i=0; i<rows.length; i++){
		assetIDs.push(rows[i].id);
	}
	//alert(assetIDs.length);	
	
	$.ajax({
		url : '${pageContext.request.contextPath}/web/send/manager/send/save',
		type : 'post',
		dataType : 'json',
		data : {"ids":assetIDs,"contractId":contractId,"selectDate":selectDT},
		success : function(data){
			alert("Successed!");
		}
	});

}

function assetQuery(){
	//alert("Asset Query");
	var param = {};
	var a = $("form").serializeArray();
	$.each(a, function(i,val){   
		param[val.name] = val.value;
		//alert(val.name + "---" + val.value);
	  }); 

	if(!$("form").form('validate')){
		return;
	}
	$("#sendAssetList").datagrid('load', param); 
};

</script>

<form id="assetSendForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default" width="98%">
			<input type="hidden" id="contractId" name="contractId"
				value="${contract.id }">
			<ul>
				<li><font>合同号：</font>
				<h1>
						<input name="contractCode" id="contractCode" type="text"
							value="${contract.contractId }" disabled="disabled">
					</h1></li>
				<li><font>发货时间：</font>
				<h1>
						<input name="selectDate" type="text"
							value="${contract.sendDateView }" class="easyui-datebox"
							data-options="required:true">
					</h1></li>	
					
				<li>							
					<div class="ibms_form_btn">
						<a href="#" class="query_list_button auto-tablesavebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/send/manager/send/save',autoclose:true">保存</a>
					</div>
				</li>
			</ul>

		</div>
	</div>	
	
	
	<div class="easyui-panel ibms_form_panel" data-options="iconCls:'icon-search',collapsible:true" title="租赁物查询" width="98%">
		<div class="ibms_form_default">
			<ul>
				<li><font>品牌：</font>
					<h1>
						<input id="brandIdSearch" name="brandId" type="text"
							class="easyui-combobox">
					</h1>
				</li>
				<li><font>设备类型：</font>
					<h1>
						<input id="assetTypeIdSearch" name="assetTypeId" type="text"
							class="easyui-combobox">
					</h1>
				</li>
				<li><font>设备编码：</font>
					<h1>
						<input id="assetCodeSearch" name="assetCode" type="text" >
					</h1>
				</li>
				<li><font>设备状态：</font>
					<h1>
						<input id="assetStatus" name="rent" type="text" >
					</h1>
				</li>				
				<li><font>设备所在地：</font>
					<h1>
						<input id="assetLocationSearch" name="assetLocation" type="text" >
					</h1>
				</li>	
				<li><font>所属分享店：</font>
					<h1>
						<input id="assetShopSearch" name="assetShopId" type="text" 
							class="easyui-combobox">
					</h1>
				</li>													
			</ul>
		</div>
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="query_list_button" id="assetQueryBtn" onclick="assetQuery()">查 询</a> 			
			<a href="#" class="auto-resetbutton query_list_button">重 置</a>	
		</div>		
	</div>		
		
	<div class="ibms_clear"></div>
	<table id="sendAssetList" class="easyui-datagrid" title="租赁物列表"
		data-options="
					iconCls: 'icon-edit',
					singleSelect: true,
					toolbar: '#tb_sendManager',
					pagination:true,
					rownumbers:true,
					fitColumns: true,
					singleSelect:true,
					url: '${pageContext.request.contextPath}/web/asset/assetList?rent=1',
					pageSize:5,
					pageList: [3,5,10],
					collapsible:true,
					method: 'post'
				">
		<thead>
			<tr>
				<th data-options="field:'id',checkbox:true">序列号</th>
				<th
					data-options="field:'brand',width:'10%',editor:'text',formatter:function(value,rec){return rec.assetType.brand;}">设备品牌</th>
				<th
					data-options="field:'model',width:'10%',editor:'text',formatter:function(value,rec){return rec.assetType.model;}">设备型号</th>
				<th data-options="field:'code',width:'10%',editor:'text'">设备编号</th>
				<th
					data-options="field:'rent',width:'10%',editor:'text',formatter:
						function(value,rec){
							if(value==2){
								return '检修';
							}else if(value==0){
								return '已租';
							}else{
								return '空闲';
							}
						}">状态</th>	
										
				<th data-options="field:'assetLocation',width:'20%'">所在地</th>
				<th data-options="field:'shop.name',width:'20%'">分享店</th>
				<th data-options="field:'createTime',width:'20%'">创建时间</th>			
			</tr>
		</thead>
	</table>
	
	<div class="ibms_clear"></div>		

</form>
