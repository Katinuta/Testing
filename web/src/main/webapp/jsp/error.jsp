<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<title>
		<fmt:message  key="pagetest" bundle="${bundle}" />
	</title>
	<style type="text/css">
		.main {
			background:  url("jsp/img/main_1.jpg")  ;
			background-repeat: no-repeat;
			background-position:center center;
			height: 100vh;
			background-size: 100%;
		}
		#box2 {
			position:absolute;
			top: 50px;
			left: 50px;
			font-size: 25px;
			font-weight: bold;
			font-style: italic;
		}
		#box1{
			position:absolute;
			left:500px;
			width:400px;
			top: 40px;
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
			width:115px;
			text-align: center;
		}
		#box3{
			position:absolute;
			top: 80px;
			left:400px;
		}
		table{
			 border-collapse:separate;
			 border-spacing: 7px 11px;
		}
		button{
			font-size: 16px;
			font-weight: bold;
		}
	</style>
	</head>
	<body class="main">
		<div >
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
				<fmt:message  key="error" bundle="${bundle}" />
			</div>
			<div id="box1">
				<c:out value="${sessionScope.subject.name}"/>
			</div>


		</div>	
	</body>
</html>