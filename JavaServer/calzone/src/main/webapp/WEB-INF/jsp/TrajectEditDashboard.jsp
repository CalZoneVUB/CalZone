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
	<div class="col-lg-12" id="mainBody6">
		<div class="row">
			<h1>
				Edit Traject&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-primary"
					data-loading-text="Loading..." id="myBtnBackTrajectEdit">
					<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Return
				</button>
				<button type="button" class="btn btn-warning btn-sm"
					id="editCourseOneBtn" data-loading-text="Loading...">
					<span class="glyphicon glyphicon-pencil"></span> &nbsp;Toggle Edit
				</button>
			</h1>
		</div>
		<br>
		<div class="row">
			<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<tbody>
						<tr>
							<td style="width: 200px">Name</td>
							<td><a class="myeditableCourse" id="courseName"
								data-type="text" data-pk="${traject.id}">${traject.trajectName}</a></td>
							<td>Tools</td>
						</tr>

						<c:forEach items="${traject.courses}" var="course" varStatus="i">
							<tr>
								<td style="width: 200px"></td>
								<td><a class="trajectCourse">${course.courseName}</a></td>
							</tr>
						</c:forEach>

						<tr>
							<td></td>
							<td><a href="#" class="myeditable myCourseTraject" id="courseIdTraject0"
								data-type="select" data-title="Select Course">Courses</a></td>
						</tr>
						<tr id="editCourseDiv"></tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	$('#myBtnBackTrajectEdit').click(function(e) {
		e.stopPropagation();
		var btn = $(this);
	    btn.button('loading');
		$('#mainBody6').load("/calzone/trajectdashboard",
			function() {
			btn.button('reset');
		});
	});	
	
	
	</script>
</body>
</html>