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
				<button type="button" class="btn btn-success btn-sm"
					id="myBtnAddEdit" data-loading-text="Loading...">
					<span class="glyphicon glyphicon-pencil"></span> &nbsp;Add course
				</button>
			</h1>
		</div>
		<br>
		<div class="row">
			<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<tbody>
						<tr>
							<td></td>
							<td></td>
							<td>Tools</td>
						</tr>
						<tr>
							<td style="width: 200px">Name</td>
							<td><a class="myeditableCourse" id="courseName"
								data-type="text" data-pk="${traject.id}">${traject.trajectName}</a></td>
							<td></td>
						</tr>

						<c:forEach items="${traject.courses}" var="course" varStatus="i">
							<tr>
								<td style="width: 200px">Course Name</td>
								<td><a class="trajectCourse">${course.courseName}</a></td>
								<td>
									<button type="button"
										class="btn btn-danger deleteTrajectCourseBtn"
										id="${course.id}" name="${traject.id}"">
										<span class="glyphicon glyphicon-remove-circle"></span>
										&nbsp;Delete
									</button>
								</td>
							</tr>
						</c:forEach>

						<tr>
							<td></td>
							<td><a href="#" class="myeditable myCourseTraject"
								id="courseIdTraject0" data-type="select"
								data-title="Select Course">Courses</a></td>
						</tr>
						<tr id="editCourseDiv"></tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	
	$('.deleteTrajectCourseBtn').click(function deleteTrajectCourse() {
		$.get("api/traject/delete/course/" + this.id + "/" + this.name, function (data) {
			system.log(data);
			});
	});
	
		$('#myBtnBackTrajectEdit').click(function(e) {
			e.stopPropagation();
			var btn = $(this);
			btn.button('loading');
			$('#mainBody6').load("/calzone/trajectdashboard", function() {
				btn.button('reset');
			});
		});

		var ctr = 1;
		$('#myBtnAddEdit')
				.click(
						function() {
							var newHtml = " <tr><td>Course</td><td><a href=\"#\" class=\"myeditable myCourseTraject\" id=\"courseId";
		newHtml = newHtml + ctr;
		newHtml = newHtml + "\" data-type=\"select\" data-title=\"Select Course\" >Courses</a></td>	"
							newHtml = newHtml
									+ " <tr id=\"addCourseDiv\"></tr>";
							console.log("Replace with: " + newHtml);
							$('#editCourseDiv').replaceWith(newHtml);
							ctr++;
							trajectEditable();
						});

		$('.myCourseTraject').editable({
			source : 'api/course/all/formated',
			sourceCache : true,
			validate : function(v) {
				if (!v)
					return "Cannot be empty";
			}
		})
	</script>
</body>
</html>