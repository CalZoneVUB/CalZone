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
							<label for="NewTimeLogDescription">Beschrijving</label>
							<textarea id="NewTimeLogDescription" class="form-control" rows="3"></textarea>
						  </div>
						  
						  <div class="form-group">
						  	<select id="NewTimeLogActivity" class="form-control">
						  		<option value="default">(selecteer één activiteit)</option>
						  		
								<option value="Coding: Bekijken van lessenroosters">Coding: Bekijken van lessenroosters</option>
								
								<option value="Coding: Beveiliging">Coding: Beveiliging</option>
								
								<option value="Coding: Functionaliteit voor programmabeheerder">Coding: Functionaliteit voor programmabeheerder</option>
								
								<option value="Coding: Lokalen">Coding: Lokalen</option>
								
								<option value="Coding: Mobiele functionaliteit">Coding: Mobiele functionaliteit</option>
								
								<option value="Coding: Schedular">Coding: Schedular</option>
								
								<option value="Coding: Vakken">Coding: Vakken</option>
								
								<option value="Design User Management">Design User Management</option>
								
								<option value="Design: Lokalen">Design: Lokalen</option>
								
								<option value="Design: Schedular">Design: Schedular</option>
								
								<option value="Design: Vakken">Design: Vakken</option>
								
								<option value="GitHub">GitHub</option>
								
								<option value="Infrastructuur onderzoeken">Infrastructuur onderzoeken</option>
								
								<option value="Installation">Installation</option>
								
								<option value="Meetings">Meetings</option>
								
								<option value="Presentation">Presentation</option>
								
								<option value="Quality Control">Quality Control</option>
								
								<option value="SCMP">SCMP</option>
								
								<option value="SDD">SDD</option>
								
								<option value="SPMP">SPMP</option>
								
								<option value="SQAP">SQAP</option>
								
								<option value="STD">STD</option>
								
								<option value="System Requirements">System Requirements</option>
								
								<option value="Test Execution">Test Execution</option>
								
								<option value="User Management Back End">User Management Back End</option>
								
								<option value="User Management Front End">User Management Front End</option>
								
								<option value="User Manuals">User Manuals</option>
								
								<option value="User Requirements">User Requirements</option>
								
								<option value="Website">Website</option>
								
							</select>
						  </div>
						  
						  <div class="form-group">
							<label>Van</label><br>
							<div class="col-lg-6">
								<input type="text" class="form-control span2" value="12/05/2014" id="NewTimeLogDateFrom">
							</div>
							<div class="col-lg-4">
								<input type="text" class="form-control" value="21:13" id="NewTimeLogTimeFrom">
							</div>
							<br>
						  </div>
						  <div class="form-group">
						  <label>Tot</label><br>
							<div class="col-lg-6">
								<input type="text" class="form-control span2" value="12/05/2014" id="NewTimeLogDateTo">
							</div>
							<div class="col-lg-4">
								<input type="text" class="form-control" value="21:13" id="NewTimeLogTimeTo">
							</div>
							<br><br>
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

    <sec:authorize ifAnyGranted="ROLE_STUDENT">
	<script src='${pageContext.request.contextPath}/fullcalendar/CalendarNotEditable.js'></script>
	</sec:authorize>
	<sec:authorize ifAnyGranted="ROLE_ADMIN">
	<script src='${pageContext.request.contextPath}/fullcalendar/CalendarNotEditable.js'></script>
	</sec:authorize>
	<sec:authorize ifAnyGranted="ROLE_PROFESSOR">
	<script src='${pageContext.request.contextPath}/fullcalendar/CalendarEditable.js'></script>
	</sec:authorize>
    
  </body>
</html>