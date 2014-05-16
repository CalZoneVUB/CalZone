<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<div class="navbar navbar-inverse navbar-fixed-top"
	onload=getAmountNotifications()>
	<div class="container">
		<div class="navbar-header">
			<a href="${pageContext.request.contextPath}" class="navbar-brand"><spring:message
					code="navbar.calzone.text" /></a>
			<button class="navbar-toggle" type="button" data-toggle="collapse"
				data-target="#navbar-main">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="navbar-main">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}"><spring:message
							code="navbar.home.text" /></a></li>
				
				<li><a href="profile"><spring:message code="navbar.profile.text" /></a></li>

				<li><a href="EnrolledCourses"><spring:message
							code="navbar.courses.text" /></a></li>
				<li><a href="calendar"><spring:message
							code="navbar.calendar.text" /></a></li>
				<sec:authorize ifAnyGranted="ROLE_PROFESSOR">
					<li><a href="calendarschedule"><spring:message
								code="navbar.calendarprof.text" /></a></li>
				</sec:authorize>
				<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<li><a href="calendarschedule"><spring:message
								code="navbar.calendarschedular.text" /></a></li>
				</sec:authorize>
				<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<li><a
						href="${pageContext.request.contextPath}/admindashboard">Admindashboard</a></li>
				</sec:authorize>
				<li><a><spring:message code="navbar.help.text" /></a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a class="dropdown-toggle" href="#"
					data-toggle="dropdown"> <!-- onclick=getNotifications() -->
						<div class="notification-icon">
							<!-- <span class="glyphicon glyphicon-bell"></span> -->
							<span class="badge"><var id="amountNotifications">0</var></span>
							<strong class="caret"></strong>
							<!-- TODO change notifications -->
						</div>
				</a>
					<div class="dropdown-menu"
						style="padding: 10px; padding-bottom: 10px; min-width: 300px;">
						<!-- <div class="alert alert-success">Success</div>
							<div class="alert alert-info">Info</div>
							<div class="alert alert-warning">Warning</div>
							<div class="alert alert-danger">Danger</div> -->
						<div id="auth-save" style="max-height: 300px; overflow: auto;"></div>
						<a href=# onclick=removeNotifications() style="margin-top: 15px;">
							Mark all read </a>
					</div></li>
				<li class="dropdown"><a class="dropdown-toggle" href="#"
					data-toggle="dropdown">Language<strong class="caret"></strong></a>
					<div class="dropdown-menu"
						style="padding: 15px; padding-bottom: 15px; min-width: 300px;">
						<fieldset>
							<li><a href="?lang=en">English</a></li>
							<li><a href="?lang=nl">Nederlands</a></li>
						</fieldset>
					</div></li>
				<li><a href="<c:url value='j_spring_security_logout' />"><spring:message
							code="navbar.logout.text" /></a></li>
			</ul>

		</div>
	</div>
</div>

<%-- <script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>  --%>
<script>  
   function getNotifications() {   
  	var arr = "";
  	jQuery.ajax({
  		type: "GET",
  		url:  "${pageContext.request.contextPath}/api/notifications", 
  		data:  {},
  		dataType: "json",
  		success: function(rdata){
  			arr = arr + "<div id=\"auth-save\" style=\"max-height: 300px; overflow: auto;\">";
  			for (var i=0;i<rdata.length;i++) {
  				if (rdata[i].type == "Time") {
  					arr = arr + "<div class=\"alert alert-warning notification\">" 
  							  + rdata[i].message[0] + " <spring:message code="notification.time1.text" /> " 
  							  +	rdata[i].message[1] + " <spring:message code="notification.time2.text" /> " 
  							  +	rdata[i].message[2] + "</div>";
  				} else if (rdata[i].type == "System") {
  					arr = arr + "<div class=\"alert alert-danger notification\">" + rdata[i].message[0] + "</div>";	
  				} else if (rdata[i].type == "Room") {
  					arr += "<div class=\"alert alert-warning notification\">" ;
  					arr += rdata[i].message[0];
  					arr += " changed from room ";
  					arr += rdata[i].message[1];
  					arr += " to ";
  					arr += rdata[i].message[2];
  					arr += "</div>";
  				}
  			};
  			arr = arr + "</div>";
  			console.log("Replacing with: " + arr);
  			$("#auth-save").replaceWith(arr);
  		},
  		error: function() {
  			//Errorcode komt hier...
  		}
  	});
   }

   function removeNotifications() {
	   jQuery.ajax({
	  		type: "GET",
	  		url:  "${pageContext.request.contextPath}/api/notifications/remove", 
	  		data:  {},
	  		dataType: "json",
	  		success: function(){
	  			//Success code
	  			console.log("removing notifications");
	  			amount = 0;
	  			$("#amountNotifications").text(0);
	  			getAmountNotifications();
	  			
	  			$('#auth-save').replaceWith("<div id=\"auth-save\" style=\"max-height: 300px; overflow: auto;\"> </div>");
	  		},
	  		error: function() {
	  			//Error code
	  			console.log("removing notifications failed");
	  		}
	  	});
	   } 
	
     var amount = 0;
	 function getAmountNotifications() {
	   jQuery.ajax({
	  		type: "GET",
	  		url:  "${pageContext.request.contextPath}/api/notifications/amount", 
	  		data:  {},  
	  		dataType: "json",
	  		success: function(rdata){
	  			if (rdata.message != amount) {
	  				$("#amountNotifications").text(rdata.message);
	  				amount = rdata.message;
	  				getNotifications();
	  			} 
	  			
	  		},
	  		error: function() {
	  			//Error code
	  		}
	  	});
	   } 
	 
	 setInterval(function(){
		   getAmountNotifications();
		}, 10000);
	 
	 window.onload = function () {
		 getAmountNotifications();
		 }
	 </script>
