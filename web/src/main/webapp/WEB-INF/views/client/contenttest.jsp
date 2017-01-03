<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type="text/css">

	</style>
	<title>
		<spring:message code="test" />
	</title>
	</head>
		<c:out value="${user.name}"/>
		<c:out value="${user.surname}"/><br>
		<c:out value="${subject.name}"/>
			<table>
				<form:form  modelAttribute="test" method="post"
							action="${pageContext.request.contextPath}/client/checktest">
					<tr>
						<td>
							<label>
								<spring:message code="nametest"  />
							</label>
						</td>
						<td>
							<c:out value="${test.name}"/>
						</td>
					</tr>
		<c:forEach var="question" items="${test.listQuestions}" varStatus="status">
			<tr>
				<td>
					<spring:message  code="question"/>
					<c:out value="${status.count}"/>
				</td>
				<td>
					<form:textarea type="text" path="listQuestions[${status.index}].content"
								   value="${question.content}"/>
				</td>
			</tr>
			<c:forEach var="answer" items="${question.listAnswer}" varStatus="statusAnswer">
				<tr>
					<td>
						<spring:message code="answer"/>
						<c:out value="${statusAnswer.count}"/>
					</td>
					<td>
						<form:input type="text"
									path="listQuestions[${status.index}].listAnswer[${statusAnswer.index}].content"
									value="${answer.content}"/>
					</td>
					<td>
						<form:checkbox
								path="listQuestions[${status.index}].listAnswer[${statusAnswer.index}].rightAnswer" value="false"/>
					</td>
				</tr>
			</c:forEach>
		</c:forEach>
				<tr>
					<td/>
					<td>
						<button>
							<spring:message code="button.checktest"/>
						</button>
					</td>
				</tr>
			</form:form>
		</table>
	</body>
</html>