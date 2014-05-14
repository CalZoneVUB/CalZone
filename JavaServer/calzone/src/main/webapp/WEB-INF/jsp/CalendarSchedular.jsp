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
	
	<!--<link href='${pageContext.request.contextPath}/fullcalendar/dot-luv/jquery-ui-1.10.4.custom.css' rel='stylesheet'/>-->
	
	<style>
		/*body {
			margin-top: 40px;
			text-align: center;
			font-size: 14px;
			font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
			}*/
			
		/*#wrap {
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
			
		.external-event { // try to mimick the look of a real event
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
			}*/
	
		#calendar {
			float: left;
			width: 100%;
			height: 100%;
			}
		.blue {
		    color: blue;
		}
		.red {
		    color: red;
		}
		.orange {
		    color: orange;
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
    <sec:authorize ifAnyGranted="ROLE_PROFESSOR">
		<div class="col-sm-3 col-md-2 sidebar">
			<div style="margin-bottom:10px;">
          		<h1 class="page-header">Calendar</h1>
		  		<h4>Draggable Events</h4>
	         	<ul id='external-events' class="nav nav-sidebar">
					<li class='external-event'><a href="#">My Event 1</a></li>
					<li class='external-event'><a href="#">My Event 2</a></li>
					<li class='external-event'><a href="#">My Event 3</a></li>
					<li class='external-event'><a href="#">My Event 4</a></li>
					<li class='external-event block'><a href="#">Bezet</a></li>
					<p>
					<input type='checkbox' id='drop-remove' checked="checked" /> <label for='drop-remove'>remove after drop</label>
					</p>
				</ul>
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
				
				<button type="button" class="btn btn-warning btn-sm" id="ScheduleTraject">
					<span class="glyphicon glyphicon-play"></span>&nbsp;Run Scheduler
				</button>
			</div>
			<div style="position:fixed; height: 35px; top: 100%; margin-top:-35px; margin-left:-20px; padding-top:10px; width:240px; padding-bottom: 10px; background-color: #000000;">
				<table style="width:100%;">
					<tr>
						<td id="left_menu_errors" style="width:33%;" data-toggle="modal" data-target="#schedule_errors">
							<span class="glyphicon glyphicon-remove-circle red" ></span> 0
						</td>
						<td id="left_menu_warnings" style="width:33%;" data-toggle="modal" data-target="#schedule_warnings">
							<span class="glyphicon glyphicon-warning-sign orange"></span> 0
						</td>
						<td id="left_menu_info" style="width:33%;" data-toggle="modal" data-target="#schedule_info">
							<span class="glyphicon glyphicon-info-sign blue"></span> 0
						</td>
					</tr>
				</table>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="height:100px;">
        	<div id='calendar' style="height:100px;"></div>
        	<!-- Modal -->
			<div class="modal fade" id="schedule_errors" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			        <h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-remove-circle red" ></span> Errors</h4>
			      </div>
			      <div class="modal-body">
			        ...
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			      </div>
			    </div>
			  </div>
			</div>
			<!-- Modal -->
			<div class="modal fade" id="schedule_warnings" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			        <h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-warning-sign orange"></span> Warnings</h4>
			      </div>
			      <div class="modal-body">
			        ...
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			      </div>
			    </div>
			  </div>
			</div>
			<!-- Modal -->
			<div class="modal fade" id="schedule_info" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			        <h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-info-sign blue"></span> Info</h4>
			      </div>
			      <div class="modal-body">
			        ...
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        <!--<button type="button" class="btn btn-primary">Save changes</button>-->
			      </div>
			    </div>
			  </div>
			</div>
        </div>
	</sec:authorize>
	<sec:authorize ifAnyGranted="ROLE_STUDENT">
		<div class="col-sm-12 col-md-12 main" style="height:100px;">
			<div id='calendar' style="height:100px;"></div>
        </div>
	</sec:authorize>
	<sec:authorize ifAnyGranted="ROLE_ADMIN">
		<div class="col-sm-12 col-md-12 main" style="height:100px;">
			<div id='calendar' style="height:100px;"></div>
        </div>
	</sec:authorize>
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
	<script src='${pageContext.request.contextPath}/fullcalendar/fullcalendar.js'></script>
	<script src='${pageContext.request.contextPath}/fullcalendar/CalendarSchedular.js'></script>
	
	<script>
	$('#testid').tooltip('hide');
	//$('#left_menu_warnings').tooltip('hide');
	//$('#left_menu_info').tooltip('hide');
	
	$('#ScheduleTraject').click(function () {
		alert('Schedule this');
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
		alert('Schedule this');
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