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
	<div class="col-lg-12" id="mainBody2">
		<div class="row">
			<h1>Edit Course&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-primary" data-loading-text="Loading..." id="myBtnBackCourseEdit">
					<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Return
				</button>
				<button type="button" class="btn btn-warning btn-sm"
										id="editCourseOneBtn" data-loading-text="Loading...">
										<span class="glyphicon glyphicon-pencil"></span>
										&nbsp;Toggle Edit
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
							<td><a class="myeditableCourse" id="courseName" data-type="text"
								data-pk="${course.id}">${course.courseName}</a></td>
						</tr>
						<tr>
							<td style="width: 200px">Description</td>
							<td><a class="myeditableCourse" id="courseDataDescirption" data-type="textarea"
								data-pk="${course.id}">${course.courseData.description}</a></td>
						</tr>
						<tr>
							<td style="width: 200px">Reexamination Possible</td>
							<td><a class="myeditableCourse" id="courseDataReexaminationPossible" data-type="select"
								data-pk="${course.id}" data-source="[{value: 0, text: 'NO'},{value: 1, text: 'YES'}]">${course.courseData.reexaminationPossible}</a></td>
						</tr>
						<tr>
							<td style="width: 200px">ECTS</td>
							<td><a class="myeditableCourse" id="courseDataECTS" data-type="number"
								data-pk="${course.id}">${course.courseData.ECTS}</a></td>
						</tr>
						<tr>
							<td style="width: 200px">Language</td>
							<td><a class="myeditableCourse" id="courseDataLanguage" data-type="select"
								data-pk="${course.id}" data-source="[{value: 0, text: 'Dutch'},{value: 1, text: 'English'}]">${course.courseData.language}</a></td>
						</tr>
						<tr>
							<td style="width: 200px">Learning Goals</td>
							<td><a class="myeditableCourse" id="courseDataDescirption" data-type="textarea"
								data-pk="${course.id}">${course.courseData.learningGoals}</a></td>
						</tr>
						<tr>
							<td style="width: 200px">Grading</td>
							<td><a class="myeditableCourse" id="courseDataGrading" data-type="textarea"
								data-pk="${course.id}">${course.courseData.grading}</a></td>
						</tr>
						
						<c:forEach items="${course.courseComponents}" var="component" varStatus="i">
							<tr>
								<td style="width: 200px">Coursecomponent Type</td>
								<td><a class="courseComponentType" data-type="select"
									data-pk="${component.id}" >${component.type}</a></td>
							</tr>
							<tr>
								<td style="width: 200px">Coursecomponent Term</td>
								<td><a class="courseComponentTerm"  data-type="select"
									data-pk="${component.id}">${component.term}</a></td>
							</tr>
							<tr>
								<td style="width: 200px">Contact Hours</td>
								<td><a class="courseComponentContactHours" data-type="number"
									data-pk="${component.id}">${component.contactHours}</a></td>
							</tr>		
							<!-- TODO include Starting & End Date -->
							<tr>
								<td style="width: 200px">Duration</td>
								<td><a class="courseComponentDuration"  data-type="number"
									data-pk="${component.id}">${component.duration}</a></td>
							</tr>	
							<tr>
								<td style="width: 200px">Room Capacity Requirement</td>
								<td><a class="courseComponentRoomProjectorRequirement" data-type="number"
									data-pk="${component.id}">${component.roomProjectorRequirement}</a></td>
							</tr>	
							<tr>
								<td style="width: 200px">Other Requirements</td>
								<td><a class="courseComponentRequirements" data-type="checklist"
									data-pk="${component.id}">
									Requirements</a></td>
							</tr>	
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	$('#myBtnBackCourseEdit').click(function(e) {
		e.stopPropagation();
		var btn = $(this);
	    btn.button('loading');
		$('#mainBody2').load("/calzone/coursesdashboard",
			function() {
			btn.button('reset');
		});
	});	
	
	$('#editCourseOneBtn').click(function (e) {
		e.stopPropagation();
		$('.courseComponentType').editable({
			name: 'courseComponentType',
			source : [{value: 0, text: "HOC"},{value: 1, text: "WPO"},{value: 2, text: "EXE"}],
			ajaxOptions : {
				dataType : 'json'
			},
			url: 'api/course/edit'
		});
		$('.myeditableCourse').editable({
			url: 'api/course/edit'
		});
		console.log("getting to editable");
		
		$('.courseComponentContactHours').editable({
			name : 'courseComponentContactHours',
			url : 'api/course/edit'
		});
		$('.courseComponentTerm').editable({
			name : 'courseComponentTerm',
			source : [{value: "S1", text: "Semester 1"},{value: "S2", text: "Semester2"},{value: "S3", text: "Semester 1 & 2"}],
			url : 'api/course/edit'
		});
		$('.courseComponentDuration').editable({
			name : 'courseComponentDuration',
			url : 'api/course/edit'
		});
		$('.courseComponentRoomProjectorRequirement').editable({
			name : 'courseComponentRoomProjectorRequirement',
			url : 'api/course/edit'
		});
		$('.courseComponentRequirements').editable({
			source : [{value: 0, text: "Projector"},{value: 1, text: "Recorder"},{value: 2, text: "SMART Board"}],
			name : 'courseComponentRequirements',
			url : 'api/course/edit'
		});
	});
	
	</script>
</body>
</html>