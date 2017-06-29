<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.sys1.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>优尼斯U2U融资租赁系统 - 后台管理系统</title>

</head>
<body>
	<div id="mask">
		<div class="header">
			<a class="logo" href="#"></a>
			<ul class="main-nav">
<!-- 
		<li class="active"><a class="monitor" href="#" id="1">集中监控</a></li>
		<li><a class="warning" href="#" id="2">集中告警</a></li>
		<li><a class="config" href="#" id="3">联动配置</a></li>
		<li><a class="analysis" href="#" id="4">统计分析</a></li>
		<li><a class="access-control" href="#" id="5">权限管理</a></li>
		<li><a class="log-manage" href="#" id="6">日志管理</a></li>
-->
			</ul>
			<div class="user-logined">
				<div>
					<span>欢迎</span>&nbsp;<span class="name">${login_session_user}</span>&nbsp;<span>登录！</span>
				</div>
				<div>
					<span id="sys_time_span">2015/1/27 10:46:12</span>
				</div>
				<a class="icon-logout" href="#"></a> <a id="logout"
					href="${pageContext.request.contextPath}/auth/logout"></a>
				<script type="text/javascript">
			$(".icon-logout").click(function(){
				$.messager.confirm("退出提示", "确定要退出么？", function(r) {
					if (r){
						window.location.href = "${pageContext.request.contextPath}/auth/logout";
					}
				});
			});
		</script>
			</div>
		</div>
		<!-- .header ends -->
		<div id="outermost" class="outermost"></div>
		<!-- <div  class="warning-wrapper"> -->
		<!-- 	<a class="warning-triger" href="javascript: void(0);"></a> -->
		<!-- 	<div id="bottom_alarm_list" class="cont"> -->
		<!-- <p>dumy dumy <br> dumy dumy <br> dumy dumy</p> -->
		<!-- 	</div> -->
		<!-- </div> -->

	</div>
</body>
</html>