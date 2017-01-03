<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type="text/css">
		table{
			border-collapse: collapse;
			border-spacing: 7px 11px;
		}
	</style>
</head>
<body>
<c:url value="/login?logout" var="logoutUrl" />
<a href="${logoutUrl}">
	<spring:message code="exit" />
</a>
<sec:authentication var="principal" property="principal" />
<c:out value="${principal.username}"/>
<table>
	<c:forEach var="subject" items="${subjectList}"  >
		<tr>
			<td>
				<c:out value="${subject.name}"/>
			</td>
			<td>
				<a href="${pageContext.request.contextPath}/client/tests?subjectId=${subject.subjectId}&currentPage=1">
					<spring:message code="passtest"/>
				</a>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<c:forEach var="number" items="${listPages}">
			<a href="${pageContext.request.contextPath}/client/subjects/${number}">${number}</a>
		</c:forEach>
	</tr>
</table>
</body>
</html>