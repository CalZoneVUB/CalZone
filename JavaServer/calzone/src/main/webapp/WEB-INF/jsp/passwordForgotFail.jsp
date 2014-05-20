<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html lang="en">
<head>
<title>CalZone</title>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta charset="utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css"
	media="screen">
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/themes/css/lumen/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/themes/css/dashboard.css"
	rel="stylesheet">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	  <script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
	  <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
	<![endif]-->
<style>
Body,HTML {
	height: 100%;
	margin: 0;
}
</style>
</head>
<body>
	<div class="container ">
		<div class="col-lg-4">&nbsp;</div>
		<div class="col-lg-4 box-top box-shadow">
			<form class="bs-example form-horizontal">
				<fieldset>
					<div class="spacer" /></div>
					<h3>
						<spring:message code="passwordForgotFail.info.text"/> <br>
						<br>
					</h3>
					<div class="form-group">
						<div class="col-lg-12">
							<a href="${pageContext.request.contextPath}"><div
									class="btn btn-primary full-width">
									<spring:message code="passwordForgotFail.home.text"/>
									</div></a>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>


</body>
</html>