<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Profile Page</h1>

	<c:if test="${'fail' eq param.lookup}">
		<div style="color: red">
			User not in the database.<br />
		</div>
	</c:if>

	<p>Username: ${user.getUserName()}</p>
	<p>Firstname: ${firstname}</p>
	<p>Lastname: ${lastname}</p>
	<p>email: ${email}</p>


</body>
</html>