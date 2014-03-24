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
	href="${pageContext.request.contextPath}/themes/css/lumen/bootstrap.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/themes/css/dashboard.css"
	rel="stylesheet">

<!-- Additional styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/themes/css/agenda.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/themes/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/themes/css/custom.css">

<!-- JavaScript at bottom except for Modernizr -->
<script
	src="${pageContext.request.contextPath}/themes/js/libs/modernizr.custom.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>
<body>
	<script src="${pageContext.request.contextPath}/js/bsa.js"></script>

	<jsp:include page="NavigationBarSignedIn.jsp" />

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
									<form:input path="userName" class="form-control" readonly="true"/>
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
									<form:input path="lastName" class="form-control" />
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
		<script src="${pageContext.request.contextPath}/js/calzone.js"></script>
</body>
</html>