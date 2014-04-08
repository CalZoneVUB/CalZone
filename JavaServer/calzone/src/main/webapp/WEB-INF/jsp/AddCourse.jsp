<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
 
<sec:authorize access="isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBarSignedIn.jsp" />
	</sec:authorize>

<div class="container">
					<div class="col-lg-6 outer-box">
						<div class="box-shadow profile-box">
							<br>
							<table style="border-spacing:50px">
							<tr>
								<td> <strong><spring:message code="addCourse.courseName.text" /></strong> </td>
								<td> <a class="row1" href="#" data-type="text" data-pk="1"> Vak Naam </a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="addCourse.ECTS.text" /></strong></td>
								<td><a class="row2" href="#" data-type="text" data-pk="1"> Studiepunten </a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="addCourse.studyTime.text" /></strong> </td>
								<td><a class="row3" href="#" data-type="text" data-pk="1"> Studie tijd </a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="addCourse.prerequisites.text" /></strong> </td>
								<td><a class="row4" href="#" data-type="select2" data-pk="1"> Prerequisities </a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="addCourse.semester.text" /></strong> </td>
								<td><a class="row5" href="#" data-type="select" data-pk="1"> Semester </a> </td>
							</tr>
							<br>
							<tr>
								<td><strong><spring:message code="addCourse.reexamination.text" /></strong> </td>
								<td><a class="row6" href="#" data-type="select" data-pk="1"> Re-examinatie mogelijk? </a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="addCourse.language.text" /></strong> </td>
								<td><a class="row7" href="#" data-type="select" data-pk="1"> Taal </a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="addCourse.results.text" /></strong> </td>
								<td><a class="row8" href="#" data-type="textarea" data-pk="1"> Leer Resultaten </a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="addCourse.grading.text" /></strong> </td>
								<td><a class="row9" href="#" data-type="textarea" data-pk="1"> Beoordelings Informatie </a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="addCourse.duration.text" /></strong> </td>
								<td><a class="row10" href="#" data-type="text" data-pk="1"> Les duur </a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="addCourse.description.text" /></strong> </td>
								<td><a class="row11" href="#" data-type="textarea" data-pk="1"> Beschrijving </a> </td>
							</tr>
							
							</table>
						</div>
					</div>

				</div>

<!--  
<h1>Voeg een vak toe.</h1>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="description" class="form-control" placeholder="Vakbeschrijving" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="ECTS" class="form-control" placeholder="Studiepunten" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="prerequisites" class="form-control" placeholder="Prerequisities" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="term" class="form-control" placeholder="Semester" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="studyTime" class="form-control" placeholder="Studie Tijd" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="catalogueID" class="form-control" placeholder="Identificatie Nummer" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="checkbox" name="reexaminationPossible" class="form-control" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="language" class="form-control" placeholder="Taal" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="faculty" class="form-control" placeholder="Faculteit" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="department" class="form-control" placeholder="Departement" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="educationalTeam" class="form-control" placeholder="Educatie Team" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="activities" class="form-control" placeholder="Activiteiten" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="courseMaterial" class="form-control" placeholder="Les Materiaal" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="learningGoals" class="form-control" placeholder="Leer Resultaten" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="grading" class="form-control" placeholder="Beoordelings Informatie" />
	</div>
</div>
<button type="button" onclick="alert('Button Pressed')">Submit</button>
-->


	<%-- <script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script> --%>
	<%-- <script src="${pageContext.request.contextPath}/js/bootswatch.js"></script> --%>
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-2.1.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/calzone.js"></script>
	<!-- X-editable Bootstrap -->
	<!-- <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>   -->
	<script src="${pageContext.request.contextPath}/js/bootstrap-editable.min.js"></script>
	<!-- main.js -->
	<script src="${pageContext.request.contextPath}/js/xedit/addCourse.js"></script>
	
</body>
</html>