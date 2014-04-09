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
<script
	src="${pageContext.request.contextPath}/themes/js/libs/modernizr.custom.js"></script>


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
					</h1>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<a class="capacity" href="#" data-type="number"
					data-pk="${room.id}">${room.capacity}</a>
				<a class="roomtype" href="#" data-type="select"
					data-pk="${room.id}">${room.type}</a>

				<a class="projectorEquipped" href="#" data-type="select"
					data-pk="${room.id}">${room.projectorEquipped}</a>
				<a class="smartBoardEquipped" href="#" data-type="select"
					data-pk="${room.id}">${room.smartBoardEquipped}</a>
				<a class="recorderEquipped" href="#" data-type="select"
					data-pk="${room.id}">${room.recorderEquipped}</a>
			</div>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootswatch.js"></script>

	<!-- X-editable Bootstrap -->
	<!-- <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>   -->
	<script
		src="${pageContext.request.contextPath}/js/bootstrap-editable.min.js"></script>

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
				{ value : "ClassRoom", text : '<spring:message code="classrooms.classroom"/>'}, 
				{ value : "ComputerRoom", text : '<spring:message code="classrooms.computerroom"/>'} ],
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
				{ value : "true", text : '<spring:message code="general.yes.text"/>' },
				{ value : "false", text : '<spring:message code="general.no.text"/>' } ],
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
				{ value : "true", text : '<spring:message code="general.yes.text"/>' },
				{ value : "false", text : '<spring:message code="general.no.text"/>' } ],
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
				{ value : "true", text : '<spring:message code="general.yes.text"/>' },
				{ value : "false", text : '<spring:message code="general.no.text"/>' } ],
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