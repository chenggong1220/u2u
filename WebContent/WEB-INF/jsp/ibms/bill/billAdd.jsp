<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm" >
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
	        <ul>
	           <li><font>对方账号：</font><h1><input id="accountNum" name="accountNum"  type="text" ></h1></li>
	        </ul>
	        <ul>
	           <li><font>对方户名：</font><h1><input id="accountName" name="accountName"  type="text" ></h1></li>
	        </ul>
	       	<ul>
	           <li><font>客户姓名：</font><h1><input id="cusName" name="cusName"  type="text" value="${bill.cusName }"></h1></li>
	        </ul>     
	        <ul>
	           <li><font>联系电话：</font><h1><input id="cusNum" name="cusNum"  type="text" value="${bill.cusNum }"></h1></li>
	        </ul>      
	        <ul>
	           <li><font>交易日期：</font><h1><input id="dealDate" name="dealDate"  type="text"  class="easyui-datebox"></h1></li>
	        </ul>
	        <ul>
	           <li><font>借方发生额：</font><h1><input name="amount"  type="text" ></h1></li>
	        </ul>
	        <ul>
	           <li><font>已开票日期：</font><h1><input name="billingDate"  type="text" class="easyui-datebox"></h1></li>
	        </ul>
	        <ul>
	           <li><font>财务凭证号：</font><h1><input name="financialNum"  type="text" ></h1></li>
	        </ul>
        </div>
        <div class="ibms_clear"></div>
	    <div class="ibms_form_btn">
	        <a href="#" class="query_list_button auto-savebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/bill/save',autoclose:true">保存</a>
	        <a href="#" class="query_list_button auto-resetbutton">重 置</a>
        </div>
	</div>
</form>

<script type="text/javascript">

</script>