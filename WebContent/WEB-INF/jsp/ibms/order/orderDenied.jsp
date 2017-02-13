<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm">
	<c:out value="${message==null?'订单已经被废弃，不允许再操作！':message }"></c:out>
</form>
