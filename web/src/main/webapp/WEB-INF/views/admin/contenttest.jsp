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
							action="${pageContext.request.contextPath}/admin/addquestion">
					<c:choose>
						<c:when test="${empty test.name}">
								<tr>
									<td>
										<label>
											<spring:message code="newtest"/>
										</label>
									</td>
									<td>
										<form:input  path="name" id="nametest"/>
									</td>
								</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td>
									<label for="nametest">
										<spring:message code="nametest"  />
									</label>
								</td>
								<td>
									<c:out value="${test.name}"/>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
					<tr>
						<td>
							<button type="submit">
								<spring:message code="addquestion"/>
							</button>
						</td>
					</tr>
				</form:form>
			</table>
		<c:if test="${not empty test.name}">
			<c:forEach var="question" items="${test.listQuestions}">
				<table>
					<tr>
						<td>
							<c:out value="${question.content}"/>
						</td>
						<td/>
					</tr>
					<c:forEach var="answer" items="${question.listAnswer}">
						<tr>
							<td>
								<c:out value="${answer.content}"/>
							</td>
							<td>
								<c:choose>
									<c:when test="${answer.rightAnswer}">
										<input type="checkbox" checked/>
									</c:when>
									<c:otherwise>
										<input type="checkbox"/>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:forEach>
		</c:if>
		<form action="${pageContext.request.contextPath}/admin/savetest" method="post">
			<table>
				<tr>
					<td/>
					<td>
						<button>
							<spring:message code="savetest"/>
						</button>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>