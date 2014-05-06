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
	<div class="col-lg-12" id="mainBodyProgram">
		<div class="row">
			<h1>
				Program&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-success" id="newProgramBtn">
					<span class="glyphicon glyphicon-plus"></span>&nbsp;Add new program
				</button>
			</h1>
		</div>
		<br>
		<div class="row">
			<div class="table-responsive">
				<table id="programDashboardTable"
					class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>Program ID</th>
							<th>Name</th>
							<th>Edit</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${programList}" var="program" varStatus="i">
							<tr>
								<td>${program.id}</td>
								<td>${program.programName}</td>
								<td><button type="button" class="btn btn-primary btn-sm editProgram"
										name="${program.id}" >
										<span class="glyphicon glyphicon-pencil"></span>&nbsp;Edit
									</button>
									<button type="button" class="btn btn-danger deleteProgramBtn"
										id="${program.id}">
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
		var editProgramId = 0;
		$('.editProgram').click(function editProgram() {
			editProgramId = this.name;
			$('#mainBodyProgram').load("/calzone/programdashboard/edit/" + this.name, function() {
			});
		});

		$('.deleteProgramBtn').click(function deleteProgram() {
			$.get("api/traject/delete/" + this.id, function (data) {
				system.log(data);
				});
		});

		$('#newProgramBtn').click(function newItemProgram() {
			$('#mainBodyProgram').load("/calzone/programdashboard/new", function() {
			});
		});

		$(document).ready(function() {
			$('#programDashboardTable').DataTable();
		});
	</script>
</body>
</html>