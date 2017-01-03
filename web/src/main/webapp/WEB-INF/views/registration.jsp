<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<title>
		<fmt:message  key="registration" bundle="${bundle}" />
	</title>
	<style type="text/css">
		.main {
			background:  url("main_1.jpg")  ;
			background-repeat: no-repeat;
			background-position:center center;
			height: 100vh;
			background-size: 100%;
		}
		#box3{
			position:absolute;
			top: 80px;
			left:500px;
			width:600px;
		}
		.td1col{
			width:100px;
			font-size: 24px;
			font-weight: bold;
			font-style: italic;
			text-align: left
		}
		.td2col{
			width:250px;
		}
		legend{
			font-size: 30px;
			font-weight: bolder;
			text-align: center;
		}
		button{
			font-size: 20px;
		}
		input{
			width:250px;
			height: 25px;
		}
	</style>
</head>
	<body class="main">

	<div class="block">

		<form:form action="add-user" modelAttribute="user">
			<span style="float:right">
                    <a href="?lang=en">en</a>
                    <a href="?lang=ru">ru</a>
                </span>
		<table>
			<th><label for="login"><spring:message code="login"/></label></th>
			<td><form:input path="login" id="login"/> <!-- Поле имени пользователя -->
			</td>
			</tr>
			<tr>
				<th><label for="password">Password:</label></th>
				<td>
					<form:password path="password" showPassword="true" id="password"/>
				</td>
			</tr>
			<tr>
				<th><label for="name">Name:</label></th>
				<td>
					<form:input path="name"  id="name"/>
				</td>
			</tr>
			<tr>
				<th><label for="surname">Surname:</label></th>
				<td>
					<form:input path="surname"  id="surname"/>
				</td>
			</tr>

			<th>
			<td>
				<form:button value="submit">Submit</form:button>
			</td>
			</th>

		</table>

		</form:form>

		<%--<form action="controller" method="GET">--%>
			<%--<input type="hidden" name="command" value="registration"/>--%>
			<%--<div id="box3">--%>
				<%--<fieldset  style="width: 60%">--%>
					<%--<legend>--%>
                        <%--<fmt:message  key="input" bundle="${bundle}" />--%>
                    <%--</legend>--%>
					<%--<table>--%>
						<%--<tr>--%>
							<%--<td class="td1col">--%>
								<%--<label for="name">--%>
                                    <%--<fmt:message  key="name" bundle="${bundle}" />--%>
                                <%--</label>--%>
							<%--</td>--%>
							<%--<td class="td2col">--%>
								<%--<input type="text" id="name" name="name" placeholder="Name" required pattern="^[a-zA-Zа-яА-Я]+$">--%>
							<%--</td>--%>
						<%--</tr>--%>
						<%--<tr>--%>
							<%--<td class="td1col">--%>
								<%--<label for="surname">--%>
                                    <%--<fmt:message  key="surname" bundle="${bundle}" />--%>
                                <%--</label>--%>
							<%--</td>--%>
							<%--<td class="td2col">--%>
								<%--<input type="text" id="surname" name="surname" placeholder="Surname" required pattern="^[a-zA-Zа-яА-Я]+$">--%>
							<%--</td>--%>
						<%--</tr>--%>
						<%--<tr>--%>
							<%--<td class="td1col">--%>
								<%--<label for="login">--%>
									<%--<fmt:message  key="login" bundle="${bundle}" />--%>
								<%--</label>--%>
							<%--</td>--%>
							<%--<td class="td2col">--%>
								<%--<input type="text" id="login" name="login" placeholder="Login" required>--%>
							<%--</td>--%>
						<%--</tr>--%>
						<%--<tr>--%>
							<%--<td class="td1col">--%>
								<%--<label for="password">--%>
									<%--<fmt:message  key="password" bundle="${bundle}"/>--%>
								<%--</label>--%>
							<%--</td>--%>
							<%--<td class="td2col">--%>
								<%--<input type="password" id="password" name="password" placeholder="Password" required>--%>
							<%--</td>--%>
						<%--</tr>--%>
						<%--<tr class="td1col">--%>
							<%--<td>${errorRegMessage}</td>--%>
							<%--<td>--%>
								<%--<button type="submit" name="savebutton">--%>
                                    <%--<fmt:message  key="book" bundle="${bundle}"/>--%>
                                <%--</button>--%>
							<%--</td>--%>
						<%--</tr>--%>
					<%--</table>--%>
				<%--</fieldset>--%>
			<%--</div>--%>
		<%--</form>--%>
	</body>
</html>