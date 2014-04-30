<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap core CSS -->
<body>
	<div class="col-lg-12" id="mainBody5">
		<div class="row">
			<h1>
				Creating new Course&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-primary"
					data-loading-text="Loading..." id="myBtnBackCourse">
					<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Return
				</button>
				&nbsp;
				<button type="button" class="btn btn-success" id="myBtnAddComponent">
					<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Course
					Component
				</button>
			</h1>
			<br>
		</div>

		<div class="row">
			<table class="table table-hover table-responsive" id="record">
				<tbody>
					<tr>
						<td style="width: 200px"><spring:message
								code="addCourse.courseName.text" /></td>
						<td><a class="myeditableCA CourseName" id="new_courseName"
							data-type="text"></a></td>
					</tr>
					<tr>
						<td style="width: 200px"><spring:message
								code="addCourse.ECTS.text" /></td>
						<td><a class="myeditableCA" id="new_ECTS" data-type="number"></a></td>
					</tr>

					<tr>
						<td style="width: 200px"><spring:message
								code="addCourse.studyTime.text" /></td>
						<td><a class="myeditableCA" id="new_studyTime"
							data-type="number"></a></td>
					</tr>
					<tr>
						<td style="width: 200px"><spring:message
								code="addCourse.reexamination.text" /></td>
						<td><a class="myeditableCA" id="new_reexamination"
							data-type="select" data-source='[{value: 0, text: "YES"}, {value: 1, text: "NO"}]'></a></td>
					</tr>
					<tr>
						<td style="width: 200px"><spring:message
								code="addCourse.language.text" /></td>
						<td><a class="myeditableCA" id="new_language" data-type="select" data-source='[{value: 0, text: "Nederlands"},{value: 1, text: "English"}]'></a></td>
					</tr>
					<tr>
						<td style="width: 200px"><spring:message
								code="addCourse.results.text" /></td>
						<td><a class="myeditableCA" id="new_results" data-type="textarea"></a></td>
					</tr>
					<tr>
						<td style="width: 200px"><spring:message
								code="addCourse.grading.text" /></td>
						<td><a class="myeditableCA " id="new_grading" data-type="textarea"></a></td>
					</tr>
					<tr id="addCoursecomponentDiv"></tr>
				</tbody>
			</table>
<a id="save_btn_new_course">
							<button type="button" class="btn btn-primary myeditable"
								 data-loading-text="Loading...">
								<span class="glyphicon glyphicon-chevron-down"></span>&nbsp;Submit
							</button>
						</a>
		</div>
	</div>




	<script type="text/javascript">
		$(function() {
			$('#new_courseName').editable({
				title : 'New Course Name',
				placeholder : "Course Name",
				validate : function(v) {
					if (!v)
						return "Cannot be empty";
				}
			});

			$('#new_ECTS').editable({
				title : 'ECTS Score',
				placeholder : "ECTS Score",
				validate : function(v) {
					if (!v)
						return "Cannot be empty";
				}
			});

			$('#new_studyTime').editable({
				title : 'Study Time',
				placeholder : "Study Time",
				validate : function(v) {
					if (!v)
						return "Cannot be empty";
				}
			});

			$('#new_reexamination').editable({
				title : 'Reexamination Possible',
				validate : function(v) {
					if (!v)
						return "Cannot be empty";
				}
			});

			$('#new_language').editable({
				title : 'Language',
				validate : function(v) {
					if (!v)
						return "Cannot be empty";
				}
			});

			$('#new_results').editable({
				title : 'Results',
				placeholder : "Results",
				validate : function(v) {
					if (!v)
						return "Cannot be empty";
				}
			});

			$('#new_grading').editable({
				title : 'Grading',
				placeholder : "Grading",
				validate : function(v) {
					if (!v)
						return "Cannot be empty";
				}
			});

			$('#save_btn_new_course').click(function() {
				/* var $editables = $(this).siblings('.myeditable'); */
				var editables = "";
				editables = $('.myeditableCA');
				console.log(editables);
				var values = editables.editable('getValue');
				console.log(values);
				editables.editable('submit', {
					url : 'api/course/new',
					ajaxOptions : {
						data : JSON.stringify(values),
						contentType : 'application/json',
						dataType : 'json' //assuming json response
					}
				});
			});

			$('#myBtnBackCourse').click(function() {
				var btn = $(this);
				btn.button('loading');
				$('#mainBody5').load("/calzone/coursesdashboard", function() {
					btn.button('reset');
				});
			});
		});

		function courseEditable() {
			$('.myeditableCourse').editable({
				title : 'Course component',
				validate : function(v) {
					if (!v)
						return "Cannot be empty";
				}
			})
		};

		var ctr = 1;
		$('#myBtnAddComponent')
				.click(
						function() {
	var newHtml = " <tr><td><spring:message code='addCourse.courseComponentType.text' /></td><td><a href=\"#\" class=\"myeditableCourse myeditableCA\" id=\"new_courseComponent";
	newHtml = newHtml + ctr;
	newHtml = newHtml + "\" data-type=\"select\" data-title=\"Select Course\" data-source='[{value: 0, text: \"HOC\"},{value: 1, text: \"WPO\"},{value: 2, text: \"EXE\"},{value: 3, text: \"ZLF\"}]'>Courses</a></td>	"
							
							//semester
							newHtml = newHtml
									+ " <tr><td><spring:message code='addCourse.semester.text'/></td> <td><a class=\"myeditableCourse myeditableCA\" data-type='select' data-source='[{value:0, text: \"Exams Period\"}, {value: 1, text: \"1st Semester\"}, {value:2, text: \"2nd Semester\"} , {value:3, text: \"Both Semsters\"}]' id=\"new_semester"
	newHtml = newHtml + ctr;
	newHtml = newHtml + " \"data-type=\"text\"></a></td>";
							//contact hours
							newHtml = newHtml
									+ " <tr><td><spring:message code='addCourse.contactHours.text' /></td> <td><a class=\"myeditableCourse myeditableCA\" data-type='number' id=\"new_contactHours"
	newHtml = newHtml + ctr;
	newHtml = newHtml + " \"data-type=\"number\"></a></td>";
							//start date
							newHtml = newHtml
									+ " <tr><td><spring:message code='addCourse.startDate.text' /></td> <td><a class=\"myeditableCourse myeditableCA\" data-title=\"Week of academic year\" data-type='number' id=\"new_startDate"
	newHtml = newHtml + ctr;
	newHtml = newHtml + " \"data-type=\"text\"></a></td>";
							//end date
							newHtml = newHtml
									+ " <tr><td><spring:message code='addCourse.endDate.text' /></td> <td><a class=\"myeditableCourse myeditableCA\" data-type='number' id=\"new_endDate"
	newHtml = newHtml + ctr;
	newHtml = newHtml + " \"data-type=\"text\"></a></td>";
							//duration
							newHtml = newHtml
									+ " <tr><td><spring:message code='addCourse.duration.text' /></td> <td><a class=\"myeditableCourse myeditableCA\" data-type='number' id=\"new_duration"
	newHtml = newHtml + ctr;
	newHtml = newHtml + " \"data-type=\"number\"></a></td>";
							//capacity
							newHtml = newHtml
									+ " <tr><td><spring:message code='addCourse.roomCapacity.text' /></td> <td><a class=\"myeditableCourse myeditableCA\" data-type='number' id=\"new_roomCapacity"
	newHtml = newHtml + ctr;
	newHtml = newHtml + " \"data-type=\"number\"></a></td>";
							//requirements
							newHtml = newHtml
									+ " <tr><td><spring:message code='addCourse.roomRequirements.text' /></td> <td><a class=\"myeditableCourse myeditableCA\" data-source='[{value: 0, text: \"Projector\"},{value: 1, text: \"Recorder\"},{value: 2, text: \"SMART Board\"}]' data-type='checklist' id=\"new_roomRequirements"
	newHtml = newHtml + ctr;
	newHtml = newHtml + " \"data-type=\"text\"></a></td> <tr id=\"addCoursecomponentDiv\"></tr>" ;
							console.log("Replace with: " + newHtml);
							$('#addCoursecomponentDiv').replaceWith(newHtml);
							ctr++;
							courseEditable();
						});
	</script>
</body>
</html>