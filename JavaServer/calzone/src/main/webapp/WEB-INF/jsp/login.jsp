<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
      <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
    <![endif]-->
    <style>
	Body, HTML{
		height:100%;
		margin: 0;
	}
	</style>
  </head>
<body>
<!-- Wrap all page content here -->
	<div id="wrap">
		<div id='myoutercontainer' class="container ">
			<div id='myinnercontainer' class="col-lg-7 style="display:inline-block; vertical-align:middle"">
				<img src="${pageContext.request.contextPath}/img/CalZone.png" alt="CalZone Logo" class="img-responsive center vcenter">
			</div>
			<div class="col-lg-7">&nbsp;</div>
			<div class="col-lg-4 box-top box-shadow">
				<form:form commandName="user" class="bs-example form-horizontal">
					<fieldset>
						<p>View general programmes</p>
							<div class="form-group">
								<div class="col-lg-12">
									<a href="${pageContext.request.contextPath}"><div class="btn btn-primary full-width">Programmes</div></a> 
								</div>
							</div>
							<br><br>
						<c:if test="${'fail' eq param.auth}">
							<div class="alert alert-danger">Login Failed: User name or password incorrect</div>
						</c:if>
						<div class="form-group">
							<div class="col-lg-12">
								<form:input path="userName" class="form-control" placeholder="Username" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<form:password path="password" class="form-control" placeholder="Password"/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<button type="submit" class="btn btn-primary full-width">Login</button>
								<p><br>Forgot password? <a>click here</a></p>
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>
    
    <div id="footer">
		<div class="container">
			<div class="col-lg-7">&nbsp;</div>
			<div class="col-lg-4 box-bottom box-shadow">
				<div class="bs-example form-horizontal">
					<fieldset>
						<p>Don't have a CalZone account yet?</p>
						<div class="form-group">
							<div class="col-lg-12">
								<a href="${pageContext.request.contextPath}/login/create"><button class="btn btn-primary full-width">Sign up</button></a> 
							</div>
						</div>
					</fieldset>
				</div>
			</div>
		</div>
	</div>
	
    <script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootswatch.js"></script>
  </body>
</html>