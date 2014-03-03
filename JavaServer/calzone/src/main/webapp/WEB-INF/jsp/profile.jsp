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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css"
	media="screen">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootswatch.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/calzone.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/profile.css">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
      <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<script src="${pageContext.request.contextPath}/js/bsa.js"></script>

	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="${pageContext.request.contextPath}/" class="navbar-brand"><spring:message
						code="navbar.calzone.text" /></a>
				<button class="navbar-toggle" type="button" data-toggle="collapse"
					data-target="#navbar-main">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="navbar-collapse collapse" id="navbar-main">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}"><spring:message
								code="navbar.home.text" /></a></li>
					<li><a><spring:message code="navbar.account.text" /></a></li>
					<li><a><spring:message code="navbar.courses.text" /></a></li>
					<li><a href="${pageContext.request.contextPath}/hello/"><spring:message
								code="navbar.calendar.text" /></a></li>
					<li><a><spring:message code="navbar.help.text" /></a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="?lang=en">English</a></li>
					<li><a href="?lang=nl">Nederlands</a></li>
					<li><a href="<c:url value='j_spring_security_logout' />"><spring:message
								code="navbar.logout.text" /></a></li>
				</ul>

			</div>
		</div>
	</div>

	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h1 id="type">
						<spring:message code="profile.title.text" />
					</h1>
				</div>
			</div>
		</div>

		<!-- Nav tabs -->
		<ul class="nav nav-tabs">
			<li class="active"><a href="#profile" data-toggle="tab"> <spring:message
						code="profile.tab.profile.text" />
			</a></li>
			<li><a href="#messages" data-toggle="tab"> <spring:message
						code="profile.tab.messages.text" />
			</a></li>
			<li><a href="#settings" data-toggle="tab"> <spring:message
						code="profile.tab.settings.text" />
			</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane active" id="profile">
				<div class="row">
					<c:if test="${'fail' eq param.lookup}">
						<div style="color: red">
							User not in the database.<br />
						</div>
					</c:if>

					<div class="col-lg-6 outer-box">
						<div class="box-shadow profile-box">
							<form:form commandName="user" method="POST">
								<h4>
									<strong><spring:message code="profile.username.text" /></strong>
									<form:input path="userName" class="form-control"
										readonly="true" />
									<form:errors path="firstName" cssClass="error"></form:errors>
								</h4>
								<br>
								<h4>
									<strong><spring:message code="profile.firstName.text" /></strong>
									<form:input path="firstName" class="form-control" />
									<form:errors path="firstName" cssClass="error"></form:errors>
								</h4>
								<h4>
									<strong><spring:message code="profile.lastName.text" /></strong>
									<form:input path="lastName" class="form-control"/>
									<form:errors path="lastName" cssClass="error"></form:errors>
								</h4>
								<br>
								<h4>
									<strong><spring:message
											code="profile.userTypeName.text" /></strong>
									<form:input path="userTypeName" class="form-control"
										readonly="true" />
									<form:errors path="userTypeName" cssClass="error"></form:errors>

								</h4>
								<h4>
									<strong><spring:message code="profile.email.text" /></strong>
									<form:input path="email" class="form-control" readonly="true" />
									<form:errors path="email" cssClass="error"></form:errors>
								</h4>
								<h4>
									<strong><spring:message code="profile.birthDate.text" /></strong>
									<form:input path="birthdate" class="form-control" />
									<form:errors path="birthdate" cssClass="error"></form:errors>
								</h4>
								<h4>
									<strong><spring:message code="profile.language.text" /></strong>
									<form:input path="language" class="form-control" />
									<form:errors path="language" cssClass="error"></form:errors>
								</h4>
								<h4>
									<form:hidden path="password" class="form-control"
										readonly="true" />
									<form:errors path="password" cssClass="error"></form:errors>
								</h4>
								<button type="submit" class="btn btn-primary full-width">Update</button>
							</form:form>
						</div>
					</div>

				</div>
			</div>
			<div class="tab-pane" id="messages">

				<div class="col-lg-6 outer-box">
					<div class="box-shadow profile-box">
						<spring:message code="profile.tab.notifications.text" />
					</div>
				</div>

			</div>
			<div class="tab-pane" id="settings">...</div>

		</div>


		<script
			src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootswatch.js"></script>
</body>
</html>