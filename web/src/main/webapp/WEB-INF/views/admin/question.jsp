<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>
			<spring:message code="createquestion"/>
		</title>
		<style>
		</style>
	</head>
	<body >
		<form:form action="${pageContext.request.contextPath}/admin/savequestion" method="post" modelAttribute="question">
			<table>
				<tr>
					<td>
						<label for="question">
							<spring:message  code="question"/>
						</label>
					</td>
					<td>
						<form:textarea id="question" path="content" cols="30" rows="5"></form:textarea><br/>
					</td>
				</tr>
				<c:forEach var="answer" items="${question.listAnswer}" varStatus="status">
					<tr>
						<td>
							<label>
								<spring:message  code="answer"/>
							</label>
						</td>
					</tr>
					<tr>
						<td/>
						<td>
							<form:input type="text" path="listAnswer[${status.index}].content"  />
						</td>
						<td>
							<form:checkbox path="listAnswer[${status.index}].rightAnswer"/>
						</td>
					</tr>
				</c:forEach>
					<tr>
						<td/>
						<td>
							<button type="submit">
								<spring:message code="savequestion"/>
							</button>
						</td>
					</tr>
				</table>
		</form:form>
	</body>
</html>