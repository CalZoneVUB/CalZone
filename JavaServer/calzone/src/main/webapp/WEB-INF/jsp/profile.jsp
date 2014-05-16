<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

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

<!-- x-editable (bootstrap version) -->
<link
	href="${pageContext.request.contextPath}/css/bootstrap-editable.css"
	rel="stylesheet" />

<!-- JavaScript at bottom except for Modernizr -->
<script src="${pageContext.request.contextPath}/themes/js/libs/modernizr.custom.js"></script>


	
</head>
<body>
	<%-- <script src="${pageContext.request.contextPath}/js/bsa.js"></script> --%>

	<sec:authorize access="isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBarSignedIn.jsp" />
	</sec:authorize>
	
	<sec:authorize access="!isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBar.jsp" />
	</sec:authorize>


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
			<li><a href="#profile" data-toggle="tab"> <spring:message code="profile.tab.profile.text" /></a></li>
			<li class="active"><a href="#messages" data-toggle="tab"> <spring:message code="profile.tab.messages.text" /> </a></li>
			<li><a href="#settings" data-toggle="tab"> <spring:message code="profile.tab.settings.text" /> </a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane fade" id="profile">
				<div class="row">
					<c:if test="${'fail' eq param.lookup}">
						<div style="color: red">
							User not in the database.<br />
						</div>
					</c:if>

					<div class="col-lg-12 outer-box">
						
							<sec:authorize access="isAuthenticated()">
								<br>
									<button type="button" class="btn btn-default modeldelete" id="edit-button">Edit your Profile</button> <!-- TODO verschillende talen -->
								<br>
							</sec:authorize>
							<br>
							<table style="border-spacing:50px">
							<tr>
								<td> <strong><spring:message code="profile.username.text" /></strong> </td>
								<td> <a class="row1" href="#" data-type="text" data-pk=${user.id}> ${user.username} </a> </td>
							</tr>
							<!-- <br> -->
							<tr>
								<td><strong><spring:message code="profile.firstName.text" /></strong></td>
								<td><a class="row2" href="#" data-type="text" data-pk=${user.id}>${user.person.firstName}</a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="profile.lastName.text" /></strong> </td>
								<td><a class="row3" href="#" data-type="text" data-pk=${user.id}> ${user.person.lastName}</a> </td>
								<%-- <form:input path="lastName" class="form-control" />
									<form:errors path="lastName" cssClass="error"></form:errors> --%>
							</tr>
							<tr>
								<td><strong><spring:message code="profile.userTypeName.text" /></strong> </td>
								<td> <a class="row4" href="#" data-type="text" data-pk=${user.id}> ${user.userRole}</a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="profile.email.text" /></strong> </td>
								<td><a class="row5" href="#" data-type="text" data-pk=${user.id}> ${user.person.email}</a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="profile.birthDate.text" /></strong> </td>
								<td><a class="row6" href="#" data-type="date" data-pk=${user.id}> ${user.person.birthdate}</a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="profile.language.text" /></strong> </td>
								<td><a class="row7" href="#" data-type="select" data-pk=${user.id}> ${user.language}</a> </td>
							</tr>
							</table>
						
					</div>

				</div>
			</div>
			<div class="tab-pane fade in active" id="messages">
				<div class="col-lg-12 outer-box">	
							<div id="replace_message" ></div>
					<%-- <jsp:include page="/WEB-INF/jsp/hello.jsp" />		 --%>		
				</div>

			</div>
			<div class="tab-pane fade" id="settings">...</div>

		</div>
	</div>
	<script type="text/javascript">
	window.onload = function loadMessages() {
		console.log("Loading Messages ...");
		$("#replace_message").load("/calzone/messages");};
	</script>
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
	
	<jsp:include page="/WEB-INF/jsp/PusherAlerts.jsp" />
</body>
</html>