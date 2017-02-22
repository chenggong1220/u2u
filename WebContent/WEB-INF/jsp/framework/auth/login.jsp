<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>优尼斯U2U融资租赁系统</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/css/easyui/default/easyui.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/css/easyui/icon.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/css/showLoading.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/css/login.css'/>" id="ChangeSkin-login">
<script>
        	var WEB_APP="${pageContext.request.contextPath}"; 
        	var LOGIN_SESSION_USER="${login_session_user}";
        	var LOGIN_SESSION_STYLE = "${login_session_style}";
        </script>
<script type="text/javascript"
	src="<c:url value='/styles/js/lib/jquery.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/styles/js/lib/easyui.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/styles/js/lib/jquery.showLoading.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/styles/js/lib/ibms.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/styles/js/lib/login.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/styles/js/lib/easyui-lang-zh_CN.js'/>"></script>

</head>
<body>
	<div id="mask" class="login">
		<form action="" id="formid">
			<ul class="container">
				<li><span class="user"></span> <input type="text"
					class="vali_username input" name="j_username" /></li>
				<li><span class="password"></span> <input type="password"
					class="vali_password  input password" name="j_password" /> <input
					type="button" class="button" /></li>
				<span class="loginmsg"></span>
			</ul>
		</form>
		<span class="copyright">Copyright (C) 2015-2016 优尼斯U2U融资租赁系统</span>
	</div>
</body>
</html>