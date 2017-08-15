<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm">
	<div id="infopointAccordion" class="easyui-accordion"
		style="width: 100%;" data-options="multiple:true">
		<div title="订单信息" class="ibms_form_panel" data-options="selected:true">
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
					<ul>
						<li><font>订单号：</font>
							<h1>
								<input disabled="disabled" name="code" value="${order.code}"
									type="text">
							</h1></li>
						<li><font>创建时间：</font>
							<h1>
								<input disabled="disabled" name="createDate"
									value="${order.viewDate}" type="text">
							</h1></li>
						<!--
						<li>
							<font>业务员：</font>
							<h1>
								<input disabled="disabled" name="operator" value="${order.operator}" type="text">
							</h1>
						</li>
-->
						<li><font>客户类型：</font>
							<h1>
								<input disabled="disabled" name="rentType"
									value="${order.rentPersonType==0?'个人':'企业'}" type="text">
							</h1></li>
						<li><font>来源：</font>
							<h1>
								<input disabled="disabled" name="createSource"
									value="${order.createSource==0?'APP':'WEB后台' }" type="text">
							</h1></li>
					</ul>
					<div class="ibms_clear"></div>
					<div id="personInfo" hidden="hidden">
						<ul>
							<li><font>联系人：</font>
								<h1>
									<input disabled="disabled" name="name"
										value="${order.rentPersonInfo.name}" type="text">
								</h1></li>
							<li><font>手机号码：</font>
								<h1>
									<input disabled="disabled" name="mobile"
										value="${order.rentPersonInfo.mobile}" type="text">
								</h1></li>
							<li><font>Email：</font>
								<h1>
									<input disabled="disabled" name="email"
										value="${order.rentPersonInfo.email}" type="text">
								</h1></li>
						</ul>

						<ul>
							<li><font>邮政编码：</font>
								<h1>
									<input disabled="disabled" name="postcode"
										value="${order.rentPersonInfo.postcode}" type="text">
								</h1></li>
							<div class="ibms_clear"></div>
							<div class="ibms_form_default_textarea">
								<font>通讯地址：</font>
								<h1>
									<textarea id="postAddress" disabled="disabled"
										name="postAddress">${order.rentPersonInfo.address}</textarea>
								</h1>
							</div>
							<div class="ibms_clear"></div>
							<div class="ibms_form_default_textarea">
								<font>设备放置地：</font>
								<h1>
									<textarea id="machLocation" disabled="disabled"
										name="machLocation">${order.rentPersonInfo.personProvince}${order.rentPersonInfo.personCity}${order.detailLocation}</textarea>
								</h1>
							</div>


						</ul>

						<!-- Start: Add by SUNZHE, 2017-01-15 -->
						<ul>
							<li><font>紧急联系人：</font>
								<h1>
									<input disabled="disabled" name="mobile"
										value="${order.rentPersonInfo.emergencyContact}" type="text">
								</h1></li>

							<li><font>关系：</font>
								<h1>
									<input disabled="disabled" name="mobile"
										value="${order.rentPersonInfo.relation}" type="text">
								</h1></li>

							<li><font>联系电话：</font>
								<h1>
									<input disabled="disabled" name="mobile"
										value="${order.rentPersonInfo.emergencyContactMobile}"
										type="text">
								</h1></li>
						</ul>
						<!-- End: Add by SUNZHE, 2017-01-15 -->

					</div>
					<div class="ibms_clear"></div>
					<div id="companyInfo" hidden="hidden">
						<ul>
							<li><font>企业名称：</font>
								<h1>
									<input disabled="disabled" name="compName"
										value="${order.rentCompanyInfo.name}" type="text">
								</h1></li>
							<!--  							
							<li>
								<font>通讯地址：</font>
								<h1>
									<input disabled="disabled" name="postcode" value="${order.rentCompanyInfo.postalAddress}" type="text">
								</h1>
							</li>
-->

						</ul>
						<ul>
							<li><font>联系人：</font>
								<h1>
									<input disabled="disabled" name="legalName"
										value="${order.rentCompanyInfo.legalName}" type="text">
								</h1></li>
							<li><font>手机号码：</font>
								<h1>
									<input disabled="disabled" name="legalMobile"
										value="${order.rentCompanyInfo.legalMobile}" type="text">
								</h1></li>
							<li><font>Email：</font>
								<h1>
									<input disabled="disabled" name="legalMobile"
										value="${order.rentCompanyInfo.legalEmail}" type="text">
								</h1></li>

							<li><font>邮政编码：</font>
								<h1>
									<input disabled="disabled" name="postcode"
										value="${order.rentCompanyInfo.postcode}" type="text">
								</h1></li>
						</ul>
						
						<div class="ibms_clear"></div>
						<ul>
							<li><font>实际控制人：</font>
								<h1>
									<input disabled="disabled" name="legalName"
										value="${order.rentCompanyInfo.actualController}" type="text">
								</h1></li>
							<li><font>控制人电话：</font>
								<h1>
									<input disabled="disabled" name="legalMobile"
										value="${order.rentCompanyInfo.controllerMobile}" type="text">
								</h1></li>
						</ul>

						<div class="ibms_clear"></div>
						<div class="ibms_form_default_textarea">
							<font>通讯地址：</font>
							<h1>
								<textarea id="postalAddress" name="postalAddress"
									disabled="disabled">${order.rentCompanyInfo.postalAddress}</textarea>
							</h1>
						</div>

						<div class="ibms_clear"></div>
						<div class="ibms_form_default_textarea">
							<font>设备放置地：</font>
							<h1>
								<textarea id="feedback" name="feedback" disabled="disabled">${order.rentCompanyInfo.companyProvince}${order.rentCompanyInfo.companyCity}${order.detailLocation}</textarea>
							</h1>
						</div>

						<!-- Start: Add by SUNZHE, 2017-01-15 -->
						<ul>
							<li><font>紧急联系人：</font>
								<h1>
									<input disabled="disabled" name="mobile"
										value="${order.rentCompanyInfo.emergencyContact}" type="text">
								</h1></li>

							<li><font>关系：</font>
								<h1>
									<input disabled="disabled" name="mobile"
										value="${order.rentCompanyInfo.relation}" type="text">
								</h1></li>

							<li><font>联系人电话：</font>
								<h1>
									<input disabled="disabled" name="mobile"
										value="${order.rentCompanyInfo.emergencyContactMobile}"
										type="text">
								</h1></li>
						</ul>
						<!-- End: Add by SUNZHE, 2017-01-15 -->

					</div>
					<div class="ibms_clear"></div>
				</div>
			</div>
		</div>
		<div title="身份认证" class="ibms_form_panel" data-options="selected:true">
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
					<div class="ibms_clear"></div>
					<div class="ibms_form_default_textarea" id="personId">
						<font>身份证验证：</font>
						<h1 style="width: 90%;">
							<b> 
								<a class="POPUP_A" href="javascript:void(0);"><img width="300" height="200"
								src="data:image/png;base64,${realpicture.idImg }" name="idCardVerifyImg" /></a>
							</b>
						</h1>					
						<font>身份证正面：</font>
						<h1 style="width: 90%;">
							<b> <a class="POPUP_A" href="javascript:void(0);"><img
									width="300" height="200"
									src="${pageContext.request.contextPath}${order.rentPersonInfo.idCardFrontImg }"
									name="idCardFrontImg" /></a>
							</b>
						</h1>
						<font>身份证反面：</font>
						<h1 style="width: 90%;">
							<b> <a class="POPUP_A" href="javascript:void(0);"><img
									width="300" height="200"
									src="${pageContext.request.contextPath}${order.rentPersonInfo.idCardBackImg }"
									name="idCardBackImg" /></a>
							</b>
						</h1>
						<font>身份证手持：</font>
						<h1 style="width: 90%;">
							<b> <a class="POPUP_A" href="javascript:void(0);"><img
									width="300" height="200"
									src="${pageContext.request.contextPath}${order.rentPersonInfo.idCardHandImg }"
									name="idCardHandImg" /></a>
							</b>
						</h1>
					</div>
					<div class="ibms_form_default_textarea" id="companyId">
						<font>身份证验证：</font>
						<h1 style="width: 90%;">
							<b> 
								<a class="POPUP_A" href="javascript:void(0);"><img width="300" height="200"
								src="data:image/png;base64,${realpicture.idImg }" name="idCardVerifyImg" /></a>
							</b>
						</h1>					
						<font>身份证正面：</font>
						<h1 style="width: 90%;">
							<b> <a class="POPUP_A" href="javascript:void(0);"><img
									width="300" height="200"
									src="${pageContext.request.contextPath}${order.rentCompanyInfo.idCardFrontImg }"
									name="idCardFrontImg" /></a>
							</b>
						</h1>
						
						<font>身份证反面：</font>
						<h1 style="width: 90%;">
							<b> <a class="POPUP_A" href="javascript:void(0);"><img
									width="300" height="200"
									src="${pageContext.request.contextPath}${order.rentCompanyInfo.idCardBackImg }"
									name="idCardBackImg" /></a>
							</b>
						</h1>
						<font>身份证手持：</font>
						<h1 style="width: 90%;">
							<b> <a class="POPUP_A" href="javascript:void(0);"><img
									width="300" height="200"
									src="${pageContext.request.contextPath}${order.rentCompanyInfo.idCardHandImg }"
									name="idCardHandImg" /></a>
							</b>
						</h1>
						<!-- Start: Added pics for Comp, by SUNZHE, 2017-01-17	---->
						<font>营业执照：</font>
						<h1 style="width: 90%;">
							<b> <a class="POPUP_A" href="javascript:void(0);"><img
									width="300" height="200"
									src="${pageContext.request.contextPath}${order.rentCompanyInfo.businessLicensePath }"
									name="businessLicensePath" /></a>
							</b>
						</h1>
						<font>纳税登记表：</font>
						<h1 style="width: 90%;">
							<b> <a class="POPUP_A" href="javascript:void(0);"><img
									width="300" height="200"
									src="${pageContext.request.contextPath}${order.rentCompanyInfo.lastYearApplicationFormPath }"
									name="lastYearApplicationFormPath" /></a>
							</b>
						</h1>
						<!-- End: Added pics for Comp, by SUNZHE, 2017-01-17	---->
						
						<!-- Start: Added Tax Proof Pic for Comp, by SUNZHE, 2017-08-02	---->
						<font>上年纳税凭证：</font>
						<h1 style="width: 90%;">
							<b> <a class="POPUP_A" href="javascript:void(0);"><img
									width="300" height="200"
									src="${pageContext.request.contextPath}${order.rentCompanyInfo.lastYearTaxProofPath }"
									name="lastYearTaxProofPath" /></a>
							</b>
						</h1>
						<!-- End: Added Tax Proof Pic for Comp, by SUNZHE, 2017-08-02 ---->						
					</div>
				</div>
			</div>
		</div>
		<div title="租赁信息" class="dcim_form_panel">
			<table id="querygrid" class="easyui-datagrid" title="租赁信息"
				data-options="
							iconCls: 'icon-edit',
							singleSelect: true,
							toolbar: '#tb_sub',
							pagination:true,
							rownumbers:true,
							fitColumns: true,
							singleSelect:true,
							url: '${pageContext.request.contextPath}/web/order/verify/suborders?orderId=${order.id }',
							pageSize:10,
							pageList: [10,20,50],
							collapsible:true,
							method: 'post'
						">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true">序列号</th>
						<th data-options="field:'brand',width:'10%'">品牌</th>
						<th data-options="field:'assetType',width:'10%'">型号</th>
						<th data-options="field:'count',width:'20%'">数量</th>
						<th data-options="field:'combo',width:'20%'">套餐</th>
						<th data-options="field:'deposit',width:'10%'">保证金</th>
						<th data-options="field:'amount',width:'20%'">净值</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</form>

<script type="text/javascript">
	if('${order.rentPersonType}'=='0'){
		$("#companyInfo").remove();
		$("#personInfo").show();
		$("#personId").show();
		$("#companyId").remove();
	}else{
		$("#personInfo").remove();
		$("#personId").remove();
		$("#companyId").show();
  		$("#companyInfo").show();
	}
</script>