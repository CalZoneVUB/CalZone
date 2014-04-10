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
				Name: <a class="name" href="#" data-type="text">derp</a><br>
				Capacity: <a class="capacity" href="#" data-type="number">derp</a><br>
				Type: <a class="roomtype" href="#" data-type="select">derp</a><br>
				Location: <a class="location" href="#" data-type="select">fuck</a><br>
				Projector equipped? <a class="projectorEquipped" href="#" data-type="select">derp</a><br>
				SMART Board equipped? <a class="smartBoardEquipped" href="#" data-type="select">derp</a><br>
				Recorder equipped? <a class="recorderEquipped" href="#" data-type="select">derp</a><br>
			</div>
			<button type="submit" class="classroom-submit"><spring:message code="general.save"/></button>
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
	//all fields required
	$('.name').editable('option', 'validate', function(v) {
	    if(!v) return 'Required field!';
	});
	$('.capacity').editable('option', 'validate', function(v) {
	    if(!v) return 'Required field!';
	});
	$('.roomtype').editable(
			{	value: 'ClassRoom',
				source: [
				{value: 'ClassRoom', text: 'ClassRoom'},
	            {value: 'ComputerRoom', text: 'ComputerRoom'}]}, 
	        'validate', function(v) {
		
	    if(!v) return 'Required field!';
	});
	$('.projectorEquipped').editable(
			{	value: 'false',
				source: [
				{value: 'false', text: '<spring:message code="general.no.text"/>'},
	            {value: 'true', text: '<spring:message code="general.yes.text"/>'}]}, 
	        'validate', function(v) {
		
	    if(!v) return 'Required field!';
	});
	$('.smartBoardEquipped').editable(
			{	value: 'false',
				source: [
				{value: 'false', text: '<spring:message code="general.no.text"/>'},
	            {value: 'true', text: '<spring:message code="general.yes.text"/>'}]}, 
	        'validate', function(v) {
		
	    if(!v) return 'Required field!';
	});
	$('.recorderEquipped').editable(
			{	value: 'false',
				source: [
				{value: 'false', text: '<spring:message code="general.no.text"/>'},
	            {value: 'true', text: '<spring:message code="general.yes.text"/>'}]}, 
	        'validate', function(v) {
		
	    if(!v) return 'Required field!';
	});
	
	$('.location').editable({ 
        placement: 'top',                       
        select2: {
            data: '${data}',
            id: function (item) {
                return item.text;
            }
        },                  
        ajaxOptions: {
            type: 'put'
        }   
  });
	</script>
</body>
</html>