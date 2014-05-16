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
		
	<style>
	
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
			<div id="TopBanner" style="height: 35px; top: 0; margin-top:-20px; margin-left:-20px; padding-top:2px; width:240px; padding-bottom: 40px; display:none;">
				<table class="scheduleoption">
					<tr>
						<!--  <td id="left_menu_errors" style="width:33%;" data-toggle="modal" data-target="#schedule_errors">
							<span class="glyphicon glyphicon-remove-circle red" ></span> 0
						</td>--->
						<!-- <td id="left_menu_warnings" style="width:50%;" data-toggle="modal" data-target="#schedule_warnings">
							<span class="glyphicon glyphicon-warning-sign orange"></span> 0
						</td>-->
						<!-- <td id="left_menu_info" style="width:33%;" data-toggle="modal" data-target="#schedule_info">
							<span class="glyphicon glyphicon-info-sign blue"></span> 0
						</td>-->
						<td id="left_menu_buttion" style="height: 35px; width:100%;">
							<button type="button" class="btn btn-warning btn-sm full-width scheduleoption-item" id="ViewSchedulingConstraints">
								<span class="glyphicon glyphicon-warning-sign"></span>&nbsp;Bekijk constraints
							</button>
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
					<h4 class="scheduleoption-item">Toon Trajecten</h4>
				</div>
				<div class="scheduleoption">
					<p class="scheduleoption-item">Alle</p>
					<select class="form-control scheduleoption-item" id="TrajectSelectionSchedular">
						<option>Bezig met laden...</option>
					</select>
					<button type="button" class="btn btn-primary btn-sm scheduleoption-item" id="ScheduleTrajectView">
						<span class="glyphicon glyphicon-play"></span>&nbsp;Bekijk schedule
					</button>
				</div>
				<div class="scheduleoption">
					<p class="scheduleoption-item">Onbevroren</p>
					<select class="form-control scheduleoption-item" id="TrajectSelectionSchedularNotFrozen">
						<option>Bezig met laden...</option>
					</select>
					<button type="button" class="btn btn-primary btn-sm full-width scheduleoption-item" id="ScheduleTrajectViewNotFrozen">
						<span class="glyphicon glyphicon-play"></span>&nbsp;Bekijk schedule
					</button>
				</div>
				<br><br>
				<div id="ScheduleActions" class="scheduleoption" style="display:none;">
					<h4 class="scheduleoption-item">Acties</h4>
	          		<button type="button" class="btn btn-warning btn-sm scheduleoption-item" id="ScheduleTraject" data-traject="0">
						<span class="glyphicon glyphicon-play"></span>&nbsp;Schedule
					</button>
					<br><br>
	          		<button type="button" class="btn btn-info btn-sm scheduleoption-item" id="FreezeTraject" data-traject="0">
						<span class="glyphicon glyphicon-play"></span>&nbsp;Freeze
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
    
    <div class="modal fade" id="constraintsModal">
	<div class="modal-dialog">
	  <div class="modal-content">
	    <div class="modal-header">
	      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	      <h4 class="modal-title">Constraints</h4>
	    </div>
	    <div class="modal-body" style="height:400px; overflow:scroll;">
	    	<div id="modalProgressBar" class="progress progress-striped active">
				<div class="progress-bar" role="progressbar" aria-valuenow="100"
					aria-valuemin="0" aria-valuemax="100" style="width: 100%">
					<span class="sr-only">Loading...</span>
				</div>
			</div>
	      <table>
	          <thead>
	          </thead>
	          <tbody id="constraintsTable" class="table">
	              <tr>
	              </tr>
	          </tbody>
	      </table>
	    </div>
	    <div class="modal-footer">
	      <button type="button" class="btn btn-default" data-dismiss="modal">Sluiten</button>
	    </div>
	  </div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

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
	
	<jsp:include page="/WEB-INF/jsp/PusherAlerts.jsp" />
    
  </body>
</html>