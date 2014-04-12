<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html lang="en">
<head>
<title>CalZone</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" media="screen">
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/themes/css/lumen/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/themes/css/dashboard.css" rel="stylesheet">
<style>
Body,HTML {
	height: 100%;
	margin: 0;
}
</style>
</head>
<body>

	<jsp:include page="NavigationBar.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h1 id="type">
						<spring:message code="passwordforgotapply.title.text" />
					</h1>
				</div>
			</div>
		</div>

		<div class="row">
			<!-- Wrap all page content here -->
			<div id="wrap">
				<div id='myoutercontainer' class="container ">
					<div class="col-lg-4">&nbsp;</div>
					<div class="col-lg-4 box-top box-shadow">
						<form:form commandName="password"
							class="bs-example form-horizontal">
							<fieldset>
								<a href="${pageContext.request.contextPath}" class="left">Back</a>
								<div class="form-group">
									<div class="col-lg-12">
										<form:errors path="*" cssClass="error" style="color:red;"></form:errors>
									</div>
									<div class="col-lg-12">
										<form:password path="password" class="form-control"
											placeholder="New Password" />
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-12">
										<form:password path="confirmPassword" class="form-control"
											placeholder="Repeat Password" />
									</div>
								</div>
							</fieldset>
					</div>
				</div>
			</div>

			<div id="footer">
				<div class="container">
					<div class="col-lg-4">&nbsp;</div>
					<div class="col-lg-4 box-bottom box-shadow">
						<div class="bs-example form-horizontal">
							<fieldset>
								<div class="form-group">
									<div class="col-lg-12">
										<button type="submit" class="btn btn-primary full-width">Next</button>
									</div>
								</div>
							</fieldset>
						</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
		<script
			src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootswatch.js"></script>
</body>
</html>