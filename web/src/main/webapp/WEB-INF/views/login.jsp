<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
    <%--pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>--%>

<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--<html>--%>
	<%--<head>--%>
		<%--<title>Login</title>--%>
		<%--<style>--%>
			<%--.block { --%>
			    <%--width: 260px; --%>
				<%--height: 140px;--%>
			    <%--background:  #e0e0d1;--%>
			    <%--border: solid 1px black; --%>
				<%--font-size: 20px;--%>
				<%--margin-left: 35%;--%>
				<%--margin-right: 40%; --%>
				<%--padding: 15px;--%>
				<%--margin-top:15%;--%>
			   <%--}--%>
			  <%--#but{--%>
				<%--width: 120px;--%>
				<%--height: 25px;--%>
			  <%--}--%>
			   <%--font{--%>
			  	 <%--font-size:20px;--%>
			   <%--}--%>
			   <%--#forbut{--%>
			   	<%--font-size:16px;--%>
			   <%--}--%>
		<%--</style>--%>
	<%--</head>--%>
	<%--<body>--%>

	<%--<div>--%>
		 <%--<span style="float:right">--%>
                    <%--<a href="?locale=en">en</a>--%>
                    <%--<a href="?locale=ru">ru</a>--%>
                <%--</span>--%>
			<%--<div class="block">--%>
			<%--<c:if test="{not empty error}">--%>
				<%--<c:out value="${error}"/>--%>
			<%--</c:if>--%>
                <%--<form name='loginForm'--%>
                      <%--action="/j_spring_security_check" method='POST'>--%>

                    <%--<table>--%>
                        <%--<tr>--%>
                            <%--<td>--%>
                                <%--<label for="login">--%>
                                     <%--<spring:message code="login"/>--%>
                                <%--</label>--%>
                            <%--</td>--%>
                            <%--<td><input type='text' name='login' id="login"></td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td>--%>
								<%--<label for="password">--%>
									<%--<spring:message code="password"/>--%>
								<%--</label>--%>
							<%--</td>--%>
                            <%--<td><input type='password' name='password' id="password" /></td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td colspan='2'>--%>
                                <%--<input name="submit" type="submit" value="submit" />--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                    <%--</table>--%>

                    <%--<input type="hidden" name="${_csrf.parameterName}"--%>
                           <%--value="${_csrf.token}" />--%>
					<%--<a href="registration">--%>
						<%--<spring:message code="registration"/>--%>
					<%--</a>--%>

                <%--</form>--%>


				<%--&lt;%&ndash;<form  name="form_login"action="j_spring_security_check" method="post">&ndash;%&gt;--%>

					<%--&lt;%&ndash;<table>&ndash;%&gt;--%>
						<%--&lt;%&ndash;<th><label for="login">&ndash;%&gt;--%>
							<%--&lt;%&ndash;<spring:message code="login"/>&ndash;%&gt;--%>
						<%--&lt;%&ndash;</label></th>&ndash;%&gt;--%>
						<%--&lt;%&ndash;<td><input path="login" name="login" id="login"/> <!-- Поле имени пользователя -->&ndash;%&gt;--%>
						<%--&lt;%&ndash;</td>&ndash;%&gt;--%>
						<%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
						<%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
							<%--&lt;%&ndash;<th><label for="password">&ndash;%&gt;--%>
								<%--&lt;%&ndash;<spring:message code="password" ></spring:message>&ndash;%&gt;--%>
							<%--&lt;%&ndash;</label></th>&ndash;%&gt;--%>
							<%--&lt;%&ndash;<td>&ndash;%&gt;--%>
								<%--&lt;%&ndash;<input path="password" name="password" showPassword="true" id="password"/>&ndash;%&gt;--%>
							<%--&lt;%&ndash;</td>&ndash;%&gt;--%>
						<%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
						<%--&lt;%&ndash;<th>&ndash;%&gt;--%>
							<%--&lt;%&ndash;<td>&ndash;%&gt;--%>
								<%--&lt;%&ndash;<button value="submit">&ndash;%&gt;--%>
									<%--&lt;%&ndash;<spring:message code="login"></spring:message>&ndash;%&gt;--%>
								<%--&lt;%&ndash;</button>&ndash;%&gt;--%>
							<%--&lt;%&ndash;</td>&ndash;%&gt;--%>
						<%--&lt;%&ndash;</th>&ndash;%&gt;--%>

						<%--&lt;%&ndash;</table>&ndash;%&gt;--%>
						<%--&lt;%&ndash;<a href="registration">&ndash;%&gt;--%>
							<%--&lt;%&ndash;<spring:message code="registration"></spring:message>&ndash;%&gt;--%>
						<%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<c:out value="${lang}"/>&ndash;%&gt;--%>
				<%--&lt;%&ndash;</form>&ndash;%&gt;--%>

			<%--</div>--%>
		<%--</div>--%>
	<%--</body>--%>
<%--</html>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<title>Login Page</title>
	<style>
		.error {
			padding: 15px;
			margin-bottom: 20px;
			border: 1px solid transparent;
			border-radius: 4px;
			color: #a94442;
			background-color: #f2dede;
			border-color: #ebccd1;
		}

		.msg {
			padding: 15px;
			margin-bottom: 20px;
			border: 1px solid transparent;
			border-radius: 4px;
			color: #31708f;
			background-color: #d9edf7;
			border-color: #bce8f1;
		}

		#login-box {
			width: 300px;
			padding: 20px;
			margin: 100px auto;
			background: #fff;
			-webkit-border-radius: 2px;
			-moz-border-radius: 2px;
			border: 1px solid #000;
		}
	</style>
</head>
<body onload='document.loginForm.username.focus();'>

<h1>Spring Security Custom Login Form (XML)</h1>

<div id="login-box">

	<h3>Login with Username and Password</h3>

	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>

	<form name='loginForm'
		  action="<c:url value='/j_spring_security_check' />" method='POST'>

		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='login'></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
									   value="submit" /></td>
			</tr>
		</table>

		<input type="hidden" name="${_csrf.parameterName}"
			   value="${_csrf.token}" />

	</form>
</div>

</body>
</html>