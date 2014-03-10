<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>CalZone</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootswatch.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/calzone.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
      <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <script src="${pageContext.request.contextPath}/js/bsa.js"></script>
	
    <jsp:include page="NavigationBarSignedIn.jsp" />
	
	
	<div class="container">
	
        <div class="row">
          <div class="col-lg-12">
            <div class="page-header">
              <h1 id="type">Calendar Page</h1>
            </div>
          </div>
        </div>
        
        <div class="page-header">

		<div class="pull-right form-inline">
			<div class="btn-group">
				<button class="btn btn-primary" data-calendar-nav="prev"><< Prev</button>
				<button class="btn" data-calendar-nav="today">Today</button>
				<button class="btn btn-primary" data-calendar-nav="next">Next >></button>
			</div>
			<div class="btn-group">
				<button class="btn btn-warning" data-calendar-view="year">Year</button>
				<button class="btn btn-warning active" data-calendar-view="month">Month</button>
				<button class="btn btn-warning" data-calendar-view="week">Week</button>
				<button class="btn btn-warning" data-calendar-view="day">Day</button>
			</div>
		</div>

		<h3></h3>
			<small>To see example with events navigate to march 2013</small>
		</div>
	
        <div class="row">
		<div class="span9">
			<div id="calendar"></div>
		</div>
		<div class="span3">
			<div class="row-fluid">
				<select id="first_day" class="span12">
					<option value="" selected="selected">First day of week language-dependant</option>
					<option value="2">First day of week is Sunday</option>
					<option value="1">First day of week is Monday</option>
				</select>
				<select id="language" class="span12">
					<option value="">Select Language (default: nl-BE)</option>
					<option value="en-US">English</option>
					<option value="fr-FR">French</option>
					<option value="de-DE">German</option>
					<option value="el-GR">Greek</option>
					<option value="it-IT">Italian</option>
					<option value="pl-PL">Polish</option>
					<option value="pt-BR">Portuguese (Brazil)</option>
					<option value="es-MX">Spanish (Mexico)</option>
					<option value="es-ES">Spanish (Spain)</option>
					<option value="ru-RU">Russian</option>
					<option value="sv-SE">Swedish</option>
				</select>
				<label class="checkbox">
					<input type="checkbox" value="#events-modal" id="events-in-modal"> Open events in modal window
				</label>
			</div>

			<h4>Events</h4>
			<small>This list is populated with events dynamically</small>
			<ul id="eventlist" class="nav nav-list"></ul>
		</div>
	</div>
	</div>


    <script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootswatch.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/underscore/underscore-min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jstimezonedetect/jstz.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/language/en-US.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/language/fr-FR.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/language/de-DE.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/language/el-GR.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/language/it-IT.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/language/pl-PL.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/language/pt-BR.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/language/es-MX.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/language/es-ES.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/language/ru-RU.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/language/sv-SE.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/app.js"></script>
  </body>
</html>