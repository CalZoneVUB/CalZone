<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css"
	media="screen">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootswatch.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/calzone.css">
<style>
Body,HTML {
	height: 100%;
	margin: 0;
}
</style>
</head>
<body>
<nav class="navbar navbar-inverse" role="navigation">
<ul>
	<li class="active"><a href="profile">Home</a></li>
	<li class="dropdown">
		<a class="dropdown-toggle" role="button" data-toggle="dropdown" href="#">
	 		Account <span class="caret"></span>
		</a>
		<ul class="dropdown-menu" role="menu">
			<li><a href="profile">Profiel</a></li>	<!--Profile-->
			<li><a href="#">Berichten</a></li>	<!--Messages-->
			<li><a href="#">Instelling</a></li> <!--Settings-->
		</ul>
	</li>
	<li><a href="#">Kalender</a></li>	<!--Calendar-->
	<li><a href="EnrolledCourses">Vakken</a></li>		<!--Classes-->
	<li><a href="#">Help</a></li>
</ul>
</nav>
</body>
</html>