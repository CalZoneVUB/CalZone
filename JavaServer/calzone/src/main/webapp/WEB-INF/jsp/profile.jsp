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
<link href="${pageContext.request.contextPath}/themes/css/lumen/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/themes/css/lumen/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/themes/css/dashboard.css" rel="stylesheet">

<!-- Additional styles -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/themes/css/agenda.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/themes/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/themes/css/custom.css">

<!-- x-editable (bootstrap version) -->
<link href="${pageContext.request.contextPath}/css/bootstrap-editable.css" rel="stylesheet"/>

<!-- JavaScript at bottom except for Modernizr -->
<script src="${pageContext.request.contextPath}/themes/js/libs/modernizr.custom.js"></script>



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
							<%-- <form:form commandName="user" method="POST"> --%>
								<br>
								<h4>
									<strong><spring:message code="profile.username.text" /></strong>
									<a class="row1" href="#" data-type="text" data-pk="1"> ${user.userName} </a>
									<%-- <form:input path="userName" class="form-control" readonly="true"/>
									<form:errors path="firstName" cssClass="error"></form:errors> --%>
									
								</h4>
								<!-- <br> -->
								<h4>
									<strong><spring:message code="profile.firstName.text" /></strong>
									<a class="row2" href="#" data-type="text" data-pk="1"> ${user.firstName}</a>
									<%-- <form:input path="firstName" class="form-control" />
									<form:errors path="firstName" cssClass="error"></form:errors> --%>
								</h4>
								<h4>
									<strong><spring:message code="profile.lastName.text" /></strong>
									<a class="row3" href="#" data-type="text" data-pk="1"> ${user.lastName}</a>
									<%-- <form:input path="lastName" class="form-control" />
									<form:errors path="lastName" cssClass="error"></form:errors> --%>
								</h4>
								<!-- <br> -->
								<h4>
									<strong><spring:message code="profile.userTypeName.text" /></strong>
									<a class="row4" href="#" data-type="text" data-pk="1"> ${user.userTypeName}</a>
									<%-- <form:input path="userTypeName" class="form-control" readonly="true"/>
									<form:errors path="userTypeName" cssClass="error"></form:errors> --%>

								</h4>
								<h4>
									<strong><spring:message code="profile.email.text" /></strong>
									<a class="row5" href="#" data-type="text" data-pk="1"> ${user.email}</a>
									<%-- <form:input path="email" class="form-control" readonly="true" />
									<form:errors path="email" cssClass="error"></form:errors> --%>
								</h4>
								<h4>
									<strong><spring:message code="profile.birthDate.text" /></strong>
									<a class="row6" href="#" data-type="date" data-pk="1"> ${user.birthdate}</a>
									<%-- <form:input path="birthdate" class="form-control" />
									<form:errors path="birthdate" cssClass="error"></form:errors> --%>
								</h4>
								<h4>
									<strong><spring:message code="profile.language.text" /></strong>
									<a class="row7" href="#" data-type="select" data-pk="1"> ${user.language}</a>
									<%-- <form:input path="language" class="form-control" />
									<form:errors path="language" cssClass="error"></form:errors> --%>
								</h4>
								<%-- <h4>
									<form:hidden path="password" class="form-control" readonly="true" />
									<form:errors path="password" cssClass="error"></form:errors>
								</h4>
								<br> --%>
								<%-- <button type="submit" class="btn btn-primary full-width">Update</button>
							</form:form> --%>
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


		<%-- <script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script> --%>
		<%-- <script src="${pageContext.request.contextPath}/js/bootswatch.js"></script> --%>
		<script src="${pageContext.request.contextPath}/js/jquery/jquery-2.1.0.min.js"></script> 
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/calzone.js"></script>
		
		<!-- X-editable Bootstrap -->
		
		<!-- <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>   -->
		<script src="${pageContext.request.contextPath}/js/bootstrap-editable.min.js"></script>
		
		<!-- main.js --> 
 		<script src="${pageContext.request.contextPath}/js/xedit/profile.js"></script>
</body>
</html>