<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
        <fmt:message  key="addsub" bundle="${bundle}" />
    </title>
	<style>
	.main {
		background:  url("main_1.jpg")  ;
		background-repeat: no-repeat;
		background-position:center center;
		height: 100vh;
		background-size: 100%;
	}
	#box2 {
		position:absolute;
		top: 20px;
		left: 50px;
		font-size: 25px;
		font-weight: bold;
		font-style: italic;
	}
	#box3{
		position:absolute;
		top: 100px;
		left:500px;
	}
	#subject{
		width:150px;
		height: 30px;
	}
	.td1col{
		width:200px;
		font-size: 20px;
		font-weight: bold;
		font-style: italic;
		text-align: center;
	}
	button{
		font-size: 18px;
		font-weight: bold;
	}
	</style>
</head>
<body class="main">
	<div id="box2">
		<c:out value="${sessionScope.user.name}"/>
		<c:out value="${sessionScope.user.surname}"/><br>
		<c:url var="tomain" value="/controller">
			<c:param name="command" value="returnMain"/>
		</c:url>
		<a href="${tomain}">
			<fmt:message  key="return" bundle="${bundle}" />
		</a><br/>
		<c:url var="logout" value="/controller">
			<c:param name="command" value="logout"/>
		</c:url>
		<a href="${logout}">
			<fmt:message  key="exit" bundle="${bundle}" />
		</a>
	</div>
	<form action="controller" method="GET">
	<input type="hidden" name="command" value="savesubject">
		<div id="box3">
			<table>
			<tr>
				<td class="td1col">
					<label for="subject">
						<fmt:message  key="newsubject" bundle="${bundle}" />
					</label>
				</td>
				<td>
					<input id="subject" type="text" name="subject"/><br/>
				</td>
			</tr>
			<tr/>
			<tr>
				<td/>
				<td>
					<button type="submit">
						<fmt:message  key="save" bundle="${bundle}" />
					</button>
				</td>
			</tr>
		</table>		
		</div>
	</form>
</body>
</html>