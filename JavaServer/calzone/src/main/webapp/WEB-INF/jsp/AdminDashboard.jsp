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
<script
	src="${pageContext.request.contextPath}/themes/js/libs/modernizr.custom.js"></script>


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
						Admin Dashboard<%-- <spring:message code="profile.title.text" /> --%>
					</h1>
				</div>
			</div>
		</div>

		<!-- Nav tabs -->
		<ul class="nav nav-tabs" id="myTab">
			<li class="active"><a href="#Rooms" data-toggle="tab"> Rooms</a></li>
			<li><a href="#Courses" data-toggle="tab"> Courses</a></li>
			<li><a href="#Trajects" data-toggle="tab"> Trajects</a></li>
			<li><a href="#Programs" data-toggle="tab"> Programs</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane fade in active" id="Rooms">
				<div class="col-lg-12 outer-box">
					<%-- <jsp:include page="/WEB-INF/jsp/hello.jsp" /> --%>
					<p>Rooms</p>
					
				</div>
			</div>
			<div class="tab-pane fade" id="Courses">
				<div class="col-lg-12 outer-box">
					<p>Courses</p>
					<div class="col-log-12 outer-box" id="test"> <p>Test here</p></div> 
				</div>
			</div>
			<div class="tab-pane fade" id="Trajects">
				<div class="col-lg-12 outer-box">
					<p>Trajects</p>
				</div>
			</div>
			<div class="tab-pane fade" id="Programs">
				<div class="col-lg-12 outer-box">
					<p>Programs</p>
				</div>
			</div>

		</div>
	</div>

	
	<%-- <script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script> --%>
	<%-- <script src="${pageContext.request.contextPath}/js/bootswatch.js"></script> --%>
	<script
		src="${pageContext.request.contextPath}/js/jquery/jquery-2.1.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/calzone.js"></script>
	<!-- X-editable Bootstrap -->
	<!-- <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>   -->
	<script
		src="${pageContext.request.contextPath}/js/bootstrap-editable.min.js"></script>
	<!-- main.js -->
	<script src="${pageContext.request.contextPath}/js/xedit/profile.js"></script>
	
	<script>
	/* $("#Courses").click(function(e) {
		e.stopPropagation();
		$("#test").load( "/calzone/profile" , function() {
		  alert( "This is SPARTAAAA" );
		}); }

		); */
		$('#myTab a[href="#Courses"]').click(function(e) {
			e.preventDefault();
			alert("This is SPARTAAAA");
			$("#test").load("/calzone", function() {
				alert("This is SPARTAAAA");
			});
			$(this).tab('show')
		});
	</script>
</body>
</html>