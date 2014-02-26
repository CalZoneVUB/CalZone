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
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
      <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <script src="${pageContext.request.contextPath}/js/bsa.js"></script>
	
    <div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <a href="${pageContext.request.contextPath}/" class="navbar-brand">CalZone</a>
          <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
          <ul class="nav navbar-nav">
            <li>
              <a href="${pageContext.request.contextPath}">Home</a>
            </li>
            <li>
              <a>Account</a>
            </li>
            <li>
              <a>Vakken</a>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/hello/">Kalender</a>
            </li>
            <li>
              <a>Help</a>
            </li>
          </ul>

          <ul class="nav navbar-nav navbar-right">
            <li><a href="<c:url value='j_spring_security_logout' />" >Logout</a></li>
          </ul>

        </div>
      </div>
    </div>

	<div class="container">
	
        <div class="row">
          <div class="col-lg-12">
            <div class="page-header">
              <h1 id="type">Profile Page</h1>
            </div>
          </div>
        </div>
        
        <!-- Nav tabs -->
		<ul class="nav nav-tabs">
		  <li class="active"><a href="#profile" data-toggle="tab">Profile</a></li>
		  <li><a href="#messages" data-toggle="tab">Messages</a></li>
		  <li><a href="#settings" data-toggle="tab">Settings</a></li>
		</ul>
		
		<!-- Tab panes -->
		<div class="tab-content">
		  <div class="tab-pane active" id="profile">
		  	<div class="row">
				<c:if test="${'fail' eq param.lookup}">
				<div style="color: red">
					User not in the database.<br />
				</div>
				</c:if>
			     
				<div class="col-lg-6 outer-box">
					<div class="box-shadow profile-box">
						<h4><strong>Username:</strong> ${username}</h4><br>
						<h4><strong>First Name:</strong> ${firstname}</h4>
						<h4><strong>Last Name:</strong> ${lastname}</h4><br>
						<h4><strong>E-mail:</strong> ${email}</h4>
					</div>
				</div>
				
			</div>
		  </div>
		  <div class="tab-pane" id="messages">
			
			<div class="col-lg-6 outer-box">
				<div class="box-shadow profile-box">
					Notifications
				</div>
			</div>
		  
		  </div>
		  <div class="tab-pane" id="settings">...</div>
		</div>
        
	</div>


    <script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootswatch.js"></script>
  </body>
</html>