<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm" >
	<div id="infopointAccordion" class="easyui-accordion" style="width:100%;" data-options="multiple:true">
		<div title="订单信息" class="ibms_form_panel" data-options="selected:true">
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
			        <ul>
			           <li><font>租赁方式：</font><h1>
				           <select id="rentType" name="rentType" class="easyui-combobox" data-options="required:true">
				           	<option value="0">分时租赁</option>
				           	<option value="1">分月租赁</option>
				           </select>
			           </h1></li>
			        </ul>
			        <div class="ibms_clear"></div>
			        <ul>
			           <li><font>省：</font><h1><input id="province" name="provinceId" type="text" class="easyui-combobox" data-options="required:true"></h1></li>
			           <li><font>市：</font><h1><input id="city" name="cityId" type="text" class="easyui-combobox" data-options="required:true"></h1></li>
			           <li><font>具体位置：</font><h1><input id="detailLocation" name="detailLocation" type="text" class="easyui-validatebox" data-options="required:true"></h1></li>
			        </ul>
			        <div class="ibms_clear"></div>
			        <ul>
			           <li><font>开始时间：</font>
							<h1>
								<input id="startDate" name="startDatetime" type="text" class="easyui-datebox" data-options="required:true">
							</h1></li>
						<li><font>租期/月：</font>
							<h1>
							<input id="rentDate" name="rentDate" type="text">
						</h1>
						</li>
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
			        	<h1><textarea id="remark" name="remark" class="easyui-validatebox" data-options="required:true"></textarea></h1>
			        </div>   
		        </div>
			</div>
		</div>
		<div title="租赁信息" class="dcim_form_panel" id="assets">
			<div class="ibms_form_default">
				<ul>
			        <li>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
					</li>
				</ul>
			</div>	
<!-- 				<div class="ibms_form_default"> -->
<!-- 					<ul> -->
<!-- 						<li id="asset_0"><font>设备类型：</font><h1><input id="assetType" name="assetTypeId"  type="text" class="easyui-combobox"></h1></li> -->
<!-- 						<li id="asset_0"><font>数量：</font><h1><input id="assetType" name="assetTypeId"  type="text"></h1></li> -->
<!-- 						<li id="asset_0"><font>套餐：</font><h1><input id="assetType" name="assetTypeId"  type="text" class="easyui-combobox"></h1></li> -->
<!-- 						<li id="asset_0"><font>押金：</font><h1><input id="assetType" name="assetTypeId"  type="text"></h1></li> -->
<!-- 						<li id="asset_0"><font>净值：</font><h1><input id="assetType" name="assetTypeId"  type="text"></h1></li> -->
<!-- 			        </ul>	 -->
<!-- 				</div> -->
		</div>
		<script type="text/javascript">
			var count = 0;
			function append(){	
				$("#assets").append(
						'<div class="ibms_clear"></div>'+
						'<div class="easyui-panel ibms_form_panel">'+
						'	<div class="ibms_form_default" id="asset_'+count+'">'+
						'		<ul>'+
						'			<li><font>品牌：</font><h1><input id="brand_'+count+'" name="brand_'+count+'" type="text" class="easyui-combobox" data-options="required:true"></h1></li>'+
						'			<li><font>型号：</font><h1><input id="assetType_'+count+'" name="assetType_'+count+'" type="text" class="easyui-combobox" data-options="required:true"></h1></li>'+
						'			<li><font>数量：</font><h1><input id="count_'+count+'" name="count_'+count+'" type="text" class="easyui-validatebox" data-options="required:true"></h1></li>'+
						'			<li><font>套餐：</font><h1><input id="combo_'+count+'" name="combo_'+count+'" type="text" class="easyui-combobox" data-options="required:true"></h1></li>'+
						'			<li><font>押金：</font><h1><input id="deposit_'+count+'" name="deposit_'+count+'" type="text" readonly="true"></h1></li>'+
						'			<li><font>净值：</font><h1><input id="amount_'+count+'" name="amount_'+count+'" type="text" readonly="true"></h1></li>'+
						'			<li><font></font><h1><a href="#" class="easyui-linkbutton remove_asset" data-options="iconCls:\'icon-remove\',plain:true">删除</a></h1></li>'+
				        '		</ul>'+
						'	</div>'+
						'</div>');
				$.parser.parse("#asset_"+count);
				var assetType_index ='#assetType_'+count;
				var combo_index ='#combo_'+count;
				var brand_index ='#brand_'+count;
				var count_index ='#count_'+count;
				var deposit_index ='#deposit_'+count;
				var amount_index ='#amount_'+count;
				$(brand_index).combobox({
					editable:false,
				    url: WEB_APP + '/web/asset/brand/add/json?rentType='+$("#rentType").combobox('getValue'),
				    valueField:'id',
				    textField:'name',
				    onSelect: function (record) {
				    	$(assetType_index).combobox({
							editable:false,
						    url: WEB_APP + '/web/asset/type/json?brandId='+record.id+'&rentType='+$("#rentType").combobox('getValue'),
						    valueField:'id',
						    textField:'model',
						    onSelect: function (record) {
						    	$(combo_index).combobox({
									editable:false,
								    url: WEB_APP + '/web/asset/combo/getByAssetTypeId?assetTypeId='+record.id+'&rentType='+$("#rentType").combobox('getValue'),
								    valueField:'id',
								    textField:'name'
								});
						    }
						});
				    }
				});
				$(count_index).change(function(){
					console.log($(assetType_index).combobox('getValue'));
					$.post(WEB_APP+'/web/asset/type/json/get?assetTypeId='+$(assetType_index).combobox('getValue'),function(result){
						$(deposit_index).val(result.deposit * $(count_index).val());
						$(amount_index).val(result.amount * $(count_index).val());
					});
				});
// 				$(assetType_index).combobox({
// 					editable:false,
// 				    url: WEB_APP + '/web/asset/type/json',
// 				    valueField:'id',
// 				    textField:'model',
// 				    onSelect: function (record) {
// 				    	$(combo_index).combobox({
// 							editable:false,
// 						    url: WEB_APP + '/web/asset/combo/getByAssetTypeId?assetTypeId='+record.id,
// 						    valueField:'id',
// 						    textField:'name'
// 						});
// 				    }
// 				});
				
				count = count+1;
				$(".remove_asset").click(function(){
					$(this).parents('.easyui-panel').remove();
				})
			}
		</script>
		<div title="租赁人" class="dcim_form_panel" >
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
					<ul>
			           <li id="rentPerson"><font>租赁方式：</font><h1>
				           <b><input type="radio"  name="rentPersonType" value="0" id="Radio1" checked="checked"/>个人租赁</b>
		                   <b><input type="radio"  name="rentPersonType" value="1" id="Radio2"/>企业租赁</b>
			           </h1></li>
			        </ul>
			        <script type="text/javascript">
			        	$("#rentPerson input:radio[name='rentPersonType']").change(function(){
			        		var val = $("#rentPerson input:radio[name='rentPersonType']:checked").val();
			        		console.log(val);
			        		if(val=='0'){
			        			$("#companyDiv").hide();
			        			$("#personDiv").show();
			        		}else{
			        			$("#personDiv").hide();
			        			$("#companyDiv").show();
			        		}
			        	});
			        	$("#personDiv").show();
	        			$("#companyDiv").hide();
			        </script>
				</div>
			</div>
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
			        <div class="ibms_clear"></div>
			        <div id="personDiv">
				        <ul>
				           <li><font>申请人：</font><h1><input id="name" name="name"  type="text"></h1></li>
				           <li><font>手机号：</font><h1><input id="mobile" name="mobile"  type="text"></h1></li>
				           <li><font>电子邮箱：</font><h1><input id="email" name="email"  type="text"></h1></li>
				        </ul>
			        	<div class="ibms_clear"></div>
				        <ul>
				           <li><font>省：</font><h1><input id="personProvince" name="personProvinceId"  type="text" class="easyui-combobox"></h1></li>
				           <li><font>市：</font><h1><input id="personCity" name="personCityId"  type="text" class="easyui-combobox"></h1></li>
				           <li><font>通讯地址：</font><h1><input id="address" name="address"  type="text" ></h1></li>
				           <li><font>邮政编码：</font><h1><input id="postcode" name="postcode"  type="text" ></h1></li>
					    </ul>
					    <div class="ibms_clear"></div>
					    <ul>
				        	<li><font>身份证正面：</font>
				           		<h1>
									<b>
										<input type="file" name="idCardFrontImgSelected" id="idCardFrontImgSelected" accept="image/gif, image/jpeg, image/bmp, image/png"/>
										<input type="hidden" name="idCardFrontImg" id="idCardFrontImg" value=""/>
									</b>
									<b>
										<input id="upload" type="button" class="query_list_button" value="上传" onclick="javascript:uploadFiles_framework('idCardFrontImgSelected','idCardFrontImg','idCardFrontImgPreview');">
									</b>
								</h1>
							</li>
				        </ul>
				        <div class="ibms_clear"></div>
				        <div class="ibms_form_default_textarea">
					        <font>身份证正面预览：</font>
					        <h1 style="width:90%;">
								<b>
									<img width="300" height="200" src="" id="idCardFrontImgPreview" name="idCardFrontImgPreview"/>
								</b>
							</h1>
						</div>
						<ul>
				        	<li><font>身份证反面：</font>
				           		<h1>
									<b>
										<input type="file" name="idCardBackImgSelected" id="idCardBackImgSelected" accept="image/gif, image/jpeg, image/bmp, image/png"/>
										<input type="hidden" name="idCardBackImg" id="idCardBackImg" value=""/>
									</b>
									<b>
										<input id="upload" type="button" class="query_list_button" value="上传" onclick="javascript:uploadFiles_framework('idCardBackImgSelected','idCardBackImg','idCardBackImgPreview');">
									</b>
								</h1>
							</li>
				        </ul>
				        <div class="ibms_clear"></div>
				        <div class="ibms_form_default_textarea">
					        <font>身份证反面预览：</font>
					        <h1 style="width:90%;">
								<b>
									<img width="300" height="200" src="" id="idCardBackImgPreview" name="idCardBackImgPreview"/>
								</b>
							</h1>
						</div>
						<ul>
				        	<li><font>身份证手持：</font>
				           		<h1>
									<b>
										<input type="file" name="idCardHandImgSelected" id="idCardHandImgSelected" accept="image/gif, image/jpeg, image/bmp, image/png"/>
										<input type="hidden" name="idCardHandImg" id="idCardHandImg" value=""/>
									</b>
									<b>
										<input id="upload" type="button" class="query_list_button" value="上传" onclick="javascript:uploadFiles_framework('idCardHandImgSelected','idCardHandImg','idCardHandImgPreview');">
									</b>
								</h1>
							</li>
				        </ul>
				        <div class="ibms_clear"></div>
				        <div class="ibms_form_default_textarea">
					        <font>身份证手持预览：</font>
					        <h1 style="width:90%;">
								<b>
									<img width="300" height="200" src="" id="idCardHandImgPreview" name="idCardHandImgPreview"/>
								</b>
							</h1>
						</div>
			        </div>
			        <div id="companyDiv">
				        <ul>
				           <li><font>企业名称：</font><h1><input id="name" name="name"  type="text"></h1></li>
				           <li><font>地址：</font><h1><input id="address" name="address"  type="text"></h1></li>
				           <li><font>省：</font><h1><input id="companyProvince" name="companyProvinceId"  type="text" class="easyui-combobox"></h1></li>
				           <li><font>市：</font><h1><input id="companyCity" name="companyCityId"  type="text" class="easyui-combobox"></h1></li>
				           <li><font>通讯地址：</font><h1><input name="postalAddress"  type="text" ></h1></li>
				           <li><font>邮编：</font><h1><input name="postcode"  type="text" ></h1></li>
				        </ul>
				        <div class="ibms_clear"></div>
				        <ul>
				        	<li><font>营业执照：</font>
				           		<h1>
									<b>
										<input type="file" name="businessLicensePathSelected" id="businessLicensePathSelected" accept="image/gif, image/jpeg, image/bmp, image/png"/>
										<input type="hidden" name="businessLicensePath" id="businessLicensePath" value=""/>
									</b>
									<b>
										<input id="upload" type="button" class="query_list_button" value="上传" onclick="javascript:uploadFiles_framework('businessLicensePathSelected','businessLicensePath','businessLicensePathPreview');">
									</b>
								</h1>
							</li>
				        </ul>
				        <div class="ibms_clear"></div>
				        <div class="ibms_form_default_textarea">
					        <font>营业执照：</font>
					        <h1 style="width:90%;">
								<b>
									<img width="300" height="200" src="" id="businessLicensePathPreview" name="businessLicensePathPreview"/>
								</b>
							</h1>
						</div>
				        <div class="ibms_clear"></div>
				        <ul>
				        	<li><font>纳税登记表：</font>
				           		<h1>
									<b>
										<input type="file" name="lastYearApplicationFormPathSelected" id="lastYearApplicationFormPathSelected" accept="image/gif, image/jpeg, image/bmp, image/png"/>
										<input type="hidden" name="lastYearApplicationFormPath" id="lastYearApplicationFormPath" value=""/>
									</b>
									<b>
										<input id="upload" type="button" class="query_list_button" value="上传" onclick="javascript:uploadFiles_framework('lastYearApplicationFormPathSelected','lastYearApplicationFormPath','lastYearApplicationFormPathPreview');">
									</b>
								</h1>
							</li>
				        </ul>
				        <div class="ibms_clear"></div>
				        <div class="ibms_form_default_textarea">
					        <font>纳税登记表：</font>
					        <h1 style="width:90%;">
								<b>
									<img width="300" height="200" src="" id="lastYearApplicationFormPathPreview" name="lastYearApplicationFormPathPreview"/>
								</b>
							</h1>
						</div>
				        <div class="ibms_clear"></div>
				        <ul>
				           <li><font>法人代表：</font><h1><input id="legalName" name="legalName"  type="text" ></h1></li>
				           <li><font>联系电话：</font><h1><input id="legalMobile" name="legalMobile"  type="text" ></h1></li>
				           <li><font>Email：</font><h1><input name="legalEmail"  type="text" ></h1></li>
				        </ul>
				        <div class="ibms_clear"></div>
				        <ul>
				        	<li><font>身份证正面：</font>
				           		<h1>
									<b>
										<input type="file" name="c_idCardFrontImgSelected" id="c_idCardFrontImgSelected" accept="image/gif, image/jpeg, image/bmp, image/png"/>
										<input type="hidden" name="c_idCardFrontImg" id="c_idCardFrontImg" value=""/>
									</b>
									<b>
										<input id="upload" type="button" class="query_list_button" value="上传" onclick="javascript:uploadFiles_framework('c_idCardFrontImgSelected','c_idCardFrontImg','c_idCardFrontImgPreview');">
									</b>
								</h1>
							</li>
				        </ul>
				        <div class="ibms_clear"></div>
				        <div class="ibms_form_default_textarea">
					        <font>身份证正面预览：</font>
					        <h1 style="width:90%;">
								<b>
									<img width="300" height="200" src="" id="c_idCardFrontImgPreview" name="c_idCardFrontImgPreview"/>
								</b>
							</h1>
						</div>
						<ul>
				        	<li><font>身份证反面：</font>
				           		<h1>
									<b>
										<input type="file" name="c_idCardBackImgSelected" id="c_idCardBackImgSelected" accept="image/gif, image/jpeg, image/bmp, image/png"/>
										<input type="hidden" name="c_idCardBackImg" id="c_idCardBackImg" value=""/>
									</b>
									<b>
										<input id="upload" type="button" class="query_list_button" value="上传" onclick="javascript:uploadFiles_framework('c_idCardBackImgSelected','c_idCardBackImg','c_idCardBackImgPreview');">
									</b>
								</h1>
							</li>
				        </ul>
				        <div class="ibms_clear"></div>
				        <div class="ibms_form_default_textarea">
					        <font>身份证反面预览：</font>
					        <h1 style="width:90%;">
								<b>
									<img width="300" height="200" src="" id="c_idCardBackImgPreview" name="c_idCardBackImgPreview"/>
								</b>
							</h1>
						</div>
						<ul>
				        	<li><font>身份证手持：</font>
				           		<h1>
									<b>
										<input type="file" name="c_idCardHandImgSelected" id="c_idCardHandImgSelected" accept="image/gif, image/jpeg, image/bmp, image/png"/>
										<input type="hidden" name="c_idCardHandImg" id="c_idCardHandImg" value=""/>
									</b>
									<b>
										<input id="upload" type="button" class="query_list_button" value="上传" onclick="javascript:uploadFiles_framework('c_idCardHandImgSelected','c_idCardHandImg','c_idCardHandImgPreview');">
									</b>
								</h1>
							</li>
				        </ul>
				        <div class="ibms_clear"></div>
				        <div class="ibms_form_default_textarea">
					        <font>身份证手持预览：</font>
					        <h1 style="width:90%;">
								<b>
									<img width="300" height="200" src="" id="c_idCardHandImgPreview" name="c_idCardHandImgPreview"/>
								</b>
							</h1>
						</div>
			        </div>
		        </div>
			</div>
		</div>
		<div title="紧急联系人信息" class="dcim_form_panel" >
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
			        <div class="ibms_clear"></div>
			        <ul>
			           <li><font>紧急联系人：</font><h1><input id="emergencyContact" name="emergencyContact"  type="text" class="easyui-validatebox" data-options="required:true"></h1></li>
			           <li><font>联系电话：</font><h1><input id="emergencyContactMobile" name="emergencyContactMobile"  type="text" class="easyui-validatebox" data-options="required:true"></h1></li>
			           <li><font>关系：</font><h1><input id="relation" name="relation"  type="text" class="easyui-validatebox" data-options="required:true"></h1></li>
			        </ul>
		        	<div class="ibms_clear"></div>
		        </div>
			</div>
		</div>
	</div>
	<div class="ibms_form_btn">
        <a href="#" class="query_list_button auto-savebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/order/save',autoclose:true">保存</a>
        <a href="#" class="query_list_button auto-resetbutton">重 置</a>
    </div>
</form>

<script type="text/javascript">
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
		    textField:'name'
		});
    }
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
		    textField:'name'
		});
    }
});

$('#assetType').combobox({
	editable:false,
    url: WEB_APP + '/web/asset/type/json',
    valueField:'id',
    textField:'model'
});

</script>