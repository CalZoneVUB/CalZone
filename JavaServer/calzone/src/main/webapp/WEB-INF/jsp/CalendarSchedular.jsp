<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		
		.external-event { // try to mimick the look of a real event
			margin: 10px 0;
			padding: 2px 4px;
			background: rgb(21, 140, 186);
			color: #fff;
			font-size: .85em;
			cursor: pointer;
		}
		.external-event:hover { // try to mimick the look of a real event
			margin: 10px 0;
			padding: 2px 4px;
			background: rgb(21, 140, 186);
			color: #000;
			font-size: .85em;
			cursor: pointer;
		}
		.external-event.block{ // try to mimick the look of a real event
			margin: 10px 0;
			padding: 2px 4px;
			background: rgb(255, 65, 54);
			color: #fff;
			font-size: .85em;
			cursor: pointer;
		}.external-event a {
			background: rgb(21, 140, 186);
			color: #fff;
		}
		.external-event:hover a {
			background: rgb(21, 140, 186);
			color: #000;
		}
		.external-event.block a{
			background: rgb(255, 65, 54);
			color: #0;
		}
		
		.scheduleoption {
			width:100%;
			text-align:center;
			margin-bottom: 20px;
		}
		
		.scheduleoption-item {
			width:100%;
			text-align:center;
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
		<div class="col-sm-3 col-md-2 sidebar">
			<sec:authorize ifAnyGranted="ROLE_ADMIN">
			<div style="height: 35px; top: 0; margin-top:-20px; margin-left:-20px; padding-left: 20px; padding-top:10px; width:240px; padding-bottom: 10px; background-color: #000000;">
				<table class="scheduleoption">
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
			</sec:authorize>
			<div style="margin-bottom:10px;">
          		<h1 class="page-header">Schedular</h1>
          		
    			<sec:authorize ifAnyGranted="ROLE_PROFESSOR">
	         	<ul id='external-events' class="nav nav-sidebar">
		  			<h4 class="scheduleoption">Beschikbaarheid</h4>
					<li class='external-event block'><a href="#">Bezet</a></li>
					<br>
		  			<h4 class="scheduleoption">Vakken</h4>
		  			<c:forEach items="${courseComponents}" var="ccp" varStatus="i">
		  				<li class='external-event'><a id="${ccp.id}" href="#">[${ccp.type}] ${ccp.course.courseName}</a></li>
					</c:forEach>
					<input type='checkbox' id='drop-remove' checked="checked" hidden/>
				</ul>
				</sec:authorize>
				
				<sec:authorize ifAnyGranted="ROLE_ADMIN">
          		<div class="scheduleoption">
	          		<p class="scheduleoption-item">Schedule all trajects</p>
	          		<button type="button" class="btn btn-warning btn-sm scheduleoption-item" id="ScheduleTraject">
						<span class="glyphicon glyphicon-play"></span>&nbsp;Run Scheduler
					</button>
				</div>
				
				<div class="scheduleoption">
					<p class="scheduleoption-item">Select Traject to View (All)</p>
					<select class="form-control scheduleoption-item" id="TrajectSelectionSchedular">
						<option>Bezig met laden...</option>
					</select>
					<button type="button" class="btn btn-primary btn-sm scheduleoption-item" id="ScheduleTrajectView">
						<span class="glyphicon glyphicon-play"></span>&nbsp;View Traject Schedule
					</button>
				</div>
				
				<div class="scheduleoption">
					<p class="scheduleoption-item">Select Traject to View (Not frozen)</p>
					<select class="form-control scheduleoption-item" id="TrajectSelectionSchedularNotFronzen">
						<option>Bezig met laden...</option>
					</select>
					<button type="button" class="btn btn-primary btn-sm full-width scheduleoption-item" id="ScheduleTrajectViewNotFrozen">
						<span class="glyphicon glyphicon-play"></span>&nbsp;View Traject Schedule
					</button>
				</div>
				</sec:authorize>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="height:100px;">
        	<div id='calendar' style="height:100px;"></div>
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
	<script src='${pageContext.request.contextPath}/fullcalendar/fullcalendar.js'></script>
	
    <sec:authorize ifAnyGranted="ROLE_PROFESSOR">
	<script src='${pageContext.request.contextPath}/fullcalendar/CalendarSchedular.js'></script>
	</sec:authorize>
	
	<sec:authorize ifAnyGranted="ROLE_ADMIN">
	<script src='${pageContext.request.contextPath}/fullcalendar/CalendarAdminSchedular.js'></script>
	</sec:authorize>
	
    
  </body>
</html>