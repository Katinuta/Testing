<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
		<spring:message code="test" />
	</title>
	</head>
	<body>
		<c:out value="${user.name}"/>
		<c:out value="${user.surname}"/><br>
		<c:out value="${subject.name}"/>
			<table>
				<form:form  modelAttribute="test" method="post"
							action="${pageContext.request.contextPath}/admin/savetest">
					<tr>
						<td>
							<label>
								<spring:message code="nametest"/>
							</label>
						</td>
						<td>
							<form:input  path="name" id="nametest" value="${test.name}"/>
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
							<td>
								<form action="${pageContext.request.contextPath}/admin/deletequestion" method="GET">
									<button  type="submit" value="${question.questionId}" name="questionId">
										<spring:message code="delete"/>
									</button>
								</form>
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
									<c:choose>
										<c:when test="${answer.rightAnswer}">
											<form:checkbox
													path="listQuestions[${status.index}].listAnswer[${statusAnswer.index}].rightAnswer"
													checked="true"/>
										</c:when>
										<c:otherwise>
											<form:checkbox
													path="listQuestions[${status.index}].listAnswer[${statusAnswer.index}].rightAnswer"
													/>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</c:forEach>
					<tr>
						<td/>
						<td>
							<button type="submit">
								<spring:message code="savetest"/>
							</button>
						</td>
					</tr>
				</form:form>

				<form:form action="${pageContext.request.contextPath}/admin/addquestion" method="post">
					<tr/>
					<tr>
						<td/>
						<td>
							<button>
								<spring:message code="addquestion"/>
							</button>
						</td>
					</tr>
				</form:form>
			</table>
	</body>
</html>