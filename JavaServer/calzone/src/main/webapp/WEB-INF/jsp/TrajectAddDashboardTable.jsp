<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>

</head>
<body>
	<br>
	<div class="col-lg-12" id="mainBody">
		<div class="row">
			<h1>
				Creating new Traject&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-primary" data-loading-text="Loading..." id="myBtnBack">
					<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Return
				</button>
			</h1>
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
					>Year picker</a></td>
				</tr>
				<tr>
					<td>Courses</td>
					<td><a href="#" class="myeditable" id="new_courses" data-type="select"
					data-title="Select Course" >Courses</a></td>
					
				</tr>
			</table>
		</div>
	</div>
	
	<script type="text/javascript">
	//initialization
	$(function() {
	 $('#new_name').editable('option', 'validate', function(v) {
	     if(!v) return 'Required field!';
	 });
	 
	 $('#new_courses').editable({
	        value: 2,    
	        source: 'api/course/all/formated',
	        sourceCache: true
	    });  
	 });
	
	$('#myBtnBack').click(function() {
		var btn = $(this);
	    btn.button('loading');
		$('#mainBody').load("/calzone/trajectdashboard",
			function() {
			btn.button('reset');
			console.log("Pushed back");
		});
		});

	</script>
</body>
</html>