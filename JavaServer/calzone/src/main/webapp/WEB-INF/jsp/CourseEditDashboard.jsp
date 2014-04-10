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
</head>
<body>
	<div class="col-lg-12">
		
		<div class="row">
			<h1>Edit Course&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-primary" data-loading-text="Loading..." id="myBtnBackCourse">
					<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Return
				</button>
			</h1>
		</div>
		<br>
		<div class="row">
			<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<tbody>
						<tr>
							<td style="width: 200px">Course Name</td>
							<td><a class="myeditable" id="courseName" data-type="text" pk="${course.id}"></a>${course.courseName}</td>
						</tr>
						<tr>
							<td style="width: 200px">Course Description</td>
							<td><a class="myeditable" id="courseName" data-type="text" pk="${course.id}"></a>${course.courseData.description}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	$('#myBtnBackCourse').click(function() {
		var btn = $(this);
	    btn.button('loading');
		$('#mainBody').load("/calzone/coursedashboard",
			function() {
			btn.button('reset');
		});
	});	
	</script>
</body>
</html>