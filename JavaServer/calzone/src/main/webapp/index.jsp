<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
</head>

<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/calzone">CalZone</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.request.contextPath}/login/create"><spring:message
								code="login.SignUp.text" /></a></li>
					<li class="divider-vertical"></li>
					<li class="dropdown"><a class="dropdown-toggle" href="#"
						data-toggle="dropdown">Sign In <strong class="caret"></strong></a>
						<div class="dropdown-menu"
							style="padding: 15px; padding-bottom: 15px; min-width: 300px;">
							<!-- Login form here -->
							<form class="navbar-form navbar-right"
								action="<c:url value='j_spring_security_check' />" method="post">
								<fieldset>
									<c:if test="${'fail' eq param.auth}">
										<div class="alert alert-danger">
											<spring:message code="login.loginFailed.text" />
										</div>
									</c:if>
									<div class="input-group" style="padding-bottom: 5px;">
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-user"></span></span> <input type="text"
											name="j_username" class="form-control"
											placeholder='<spring:message code="login.placeHolderUsername.text"/>'>
									</div>
									<div class="input-group" style="padding-bottom: 5px;">
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-lock"></span></span> <input
											type="password" name="j_password" class="form-control"
											placeholder="Password">
									</div>
									<div class="input-group" style="padding-bottom: 5px;">
										<span class="input-group-addon"> <input type="checkbox">
											<label> Remember me</label>
										</span>
									</div>
									<input type="submit" class="btn btn-primary form-control"
										style="clear: left; width: 100%; height: 32px; font-size: 13px;"
										value="<spring:message code="login.login.text" />" />
									<p>
										<br>
										<spring:message code="login.forgotPassword.text" />
										<a href="${pageContext.request.contextPath}/passwordforgot"><spring:message
												code="login.clickHere.text" /></a>
									</p>
								</fieldset>
							</form>
						</div></li>
				</ul>
			</div>
		</div>
	</div>

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
					<select id="ffac" class="form-control">
						<option value="">Faculty</option>
						<option value="WE">Wetenschappen en
							Bio-ingenieurswetenschappen</option>
					</select>
				</div>

				<div class="btn-group " style="padding-bottom: 5px; width: 100%;">
					<select id="fcourse" class="form-control">
						<option value="">Course</option>
						<option value="CW">Computerwetenschappen</option>
					</select>
				</div>

				<div class="btn-group " style="padding-bottom: 5px; width: 100%;">
					<!--<button class="btn btn-default btn-sm dropdown-toggle full-width" type="button" data-toggle="dropdown" style="width:100%;">
					Course <span class="caret"></span>
					</button>-->
					<select id="fyear" class="form-control">
						<option value="">Year</option>
						<option value="3BA">3BA</option>
						<option value="3BAO">3BA (Keuze)</option>
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
		</p>
	</div>


	<div id="agenda-header" class="container fade blank">
		<h3>WEEK 25</h3>
		<h4 id="agenda-selection-title"></h4>
		<!--<hr>-->
	</div>


	<div class="container">
		<!--<div id="agenda-header" class="fade">
			<h3>WEEK 22</h3>
			<h4>3de bachelor computerwetenschappen</h4>
		</div>-->
		<div id="main-content" class="row">
			<br>
			<br>
			<br>
			<div class="agenda with-header auto-scroll">

				<!-- Time markers -->
				<ul class="agenda-time">
					<li class="from-7 to-8"><span>07:00</span></li>
					<li class="from-8 to-9"><span>08:00</span></li>
					<li class="from-9 to-10"><span>09:00</span></li>
					<li class="from-10 to-11"><span>10:00</span></li>
					<li class="from-11 to-12"><span>11:00</span></li>
					<li class="from-12 to-13 blue"><span>NOON</span></li>
					<li class="from-13 to-14"><span>13:00</span></li>
					<li class="from-14 to-15"><span>14:00</span></li>
					<li class="from-15 to-16"><span>15:00</span></li>
					<li class="from-16 to-17"><span>16:00</span></li>
					<li class="from-17 to-18"><span>17:00</span></li>
					<li class="from-18 to-19"><span>18:00</span></li>
					<li class="from-19 to-20"><span>19:00</span></li>
					<li class="at-20"><span>20:00</span></li>
				</ul>

				<!-- Columns wrapper -->
				<div class="agenda-wrapper">

					<!-- Events list -->
					<div class="agenda-events agenda-day1">

						<div class="agenda-header">Monday</div>

						<span class="agenda-event anthracite-gradient from-10 to-13">
							<time>10:00 - 13:00</time> Kansrekening en statistiek (HOC)<br>
						<br> G.1.022 - 22-29, 34<br> MAES DOMINIQUE
						</span>

					</div>

					<!-- Events list -->
					<div class="agenda-events agenda-day2">

						<div class="agenda-header">
							Tuesday <small>03/03/04</small>
						</div>

						<a href="#" class="agenda-event from-8 to-11"> <time>8
								AM - 11 AM</time> Event description
						</a> <span class="agenda-event from-16-30 to-17-30"> <time>4:30
								PM - 5:30 PM</time> Event description
						</span>

						<div class="agenda-now" style="top: 63.75%">
							<span>03:23</span>
						</div>

					</div>

					<!-- Events list -->
					<div class="agenda-events agenda-day3">

						<div class="agenda-header">Wednesday</div>

						<a href="#" class="agenda-event from-7 to-9"> <time>7
								AM - 9 AM</time> Event description <span class="ribbon tiny"><span
								class="ribbon-inner orange-gradient">Priv</span></span>
						</a> <span class="agenda-event from-10 to-11-30 event-1-on-2">
							<time>10 AM - 11:30 AM</time> Event description
						</span> <span
							class="agenda-event from-11 to-13 event-2-on-2 anthracite-gradient">
							<time>11 AM - 1 PM</time> Event description
						</span>

					</div>

					<!-- Events list -->
					<div class="agenda-events agenda-day4">

						<div class="agenda-header">Thursday</div>

						<a href="#" class="agenda-event from-9 to-10"> <time>9
								AM - 10 AM</time> Event description
						</a> <span class="agenda-event from-16 to-17-30 event-1-on-2">
							<time>4 PM - 5:30 PM</time> Event description
						</span> <span
							class="agenda-event from-17 to-19-30 event-2-on-2 blue-gradient">
							<time>5 PM - 7:30 PM</time> Event description
						</span>

					</div>

					<!-- Events list -->
					<div class="agenda-events agenda-day5">

						<div class="agenda-header">Friday</div>

						<a href="#" class="agenda-event from-8 to-9"> <time>8
								AM - 9 AM</time> Event description
						</a> <span class="agenda-event from-11 to-13 anthracite-gradient">
							<time>11 AM - 1 PM</time> Event description
						</span> <span class="agenda-event from-17 to-19-30 blue-gradient">
							<time>5 PM - 7:30 PM</time> Event description
						</span>

					</div>

					<!-- Events list -->
					<div class="agenda-events agenda-day6">

						<div class="agenda-header">Saturday</div>

						<div class="dark-stripes from-12 to-14"></div>

					</div>

					<!-- Events list -->
					<div class="agenda-events agenda-day7">

						<div class="agenda-header">Sunday</div>

						<div class="dark-stripes from-12 to-14"></div>

					</div>

				</div>

			</div>
		</div>
	</div>
	<hr>

	<div class="container">
		<div class="row">
			<div class="col-md-12 text-center">
				<p>&copy; Vrije Universiteit Brussel - Team CalZone</p>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/themes/js/bootstrap.min.js"></script>

	<!-- JavaScript at the bottom for fast page loading -->

	<!-- Scripts -->
	<script
		src="${pageContext.request.contextPath}/themes/js/libs/setup.js"></script>
	<script
		src="${pageContext.request.contextPath}/themes/js/libs/agenda.js"></script>
	<script src="${pageContext.request.contextPath}/themes/js/agenda.js"></script>

	<script>
		// Call template init (optional, but faster if called manually)
		$.template.init();
	</script>
</body>
</html>