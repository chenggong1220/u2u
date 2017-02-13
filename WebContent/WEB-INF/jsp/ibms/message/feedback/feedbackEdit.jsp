<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm" >
	<div class="easyui-panel ibms_form_panel">
		<input type="hidden" id="id" name="id" value="${feedback.id }" />
		<div class="ibms_form_default">
	        <ul>
	           <li><font>电话：</font><h1><input name="phone"  type="text" value="${feedback.phone }" disabled="disabled"></h1></li>
	        </ul>      
            <ul>
	           <li><font>提交时间：</font><h1>${feedback.viewDate }</h1></li>
	        </ul> 
	        <div class="ibms_clear"></div>
	        <div class="ibms_form_default_textarea">
	        	<font>反馈内容：</font>
	        	<h1><textarea id="feedback" name="feedback"  disabled="disabled">${feedback.feedback }</textarea></h1>
	        </div>
	        <div class="ibms_clear"></div>
	        <ul>
	        	<li><font>处理状态：</font><h1><input name="status"  type="text" value="${feedback.status==0?'未处理':'处理完成' }" disabled="disabled"></h1></li>
	        </ul>
<!-- 	        <ul> -->
<%-- 	        	<li><font>处理人：</font><h1><input name="phone"  type="text" value="${feedback.handlePerson }"></h1></li> --%>
<!-- 	        </ul> -->
	        <div class="ibms_clear"></div>
	        <div class="ibms_form_default_textarea">
	        	<font>处理意见：</font>
	        	<h1><textarea id="handleMethod" name="handleMethod"  >${feedback.handleMethod }</textarea></h1>
	        </div>
	        <div class="ibms_clear"></div>
        </div>
        <div class="ibms_clear"></div>
	    <div class="ibms_form_btn">
	        <a href="#" class="query_list_button auto-savebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/feedback/update',autoclose:true">提交</a>
	        <a href="#" class="query_list_button auto-resetbutton">重 置</a>
        </div>
	</div>
</form>
