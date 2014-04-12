<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script
	src="${pageContext.request.contextPath}/themes/js/libs/modernizr.custom.js"></script>

</head>
<body>

	<sec:authorize access="isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBarSignedIn.jsp" />
	</sec:authorize>

	<sec:authorize access="!isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBar.jsp" />
	</sec:authorize>
	
	<div class="container">
					<div class="col-lg-6 outer-box">
						<div class="box-shadow profile-box">
							<sec:authorize ifAnyGranted="ROLE_STUDENT">
								<br>
									<button type="button" class="btn btn-default modeldelete" id="edit-button" onclick="convertDate()">Edit course information</button> <!-- TODO verschillende talen -->
								<br>
							</sec:authorize>
							<br>
							<table style="border-spacing:50px">
							<tr>
								<td> <strong><spring:message code="courseInformation.coursetitle.text" /></strong> </td>
								<td> <a class="row1" href="#" data-type="text" data-pk="1"> ${testEntry.course.description} </a> </td>
							</tr>
							<!-- <br> -->
							<tr>
								<td><strong><spring:message code="courseInformation.courseID.text" /></strong></td>
								<td><a class="row2" href="#" data-type="text" data-pk="1">${testEntry.course.iD}</a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="courseInformation.room.text" /></strong> </td>
								<td><a class="row3" href="#" data-type="text" data-pk="1"> ${testEntry.room.name}</a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="courseInformation.startDate.text" /></strong> </td>
								<td><a class="row4" href="#" data-type="combodate" 
										data-format="DD-MM-YYYY HH:mm" data-template="DD / MM / YYYY HH:mm"  data-pk="1" 
										data-value="21-12-2012 8:30"> ${testEntry.startDate}</a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="courseInformation.endDate.text" /></strong> </td>
								<td> <a class="row5" href="#" data-type="combodate" 
										data-format="DD-MM-YYYY HH:mm" data-template="DD / MM / YYYY HH:mm" data-pk="1" 
										data-value="" id="endDate"> ${testEntry.endDate}</a> </td>
							</tr>
							</table>
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
	<script src="${pageContext.request.contextPath}/js/xedit/courseInformation.js"></script>
	<script src="${pageContext.request.contextPath}/js/moment.min.js"></script>
	<script>
	function convertDate(){
		var var1 = document.getElementById("endDate");
	}
	</script>
</body>
</html>

