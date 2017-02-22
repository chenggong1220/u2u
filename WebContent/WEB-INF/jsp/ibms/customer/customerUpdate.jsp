<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

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
</script>

<form id="updateForm">
	<div id="infopointAccordion" class="easyui-accordion"
		style="width: 100%;" data-options="multiple:true">
		<div title="客户信息" class="dcim_form_panel">
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
					<div class="ibms_clear"></div>
					<div id="personDiv">
						<ul>
							<li><input name="id" value="${customer.rentPersonInfo.id}"
								type="hidden"> <font>客户姓名：</font>
								<h1>
									<input name="name" value="${customer.rentPersonInfo.name}"
										type="text">
								</h1></li>
							<li><font>客户类型：</font>
								<h1>
									<input name="rentType" disabled="disabled"
										value="${customer.custType==0?'个人':'企业'}" type="text">
								</h1> <!--  							
									<select id="feedStatus" name="feedStatus"
											class="easyui-combobox">
											<option value="0" ${customer.custType==0?'selected':''}>个人</option>
											<option value="1" ${customer.custType==1?'selected':''}>企业</option>
									</select>
--></li>
							<li><font>手机号：</font>
							<h1>
									<input id="mobile" name="mobile"
										value="${customer.rentPersonInfo.mobile}" type="text">
								</h1></li>
							<li><font>电子邮箱：</font>
							<h1>
									<input id="email" name="email"
										value="${customer.rentPersonInfo.email}" type="text">
								</h1></li>
						</ul>
						<div class="ibms_clear"></div>
						<ul>
							<li><font>省：</font>
							<h1>
									<input id="personProvince" name="personProvinceId"
										value="${customer.rentPersonInfo.personProvinceId}"
										type="text" class="easyui-combobox">
								</h1></li>
							<li><font>市：</font>
							<h1>
									<input id="personCity" name="personCityId"
										value="${customer.rentPersonInfo.personCityId}" type="text"
										class="easyui-combobox">
								</h1></li>
							<li><font>邮政编码：</font>
							<h1>
									<input id="postcode" name="postcode"
										value="${customer.rentPersonInfo.postcode}" type="text">
								</h1></li>
						</ul>
						<div class="ibms_clear"></div>
						<div class="ibms_form_default_textarea">
							<font>通讯地址：</font>
							<h1>
								<textarea id="address" name="address">${customer.rentPersonInfo.address}</textarea>
							</h1>
						</div>

						<div class="ibms_clear"></div>
						<ul>
							<li><font>身份证正面：</font>
								<h1>
									<b> <input type="file" name="idCardFrontImgSelected"
										id="idCardFrontImgSelected"
										accept="image/gif, image/jpeg, image/bmp, image/png" /> <input
										type="hidden" name="idCardFrontImg" id="idCardFrontImg"
										value="" />
									</b> <b> <input id="upload" type="button"
										class="query_list_button" value="上传"
										onclick="javascript:uploadFiles_framework('idCardFrontImgSelected','idCardFrontImg','idCardFrontImgPreview');">
									</b>
								</h1></li>
						</ul>
						<div class="ibms_clear"></div>
						<div class="ibms_form_default_textarea">
							<font>身份证正面预览：</font>
							<h1 style="width: 90%;">
								<h1 style="width: 90%;">
									<b> <a class="POPUP_A" href="javascript:void(0);"><img
											width="300" height="200"
											src="${pageContext.request.contextPath}${customer.rentPersonInfo.idCardFrontImg}"
											id="idCardFrontImgPreview" name="idCardFrontImgPreview" /></a>
									</b>
								</h1>
							</h1>
						</div>
						<ul>
							<li><font>身份证反面：</font>
								<h1>
									<b> <input type="file" name="idCardBackImgSelected"
										id="idCardBackImgSelected"
										accept="image/gif, image/jpeg, image/bmp, image/png" /> <input
										type="hidden" name="idCardBackImg" id="idCardBackImg" value="" />
									</b> <b> <input id="upload" type="button"
										class="query_list_button" value="上传"
										onclick="javascript:uploadFiles_framework('idCardBackImgSelected','idCardBackImg','idCardBackImgPreview');">
									</b>
								</h1></li>
						</ul>
						<div class="ibms_clear"></div>
						<div class="ibms_form_default_textarea">
							<font>身份证反面预览：</font>
							<h1 style="width: 90%;">
								<b> <a class="POPUP_A" href="javascript:void(0);"><img
										width="300" height="200"
										src="${pageContext.request.contextPath}${customer.rentPersonInfo.idCardBackImg}"
										id="idCardBackImgPreview" name="idCardBackImgPreview" /></a>
								</b>
							</h1>
						</div>
						<ul>
							<li><font>身份证手持：</font>
								<h1>
									<b> <input type="file" name="idCardHandImgSelected"
										id="idCardHandImgSelected"
										accept="image/gif, image/jpeg, image/bmp, image/png" /> <input
										type="hidden" name="idCardHandImg" id="idCardHandImg" value="" />
									</b> <b> <input id="upload" type="button"
										class="query_list_button" value="上传"
										onclick="javascript:uploadFiles_framework('idCardHandImgSelected','idCardHandImg','idCardHandImgPreview');">
									</b>
								</h1></li>
						</ul>
						<div class="ibms_clear"></div>
						<div class="ibms_form_default_textarea">
							<font>身份证手持预览：</font>
							<h1 style="width: 90%;">
								<b> <a class="POPUP_A" href="javascript:void(0);"><img
										width="300" height="200"
										src="${pageContext.request.contextPath}${customer.rentPersonInfo.idCardHandImg}"
										id="idCardHandImgPreview" name="idCardHandImgPreview" /></a>
								</b>
							</h1>
						</div>
					</div>
					<div id="companyDiv">
						<ul>
							<input name="id" value="${customer.rentCompanyInfo.id}"
								type="hidden">
							<li><font>企业名称：</font>
							<h1>
									<input id="name" name="name"
										value="${customer.rentCompanyInfo.name}" type="text">
								</h1></li>
							<li><font>省：</font>
							<h1>
									<input id="companyProvince" name="companyProvinceId"
										type="text" class="easyui-combobox">
								</h1></li>
							<li><font>市：</font>
							<h1>
									<input id="companyCity" name="companyCityId" type="text"
										class="easyui-combobox">
								</h1></li>
							<li><font>邮编：</font>
							<h1>
									<input name="postcode" postalAddress type="postcode">
								</h1></li>

							<div class="ibms_clear"></div>
							<div class="ibms_form_default_textarea">
								<font>通讯地址：</font>
								<h1>
									<textarea id="address" name="address">${customer.rentCompanyInfo.postalAddress}</textarea>
								</h1>
							</div>
							<div class="ibms_clear"></div>
							<div class="ibms_form_default_textarea">
								<font>企业地址：</font>
								<h1>
									<textarea id="address" name="address">${customer.rentCompanyInfo.address}</textarea>
								</h1>
							</div>

						</ul>
						<div class="ibms_clear"></div>
						<ul>
							<li><font>营业执照：</font>
								<h1>
									<b> <input type="file" name="businessLicensePathSelected"
										id="businessLicensePathSelected"
										accept="image/gif, image/jpeg, image/bmp, image/png" /> <input
										type="hidden" name="businessLicensePath"
										id="businessLicensePath" value="" />
									</b> <b> <input id="upload" type="button"
										class="query_list_button" value="上传"
										onclick="javascript:uploadFiles_framework('businessLicensePathSelected','businessLicensePath','businessLicensePathPreview');">
									</b>
								</h1></li>
						</ul>
						<div class="ibms_clear"></div>
						<div class="ibms_form_default_textarea">
							<font>营业执照：</font>
							<h1 style="width: 90%;">
								<b> <a class="POPUP_A" href="javascript:void(0);"><img
										width="300" height="200"
										src="${pageContext.request.contextPath}${customer.rentCompanyInfo.businessLicensePath}"
										id="businessLicensePathPreview"
										name="businessLicensePathPreview" /></a>
								</b>
							</h1>
						</div>
						<div class="ibms_clear"></div>
						<ul>
							<li><font>纳税登记表：</font>
								<h1>
									<b> <input type="file"
										name="lastYearApplicationFormPathSelected"
										id="lastYearApplicationFormPathSelected"
										accept="image/gif, image/jpeg, image/bmp, image/png" /> <input
										type="hidden" name="lastYearApplicationFormPath"
										id="lastYearApplicationFormPath" value="" />
									</b> <b> <input id="upload" type="button"
										class="query_list_button" value="上传"
										onclick="javascript:uploadFiles_framework('lastYearApplicationFormPathSelected','lastYearApplicationFormPath','lastYearApplicationFormPathPreview');">
									</b>
								</h1></li>
						</ul>
						<div class="ibms_clear"></div>
						<div class="ibms_form_default_textarea">
							<font>纳税登记表：</font>
							<h1 style="width: 90%;">
								<b> <a class="POPUP_A" href="javascript:void(0);"><img
										width="300" height="200"
										src="${pageContext.request.contextPath}${customer.rentCompanyInfo.lastYearApplicationFormPath}"
										id="lastYearApplicationFormPathPreview"
										name="lastYearApplicationFormPathPreview" /></a>
								</b>
							</h1>
						</div>
						<div class="ibms_clear"></div>
						<ul>
							<li><font>法人代表：</font>
							<h1>
									<input id="legalName" name="legalName"
										value="${customer.rentCompanyInfo.legalName}" type="text">
								</h1></li>
							<li><font>联系电话：</font>
							<h1>
									<input id="legalMobile" name="legalMobile"
										value="${customer.rentCompanyInfo.legalMobile}" type="text">
								</h1></li>
							<li><font>Email：</font>
							<h1>
									<input name="legalEmail"
										value="${customer.rentCompanyInfo.legalEmail}" type="text">
								</h1></li>
						</ul>

						<div class="ibms_clear"></div>

						<div class="ibms_clear"></div>
						<ul>
							<li><font>身份证正面：</font>
								<h1>
									<b> <input type="file" name="c_idCardFrontImgSelected"
										id="c_idCardFrontImgSelected"
										accept="image/gif, image/jpeg, image/bmp, image/png" /> <input
										type="hidden" name="c_idCardFrontImg" id="c_idCardFrontImg"
										value="" />
									</b> <b> <input id="upload" type="button"
										class="query_list_button" value="上传"
										onclick="javascript:uploadFiles_framework('c_idCardFrontImgSelected','c_idCardFrontImg','c_idCardFrontImgPreview');">
									</b>
								</h1></li>
						</ul>
						<div class="ibms_clear"></div>
						<div class="ibms_form_default_textarea">
							<font>身份证正面预览：</font>
							<h1 style="width: 90%;">
								<b> <a class="POPUP_A" href="javascript:void(0);"><img
										width="300" height="200"
										src="${pageContext.request.contextPath}${customer.rentCompanyInfo.idCardFrontImg}"
										id="c_idCardFrontImgPreview" name="c_idCardFrontImgPreview" /></a>
								</b>
							</h1>
						</div>
						<ul>
							<li><font>身份证反面：</font>
								<h1>
									<b> <input type="file" name="c_idCardBackImgSelected"
										id="c_idCardBackImgSelected"
										accept="image/gif, image/jpeg, image/bmp, image/png" /> <input
										type="hidden" name="c_idCardBackImg" id="c_idCardBackImg"
										value="" />
									</b> <b> <input id="upload" type="button"
										class="query_list_button" value="上传"
										onclick="javascript:uploadFiles_framework('c_idCardBackImgSelected','c_idCardBackImg','c_idCardBackImgPreview');">
									</b>
								</h1></li>
						</ul>
						<div class="ibms_clear"></div>
						<div class="ibms_form_default_textarea">
							<font>身份证反面预览：</font>
							<h1 style="width: 90%;">
								<b> <a class="POPUP_A" href="javascript:void(0);"><img
										width="300" height="200"
										src="${pageContext.request.contextPath}${customer.rentCompanyInfo.idCardBackImg}"
										id="c_idCardBackImgPreview" name="c_idCardBackImgPreview" /></a>
								</b>
							</h1>
						</div>
						<ul>
							<li><font>身份证手持：</font>
								<h1>
									<b> <input type="file" name="c_idCardHandImgSelected"
										id="c_idCardHandImgSelected"
										accept="image/gif, image/jpeg, image/bmp, image/png" /> <input
										type="hidden" name="c_idCardHandImg" id="c_idCardHandImg"
										value="" />
									</b> <b> <input id="upload" type="button"
										class="query_list_button" value="上传"
										onclick="javascript:uploadFiles_framework('c_idCardHandImgSelected','c_idCardHandImg','c_idCardHandImgPreview');">
									</b>
								</h1></li>
						</ul>
						<div class="ibms_clear"></div>
						<div class="ibms_form_default_textarea">
							<font>身份证手持预览：</font>
							<h1 style="width: 90%;">
								<b> <a class="POPUP_A" href="javascript:void(0);"><img
										width="300" height="200"
										src="${pageContext.request.contextPath}${customer.rentCompanyInfo.idCardHandImg}"
										id="c_idCardHandImgPreview" name="c_idCardHandImgPreview" /></a>
								</b>
							</h1>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div title="紧急联系人信息" class="dcim_form_panel">
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
					<div class="ibms_clear"></div>
					<ul>
						<li><font>紧急联系人：</font>
						<h1>
								<input id="emergencyContact" name="emergencyContact"
									value="${customer.emergencyContact}" type="text"
									class="easyui-validatebox" data-options="required:true">
							</h1></li>
						<li><font>联系电话：</font>
						<h1>
								<input id="emergencyMobile" name="emergencyMobile"
									value="${customer.emergencyMobile}" type="text"
									class="easyui-validatebox" data-options="required:true">
							</h1></li>
						<li><font>关系：</font>
						<h1>
								<input id="relation" name="relation"
									value="${customer.relation}" type="text"
									class="easyui-validatebox" data-options="required:true">
							</h1></li>
					</ul>
					<div class="ibms_clear"></div>
				</div>
			</div>
		</div>

	</div>
	<div class="ibms_form_btn">

		<a href="#" class="query_list_button auto-savebutton"
			data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/customer/saveUpdate',autoclose:true">保存</a>
		<a href="#" class="query_list_button auto-resetbutton">重 置</a>
	</div>
</form>

<script type="text/javascript">
	if('${customer.custType}'=='0'){
		$("#companyDiv").remove();
		$("#personDiv").show();
	}else{
		$("#companyDiv").show();
		$("#personDiv").remove();
	}
</script>