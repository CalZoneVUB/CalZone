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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- TODO: META Description -->
    <!--
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.ico"> 
     -->

    <title>CalZone</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/themes/css/lumen/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for the dashboard template -->
    <link href="${pageContext.request.contextPath}/themes/css/dashboard.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <!-- jQuery Full Calendar CSS -->
    <link href='${pageContext.request.contextPath}/fullcalendar/fullcalendar.css' rel='stylesheet' />
	<link href='${pageContext.request.contextPath}/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
	
	<style>
		body {
			margin-top: 40px;
			text-align: center;
			font-size: 14px;
			font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
			}
			
		#wrap {
			width: 1100px;
			margin: 0 auto;
			}
			
		#external-events {
			float: left;
			width: 150px;
			padding: 0 10px;
			border: 1px solid #ccc;
			background: #eee;
			text-align: left;
			}
			
		#external-events h4 {
			font-size: 16px;
			margin-top: 0;
			padding-top: 1em;
			}
			
		.external-event { /* try to mimick the look of a real event */
			margin: 10px 0;
			padding: 2px 4px;
			background: #3366CC;
			color: #fff;
			font-size: .85em;
			cursor: pointer;
			}
			
		#external-events p {
			margin: 1.5em 0;
			font-size: 11px;
			color: #666;
			}
			
		#external-events p input {
			margin: 0;
			vertical-align: middle;
			}
	
		#calendar {
			float: right;
			width: 900px;
			}
	
	</style>
  </head>

  <body>

    <sec:authorize access="isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBarSignedIn.jsp" />
	</sec:authorize>

	<sec:authorize access="!isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBar.jsp" />
	</sec:authorize>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">Overview</a></li>
            <li><a href="#">Reports</a></li>
            <li><a href="#">Analytics</a></li>
            <li><a href="#">Export</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="">Nav item</a></li>
            <li><a href="">Nav item again</a></li>
            <li><a href="">One more nav</a></li>
            <li><a href="">Another nav item</a></li>
            <li><a href="">More navigation</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="">Nav item again</a></li>
            <li><a href="">One more nav</a></li>
            <li><a href="">Another nav item</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">Calendar</h1>

          <div id='external-events'>
			<h4>Draggable Events</h4>
			<div class='external-event'>My Event 1</div>
			<div class='external-event'>My Event 2</div>
			<div class='external-event'>My Event 3</div>
			<div class='external-event'>My Event 4</div>
			<div class='external-event'>My Event 5</div>
			<p>
			<input type='checkbox' id='drop-remove' /> <label for='drop-remove'>remove after drop</label>
			</p>
			</div>
          <div id='calendar'></div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/themes/js/bootstrap.min.js"></script>
    
    <!-- jQuery Full Calendar JS -->
    <!-- <script src='${pageContext.request.contextPath}/lib/jquery.min.js'></script>-->
	<script src='${pageContext.request.contextPath}/js/jquery/jquery-ui.custom.min.js'></script>
	<script src='${pageContext.request.contextPath}/fullcalendar/fullcalendar.min.js'></script>
	<script>
	$(document).ready(function() {
	
	
		/* initialize the external events
		-----------------------------------------------------------------*/
	
		$('#external-events div.external-event').each(function() {
		
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
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});
			
		});
	
	
		/* initialize the calendar
		-----------------------------------------------------------------*/
		
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			editable: true,
			droppable: true, // this allows things to be dropped onto the calendar !!!
			drop: function(date, allDay) { // this function is called when something is dropped
			
				// retrieve the dropped element's stored Event Object
				var originalEventObject = $(this).data('eventObject');
				
				// we need to copy it, so that multiple events don't have a reference to the same object
				var copiedEventObject = $.extend({}, originalEventObject);
				
				// assign it the date that was reported
				copiedEventObject.start = date;
				copiedEventObject.allDay = allDay;
				
				// render the event on the calendar
				// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
				
				// is the "remove after drop" checkbox checked?
				if ($('#drop-remove').is(':checked')) {
					// if so, remove the element from the "Draggable Events" list
					$(this).remove();
				}
				
			}
		});

	});
	
	</script>
  </body>
</html>