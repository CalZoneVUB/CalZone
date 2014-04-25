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
	<div class="col-lg-12" id="mainBody5">
		<div class="row">
			<h1>Scheduler&nbsp;&nbsp;&nbsp;&nbsp;</h1>
			<br>
		</div>

		<div class="row">
			<table class="table table-hover table-responsive">
				<tbody>
					<tr>
						<td>Schedule all trajects</td>
						<td><button type="button" class="btn btn-warning btn-sm"
								id="ScheduleTraject">
								<span class="glyphicon glyphicon-play"></span>&nbsp;Run Scheduler
							</button>
						</td>
					</tr>
					<tr>
						<td>Select Traject to View (All)</td>
						<td><select class="form-control"
							id="TrajectSelectionSchedular">
								<c:forEach items="${listTrajects}" var="traject" varStatus="i">
									<option value="${traject.id} ">${traject.trajectName}</option>
								</c:forEach>
						</select></td>
						<td>
							<button type="button" class="btn btn-primary btn-sm"
								id="ScheduleTrajectView">
								<span class="glyphicon glyphicon-play"></span>&nbsp;View Traject Schedule
							</button>
						</td>
					</tr>
					<tr>
						<td>Select Traject to View (Not frozen)</td>
						<td><select class="form-control"
							id="TrajectSelectionSchedularNotFronzen">
								<c:forEach items="${listTrajectsNotFrozen}" var="traject" varStatus="i">
									<option value="${traject.id} ">${traject.trajectName}</option>
								</c:forEach>
						</select></td>
						<td>
							<button type="button" class="btn btn-primary btn-sm"
								id="ScheduleTrajectViewNotFrozen">
								<span class="glyphicon glyphicon-play"></span>&nbsp;View Traject Schedule
							</button>
						</td>
					</tr>
					<tr id="SchedularCalendarDiv"></tr>
				</tbody>
			</table>
		</div>
	</div>

	<script type="text/javascript">
		$('#ScheduleTraject').click(function () {
			
		});
		
		$('#ScheduleTrajectView').click(function () {
			var valueSelect = $('#TrajectSelectionSchedular').val();
			alert("TODO Value is: " + valueSelect);
			$.get("api/schedular/" + valueSelect, function (data) {
				system.log(data);
				})
				.done(function() {
					var newHtml = "<div id=\"SchedularCalendarDiv\"></div>";
					$('#SchedularCalendarDiv').replaceWith();
				});
		});
		
		$('#ScheduleTrajectViewNotFrozen').click(function () {
			var valueSelect = $('#TrajectSelectionSchedularNotFronzen').val();
			alert("TODO Value is (Not Fronzen): " + valueSelect);
			$.get("api/schedular/" + valueSelect, function (data) {
				system.log(data);
				})
				.done(function() {
					var newHtml = "<div id=\"SchedularCalendarDiv\"></div>";
					$('#SchedularCalendarDiv').replaceWith();
				});
		});
	</script>
</body>
</html>