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
							<td><strong>Tools</strong></td>
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
							<td>New Course</td>
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
		$('.deleteTrajectCourseBtn').click(
				function deleteTrajectCourse() {
					$.ajax({
						type : "GET",
						url : "api/traject/delete/course/" + this.id + "/"
								+ this.name,
						datatype : "json",
						success : function(data) {
							$('#mainBody6').load(
									"/calzone/trajectdashboard/edit/"
											+ editTrajectId, function() {
									});
						}
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
							var newHtml = " <tr><td>New Course</td><td><a href=\"#\" class=\"myeditable myCourseTraject\" id=\"courseId";
		newHtml = newHtml + ctr;
		newHtml = newHtml + "\" data-type=\"select\" data-title=\"Select Course\" >Courses</a></td>	</tr>"
							newHtml = newHtml
									+ " <tr id=\"editCourseDiv\"></tr>";
							console.log("Replace with: " + newHtml);
							$('#editCourseDiv').replaceWith(newHtml);
							ctr++;
							trajectEditableEdit();
						});

		function trajectEditableEdit() {
			$('.myCourseTraject').editable(
					{
						source : 'api/course/all/formated',
						sourceCache : true,
						name : 'newCourse',
						pk : editTrajectId,
						url : 'api/traject/course/new',
						success : function(res, val) {
							if (res.status == 'success') {
								$('#mainBody6').load(
										"/calzone/trajectdashboard/edit/"
												+ editTrajectId, function() {
										});
							}
						}
					});
		}

		trajectEditableEdit();
	</script>
</body>
</html>