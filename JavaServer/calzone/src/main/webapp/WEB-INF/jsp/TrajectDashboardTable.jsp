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
	<div class="col-lg-12" id="mainBody3">
		<div class="row">
			<h1>
				Traject&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-success" id="newTrajectBtn">
					<span class="glyphicon glyphicon-plus"></span>&nbsp;Add new traject
				</button>
			</h1>
		</div>
		<br>
		<div class="row">
			<div class="table-responsive">
				<table id="trajectDashboardTable"
					class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>Traject ID</th>
							<th>Name</th>
							<th>Edit</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${trajectList}" var="traject" varStatus="i">
							<tr>
								<td>${traject.id}</td>
								<td>${traject.trajectName}</td>
								<td><button type="button" class="btn btn-primary btn-sm"
										name="${traject.id}" id="editTraject">
										<span class="glyphicon glyphicon-pencil"></span>&nbsp;Edit
									</button>
									<button type="button" class="btn btn-danger deleteTrajectBtn"
										id="${traject.id}">
										<span class="glyphicon glyphicon-remove-circle"></span>
										&nbsp;Delete
									</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$('#editTraject').click(function editTraject() {
			$('#mainBody3').load("/calzone/trajectdashboard/edit/" + this.name, function() {
			});
		});

		$('.deleteTrajectBtn').click(function deleteTraject() {
			$.get("api/course/delete/" + this.id, function (data) {
				system.log(data);
				});
		});

		$('#newTrajectBtn').click(function newItemTraject() {
			$('#mainBody3').load("/calzone/trajectdashboard/new", function() {
			});
		});

		$(document).ready(function() {
			$('#trajectDashboardTable').DataTable();
		});
	</script>
</body>
</html>