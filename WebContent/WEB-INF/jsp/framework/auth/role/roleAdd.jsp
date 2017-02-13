<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<script type="text/javascript"
	src="<c:url value='/auth/role/js/role.js'/>"></script>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>


<form id="addForm">
	<div id="role" class="easyui-layout" fit="true">
	    <div data-options="region:'north'" style="height:30px;overflow:hidden;">
	   	    <div class="easyui-toolbar">
		       	<a href="#" class="easyui-linkbutton auto-savebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/auth/role/save',autoclose:true">添加</a>
				<a id="resetbutton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true">重 置</a>
	        </div>
	    </div>
	    <div data-options="region:'west'" style="width:200px;padding:5px;">
	        <span style="display:block; padding-bottom:5px;">角色名：</span>
	        <input id="rolername" name="rolename" type="text" class="easyui-validatebox" data-options="required:true,validType:'minLength[1]'">
	    </div>
	    <div data-options="region:'center'" style="padding:5px;">
	        <span style="display:block; padding-bottom:5px;">资源：</span>
	        <div id="cc"></div>
	        <input id="resources" name="resources" value="" type="hidden">
	    </div>
	</div>
</form>

<!-- <form id="addForm"> -->
<!-- 	<div class="easyui-panel ibms_form_panel"> -->
<!-- 		<div class="ibms_form_default"> -->
<!-- 	        <ul> -->
<!-- 	           <li><font>角色名：</font><h1><input id="rolername" name="rolename" type="text" class="easyui-validatebox" data-options="required:true,validType:'minLength[1]'"></h1></li> -->
<!-- 	           </li> -->
<!-- 	        </ul> -->
<!-- 	        <div class="ibms_clear"></div> -->
<!-- 	        <ul> -->
<!-- 				<li><font>资源： </font></li> -->
<!-- 			</ul> -->
<!--         </div> -->
<!-- 		<div class="ibms_clear"></div> -->
<!-- 	   	<div id="cc" style="padding-left: 80px;"></div> -->
<!-- 	   	<input id="resources" name="resources" value="" type="hidden"> -->
<!-- 	</div> -->
<!--     <div class="ibms_form_btn"> -->
<%--      	<a href="#" class="query_list_button auto-savebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/auth/role/save',autoclose:true">添加</a> --%>
<!-- 		<a id="resetbutton" href="#" class="query_list_button">重 置</a> -->
<!--     </div> -->
<!-- </form> -->