<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm">
	<div id="infopointAccordion" class="easyui-accordion"
		style="width: 100%;" data-options="multiple:true">
		<div title="项目基本信息" class="dcim_form_panel"
			data-options="selected:true">
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
					<div id="personDiv">
						<ul>
							<li><font>承租人姓名：</font>
								<h1>
									<input disabled="disabled" id="name" name="name"
										value="${order.rentPersonInfo.name }" type="text">
								</h1></li>
							<li><font>手机号：</font>
								<h1>
									<input disabled="disabled" id="mobile" name="mobile"
										value="${order.rentPersonInfo.mobile }" type="text">
								</h1></li>
							<li><font>Email：</font>
								<h1>
									<input disabled="disabled" name="email"
										value="${order.rentPersonInfo.email}" type="text">
								</h1></li>
						</ul>
						<div class="ibms_clear"></div>
						<ul>
							<li><font>身份证号：</font>
								<h1>
									<input id="idcard_no" name="idcard_no" value="${order.idCard }"
										type="text" disabled="disabled">
								</h1></li>

							<li><font>邮政编码：</font>
								<h1>
									<input disabled="disabled" name="postcode"
										value="${order.rentPersonInfo.postcode}" type="text">
								</h1></li>
							<!-- Change to TextArea, Shield by SUNZHE, 2017-02-13						
							<li>
								<font>通讯地址：</font>
								<h1>
									<input disabled="disabled" name="postAddress" value="${order.rentPersonInfo.address}" type="text">
								</h1>
							</li>
							<li><font>客户经理：</font>
							<h1>
									<input disabled="disabled" id="operateDate" name="operateDate"
										value="${order.manager.realname }" type="text">
								</h1>
							</li>					
						</ul>

	 					
						<ul>
							<li><font>设备放置地：</font>
								<h1>
									<input id="assetSaveLocation" name="assetSaveLocation" value="${assetSaveLocation }" type="text" disabled="disabled">
								</h1>
							</li>						
						</ul>
-->

							<div class="ibms_clear"></div>
							<div class="ibms_form_default_textarea">
								<font>通讯地址：</font>
								<h1>
									<textarea id="postAddress" name="postAddress"
										disabled="disabled">${order.rentPersonInfo.address}</textarea>
								</h1>
							</div>

							<div class="ibms_clear"></div>
							<div class="ibms_form_default_textarea">
								<font>设备放置地：</font>
								<h1>
									<textarea id="machLocation" name="machLocation"
										disabled="disabled">${assetSaveLocationx}</textarea>
								</h1>
							</div>
							<div class="ibms_clear"></div>
							<ul>
								<li><font>紧急联系人：</font>
									<h1>
										<input disabled="disabled" id="emergencyContact"
											name="emergencyContact"
											value="${order.rentPersonInfo.emergencyContact }" type="text">
									</h1></li>
								<li><font>联系电话：</font>
								<h1>
										<input disabled="disabled" id="emergencyContactMobile"
											name="emergencyContactMobile"
											value="${order.rentPersonInfo.emergencyContactMobile }"
											type="text">
									</h1></li>
								<li><font>关系：</font>
								<h1>
										<input disabled="disabled" id="relation" name="relation"
											value="${order.rentPersonInfo.relation }" type="text">
									</h1></li>
							</ul>
					</div>
					<div id="companyDiv">
						<ul>
							<li><font>企业名称：</font>
								<h1>
									<input disabled="disabled" id="name" name="name"
										value="${order.rentCompanyInfo.name }" type="text">
								</h1></li>
							<!-- Start: Add CompCode, by SUNZHE, 2017-01-16  -->
							<li><font>企业代码：</font>
								<h1>
									<input id="compCode" name="compCode"
										value="${order.rentCompanyInfo.compCode}" type="text">
								</h1></li>
							<!-- End: Add CompCode, by SUNZHE, 2017-01-16  -->
							<!-- 							
							<li><font>企业地址：</font>
								<h1>
									<input disabled="disabled" id="address" name="address"
										value="${order.rentCompanyInfo.companyProvince }${order.rentCompanyInfo.companyCity }${order.rentCompanyInfo.address }" type="text">
								</h1>
							</li>
							<li><font>业务模式：</font>
								<h1>
									<input disabled="disabled" id="rentType" name="rentType"
										value="${order.rentType==0?'分时租赁':'分月租赁'}" type="text">
								</h1>
							</li>
-->
						</ul>
						<div class="ibms_clear"></div>
						<ul>
							<li><font>法人代表：</font>
								<h1>
									<input disabled="disabled" id="legalName" name="legalName"
										type="text" value="${order.rentCompanyInfo.legalName }">
								</h1></li>
							<li><font>联系电话：</font>
								<h1>
									<input disabled="disabled" id="legalMobile" name="legalMobile"
										type="text" value="${order.rentCompanyInfo.legalMobile }">
								</h1></li>
							<li><font>Email：</font>
								<h1>
									<input disabled="disabled" name="legalMobile"
										value="${order.rentCompanyInfo.legalEmail}" type="text">
								</h1></li>
							<!-- 							
							<li><font>客户经理：</font>
								<h1>
									<input disabled="disabled" name="realname" type="text" value="${order.manager.realname }">
								</h1>
							</li>
-->
						</ul>
						<div class="ibms_clear"></div>
						<ul>
							<li><font>身份证号：</font>
								<h1>
									<input id="idcard_no" name="idcard_no" value="${order.idCard }"
										type="text" disabled="disabled">
								</h1></li>

							<li><font>邮政编码：</font>
								<h1>
									<input disabled="disabled" name="postcode"
										value="${order.rentCompanyInfo.postcode}" type="text">
								</h1></li>
						</ul>

						<div class="ibms_clear"></div>
						<div class="ibms_form_default_textarea">
							<font>通讯地址：</font>
							<h1>
								<textarea id="postAddress" name="postAddress"
									disabled="disabled">${order.rentCompanyInfo.address}</textarea>
							</h1>
						</div>

						<div class="ibms_clear"></div>
						<div class="ibms_form_default_textarea">
							<font>设备放置地：</font>
							<h1>
								<textarea id="machLocation" name="machLocation"
									disabled="disabled">${assetSaveLocationx}</textarea>
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
					<ul>
						<li><font>业务员姓名：</font>
							<h1>
								<input id="operator" name="operator" value="${order.operator }"
									type="text">
							</h1></li>
						<li><font>业务员手机：</font>
							<h1>
								<input id="operatorMobile" name="operatorMobile"
									value="${order.operatorMobile }" type="text">
							</h1></li>
						<li><font>报告日期：</font>
							<h1>
								<input disabled="disabled" id="viewDate" name="viewDate"
									value="${order.viewDate }" type="text">
							</h1></li>
					</ul>
					<div class="ibms_clear"></div>
				</div>
			</div>
		</div>

		<div title="交易结构" class="ibms_form_panel" data-options="selected:true">
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
					<ul>
						<li><font>租赁设备净值：</font>
							<h1>
								<input disabled="disabled" name="amount"
									value="${orderStatistics.amount }" type="text">
							</h1></li>
						<li><font>租期（月）：</font>
							<h1>
								<input disabled="disabled" id="date" name="date" type="text"
									value="${orderStatistics.date }">
							</h1></li>
						<li><font>押金：</font>
							<h1>
								<input disabled="disabled" id="deposit" name="deposit"
									type="text" value="${orderStatistics.deposit }">
							</h1></li>
						<li><font>租金：</font>
							<h1>
								<input disabled="disabled" id="rentAmount" name="rentAmount"
									value="${orderStatistics.rentAmount }" type="text">
							</h1></li>
						<li><font>租金总额：</font>
							<h1>
								<input disabled="disabled" id="rentSumAmount"
									name="rentSumAmount" type="text"
									value="${orderStatistics.rentSumAmount }">
							</h1></li>
						<li><font>押金比例%：</font>
							<h1>
								<input disabled="disabled" id="depositRatio" name="depositRatio"
									type="text" value="${orderStatistics.depositRatio }">
							</h1></li>
					</ul>
					<div class="ibms_clear"></div>
				</div>
			</div>
		</div>
		<div title="租赁物信息" class="dcim_form_panel">
			<table id="querygrid" class="easyui-datagrid" title="租赁物信息"
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
						<th data-options="field:'deposit',width:'10%'">押金</th>
						<th data-options="field:'amount',width:'20%'">净值</th>
					</tr>
				</thead>
			</table>
		</div>
		<div title="结论建议" class="dcim_form_panel">
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
					<div class="project-table" id="jielun_jianyi_personal">
						<table cellspacing="0" cellpadding="0">
							<thead>
								<tr>
									<th>序号</th>
									<th>材料</th>
									<th>是否提供</th>
									<th>异常情况说明</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="text-center">1</td>
									<td>身份证照片（正、反两面）</td>
									<td><select id="required1" name="required1"><option
												value="true">是</option>
											<option value="false">否</option></select></td>
									<td><input type="text" name="required1Content"
										value="${project.required1Content }" /></td>
								</tr>
								<tr>
									<td class="text-center">2</td>
									<td>紧急联系人信息（姓名、联系方式、与承租人关系；联系人优先选择直系亲属）</td>
									<td><select id="required2" name="required2"><option
												value="true">是</option>
											<option value="false">否</option></select></td>
									<td><input type="text" name="required2Content"
										value="${project.required2Content }" /></td>
								</tr>
								<tr>
									<td class="text-center">3</td>
									<td>租赁设备放置地证明</td>
									<td><select id="required3" name="required3"><option
												value="true">是</option>
											<option value="false">否</option></select></td>
									<td><input type="text" name="required3Content"
										value="${project.required3Content }" /></td>
								</tr>
								<tr>
									<td class="text-center">4</td>
									<td>租赁设备放置地是否具备上网条件</td>
									<td><select id="required4" name="required4"><option
												value="true">是</option>
											<option value="false">否</option></select></td>
									<td><input type="text" name="required4Content"
										value="${project.required4Content }" /></td>
								</tr>
								<tr>
									<td class="text-center">5</td>
									<td>提供承租人手持身份证照片（清晰可辨认）</td>
									<td><select id="required5" name="required5"><option
												value="true">是</option>
											<option value="false">否</option></select></td>
									<td><input type="text" name="required5Content"
										value="${project.required5Content }" /></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="project-table" id="jielun_jianyi_company">
						<table cellspacing="0" cellpadding="0">
							<thead>
								<tr>
									<th>序号</th>
									<th>材料</th>
									<th>是否满足</th>
									<th>异常情况说明</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="text-center">1</td>
									<td>已按资料清单的要求提供资料（包括《声明书》、征信查询《授权书》、《优尼斯租赁业务提示函》）</td>
									<td><select id="required1" name="required1"><option
												value="true">是</option>
											<option value="false">否</option></select></td>
									<td><input type="text" name="required1Content"
										value="${project.required1Content }" /></td>
								</tr>
								<tr>
									<td class="text-center">2</td>
									<td>租赁设备放置地准确、有效，且具备上网条件</td>
									<td><select id="required2" name="required2"><option
												value="true">是</option>
											<option value="false">否</option></select></td>
									<td><input type="text" name="required2Content"
										value="${project.required2Content }" /></td>
								</tr>
								<tr>
									<td class="text-center">3</td>
									<td>法人代表和实际控制人提供连带责任担保</td>
									<td><select id="required3" name="required3"><option
												value="true">是</option>
											<option value="false">否</option></select></td>
									<td><input type="text" name="required3Content"
										value="${project.required3Content }" /></td>
								</tr>
								<tr>
									<td class="text-center">4</td>
									<td>企业三方网查无异常信息</td>
									<td><select id="required4" name="required4"><option
												value="true">是</option>
											<option value="false">否</option></select></td>
									<td><input type="text" name="required4Content"
										value="${project.required4Content }" /></td>
								</tr>
								<tr>
									<td class="text-center">5</td>
									<td>实际控制人三方网查无异常信息</td>
									<td><select id="required5" name="required5"><option
												value="true">是</option>
											<option value="false">否</option></select></td>
									<td><input type="text" name="required5Content"
										value="${project.required5Content }" /></td>
								</tr>
								<tr>
									<td class="text-center">6</td>
									<td>承租人上一年度报税收入≥租赁设备市场价格合计</td>
									<td><select id="required6" name="required6"><option
												value="true">是</option>
											<option value="false">否</option></select></td>
									<td><input type="text" name="required6Content"
										value="${project.required6Content }" /></td>
								</tr>
							</tbody>
						</table>
					</div>
					<script type="text/javascript">
						if("${project.order.rentPersonType}"==0){
							$("#jielun_jianyi_company").remove();	
						}else{
							$("#jielun_jianyi_personal").remove();
						}
					</script>
					<div class="ibms_clear"></div>
				</div>
			</div>
		</div>
		<div title="特殊情况说明" class="dcim_form_panel">
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
					<input type="hidden" name="id" value="${project.id }">
					<div class="ibms_form_default_textarea">
						<font>特殊情况说明：</font>
						<h1>
							<textarea id="feedback" name="feedback">${project.feedback }</textarea>
						</h1>
					</div>
					<div class="ibms_clear"></div>
					<div class="ibms_form_default_textarea" id="personId">
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
									src="${pageContext.request.contextPath}${order.rentPersonInfo.idCardHandImg }"
									name="idCardHandImg" /></a>
							</b>
						</h1>
						<font>身份证手持：</font>
						<h1 style="width: 90%;">
							<b> <a class="POPUP_A" href="javascript:void(0);"><img
									width="300" height="200"
									src="${pageContext.request.contextPath}${order.rentPersonInfo.idCardBackImg }"
									name="idCardBackImg" /></a>
							</b>
						</h1>
					</div>
					<div class="ibms_clear"></div>
					<div class="ibms_form_default_textarea">
						<font>公安部照片：</font>
						<h1 style="width: 90%;">
							<b> <img width="300" height="200"
								src="data:image/png;base64,${realpicture.idImg }" />
							</b>
						</h1>
					</div>
					<div class="ibms_form_default_textarea" id="companyId">
						<font>身份证正面：</font>
						<h1 style="width: 90%;">
							<b> <a class="POPUP_A" href="javascript:void(0);"><img
									width="300" height="200"
									src="${pageContext.request.contextPath}${order.rentCompanyInfo.idCardBackImg }"
									name="idCardBackImg" /></a>
							</b>
						</h1>
						<font>身份证反面：</font>
						<h1 style="width: 90%;">
							<b> <a class="POPUP_A" href="javascript:void(0);"><img
									width="300" height="200"
									src="${pageContext.request.contextPath}${order.rentCompanyInfo.idCardFrontImg }"
									name="idCardFrontImg" /></a>
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
					</div>

					<div class="ibms_clear"></div>
					<ul>
						<li><font>三网截屏：</font>
							<h1>
								<b> <input type="file" name="identifyImageSelected"
									id="identifyImageSelected"
									accept="image/gif, image/jpeg, image/bmp, image/png" /> <input
									type="hidden" name="identifyImage" id="identifyImage"
									value="${project.identifyImage }" />
								</b> <b> <input id="upload" type="button"
									class="query_list_button" value="上传"
									onclick="javascript:uploadFiles_framework('identifyImageSelected','identifyImage','identifyImagePreview');">
								</b>
							</h1></li>
					</ul>
					<div class="ibms_clear"></div>
					<div class="ibms_form_default_textarea">
						<font>三网截屏：</font>
						<h1 style="width: 90%;">
							<b> <a class="POPUP_A" href="javascript:void(0);"><img
									width="300" height="200"
									src="${pageContext.request.contextPath}${project.identifyImage }"
									id="identifyImagePreview" name="identifyImagePreview" /></a></b>
						</h1>
					</div>
					<c:forEach var="projectPicture" items="${projectPictures }">
						<div id="sanwang_jietu_div_${projectPicture.pictureIndex }"
							class="sanwang_div_counter">
							<div class="ibms_clear"></div>
							<ul>
								<li><font>三网截屏：</font>
									<h1>
										<b> <input type="file"
											name="identifyImageSelected_${projectPicture.pictureIndex }"
											id="identifyImageSelected_${projectPicture.pictureIndex }"
											accept="image/gif, image/jpeg, image/bmp, image/png" /> <input
											type="hidden"
											name="identifyImage_${projectPicture.pictureIndex }"
											id="identifyImage_${projectPicture.pictureIndex }"
											value="${projectPicture.picture }" />
										</b> <b> <input id="upload" type="button"
											class="query_list_button" value="上传"
											onclick="javascript:uploadFiles_framework('identifyImageSelected_${projectPicture.pictureIndex }','identifyImage_${projectPicture.pictureIndex }','identifyImagePreview_${projectPicture.pictureIndex }');">
										</b>
									</h1></li>
								<li>
									<h1>
										<b> <a
											id="sanwang_jietu_aaa_${projectPicture.pictureIndex }"
											href="javascript:void(0)"
											class="sanwang_remove easyui-linkbutton"
											data-options="iconCls:'icon-remove',plain:true">删除</a>
										</b>
									</h1>
								</li>
							</ul>
							<div class="ibms_clear"></div>
							<div class="ibms_form_default_textarea">
								<font>三网截屏：</font>
								<h1 style="width: 90%;">
									<b> <a class="POPUP_A" href="javascript:void(0);"><img
											width="300" height="200"
											src="${pageContext.request.contextPath}${projectPicture.picture }"
											id="identifyImagePreview_${projectPicture.pictureIndex }"
											name="identifyImagePreview_${projectPicture.pictureIndex }" /></a></b>
								</h1>
							</div>
						</div>
					</c:forEach>

					<div id="sanwang_add" class="ibms_clear"></div>
					<ul>
						<li><h1>
								<b><a href="javascript:void(0)" class="easyui-linkbutton"
									data-options="iconCls:'icon-add',plain:true"
									onclick="addSanwangJiePing()">添加三网截屏</a></b>
							</h1></li>

					</ul>
					<script type="text/javascript">
						if('${order.rentPersonType}'=='0'){
							$("#personId").show();
							$("#companyId").remove();
						}else{
							$("#personId").remove();
							$("#companyId").show();
						}
					
					    $(".sanwang_remove").click(function(){
				    		$(this).parent().parent().parent().parent().parent().remove();
				    	});
				    	
				    	var sangwang_count = ${projectPicturesStartIndex};
					    function addSanwangJiePing(){
					    	$("#sanwang_add").before(
					    			'<div id="sanwang_jietu_div_'+sangwang_count+'">' +
					    			'	<div class="ibms_clear"></div>' +
									'	<ul>' +
							        '	<li><font>三网截屏：</font>' +
						           	'		<h1>' +
									'			<b>' +
									'				<input type="file" name="identifyImageSelected_'+sangwang_count+'" id="identifyImageSelected_'+sangwang_count+'" accept="image/gif, image/jpeg, image/bmp, image/png"/>' +
									'				<input type="hidden" name="identifyImage_'+sangwang_count+'" id="identifyImage_'+sangwang_count+'" value=""/>' +
									'			</b>' +
									'			<b>' +
									'				<input id="upload" type="button" class="query_list_button" value="上传" onclick="javascript:uploadFiles_framework(\'identifyImageSelected_'+sangwang_count+'\',\'identifyImage_'+sangwang_count+'\',\'identifyImagePreview_'+sangwang_count+'\');">' +
									'			</b>' +
									'		</h1>' +
									'	</li>' +
									'	<li>' +
						           	'		<h1>' +
									'			<b>' +
									'				<a id="sanwang_jietu_aaa_'+sangwang_count+'" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:\'icon-remove\',plain:true">删除</a>' +
									'			</b>' +
									'		</h1>' +
									'	</li>' +
									'	</ul>' +
							    	'	<div class="ibms_clear"></div>' +
							    	'	<div class="ibms_form_default_textarea">' +
								 	'		<font>三网截屏：</font>' +
								  	'	  	<h1 style="width:90%;">' +
									'			<b><img width="300" height="200" src="${pageContext.request.contextPath}" id="identifyImagePreview_'+sangwang_count+'" name="identifyImagePreview_'+sangwang_count+'"/></b>' +
									'		</h1>' +
									'	</div>' +
									'</div>'
								);
					    	$.parser.parse("#sanwang_jietu_div_"+sangwang_count);
					    	
					    	$("#sanwang_jietu_aaa_"+sangwang_count).click(function(){
					    		$(this).parent().parent().parent().parent().parent().remove();
					    	})
					    	
					    	sangwang_count++;
					    }
				    </script>
				</div>
			</div>
		</div>
	</div>
	<div class="ibms_form_btn">
		<a href="#" class="query_list_button auto-savebutton"
			data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/project/save',autoclose:true">保存</a>
		<a href="#" class="query_list_button auto-resetbutton">重 置</a>
	</div>
</form>

<script type="text/javascript">
	if('${order.rentPersonType}'=='0'){
  		$("#companyDiv").hide();
  		$("#personDiv").show();
	}else{
		$("#personDiv").hide();
  		$("#companyDiv").show();
	}
</script>