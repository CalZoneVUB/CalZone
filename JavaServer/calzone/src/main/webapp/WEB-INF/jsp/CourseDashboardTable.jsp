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
	<div class="col-lg-12" id="mainBody">
		
		<div class="row">
			<h1>Courses&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-success" id="addNewCourseBtn">
				<span class="glyphicon glyphicon-plus"></span>&nbsp; Add new course</button>
			</h1>
		</div>
		<br>
		<div class="row">
			<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>Course ID</th>
							<th>Name</th>
							<th>Edit</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${courseList}" var="course" varStatus="i">
							<tr>
								<td>${course.id}</td>
								<td>${course.courseName}</td>
								<td><button type="button" class="btn btn-primary btn-sm editCourseBtn"
										id="${course.id}" data-loading-text="Loading...">
										<span class="glyphicon glyphicon-pencil"></span>
										&nbsp;Edit
										</button>
										&nbsp;&nbsp;
										<button type="button" class="btn btn-danger btn-sm deleteCourseBtn"
										id="${course.id}">
										<span class="glyphicon glyphicon-remove-circle"></span>
										&nbsp;Delete
									
									</button>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$('.editCourseBtn').click(function () {
			alert(this.id);
			var btn = $(this);
			btn.button('loading');
			var page = "/calzone/coursesdashboard/edit/" + this.id;
			console.log("Loading Page: " + page);
			$('#mainBody').load(page,
				function() {
					btn.button('reset');
					console.log("Pushed back");}
			);
		});
		
		$('.deleteCourseBtn').click(function () {
			alert(this.id);
		});
		
		$('#addNewCourseBtn').click(function newItem() {
			alert("TODO: Creating new Course page")
		});
		
	</script>
</body>
</html>