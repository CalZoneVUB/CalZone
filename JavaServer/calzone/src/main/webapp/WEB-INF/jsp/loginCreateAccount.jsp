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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootswatch.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/calzone.css">
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	  <script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
	  <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
	<![endif]-->
	<style>
	Body, HTML{
		height:100%;
		margin: 0;
	}
	</style>
	</head>
<body>
	<!-- Wrap all page content here -->
	<div id="wrap">
		<div id='myoutercontainer' class="container ">
			<div class="col-lg-4">&nbsp;</div>
			<div class="col-lg-4 box-top box-shadow">
				<form:form commandName="user" class="bs-example form-horizontal">
					<c:if test="${'fail' eq param.userNameTest}">
						<div style="color: red">
							User Name already exist. Pick a new one please<br />
						</div>
					</c:if>
					<c:if test="${'fail' eq param.emailTest}">
						<div style="color: red">
							Email already exists. Pick a new one please.<br />
						</div>
					</c:if>
					<fieldset>
						<a href="${pageContext.request.contextPath}" class="left" >Back</a>
						<div class="form-group">
							<div class="col-lg-12">
								<form:input path="userName" class="form-control" placeholder="Username" />
								<form:errors path="userName" cssClass="error"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<form:input path="firstName" class="form-control" placeholder="First Name"/>
								<form:errors path="firstName" cssClass="error"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<form:input path="lastName" class="form-control" placeholder="Last Name"/>
								<form:errors path="lastName" cssClass="error"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<form:input path="email" class="form-control" placeholder="Email"/>
								<form:errors path="email" cssClass="error"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<form:input path="password" class="form-control" placeholder="Password"/>
								<form:errors path="password" cssClass="error"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<input type="password" class="form-control" id="inputPassword" placeholder="Reenter Password">
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
	
    <script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootswatch.js"></script>
  </body>
</html>