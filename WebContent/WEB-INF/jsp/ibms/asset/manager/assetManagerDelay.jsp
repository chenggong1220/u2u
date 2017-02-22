<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<input type="hidden" name="orderId" value="${order.id }">
			<ul>
				<li><font>合同编号：</font>
				<h1>
						<input id="city" name="cityId" type="text"
							value="${contract.contractId }" disabled="disabled">
					</h1></li>
				<li><font>延期至：</font>
				<h1>
						<input id="selectDate" name="selectDate" type="text"
							class="easyui-datebox">
					</h1></li>
				<li><font>延期原因：</font>
				<h1>
						<select id="status" name="status" class="easyui-combobox">
							<option value="0">设备检修</option>
							<option value="1">设备返厂</option>
							<option value="2">优惠活动</option>
							<option value="3">其他原因</option>
						</select>
					</h1></li>
			</ul>
			<div class="ibms_clear"></div>
			<div class="ibms_form_default_textarea">
				<font>备注：</font>
				<h1>
					<textarea id="feedback" name="feedback"></textarea>
				</h1>
			</div>
		</div>
	</div>
	<div class="ibms_form_btn">
		<a href="#" class="query_list_button auto-savebutton"
			data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/asset/manager/delay/save',autoclose:true">保存</a>
		<a href="#" class="query_list_button auto-resetbutton">重 置</a>
	</div>
</form>
