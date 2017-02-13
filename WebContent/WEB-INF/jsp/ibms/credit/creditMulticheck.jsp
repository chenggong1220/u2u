<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm">
	<div id="infopointAccordion" class="easyui-accordion"
		style="width: 100%;" data-options="multiple:true">
		<div title="项目基本信息" class="dcim_form_panel" data-options="selected:true">
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
					<div id="personDiv">
						<ul>
							<li><font>承租人姓名：</font>
								<h1>
									<input disabled="disabled" id="name" name="name"
										value="${order.rentPersonInfo.name }" type="text">
								</h1>
							</li>
							<li><font>手机号：</font>
								<h1>
									<input disabled="disabled" id="mobile" name="mobile"
										value="${order.rentPersonInfo.mobile }" type="text">
								</h1>
							</li>		
							<li>
								<font>Email：</font>
								<h1>
									<input disabled="disabled" name="email" value="${order.rentPersonInfo.email}" type="text">
								</h1>
							</li>
						</ul>
						<div class="ibms_clear"></div>
						<ul>
							<li><font>身份证号：</font>
								<h1>
									<input id="idcard_no" name="idcard_no" value="${order.idCard }" type="text" disabled="disabled">
								</h1>
							</li>						

							<li>
								<font>邮政编码：</font>
								<h1>
									<input disabled="disabled" name="postcode" value="${order.rentPersonInfo.postcode}" type="text">
								</h1>
							</li>
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
								<textarea id="postAddress" name="postAddress" disabled="disabled">${order.rentPersonInfo.address}</textarea>
							</h1>							
						</div>	
						
						<div class="ibms_clear"></div>
						<div class="ibms_form_default_textarea">
							<font>设备放置地：</font>
							<h1>
								<textarea id="machLocation" name="machLocation" disabled="disabled">${assetSaveLocationx}</textarea>
							</h1>							
						</div>	
						<div class="ibms_clear"></div>
						<ul>
				           <li><font>紧急联系人：</font>
				           		<h1><input disabled="disabled" id="emergencyContact" name="emergencyContact" value="${order.rentPersonInfo.emergencyContact }" type="text"></h1>
				           	</li>
				           <li><font>联系电话：</font><h1>
				           		<input disabled="disabled" id="emergencyContactMobile" name="emergencyContactMobile" value="${order.rentPersonInfo.emergencyContactMobile }" type="text"></h1>
				           	</li>
				           <li><font>关系：</font><h1>
				           		<input disabled="disabled" id="relation" name="relation" value="${order.rentPersonInfo.relation }" type="text"></h1>
				           	</li>
				        </ul>
					</div>
					<div id="companyDiv">
						<ul>
							<li><font>企业名称：</font>
								<h1>
									<input disabled="disabled" id="name" name="name"
										value="${order.rentCompanyInfo.name }" type="text">
								</h1>
							</li>	
	<!-- Start: Add CompCode, by SUNZHE, 2017-01-16  -->						
							<li><font>企业代码：</font>
							<h1>
								<input id="compCode" name="compCode" value="${order.rentCompanyInfo.compCode}" type="text" disabled="disabled">
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
									<input disabled="disabled" id="legalName" name="legalName" type="text" value="${order.rentCompanyInfo.legalName }">
								</h1>
							</li>
							<li><font>联系电话：</font>
								<h1>
									<input disabled="disabled" id="legalMobile" name="legalMobile" type="text" value="${order.rentCompanyInfo.legalMobile }">
								</h1>
							</li>
							<li>
								<font>Email：</font>
								<h1>
									<input disabled="disabled" name="legalMobile" value="${order.rentCompanyInfo.legalEmail}" type="text">
								</h1>
							</li>								
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
								<input id="idcard_no" name="idcard_no" value="${order.idCard }" type="text" disabled="disabled">
							</h1></li>

							<li>
								<font>邮政编码：</font>
								<h1>
									<input disabled="disabled" name="postcode" value="${order.rentCompanyInfo.postcode}" type="text">
								</h1>
							</li>	
						</ul>
				
						<div class="ibms_clear"></div>
						<div class="ibms_form_default_textarea">
							<font>通讯地址：</font>
							<h1>
								<textarea id="postAddress" name="postAddress" disabled="disabled">${order.rentCompanyInfo.address}</textarea>
							</h1>							
						</div>	
						
						<div class="ibms_clear"></div>
						<div class="ibms_form_default_textarea">
							<font>设备放置地：</font>
							<h1>
								<textarea id="machLocation" name="machLocation" disabled="disabled">${assetSaveLocationx}</textarea>
							</h1>							
						</div>				
						
<!-- Start: Add by SUNZHE, 2017-01-15 -->
						<ul>
							<li>
								<font>紧急联系人：</font>
								<h1>
									<input disabled="disabled" name="mobile" value="${order.rentCompanyInfo.emergencyContact}" type="text">
								</h1>
							</li>

							<li>
								<font>关系：</font>
								<h1>
									<input disabled="disabled" name="mobile" value="${order.rentCompanyInfo.relation}" type="text">
								</h1>
							</li>

							<li>
								<font>联系人电话：</font>
								<h1>
									<input disabled="disabled" name="mobile" value="${order.rentCompanyInfo.emergencyContactMobile}" type="text">
								</h1>
							</li>
						</ul>
<!-- End: Add by SUNZHE, 2017-01-15 -->						
					</div>

					<div class="ibms_clear"></div>
					<ul>
						<li><font>业务员姓名：</font>
						<h1>
								<input id="operator" name="operator"
									value="${order.operator }" type="text" disabled="disabled">
							</h1></li>
						<li><font>业务员手机：</font>
						<h1>
								<input id="operatorMobile" name="operatorMobile"
									value="${order.operatorMobile }" type="text" disabled="disabled">
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
								<input disabled="disabled" id="date" name="date"
									type="text" value="${orderStatistics.date }">
							</h1></li>
						<li><font>押金：</font>
							<h1>
								<input disabled="disabled" id="deposit" name="deposit" type="text"
									value="${orderStatistics.deposit }">
							</h1></li>
						<li><font>租金：</font>
							<h1>
								<input disabled="disabled" id="rentAmount"
									name="rentAmount" value="${orderStatistics.rentAmount }"
									type="text">
							</h1></li>
						<li><font>租金总额：</font>
							<h1>
								<input disabled="disabled" id="rentSumAmount" name="rentSumAmount"
									type="text" value="${orderStatistics.rentSumAmount }">
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
									<td>${project.required1==true?'是':'否' }</td>
									<td>${project.required1Content }</td>
								</tr>
								<tr>
									<td class="text-center">2</td>
									<td>紧急联系人信息（姓名、联系方式、与承租人关系；联系人优先选择直系亲属）</td>
									<td>${project.required2==true?'是':'否' }</td>
									<td>${project.required2Content }</td>
								</tr>
								<tr>
									<td class="text-center">3</td>
									<td>租赁设备放置地证明</td>
									<td>${project.required3==true?'是':'否' }</td>
									<td>${project.required3Content }</td>
								</tr>
								<tr>
									<td class="text-center">4</td>
									<td>租赁设备放置地是否具备上网条件</td>
									<td>${project.required4==true?'是':'否' }</td>
									<td>${project.required4Content }</td>
								</tr>
								<tr>
									<td class="text-center">5</td>
									<td>提供承租人手持身份证照片（清晰可辨认）</td>
									<td>${project.required5==true?'是':'否' }</td>
									<td>${project.required5Content }</td>
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
									<td>${project.required1==true?'是':'否' }</td>
									<td>${project.required1Content }</td>
								</tr>
								<tr>
									<td class="text-center">2</td>
									<td>租赁设备放置地准确、有效，且具备上网条件</td>
									<td>${project.required2==true?'是':'否' }</td>
									<td>${project.required2Content }</td>
								</tr>
								<tr>
									<td class="text-center">3</td>
									<td>法人代表和实际控制人提供连带责任担保</td>
									<td>${project.required3==true?'是':'否' }</td>
									<td>${project.required3Content }</td>
								</tr>
								<tr>
									<td class="text-center">4</td>
									<td>企业三方网查无异常信息</td>
									<td>${project.required4==true?'是':'否' }</td>
									<td>${project.required4Content }</td>
								</tr>
								<tr>
									<td class="text-center">5</td>
									<td>实际控制人三方网查无异常信息</td>
									<td>${project.required5==true?'是':'否' }</td>
									<td>${project.required5Content }</td>
								</tr>
								<tr>
									<td class="text-center">6</td>
									<td>承租人上一年度报税收入≥租赁设备市场价格合计</td>
									<td>${project.required6==true?'是':'否' }</td>
									<td>${project.required6Content }</td>
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
					<div class="ibms_form_default_textarea">
						<font>项目特殊情况说明：</font>
						<h1>
							<textarea id="feedback" name="feedback" disabled="disabled">${project.feedback }</textarea>
						</h1>
					</div>
					<div class="ibms_clear"></div>
					<div class="ibms_form_default_textarea" id="personId">
						<font>身份证正面：</font>
						<h1 style="width: 90%;">
							<b>  <a class="POPUP_A" href="javascript:void(0);"><img width="300" height="200"
								src="${pageContext.request.contextPath}${order.rentPersonInfo.idCardFrontImg }" name="idCardFrontImg" /></a>
							</b>
						</h1>
						<font>身份证反面：</font>
						<h1 style="width: 90%;">
							<b>  <a class="POPUP_A" href="javascript:void(0);"><img width="300" height="200"
								src="${pageContext.request.contextPath}${order.rentPersonInfo.idCardBackImg }" name="idCardBackImg" /></a>
							</b>
						</h1>
						<font>身份证手持：</font>
						<h1 style="width: 90%;">
							<b>  <a class="POPUP_A" href="javascript:void(0);"><img width="300" height="200"
								src="${pageContext.request.contextPath}${order.rentPersonInfo.idCardHandImg }" name="idCardHandImg" /></a>
							</b>
						</h1>
					</div>

					<div class="ibms_clear"></div>
					<div class="ibms_form_default_textarea">
						<font>公安部验证照片：</font>
						<h1 style="width: 90%;">
							<b> <img width="300" height="200"
								src="data:image/png;base64,${realpicture.idImg }"/>
							</b>
						</h1>
					</div>

					<div class="ibms_form_default_textarea" id="companyId">	
						<font>身份证正面：</font>
						<h1 style="width: 90%;">
							<b> <a class="POPUP_A" href="javascript:void(0);"><img width="300" height="200"
								src="${pageContext.request.contextPath}${order.rentCompanyInfo.idCardFrontImg }" name="idCardFrontImg" /></a>
							</b>
						</h1>
						<font>身份证反面：</font>
						<h1 style="width: 90%;">
							<b>  <a class="POPUP_A" href="javascript:void(0);"><img width="300" height="200"
								src="${pageContext.request.contextPath}${order.rentCompanyInfo.idCardBackImg }" name="idCardBackImg" /></a>
							</b>
						</h1>
						<font>身份证手持：</font>
						<h1 style="width: 90%;">
							<b>  <a class="POPUP_A" href="javascript:void(0);"><img width="300" height="200"
								src="${pageContext.request.contextPath}${order.rentCompanyInfo.idCardHandImg }" name="idCardHandImg" /></a>
							</b>
						</h1>
<!-- Start: Added pics for Comp, by SUNZHE, 2017-01-17	---->
						<font>营业执照：</font>
						<h1 style="width: 90%;">
							<b>  <a class="POPUP_A" href="javascript:void(0);"><img width="300" height="200"
								src="${pageContext.request.contextPath}${order.rentCompanyInfo.businessLicensePath }" name="businessLicensePath" /></a>
							</b>
						</h1>
						<font>纳税登记表：</font>
						<h1 style="width: 90%;">
							<b>  <a class="POPUP_A" href="javascript:void(0);"><img width="300" height="200"
								src="${pageContext.request.contextPath}${order.rentCompanyInfo.lastYearApplicationFormPath }" name="lastYearApplicationFormPath" /></a>
							</b>
						</h1>
<!-- End: Added pics for Comp, by SUNZHE, 2017-01-17	---->
					</div>

			        <div class="ibms_clear"></div>
			        <div class="ibms_form_default_textarea">
				        <font>三网截屏：</font>
				        <h1 style="width:90%;">
							<b>
							 <a class="POPUP_A" href="javascript:void(0);">
								<img width="300" height="200" src="${pageContext.request.contextPath}${project.identifyImage }" id="identifyImagePreview" name="identifyImagePreview"/>
							</a>
							</b>
						</h1>
					</div>
					<c:forEach items="${projectPictures }" var="projectPicture">
						<div class="ibms_clear"></div>
				        <div class="ibms_form_default_textarea">
					        <font>三网截屏：</font>
					        <h1 style="width:90%;">
								<b>
								<a class="POPUP_A" href="javascript:void(0);">
									<img width="300" height="200" src="${pageContext.request.contextPath}${projectPicture.picture }" id="identifyImagePreview" name="identifyImagePreview"/>
								</a>
								</b>
							</h1>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div title="项目处理记录" class="dcim_form_panel">
			<div class="easyui-panel ibms_form_panel">
				<div class="project-record">
<%-- 				    <h1>客户专员：<span>${project.operator }</span> 处理时间：<span>${project.operateViewDate }</span></h1> --%>
					<ul>
						<li>操作人：<span>${order.manager.realname}</span></li>
						<li>处理时间：<span>${order.operateViewDate }</span></li>
						<li>处理结果：<span>${order.feedStatus==true?'通过':'拒绝' }</span></li>
						<li>处理意见：<span>${order.feedback }</span></li>
					</ul>
					<c:forEach var="projectHandle" items="${projectHandles}">
						<ul>
							<li>操作人：<span>${projectHandle.username }</span></li>
							<li>处理时间：<span>${projectHandle.operateViewDate }</span></li>
							<li>处理结果：<span>${projectHandle.status==true?'接受':'拒绝' }</span></li>
							<li>处理意见：<span>${projectHandle.feedback }</span></li>
						</ul>
					</c:forEach>
				</div>
			</div>
		</div>
		<div title="信用审核" class="dcim_form_panel">
			<div class="easyui-panel ibms_form_panel">
				<div class="project-table">
				<input type="hidden" name="id" value="${project.id }">
						<table cellspacing="0" cellpadding="0">
							<thead>
								<tr>
									<th>复核内容</th>
									<th>工作底稿记录（若有异常点需详细说明）</th>
									<th>复核结论</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>身份信息验证</td>
									<td>${credit.required1Content}</td>
									<td>${credit.creditRequired1Name}</td>
								</tr>
								<tr>
									<td>业务信息验证</td>
									<td>${credit.required2Content}</td>
									<td>${credit.creditRequired2Name}</td>
								</tr>
								<tr>
									<td>其他信息</td>
									<td>${credit.required3Content}</td>
									<td>${credit.creditRequired3Name}</td>
								</tr>
								<tr>
									<td>项目风险提示</td>
									<td colspan="2">${credit.required4Content}</td>
								</tr>
								<tr>
									<td>客户还款情况</td>
									<td colspan="2"><a href="#">账单链接 </a></td>
								</tr>
<!--
								//Hided by SUNZHE, 2017-01-12
								<tr>
									<td>资产专员翻单意见</td>
									<td colspan="2">${credit.required6Content}</td>
								</tr>
-->
							</tbody>
						</table>
					</div>
					
				<div class="ibms_form_default">
					<ul>
						<li><font>下载文件：</font>
							<h1>	
								<a id="creditFilePreview" href="${pageContext.request.contextPath}${order.creditFile}" target="_blank">下载</a>
							</h1>
						</li>
			        </ul>
			        <div class="ibms_clear"></div>
				</div>					
				<div class="ibms_form_default">
					<ul>
						<li><font>处理结果：</font>
							<h1>
								<select id="status" name="status" class="easyui-combobox">
									<option value="true">通过</option>
									<option value="false">拒绝</option>
								</select>
							</h1>
						</li>
					</ul>
					<div class="ibms_clear"></div>
					<div class="ibms_form_default_textarea">
						<font>处理意见：</font>
						<h1>
							<textarea id="feedback" name="feedback" >${projectHandle.feedback }</textarea>
						</h1>
					</div>
			        <div class="ibms_clear"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="ibms_form_btn">
		<a id="save_credit_multicheck" href="#" class="query_list_button">保存</a>
		<a href="#" class="query_list_button auto-resetbutton">重 置</a>
		<a id="save_credit_multicheck_click" href="#" class="query_list_button auto-savebutton" style="visibility: hidden"
			data-options="plain:true,url:'${pageContext.request.contextPath}/web/credit/multicheck/save',autoclose:true">1</a>
	</div>
</form>

<script type="text/javascript">
	if('${order.rentPersonType}'=='0'){
  		$("#companyDiv").hide();
  		$("#personDiv").show();
  		$("#personId").show();
  		$("#companyId").remove();
		$("#personEmgcy").show();
		$("#companyEmgcy").remove();
	}else{
		$("#personDiv").hide();
  		$("#companyDiv").show();
  		$("#personId").remove();
  		$("#companyId").show();
		$("#personEmgcy").remove();
		$("#companyEmgcy").show();
	}
	

	$("#save_credit_multicheck").click(function(){
		var confirmMsg = "复核通过后系统会自动生成合同，请确认是否复核通过？";
		var checkValue = $('#status').combobox('getValue');  

		if(checkValue == "false")
		{
			confirmMsg = "确定要拒绝该项目吗？";
		}

		$.messager.confirm("保存提示", confirmMsg, function(r) {
			if (r){
				$("#save_credit_multicheck_click").click();
			}
		});
	});		
</script>