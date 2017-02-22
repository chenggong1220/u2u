<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript"
	src="<c:url value='/styles/js/lib/jquery.js'/>"></script>
<html>
<title>Send Message by DingTalk</title>
<meta charset="UTF-8">
<head>



<script type="text/javascript">

$(function() {

	$("#subbtn").click(function() {
		$.get("http://localhost:8089/U2U/web/message/alidayusms");

	});

});

</script>
</head>
<body>
	<p>
		<label for="userid">User ID:</label> <input id="userid" name="userid"
			type="text" value="0556376722-1854742885" />
	</p>

	<p>
		<label for="msgvalue">发送消息内容：</label> <input id="message"
			name="message " type="text" value="Value" />
	</p>

	<span id="backdata"></span>
	<p>
		<input id="subbtn" type="button" value="发送消息" />
	</p>
</body>
</html>