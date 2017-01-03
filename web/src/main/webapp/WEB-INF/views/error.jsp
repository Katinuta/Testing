<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<title>
		<spring:message code="error"  />
	</title>

	</head>
	<body class="main">
		<div >
			<div id="box2">
				<c:out value="${sessionScope.user.name}"/>
				<c:out value="${sessionScope.user.surname}"/><br>

				<a href="${tomain}">
					<spring:message  key="return" bundle="${bundle}" />
				</a><br/>
				<c:url var="logout" value="/controller">
					<c:param name="command" value="logout"/>
				</c:url>
				<a href="${logout}">
					<fmt:message  key="exit" bundle="${bundle}" />
				</a>
				<fmt:message  key="error" bundle="${bundle}" />
			</div>
			<div id="box1">
				<c:out value="${sessionScope.subject.name}"/>
			</div>


		</div>	
	</body>
</html>