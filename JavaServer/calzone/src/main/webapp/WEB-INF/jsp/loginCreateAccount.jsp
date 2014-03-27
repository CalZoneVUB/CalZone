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
	  <!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/themes/css/lumen/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/themes/css/dashboard.css"
	rel="stylesheet">


<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
<body>

<jsp:include page="NavigationBar.jsp" />
                      
	<!-- Wrap all page content here -->
	<div class="container">
	<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h1 id="type">
						<spring:message code="loginCreate.title.text" />
					</h1>
				</div>
			</div>
		</div>
	<div class="row">
			<div class="col-lg-4">&nbsp;</div>
			<div class="col-lg-4">
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
								<spring:message code="loginCreate.username.text" var="variable1"/>
								<form:input path="userName" class="form-control" placeholder="${variable1}" />
								<form:errors path="userName" cssClass="error" style="color:red;"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<spring:message code="loginCreate.firstname.text" var="variable2"/>
								<form:input path="person.firstName" class="form-control" placeholder="${variable2}"/>
								<form:errors path="person.firstName" cssClass="error" style="color:red;"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<spring:message code="loginCreate.lastname.text" var="variable3"/>
								<form:input path="person.lastName" class="form-control" placeholder="${variable3}"/>
								<form:errors path="person.lastName" cssClass="error" style="color:red;"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<spring:message code="loginCreate.email.text" var="variable4"/>
								<form:input path="person.email" class="form-control" placeholder="${variable4}"/>
								<form:errors path="person.email" cssClass="error" style="color:red;"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<spring:message code="loginCreate.password.text" var="variable5"/>
								<form:password path="password" class="form-control" placeholder="${variable5}"/>
								<form:errors path="password" cssClass="error" style="color:red;"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<spring:message code="loginCreate.reenterpassword.text" var="variable6"/>
								<input type="password" class="form-control" id="inputPassword" placeholder="${variable6}"/>
							</div>
						</div>
					<div class="form-group">
						<div class="col-lg-12">
							<button type="submit" class="btn btn-primary full-width">Next</button> 
						</div>
					</div>
				</fieldset>
				</form:form>
			</div>
		</div>
	</div>
	
    <script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootswatch.js"></script>
  </body>
</html>