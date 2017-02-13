<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm">
	<div id="infopointAccordion" class="easyui-accordion"
		style="width: 100%;" data-options="multiple:true">
		<div title="项目基本信息" class="dcim_form_panel" data-options="selected:true">
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
					<div class="ibms_clear"></div>
					<div id="personDiv">
						<ul>
							<li><font>承租人名称：</font>
							<h1>
									<input disabled="disabled" id="name" name="name"
										value="${order.rentPersonInfo.name }" type="text">
								</h1></li>
							<li><font>手机号：</font>
							<h1>
									<input disabled="disabled" id="mobile" name="mobile"
										value="${order.rentPersonInfo.mobile }" type="text">
								</h1></li>
						</ul>
						<div class="ibms_clear"></div>
						<ul>
							<li><font>承租人省：</font>
							<h1>
									<input disabled="disabled" id="personProvince"
										name="personProvinceId"
										value="${order.rentPersonInfo.personProvince }" type="text">
								</h1></li>
							<li><font>承租人市：</font>
							<h1>
									<input disabled="disabled" id="personCity" name="personCityId"
										type="text" value="${order.rentPersonInfo.personCity }">
								</h1></li>
							<li><font>承租人地址：</font>
							<h1>
									<input disabled="disabled" id="address" name="address"
										type="text" value="${order.rentPersonInfo.address }">
								</h1></li>
						</ul>
						<div class="ibms_clear"></div>
					</div>
					<div id="companyDiv">
						<ul>
							<li><font>承租人名称：</font>
							<h1>
									<input disabled="disabled" id="name" name="name"
										value="${order.rentCompanyInfo.name }" type="text">
								</h1></li>
							<li><font>承租人地址：</font>
							<h1>
									<input disabled="disabled" id="address" name="address"
										value="${order.rentCompanyInfo.address }" type="text">
								</h1></li>
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
									<input disabled="disabled" name="legalEmail" type="text"
										value="${order.rentCompanyInfo.legalEmail }">
								</h1></li>
						</ul>
						<div class="ibms_clear"></div>
					</div>
					<ul>
						<li><font>业务员姓名：</font>
						<h1>
								<input disabled="disabled" id="operator" name="operator"
									value="${order.operator }" type="text">
							</h1></li>
						<li><font>业务员手机：</font>
						<h1>
								<input disabled="disabled" id="operatorMobile" name="operatorMobile"
									value="${order.operatorMobile }" type="text">
							</h1></li>
						<li><font>报告日期：</font>
						<h1>
								<input disabled="disabled" id="operateDate" name="operateDate"
									value="${project.viewDate }" type="text">
							</h1></li>
					</ul>
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
			<table id="rentAssetTypes" class="easyui-datagrid" title="租赁物信息"
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
		
		<div title="翻单记录" class="dcim_form_panel">
			<div class="easyui-panel ibms_form_panel">
				<div class="project-record">
					<c:forEach var="fandan" items="${fandans}">
						<ul>
							<li>翻单意见：<span>${fandan.status==true?'同意':'拒绝' }</span></li>
							<li>翻单时间：<span>${fandan.viewDate }</span></li>
							<li>说明：<span>${fandan.feedback}</span></li>
						</ul>
					</c:forEach>
				</div>
			</div>
		</div>
		<div title="翻单操作" class="dcim_form_panel">
			<div class="easyui-panel ibms_form_panel">
				<div class="ibms_form_default">
					<input type="hidden" name="orderId" value="${order.id }">
			        <ul>
			           <li><font>翻单意见：</font>
			           	<h1>
			           		<select id="status" name="status" class="easyui-combobox">
			           			<option value="true">同意</option>
			           			<option value="false">拒绝</option>
			           		</select>
			           	</h1></li>
			        </ul>
			        <div class="ibms_clear"></div>
			        <div class="ibms_form_default_textarea">
			        	<font>说 明：</font>
			        	<h1><textarea id="feedback" name="feedback" ></textarea></h1>
			        </div>   
		        </div>
			</div>
		</div>
	</div>
	<div class="ibms_form_btn">
		<a href="#" class="query_list_button auto-savebutton"
			data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/asset/manager/fandan/save',autoclose:true">保存</a>
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