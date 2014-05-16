<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

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

<link
	href="${pageContext.request.contextPath}/css/dataTables.bootstrap.css"
	rel="stylesheet" />

<!-- JavaScript at bottom except for Modernizr -->
<script
	src="${pageContext.request.contextPath}/themes/js/libs/modernizr.custom.js"></script>
<link href="${pageContext.request.contextPath}/css/select2.css"
	rel="stylesheet" type="text/css">


</head>
<body>
	<%-- <script src="${pageContext.request.contextPath}/js/bsa.js"></script> --%>

	<sec:authorize access="isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBarSignedIn.jsp" />
	</sec:authorize>

	<sec:authorize access="!isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBar.jsp" />
	</sec:authorize>

	<!-- TODO localizations -->

	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h1 id="type">
						Admin Dashboard
						<%-- <spring:message code="profile.title.text" /> --%>
					</h1>
				</div>
			</div>
		</div>

		<!-- Nav tabs -->
		<ul class="nav nav-tabs" id="myTab">
			<li class="active"><a href="#Rooms" data-toggle="tab"> <spring:message
						code="admindash.rooms" /></a></li>
			<li><a href="#Courses" data-toggle="tab"> <spring:message
						code="admindash.courses" /></a></li>
			<li><a href="#Trajects" data-toggle="tab"> <spring:message
						code="admindash.trajects" /></a></li>
			<li><a href="#Programs" data-toggle="tab"> <spring:message
						code="admindash.programs" /></a></li>
			<%-- <li><a href="#Schedular" data-toggle="tab"> <spring:message
						code="admindash.scheduler" /></a></li> --%>
			<li><a href="#Publisher" data-toggle="tab"> <spring:message
						code="admindash.publisher" /></a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane fade in active" id="Rooms">
				<div class="col-lg-12 outer-box">
					<div class="col-log-11 outer-box" id=RoomTab>
						<br> <br> <br>
						<div class="progress progress-striped active">
							<div class="progress-bar" role="progressbar" aria-valuenow="100"
								aria-valuemin="0" aria-valuemax="100" style="width: 100%">
								<span class="sr-only">Loading...</span>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="tab-pane fade" id="Courses">
				<div class="col-lg-12 outer-box">
					<div class="col-log-11 outer-box" id=CourseTab>
						<br> <br> <br>
						<div class="progress progress-striped active">
							<div class="progress-bar" role="progressbar" aria-valuenow="100"
								aria-valuemin="0" aria-valuemax="100" style="width: 100%">
								<span class="sr-only">Loading...</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="Trajects">
				<div class="col-lg-12 outer-box">
					<div class="col-log-11 outer-box" id=TrajectTab>
						<br> <br> <br>
						<div class="progress progress-striped active">
							<div class="progress-bar" role="progressbar" aria-valuenow="100"
								aria-valuemin="0" aria-valuemax="100" style="width: 100%">
								<span class="sr-only">Loading...</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="Programs">
				<div class="col-lg-12 outer-box">
					<div class="col-log-11 outer-box" id="ProgramsTab">
						<br> <br> <br>
						<div class="progress progress-striped active">
							<div class="progress-bar" role="progressbar" aria-valuenow="100"
								aria-valuemin="0" aria-valuemax="100" style="width: 100%">
								<span class="sr-only">Loading...</span>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="tab-pane fade" id="Schedular">
				<div class="col-lg-12 outer-box">
					<div class="col-log-11 outer-box" id=SchedularTab>
						<br> <br> <br>
						<div class="progress progress-striped active">
							<div class="progress-bar" role="progressbar" aria-valuenow="100"
								aria-valuemin="0" aria-valuemax="100" style="width: 100%">
								<span class="sr-only">Loading...</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="Publisher">
				<div class="col-lg-12 outer-box">
					<div class="col-log-11 outer-box" id=PublisherTab>
						<br> <br> <br>
						<div class="progress progress-striped active">
							<div class="progress-bar" role="progressbar" aria-valuenow="100"
								aria-valuemin="0" aria-valuemax="100" style="width: 100%">
								<span class="sr-only">Loading...</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<script
			src="${pageContext.request.contextPath}/js/jquery/jquery-2.1.0.min.js"></script>
		<%-- <script src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script> --%>
		<!-- <script type="text/javascript" charset="utf8" src="//code.jquery.com/jquery-1.10.2.min.js"></script> -->
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/calzone.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/bootstrap-editable.js"></script>
		<script src="${pageContext.request.contextPath}/js/xedit/profile.js"></script>
		<script src="${pageContext.request.contextPath}/js/select2.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/jquery.dataTables.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/dataTables.bootstrap.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/dataTables.bootstrapPagination.js"></script>
		<!-- 
		<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.0-beta.2/js/jquery.dataTables.js"></script> -->

		<script>
			$("#RoomTab").load("/calzone/classrooms", function() {
			});

			var firstCourse = true;
			$('#myTab a[href="#Courses"]').click(
					function(e) {
						e.preventDefault();
						if (firstCourse == true) {
							firstCourse = false;
							$("#CourseTab").load("/calzone/coursesdashboard",
									function() {
									});
						}
					});

			var firstTraject = true;
			$('#myTab a[href="#Trajects"]').click(
					function(e) {
						e.preventDefault();
						if (firstTraject == true) {
							firstTraject = false;
							$("#TrajectTab").load("/calzone/trajectdashboard",
									function() {
									});
						}
					});
			var firstProgram = true;
			$('#myTab a[href="#Programs"]').click(
					function(e) {
						e.preventDefault();
						if (firstProgram== true) {
							firstProgram = false;
							$("#ProgramsTab").load("/calzone/programdashboard",
									function() {
									});
						}
					});
			var firstSchedular = true;
			$('#myTab a[href="#Schedular"]').click(
					function(e) {
						e.preventDefault();
						if (firstSchedular == true) {
							firstSchedular = false;
							$("#SchedularTab").load(
									"/calzone/schedulardashboard", function() {
									});
						}
					});
			var firstPublisher = true;
			$('#myTab a[href="#Publisher"]').click(function(e) {
				e.preventDefault();
				if (firstPublisher == true) {
					firstPublisher = false;
					$("#PublisherTab").load("/calzone/publisher", function() {
					});
				}
			});
		</script>
		<jsp:include page="/WEB-INF/jsp/PusherAlerts.jsp" />
</body>
</html>
