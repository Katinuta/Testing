<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
        <spring:message  code="addsub" />
    </title>
	<style>

	</style>
</head>
<body>

		<%--<c:out value="${sessionScope.user.name}"/>--%>
		<%--<c:out value="${sessionScope.user.surname}"/><br>--%>
		<%--<c:url var="tomain" value="">--%>
			<%--<c:param name="command" value="returnMain"/>--%>
		<%--</c:url>--%>
		<%--<a href="${tomain}">--%>
			<%--<spring:message  code="mainpage" />--%>
		<%--</a><br/>--%>
		<%--<c:url var="logout" value="">--%>
			<%--<c:param name="command" value="logout"/>--%>
		<%--</c:url>--%>
		<%--<a href="${logout}">--%>
			<%--<spring:message  code="exit" />--%>
		<%--</a>--%>

	<form:form action="${pageContext.request.contextPath}/admin/savesubject" modelAttribute="subject">
		<table>
			<tr>
				<td >
					<label for="s">
						<spring:message  code="newsubject" />
					</label>
				</td>
				<td>
					<form:input id="s" path="name"/><br/>
				</td>
			</tr>
			<tr/>
			<tr>
				<td/>
				<td>
					<form:button type="submit">
						<spring:message code="save"/>
					</form:button>
				</td>
			</tr>
		</table>		
	</form:form>
</body>
</html>