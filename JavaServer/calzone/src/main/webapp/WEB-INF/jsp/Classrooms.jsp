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
<title>CalZone</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/themes/css/lumen/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/themes/css/lumen/bootstrap.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/themes/css/dashboard.css"
	rel="stylesheet">

<!-- Additional styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/themes/css/agenda.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/themes/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/themes/css/custom.css">

<!-- x-editable (bootstrap version) -->
<link
	href="${pageContext.request.contextPath}/css/bootstrap-editable.css"
	rel="stylesheet" />

<!-- JavaScript at bottom except for Modernizr -->
<script src="${pageContext.request.contextPath}/themes/js/libs/modernizr.custom.js"></script>


</head>
<body>
	<script src="${pageContext.request.contextPath}/js/bsa.js"></script>

	<sec:authorize access="isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBarSignedIn.jsp" />
	</sec:authorize>

	<sec:authorize access="!isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBar.jsp" />
	</sec:authorize>

	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h1 id="type">
						<spring:message code="classrooms.title.text" />							
						<sec:authorize ifAnyGranted="ROLE_ADMIN">	
							<button type="button" class="btn btn-primary"
								id="edit-button"><spring:message code="classrooms.edit.text" /></button>
						</sec:authorize>
						
						<!-- Button trigger modal -->
						<a data-toggle="modal"
							href="${pageContext.request.contextPath}/#addNewClassroom"
							class="btn btn-primary">+ <spring:message
								code="classroom.new.text" /></a>
					</h1>
				</div>
			</div>
		</div>

		<!-- Modal -->
		<div class="modal fade" id="addNewClassroom" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">
							<spring:message code="classroom.new.text" />
						</h4>
					</div>
					<form:form commandName="room" class="bs-example form-horizontal">
						<div class="modal-body">
							<fieldset>
								<div class="form-group">
									<div class="col-lg-12">
										<spring:message code="classrooms.name.text"
											var="namePlaceholderText" />
										<form:input path="name" class="form-control"
											placeholder="${namePlaceholderText}" />
										<form:errors path="name" cssClass="error"></form:errors>
									</div>
									<br>
									<div class="col-lg-12">
										<spring:message code="classrooms.capacity.text"
											var="capacityPlaceholderText" />
										<form:input path="capacity" class="form-control"
											placeholder="${capacityPlaceholderText}" />
										<form:errors path="capacity" cssClass="error"></form:errors>
									</div>
									<br>
									<div class="col-lg-12">
										<spring:message code="classrooms.hasProjector.text"
											var="projectorPlaceholderText" />
										<form:checkbox path="projectorEquipped" class=""
											label="${projectorPlaceholderText}" />
									</div>
									<div class="col-lg-12">
										<spring:message code="classrooms.hasSMARTBoard.text"
											var="SMARTBoardPlaceholderText" />
										<form:checkbox path="smartBoardEquipped" class=""
											label="${SMARTBoardPlaceholderText}" />
									</div>
									<div class="col-lg-12">
										<spring:message code="classrooms.hasRecorder.text"
											var="recorderPlaceholderText" />
										<form:checkbox path="recorderEquipped" class=""
											label="${recorderPlaceholderText}" />
									</div>
									<div class="col-lg-12">
										<form:select path="type" items="${roomTypes}" />
									</div>
								</div>
							</fieldset>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<spring:message code="general.close.text" />
							</button>
							<button type="submit" class="btn btn-primary"
								data-loading-text=<spring:message code="general.working.text"/>>
								<spring:message code="general.add.text" />
							</button>
						</div>
					</form:form>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

		<div class="row">
			<div class="col-lg-12">
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th><spring:message code="classrooms.room.text" /></th>
								<th><spring:message code="classrooms.capacity.text" /></th>
								<th><spring:message code="classrooms.roomtype.text"/></th>
								<th><spring:message code="classrooms.projector.text" /></th>
								<th><spring:message code="classrooms.smartboard.text" /></th>
								<th><spring:message code="classrooms.recording.text" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${classroomList}" var="room" varStatus="i">
								<tr>
									<td><a class="displayname" href="#" data-type="text"
										data-pk="${room.id}"> <c:out
												value="${classroomNamesList[i.index]}" /></a></td>
									<td><a class="capacity" href="#" data-type="number"
										data-pk="${room.id}">${room.capacity}</a></td>
									<td><a class="roomtype" href="#" data-type="select"
										data-pk="${room.id}">${room.type}</a></td>

									<td><a class="projectorEquipped" href="#"
										data-type="select" data-pk="${room.id}">${room.projectorEquipped}</a></td>
									<td><a class="smartBoardEquipped" href="#"
										data-type="select" data-pk="${room.id}">${room.smartBoardEquipped}</a></td>
									<td><a class="recorderEquipped" href="#"
										data-type="select" data-pk="${room.id}">${room.recorderEquipped}</a></td>
								</tr>
								<!--
								<tr>
									<td><c:out value="${classroomNamesList[i.index]}" /></td>
									<td>${room.capacity}</td>
									<td>${room.projectorEquipped}</td>
									<td>${room.smartBoardEquipped}</td>
									<td>${room.recorderEquipped}</td>
									<td><a
										href="${pageContext.request.contextPath}/classrooms/edit-${room.id}"><spring:message
												code="general.edit.text" /></a></td>
								</tr>
								-->
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootswatch.js"></script>
	
	<!-- X-editable Bootstrap -->
	<!-- <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>   -->
	<script src="${pageContext.request.contextPath}/js/bootstrap-editable.min.js"></script>
	
	<script>
	$('#edit-button').click(function(e) {
		e.stopPropagation();
		var api = '/calzone/api/classrooms';
		$('.displayname')
		.editable({
			type : 'text',
			name : 'displayName',
			url : api,
			title : '<spring:message code="classrooms.edit.displayname"/>',
			ajaxOptions : {
				dataType : 'json'
			},
			success : function(response) {
				if (response.status == "error")
					return response.message;
			},
			validate : function(value) {
				if ($.trim(value) == '') {
					return '<spring:message code="general.fieldrequired.text"/>';
				}
			}
		});
		$('.capacity').editable({
			name : 'capacity',
			url : api,
			title : '<spring:message code="classrooms.edit.capacity"/>',
			ajaxOptions : {
				dataType : 'json'
			},
			success : function(response) {
				if (response.status == "error")
					return response.message;
			},
			validate : function(value) {
				if ($.trim(value) == '') {
					return '<spring:message code="general.fieldrequired.text"/>';
				}
			}
		});
		$('.roomtype').editable({
			source : [
				{ value : 1, text : "ClassRoom" }, 
				{ value : 2, text : "ComputerRoom" } ],
			name : 'roomType',
			url : api,
			title : '<spring:message code="classrooms.edit.roomtype"/>',
			ajaxOptions : {
				dataType : 'json'
			},
			success : function(response) {
				if (response.status == "error")
					return response.message;
			},
			validate : function(value) {
				if ($.trim(value) == '') {
					return '<spring:message code="general.fieldrequired.text"/>';
				}
			}
		});
		$('.projectorEquipped').editable({
			source : [
				{ value : 1, text : "true" },
				{ value : 2, text : "false" } ],
			name : 'projectorEquipped',
			url : api,
			title : '<spring:message code="classrooms.edit.projectorEquipped"/>',
			ajaxOptions : {
				dataType : 'json'
			},
			success : function(response) {
				if (response.status == "error")
					return response.message;
			},
			validate : function(value) {
				if ($.trim(value) == '') {
					return '<spring:message code="general.fieldrequired.text"/>';
				}
			}
		});
		$('.smartBoardEquipped').editable({
			source : [
				{ value : 1, text : "true" },
				{ value : 2, text : "false" } ],
			name : 'smartBoardEquipped',
			url : api,
			title : '<spring:message code="classrooms.edit.smartBoardEquipped"/>',
			ajaxOptions : {
				dataType : 'json'
			},
			success : function(response) {
				if (response.status == "error")
					return response.message;
			},
			validate : function(value) {
				if ($.trim(value) == '') {
					return '<spring:message code="general.fieldrequired.text"/>';
				}
			}
		});
		$('.recorderEquipped').editable({
			source : [ 
				{ value : 1, text : "true" },
				{ value : 2, text : "false" } ],
			name : 'recorderEquipped',
			url : api,
			title : '<spring:message code="classrooms.edit.recorderEquipped"/>',
			ajaxOptions : {
				dataType : 'json'
			},
			success : function(response) {
				if (response.status == "error")
					return response.message;
			},
			validate : function(value) {
				if ($.trim(value) == '') {
					return '<spring:message code="general.fieldrequired.text"/>';
				}
			}
		});
	});
	</script>
</body>
</html>