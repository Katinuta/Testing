<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; UTF-8">
		<title>
			<fmt:message  key="result" bundle="${bundle}" />
		</title>
		<style type="text/css">

		</style>
	</head>
	<body>
		<div>
			<c:out value="${user.name}"/>
			<c:out value="${user.surname}"/><br/>
		</div>
		<div>
			<c:out value="${subject.name}"/><br/>
			<c:out value="${test.name}"/><br/>
			<c:out value="${result.result}"/>
			<spring:message code="from"/>
			<spring:message code="page.maxresult"/><br/>
            <a href="${pageContext.request.contextPath}/client/subjects/1">
                <spring:message code="return"/>
            </a>
		</div>
	</body>
</html>