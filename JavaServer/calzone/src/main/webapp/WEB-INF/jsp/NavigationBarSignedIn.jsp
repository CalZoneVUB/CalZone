<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="${pageContext.request.contextPath}/" class="navbar-brand"><spring:message
						code="navbar.calzone.text" /></a>
				<button class="navbar-toggle" type="button" data-toggle="collapse"
					data-target="#navbar-main">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="navbar-collapse collapse" id="navbar-main">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}"><spring:message code="navbar.home.text" /></a></li>
					<li class="dropdown"><a class="dropdown-toggle" href="#"
						data-toggle="dropdown">Account<strong class="caret"></strong></a>
						<div class="dropdown-menu"
							style="padding: 15px; padding-bottom: 15px; min-width: 300px;">
							<!-- Login form here -->

							<fieldset>
								<li><a href="profile"><spring:message
											code="navbar.profile.text" /></a></li>
								<li><a href="profile#messages"><spring:message
											code="navbar.messages.text" /></a></li>
								<li><a href="profile#settings"><spring:message
											code="navbar.settings.text" /></a></li>
							    <sec:authorize ifAnyGranted="ROLE_STUDENT">
									<li><a href="profile#settings">ROLE_STUDENT</a></li>
								</sec:authorize>
							</fieldset>
						</div></li>
					<li><a href="EnrolledCourses"><spring:message
								code="navbar.courses.text" /></a></li>
					<li><a href="${pageContext.request.contextPath}/hello"><spring:message
								code="navbar.calendar.text" /></a></li>
					<li><a><spring:message code="navbar.help.text" /></a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="?lang=en">English</a></li>
					<li><a href="?lang=nl">Nederlands</a></li>
					<li><a href="<c:url value='j_spring_security_logout' />"><spring:message
								code="navbar.logout.text" /></a></li>
				</ul>

			</div>
		</div>
	</div>
