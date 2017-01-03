<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<title>
		<spring:message code="pagetest" />
	</title>
	<style type="text/css">

		table{
			 border-collapse:separate;
			 border-spacing: 7px 11px;
		}

	</style>
	</head>
	<body>
		<c:out value="${sessionScope.user.name}"/>
		<c:out value="${sessionScope.user.surname}"/><br>
		<a href="${pageContext.request.contextPath}/client/subjects/1">
			<spring:message code="return"/>
		</a>
		<br/>
		<c:url value="/login?logout" var="logoutUrl" />
		<a href="${logoutUrl}">
			<spring:message code="exit" />
		</a>
		<c:out value="${subject.name}"/>
		<table border="1">
			<c:forEach var="test" items="${testList}" >
				<tr >
					<td>
						<c:out value="${test.name}"/>
					</td>
					<td>
						<form action="${pageContext.request.contextPath}/client/passtest" method="GET">
							<button  type="submit" value="${test.testId}" name="testId">
								<spring:message  code="passtest"/>
							</button>
						</form>
					</td>

				</tr>
			</c:forEach>
				<tr/>
			<tr>
				<td/>
					<c:forEach var="number" items="${listPages}">
						<a href="${pageContext.request.contextPath}/tests?subjectId=${subject.subjectId}&currentPage=${number}">${number}</a>
					</c:forEach>
				<td/>
			</tr>
		</table>
	</body>
</html>