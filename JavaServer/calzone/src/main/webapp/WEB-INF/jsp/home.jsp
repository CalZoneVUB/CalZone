<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>CalZone</title>

<!-- Bootstrap core CSS -->
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

<!-- JavaScript at bottom except for Modernizr -->
<script
	src="${pageContext.request.contextPath}/themes/js/libs/modernizr.custom.js"></script>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	
	<!-- jQuery Full Calendar CSS -->
    <link href='${pageContext.request.contextPath}/fullcalendar/fullcalendar.css' rel='stylesheet' />
	<link href='${pageContext.request.contextPath}/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
	
</head>

<body>
	
	<sec:authorize access="isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBarSignedIn.jsp" />
	</sec:authorize>
	
	<sec:authorize access="!isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBar.jsp" />
	</sec:authorize>
	 

	<div class="bg"></div>
	<div id="para" class="jumbotron">

		<p id="schedule_selection" class="lead">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<!-- <h1>CalZone</h1>
			<p class="lead">+ Scheduling your courses</p> -->
			<img src="${pageContext.request.contextPath}/img/CalZone.png"
				alt="CalZone Logo" class="img-responsive center vcenter">

			<!--<img src="./img/logo.jpg" alt="Smiley face" height="150">-->

			<!-- Login form here -->
			<form id="target">
				<div class="btn-group " style="padding-bottom: 5px; width: 100%;">
					<select id="program" class="form-control" name="">
                      <option value=""><spring:message code="homepage.selectprogram"/></option>
                    </select>
				</div>

				<div class="btn-group " style="padding-bottom: 5px; width: 100%;">
					<select id="trajectory" class="form-control" name="">
                       <option value=""><spring:message code="homepage.selectprogramfirst"/></option>
                  </select>
				</div>
				<button id="go_btn" type="submit"
					class="btn btn-primary form-control has-spinner"
					style="clear: left; width: 100%; height: 32px; font-size: 13px;">
					<span class="spinner"><i class="icon-spin icon-refresh"></i></span>
					Go!
				</button>
				<!--<input class="btn btn-primary form-control has-spinner" style="clear: left; width: 100%; height: 32px; font-size: 13px;" type="submit" name="commit" value="Go!" />-->
			</form>
		</div>
		<div class="col-sm-3"></div>

	</div>


	<!-- <div id="agenda-header" class="container fade blank">
		<h3>WEEK 25</h3>
		<h4 id="agenda-selection-title"></h4>
	</div> -->


	<div id="main-content-container" class="container" style="display:none;">
		<div id="main-content" class="row" style="background-color:white;">
			<div class="col-sm-12 col-md-12 main" style="height:700px;">
				<div id='calendar' style="height:700px;"></div>
        	</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/themes/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/themes/js/agenda.js"></script>
	
	<!-- jQuery Full Calendar JS -->
    <!-- <script src='${pageContext.request.contextPath}/lib/jquery.min.js'></script>-->
	<script src='${pageContext.request.contextPath}/js/jquery/jquery-ui.custom.min.js'></script>
	<script src='${pageContext.request.contextPath}/fullcalendar/fullcalendar.min.js'></script>
	<script>
	$(document).ready(function() {

		/* initialize the external events
		-----------------------------------------------------------------*/

		$('#external-events li.external-event').each(function() {
		
			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title: $.trim($(this).text()) // use the element's text as the event title
			};
			
			// store the Event Object in the DOM element so we can get to it later
			$(this).data('eventObject', eventObject);
			
			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex: 999,
				scroll: false,
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});
			
		});
	});
	
	var programDataSource = JSON.parse('${programDataSource}');
	$(function(){
		  $.each(programDataSource.listItems, function (index, value) {
		    $("#program").append('<option value="'+value.id+'">'+value.name+'</option>');
		  });
		  
		    $('#program').on('change', function(){
		      $('#trajectory').html('<option value="000"><spring:message code="homepage.selecttrajectory"/></option>');
		      for(var i = 0; i < programDataSource.listItems.length; i++)
		      {
		        if(programDataSource.listItems[i].id == $(this).val())
		        {
		           $.each(programDataSource.listItems[i].trajectories, function (index, value) {
		              $("#trajectory").append('<option value="'+value.id+'">'+value.name+'</option>');
		          });
		        }
		      }
		  });
		});  

	</script>

	<jsp:include page="/WEB-INF/jsp/PusherAlerts.jsp" />
	
</body>
</html>