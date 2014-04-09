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
<link
	href="${pageContext.request.contextPath}/css/select2.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<br>
	<div class="col-lg-12">
		<div class="row">
			<h1>Creating new Traject</h1>
			<br>
		</div>

		<div class="row">
			<table class="table table-hover" id="record">
				<tr>
					<td>Traject name</td>
					<td><a class="myeditable" id="new_name" data-type="text">Name
							here</a></td>
				</tr>
				<tr>
					<td>Academic Year</td>
					<td><a class="myeditable" id="new_year" data-type="text"
					>Year
							picker</a></td>
				</tr>
				<tr>
					<td>Courses</td>
					<td><a class="myeditable" id="new_courses"
					data-type="select2" data-name="Courses" data-title="Select Courses"
					data-source="api/course/all/formated">Courses</a></td>
				</tr>
			</table>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootswatch.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-editable.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/xedit/profile.js"></script>
	<script src="${pageContext.request.contextPath}/js/select2.js"></script>
	<script type="text/javascript">
	//initialization
	$('.myeditable').editable({
		 url: '/api/traject/update'
	 });
	
	//make username required
	 $('#new_name').editable('option', 'validate', function(v) {
	     if(!v) return 'Required field!';
	 });
	
	$('#new_courses').select2({
		type: 'select2',
		select2: {multiple: true},
		inputclass: 'input-large'
		});
	 
	</script>
</body>
</html>