<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
/*
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
		    textField:'name',
		    onLoadSuccess:function(){$(this).combobox('setValue', "${order.city}");}
		});
    },
    onLoadSuccess:function(){$(this).combobox('setValue', "${order.province}");}
});

$('#personProvince').combobox({
	editable:false,
    url: WEB_APP + '/web/location/getProvinces',
    valueField:'id',
    textField:'name',
    onSelect: function (record) {
    	$('#personCity').combobox({
			editable:false,
		    url: WEB_APP + '/web/location/getCities?provinceId='+record.id,
		    valueField:'id',   
		    textField:'name',
		    onLoadSuccess:function(){$(this).combobox('setValue', "${order.rentPersonInfo.personCity}");}
		});
    },
    onLoadSuccess:function(){$(this).combobox('setValue', "${order.rentPersonInfo.personProvince}");}
});

$('#companyProvince').combobox({
	editable:false,
    url: WEB_APP + '/web/location/getProvinces',
    valueField:'id',
    textField:'name',
    onSelect: function (record) {
    	$('#companyCity').combobox({
			editable:false,
		    url: WEB_APP + '/web/location/getCities?provinceId='+record.id,
		    valueField:'id',   
		    textField:'name',
		    onLoadSuccess:function(){$(this).combobox('setValue', "${order.rentCompanyInfo.companyCity}");}
		});
    },
    onLoadSuccess:function(){$(this).combobox('setValue', "${order.rentCompanyInfo.companyProvince}");}
});

$('#assetType').combobox({
	editable:false,
    url: WEB_APP + '/web/asset/type/json',
    valueField:'id',
    textField:'model'
});
*/
</script>


<form id="addForm">
	<div id="infopointAccordion" class="easyui-accordion"
		style="width: 100%;" data-options="multiple:true">
		<div title="订单信息" class="ibms_form_panel" data-options="selected:true">
			<div class="easyui-panel ibms_form_panel">
				<input id="id" name="id" value="${order.id}" type="hidden">
				<div class="ibms_form_default">
					<ul>
						<li><font>订单号：</font>
							<h1>
								<input disabled="disabled" name="code" value="${order.code}"
									type="text">
							</h1>
						</li>						
					
						<li><font>租赁方式：</font>
						<h1>
								<select id="rentType" name="rentType" class="easyui-combobox"
									data-options="required:true">
									<option value="0">分时租赁 </option>
									<option value="1" ${order.rentType=="1"?"Selected":""}>分月租赁</option>
								</select>
							</h1></li>
					</ul>
					<div class="ibms_clear"></div>
					<ul>					
						<li><font>省：</font>
						<h1>
								<input id="province" name="provinceId" type="text" disabled="disabled"
									value="${order.province}" data-options="required:true">
							</h1></li>
						<li><font>市：</font>
						<h1>
								<input id="city" name="cityId" type="text" disabled="disabled"
									value="${order.city}" data-options="required:true" >
							</h1></li>					

						<li><font>具体位置：</font>
						<h1>
								<input id="detailLocation" name="detailLocation" type="text" value="${order.detailLocation}"
									class="easyui-validatebox" data-options="required:true">
							</h1></li>
					</ul>
					<div class="ibms_clear"></div>
					<ul>
						<li><font>开始时间：</font>
							<h1>
								<input id="startDate" name="startDatetime" type="text"  value="${order.startDatetime}"
									class="easyui-datebox" data-options="required:true">
							</h1></li>
						<li><font>租期/月：</font>
							<h1>
								<input id="rentDate" name="rentDate" type="text" value="${order.rentDate}">
							</h1></li>
					</ul>
					<div class="ibms_clear"></div>
					<ul>
						<li><font>是否开票：</font>
							<h1>
								<select id="needInvoices" name="needInvoices" class="easyui-combobox" data-options="required:true">
									<option value="false">否</option>
									<option value="true">是</option>
								</select>
							</h1></li>
					</ul>
					<div class="ibms_clear"></div>
					<div class="ibms_form_default_textarea">
						<font>备注：</font>
						<h1>
							<textarea id="remark" name="remark" class="easyui-validatebox" >${order.remark}</textarea>
						</h1>
					</div>
				</div>
			</div>
		</div>
			
		<div title="租赁人" class="dcim_form_panel">
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
					<ul>
						<li id="rentPerson"><font>租赁方式：</font>
						<h1>
							<b>
								<div id="personTypeDiv">
									<input type="radio" name="rentPersonType" value="0" id="Radio1" checked="checked" />个人租赁
								</div> 
							</b>
								
							<b>
								<div id="companyTypeDiv">
									<input type="radio" name="rentPersonType" value="1" id="Radio2" />企业租赁
								</div>
							</b>
						</h1></li>
					</ul>
				</div>
			</div>
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
					<div class="ibms_clear"></div>
					<div id="personDiv">
						<ul>
							<li><font>申请人：</font>
							<h1>
									<input id="name" name="name" type="text" value="${order.rentPersonInfo.name}" >
								</h1></li>
							<li><font>手机号：</font>
							<h1>
									<input id="mobile" name="mobile" type="text" value="${order.rentPersonInfo.mobile}" >
								</h1></li>
							<li><font>电子邮箱：</font>
							<h1>
									<input id="email" name="email" type="text" value="${order.rentPersonInfo.email}" >
								</h1></li>
						</ul>
						<div class="ibms_clear"></div>
						<ul>
							<li><font>省：</font>
							<h1>
									<input id="personProvince" name="personProvinceId" type="text" disabled="disabled"
										value="${order.rentPersonInfo.personProvince}" >
								</h1></li>
							<li><font>市：</font>
							<h1>
									<input id="personCity" name="personCityId" type="text" disabled="disabled"
										value="${order.rentPersonInfo.personCity}" >
								</h1></li>
							<li><font>通讯地址：</font>
							<h1>
									<input id="address" name="address" type="text"  value="${order.rentPersonInfo.address}" >
								</h1></li>
							<li><font>邮政编码：</font>
							<h1>
									<input id="postcode" name="postcode" type="text" value="${order.rentPersonInfo.postcode}">
								</h1></li>
						</ul>
					</div>
					<div id="companyDiv">
						<ul>
							<li><font>企业名称：</font>
							<h1>
									<input id="name" name="name" type="text" value="${order.rentCompanyInfo.name}" >
								</h1></li>
							<li><font>省：</font>
							<h1>
									<input id="companyProvince" name="companyProvinceId" type="text" disabled="disabled"
										value="${order.rentCompanyInfo.companyProvince}" >
								</h1></li>
							<li><font>市：</font>
							<h1>
									<input id="companyCity" name="companyCityId" type="text"  disabled="disabled"
										value="${order.rentCompanyInfo.companyCity}">
								</h1></li>								
							<li><font>地址：</font>
							<h1>
									<input id="address" name="address" type="text" value="${order.detailLocation}">
								</h1></li>

							<li><font>通讯地址：</font>
							<h1>
									<input name="postalAddress" type="text" value="${order.rentCompanyInfo.postalAddress}">  
								</h1></li>
							<li><font>邮编：</font>
							<h1>
									<input name="postcode" type="text" value="${order.rentCompanyInfo.postcode}">
								</h1></li>
						</ul>
						
						<div class="ibms_clear"></div>
						<ul>
							<li><font>法人代表：</font>
							<h1>
									<input id="legalName" name="legalName" type="text" value="${order.rentCompanyInfo.legalName}">
								</h1></li>
							<li><font>联系电话：</font>
							<h1>
									<input id="legalMobile" name="legalMobile" type="text" value="${order.rentCompanyInfo.legalMobile}">
								</h1></li>
							<li><font>Email：</font>
							<h1>
									<input name="legalEmail" type="text" value="${order.rentCompanyInfo.legalEmail}">
								</h1></li>
						</ul>
						
						<div class="ibms_clear"></div>
						<ul>
							<li><font>实际控制人：</font>
								<h1>
									<input id="actualController" name="actualController"
										value="${order.rentCompanyInfo.actualController}" type="text">
								</h1></li>
							<li><font>控制人电话：</font>
								<h1>
									<input id="controllerMobile" name="controllerMobile"
										value="${order.rentCompanyInfo.controllerMobile}" type="text">
								</h1></li>
						</ul>						
					</div>
				</div>
			</div>
		</div>
		<div title="紧急联系人信息" class="dcim_form_panel">
			<div class="easyui-panel ibms_form_panel">
				<div  id="personEmergencyInfo" hidden="hidden" class="ibms_form_default">
					<div class="ibms_clear"></div>
					<ul>
						<li><font>紧急联系人：</font>
						<h1>
								<input id="emergencyContact" name="emergencyContact" type="text"
									class="easyui-validatebox"  value="${order.rentPersonInfo.emergencyContact}">
							</h1></li>
						<li><font>联系电话：</font>
						<h1>
								<input id="emergencyContactMobile" name="emergencyContactMobile"
									type="text" class="easyui-validatebox" value="${order.rentPersonInfo.emergencyContactMobile}">
							</h1></li>
						<li><font>关系：</font>
						<h1>
								<input id="relation" name="relation" type="text"
									class="easyui-validatebox" value="${order.rentPersonInfo.relation}" >
							</h1></li>
					</ul>
					<div class="ibms_clear"></div>
				</div>
				
				<div  id="companyEmergencyInfo" class="ibms_form_default">
					<div class="ibms_clear"></div>
					<ul>
						<li><font>紧急联系人：</font>
						<h1>
								<input id="emergencyContact" name="emergencyContact" type="text"
									class="easyui-validatebox" value="${order.rentCompanyInfo.emergencyContact}" >
							</h1></li>
						<li><font>联系电话：</font>
						<h1>
								<input id="emergencyContactMobile" name="emergencyContactMobile"
									type="text" class="easyui-validatebox" value="${order.rentCompanyInfo.emergencyContactMobile}">
							</h1></li>
						<li><font>关系：</font>
						<h1>
								<input id="relation" name="relation" type="text"
									class="easyui-validatebox" value="${order.rentCompanyInfo.relation}" >
							</h1></li>
					</ul>
					<div class="ibms_clear"></div>
				</div>				
			</div>
		</div>
	</div>
	<div class="ibms_form_btn">
		<a href="#" class="query_list_button auto-savebutton" id="modifyorderbutton">保存</a>
		<a href="#" class="query_list_button auto-resetbutton">重 置</a>
		<a href="#" class="query_list_button auto-savebutton" id="realSave" style="visibility:hidden">保存</a>
	</div>
</form>

<script type="text/javascript">

$("#modifyorderbutton").click(function(){
	var dataOption = parseOption(this);
	var alarmInfo = dataOption.alarmInfo || "确定要修改订单吗?";
	IBMS.messager.confirm('确认', alarmInfo, function(r){
		if(r){
			$("#realSave").attr("data-options","iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/order/modifyorder',autoclose:true");
			$("#realSave").click();
		}
	});
	
});	

if('${order.rentPersonType}'=='0'){
	$("#personEmergencyInfo").show();
	$("#companyEmergencyInfo").remove();
	$("#personDiv").show();
	$("#companyDiv").remove();
	
	$("#Radio1").attr("checked","checked");
	//$("#Radio2").attr("checked",false);
	$("#companyTypeDiv").remove();
}else{
	$("#personEmergencyInfo").remove();
	$("#companyEmergencyInfo").show();
	$("#personDiv").remove();
	$("#companyDiv").show();	
	
	$("#Radio2").attr("checked","checked");
	//$("#Radio1").attr("checked",false);	
	$("#personTypeDiv").remove();	
}
</script>