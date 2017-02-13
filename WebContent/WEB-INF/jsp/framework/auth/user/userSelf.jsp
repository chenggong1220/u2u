<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<div class="easyui-panel ibms_form_panel" data-options="iconCls:'icon-search'" title="个人信息">
	<div class="ibms_form_default">
        <ul>
           <li><font>用户名：</font>
               <h1><label><span>${user.username}</span></label></h1>
           </li>
        </ul>
        <div class="ibms_clear"></div>
        <ul>
           <li><font>密码：</font>
               <h1><label><span>********</span></label></h1>
           </li>
        </ul>
        <div class="ibms_clear"></div>
        <ul>
           <li><font>角色：</font>
               <h1><label><span>${user.roles}</span></label></h1>
           </li>
        </ul>
        <div class="ibms_clear"></div>
       </div>
       <div class="ibms_form_btn">
       	<a href="#" id="user_update" class="auto-querybutton">编辑</a>
       </div>
</div>

<script>
$("#user_update").click(function(){
	$("#ibms_main_content_01").load(WEB_APP + "/auth/user/self/update?username=${user.username}");
});
</script>