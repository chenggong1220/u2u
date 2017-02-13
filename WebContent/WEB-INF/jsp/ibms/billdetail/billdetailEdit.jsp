<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm" >
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
		<input type="hidden" id="id" name="id" value="${billdetail.id }" />
	        <ul>
	           <li><font>对方账号：</font><h1><input id="accountNum" name="accountNum"  type="text" value="${billdetail.accountNum }"></h1></li>
	        </ul>
	        <ul>
	           <li><font>对方户名：</font><h1><input id="accountName" name="accountName"  type="text" value="${billdetail.accountName }"></h1></li>
	        </ul>     
	        <ul>
	           <li><font>交易日期：</font><h1><input id="dealDate" name="dealDate"  type="text" value="${billdetail.dealDate }"></h1></li>
	        </ul>
	        <ul>
	           <li><font>借方发生额：</font><h1><input name="amount"  type="text" value="${billdetail.amount }"></h1></li>
	        </ul>
	        <ul>
	           <li><font>已开票日期：</font><h1><input name="billdetailingDate"  type="text" value="${billdetail.billdetailingDate }"></h1></li>
	        </ul>
	        <ul>
	           <li><font>财务凭证号：</font><h1><input name="financialNum"  type="text" value="${billdetail.financialNum }"></h1></li>
	        </ul>
        </div>
        <div class="ibms_clear"></div>
	    <div class="ibms_form_btn">
	        <a href="#" class="query_list_button auto-savebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/billdetail/update',autoclose:true">保存</a>
	        <a href="#" class="query_list_button auto-resetbutton">重 置</a>
        </div>
	</div>
</form>

<script type="text/javascript">

</script>