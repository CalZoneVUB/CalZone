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
	<div class="col-lg-12" id="mainBodyProgramEdit">
		<div class="row">
			<h1>
				Edit Program&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-primary"
					data-loading-text="Loading..." id="myBtnBackProgramEdit">
					<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Return
				</button>
				<button type="button" class="btn btn-success btn-sm"
					id="myBtnAddEditTraject" data-loading-text="Loading...">
					<span class="glyphicon glyphicon-pencil"></span> &nbsp;Add traject
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
							<td><a class="myeditableTraject" id="trajectName"
								data-type="text" data-pk="${program.id}">${program.programName}</a></td>
							<td></td>
						</tr>

						<c:forEach items="${program.trajects}" var="traject" varStatus="i">
							<tr>
								<td style="width: 200px">Traject Name</td>
								<td><a class="programTraject">${traject.trajectName}</a></td>
								<td>
									<button type="button"
										class="btn btn-danger deleteProgramTrajectBtn"
										id="${traject.id}" name="${program.id}"">
										<span class="glyphicon glyphicon-remove-circle"></span>
										&nbsp;Delete
									</button>
								</td>
							</tr>
						</c:forEach>

						<tr>
							<td>New Traject</td>
							<td><a href="#" class="myeditable myTrajectProgram"
								id="courseIdProgram0" data-type="select"
								data-title="Select Traject">Trajectories</a></td>
						</tr>
						<tr id="editTrajectDiv"></tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$('.deleteProgramTrajectBtn').click(
				function deleteProgramTraject() {
					$.ajax({
						type : "GET",
						url : "api/program/delete/traject/" + this.id + "/"
								+ this.name,
						datatype : "json",
						success : function(data) {
							$('#mainBodyProgramEdit').load(
									"/calzone/programdashboard/edit/"
											+ editProgramId, function() {
									});
						}
					});
				});

		$('#myBtnBackProgramEdit').click(function(e) {
			e.stopPropagation();
			var btn = $(this);
			btn.button('loading');
			$('#mainBodyProgramEdit').load("/calzone/programdashboard", function() {
				btn.button('reset');
			});
		});

		var ctr = 1;
		$('#myBtnAddEditTraject')
				.click(
						function() {
							var newHtml = " <tr><td>New Traject</td><td><a href=\"#\" class=\"myeditable myTrajectProgram\" id=\"trajectId";
		newHtml = newHtml + ctr;
		newHtml = newHtml + "\" data-type=\"select\" data-title=\"Select Traject\" >Trajectories</a></td>	</tr>"
							newHtml = newHtml
									+ " <tr id=\"editTrajectDiv\"></tr>";
							console.log("Replace with: " + newHtml);
							$('#editTrajectDiv').replaceWith(newHtml);
							ctr++;
							programEditableEdit();
						});

		function programEditableEdit() {
			$('.myTrajectProgram').editable(
					{
						source : 'api/traject/all/formated',
						sourceCache : true,
						name : 'newTraject',
						pk : editProgramId,
						url : 'api/program/traject/new',
						success : function(res, val) {
							if (res.status == 'success') {
								$('#mainBodyProgramEdit').load(
										"/calzone/programdashboard/edit/"
												+ editProgramId, function() {
										});
							}
						}
					});
		}

		programEditableEdit();
	</script>
</body>
</html>