<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Account Pagina</title>
</head>
<body>
Language <a href="?language=en">English</a> | <a href="?language=nl">Nederlands</a>

<form:form commandName="user">
	<table>
		<tr>
			<td><spring:message code="login.username.text"/></td>
			<td><form:input path="userName"/></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><form:password path="password"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="Login"/></td>
		</tr>
	</table>
</form:form>

</body>
</html>