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
								data-pk="${course.id}"></a>${course.courseName}</td>
						</tr>
						<tr>
							<td style="width: 200px">Description</td>
							<td><a class="myeditableCourse" id="courseDataDescirption" data-type="textarea"
								data-pk="${course.id}"></a>${course.courseData.description}</td>
						</tr>
						<tr>
							<td style="width: 200px">Reexamination Possible</td>
							<td><a class="myeditableCourse" id="courseDataReexaminationPossible" data-type="select"
								data-pk="${course.id}" data-source="[{value: 0, text: 'NO'},{value: 1, text: 'YES'}]"></a>${course.courseData.reexaminationPossible}</td>
						</tr>
						<tr>
							<td style="width: 200px">ECTS</td>
							<td><a class="myeditableCourse" id="courseDataECTS" data-type="number"
								data-pk="${course.id}"></a>${course.courseData.ECTS}</td>
						</tr>
						<tr>
							<td style="width: 200px">Language</td>
							<td><a class="myeditableCourse" id="courseDataLanguage" data-type="select"
								data-pk="${course.id}" data-source="[{value: 0, text: 'Dutch'},{value: 1, text: 'English'}]"></a>${course.courseData.language}</td>
						</tr>
						<tr>
							<td style="width: 200px">Learning Goals</td>
							<td><a class="myeditableCourse" id="courseDataDescirption" data-type="textarea"
								data-pk="${course.id}"></a>${course.courseData.learningGoals}</td>
						</tr>
						<tr>
							<td style="width: 200px">Grading</td>
							<td><a class="myeditableCourse" id="courseDataGrading" data-type="textarea"
								data-pk="${course.id}"></a>${course.courseData.grading}</td>
						</tr>
						
						<c:forEach items="${course.courseComponents}" var="component" varStatus="i">
							<tr>
								<td style="width: 200px">Coursecomponent Type</td>
								<td><a class="myeditableCourse" id="courseComponentType" data-type="select"
									data-pk="${component.id}" data-source="[{value: HOC, text: 'HOC'},{value: WPO, text: 'WPO'},{value: EXE, text: 'EXE'}]"></a>${component.type}</td>
							</tr>
							<tr>
								<td style="width: 200px">Coursecomponent Term</td>
								<td><a class="myeditableCourse" id="courseComponentTerm" data-type="select"
									data-pk="${component.id}" data-source="[{value: S1, text: 'Semester 1'},{value: S2, text: 'Semester2'},{value: S3, text: 'Semester 1 & 2'}]"></a>${component.term}</td>
							</tr>
							<tr>
								<td style="width: 200px">Contact Hours</td>
								<td><a class="myeditableCourse" id="courseComponentContactHours" data-type="number"
									data-pk="${component.id}"></a>${component.contactHours}</td>
							</tr>		
							<!-- TODO include Starting & End Date -->
							<tr>
								<td style="width: 200px">Duration</td>
								<td><a class="myeditableCourse" id="courseComponentDuration" data-type="number"
									data-pk="${component.id}"></a>${component.duration}</td>
							</tr>	
							<tr>
								<td style="width: 200px">Room Capacity Requirement</td>
								<td><a class="myeditableCourse" id="courseComponentRoomProjectorRequirement" data-type="number"
									data-pk="${component.id}"></a>${component.roomProjectorRequirement}</td>
							</tr>	
							<tr>
								<td style="width: 200px">Other Requirements</td>
								<td><a class="myeditableCourse" id="courseComponentRequirements" data-type="checkbox"
									data-pk="${component.id}"
									data-source="[{value: 0, text: 'Projector},{value: 1, text: 'Recorder'},{value: 2, text: 'SMART Board'}]"></a>
									Requirements</td>
							</tr>	
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	$('#myBtnBackCourseEdit').click(function() {
		var btn = $(this);
	    btn.button('loading');
		$('#mainBody2').load("/calzone/coursesdashboard",
			function() {
			btn.button('reset');
		});
	});	
	
	$('.myeditableCourse').editable({
		url: '/api/course/edit'
	});
	
	</script>
</body>
</html>