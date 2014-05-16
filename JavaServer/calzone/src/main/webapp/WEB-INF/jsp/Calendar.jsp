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
		<div class="col-sm-12 col-md-12 main" style="height:100px;">
			<!-- ChangeModal -->
			<div class="modal fade" id="entryChangeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" aria-hidden="true" id="entryChangeModalX">&times;</button>
			        <h4 class="modal-title" id="entryChangeModalLabel">Tijdstip aanpassen</h4>
			      </div>
			      <div id="entryChangeModalBody" class="modal-body">
			        ...
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" id="entryChangeModalCancel">Sluiten</button>
			        <button type="button" class="btn btn-primary" id="entryChangeModalSave">Opslaan</button>
			      </div>
			    </div>
			  </div>
			</div>
			
			<!-- EditModal -->
			<div class="modal fade" id="entryEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
						<button type="button" class="close" aria-hidden="true" id="entryEditModalX">×</button>
						<h4 class="modal-title"id="entryEditModalLabel">Les bewerken</h4>
			      	</div>
					<div id="addNewTimeLogMessage"></div>
			      	<div id="entryEditModalBody" class="modal-body">
						<form role="form"> 
						  <div class="form-group">
							<label for="NewTimeLogDescription">Lokaal wijzigen</label>
						  	<select id="entryEditModalSelect" class="form-control">
						  		<option value="default">(beschikbare lokalen)</option>
							</select>
						  </div>
						  <div class="form-group">
							<label for="NewTimeLogDescription">Mededeling (aan de studenten)</label>
							<textarea id="entryEditModalMessage" class="form-control" rows="3"></textarea>
						  </div>
						</form>
					</div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" id="entryEditModalCancel">Sluiten</button>
			        <button type="button" class="btn btn-primary" id="entryEditModalSave">Opslaan</button>
			      </div>
			    </div>
			  </div>
			</div>
			
			<sec:authorize ifAnyGranted="ROLE_ADMIN">
	      	<div class="row">
				<div class="col-sm-10 col-md-10 main">
					<select class="form-control scheduleoption-item" id="TrajectSelectionSchedular">
						<option>Bezig met laden trajecten...</option>
					</select>
				</div>
				<div class="col-sm-2 col-md-2 main">
					<button type="button" class="btn btn-primary btn-sm scheduleoption-item" id="ScheduleTrajectView">
						<span class="glyphicon glyphicon-play"></span>&nbsp;View Traject Schedule
					</button>
				</div>
			</div>
			</sec:authorize>
			
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
	<script src='${pageContext.request.contextPath}/js/jquery/jquery-ui.custom.min.js'></script>
	<script src='${pageContext.request.contextPath}/fullcalendar/fullcalendar.js'></script>

    <sec:authorize ifAnyGranted="ROLE_STUDENT">
	<script src='${pageContext.request.contextPath}/fullcalendar/CalendarNotEditable.js'></script>
	</sec:authorize>
	<sec:authorize ifAnyGranted="ROLE_ADMIN">
	<script src='${pageContext.request.contextPath}/fullcalendar/CalendarEditable.js'></script>
	<script src='${pageContext.request.contextPath}/fullcalendar/CalendarAdminSchedular.js'></script>
	</sec:authorize>
	<sec:authorize ifAnyGranted="ROLE_PROFESSOR">
	<script src='${pageContext.request.contextPath}/fullcalendar/CalendarEditable.js'></script>
	</sec:authorize>
    
    <jsp:include page="/WEB-INF/jsp/PusherAlerts.jsp" />
    
  </body>
</html>