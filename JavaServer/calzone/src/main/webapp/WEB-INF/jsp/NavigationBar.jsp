<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/">CalZone</a>
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