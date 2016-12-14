<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css">


			caption{
				font-size: 30px;
				font-weight: bolder;
				text-align: center;
			}
			.td1col{
				width:300px;
				font-size: 24px;
				font-weight: bold;
				font-style: italic;
				text-align: center;
			}
			.td2col{
				width:200px;
				font-size: 16px;
				font-weight: bold;
			}
			table{
				 border-collapse:separate;
				 border-spacing: 7px 11px;
			}
		</style>
	</head>
	<body>
	<c:out value="${user.name}"/>
	<c:out value="${user.surname}"/><br/>
		<table >

			<c:forEach var="subject" items="${listSubject}"  >
				<tr>
					<td class="td1col">
					<c:out value="${subject.name}"/>
					</td>
					<td/>
					<td class="td2col">
						<button  type="submit" value="${subject.subjectId}" name="but">--%>
							<fmt:message  key="butpass" bundle="${bundle}" />
						</button>

					</td>
				</tr>
			</c:forEach>
				<tr>
					<td/>
					<c:forEach var="number" items="${listPages}">
					<a href="${number}">${number}</a>
				</c:forEach>
					<td/>
				</tr>
	</body>
</html>