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
			<h1>Schedular&nbsp;&nbsp;&nbsp;&nbsp;</h1>
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
						<td>Select Traject to View</td>
						<td><select class="form-control"
							id="TrajectSelectionSchedular">
								<c:forEach items="${listTrajects}" var="traject" varStatus="i">
									<option value="${traject.id} ">${traject.trajectName}</option>
								</c:forEach>
						</select></td>
						<td>
							

							<button type="button" class="btn btn-primary btn-sm"
								id="ScheduleTrajectView">
								<span class="glyphicon glyphicon-play"></span>&nbsp;View
								Scheduler
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
			var valueSelect = $('#TrajectSelectionSchedular').val();
			alert("TODO Run the shedule once schedular is done this will forward to shedule for revieuw. Value is: " + valueSelect);
			$.get("api/schedular/" + valueSelect, function (data) {
				system.log(data);
				})
				.done(function() {
					var newHtml = "<div id=\"SchedularCalendarDiv\"></div>";
					$('#SchedularCalendarDiv').replaceWith();
				});
		});
		
		$('#ScheduleTrajectView').click(function () {
			alert("TODO View calendar of the shedule");
		});
	</script>
</body>
</html>