<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>CalZone - Account Activation</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" media="screen">
	<link href="${pageContext.request.contextPath}/themes/css/lumen/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/themes/css/dashboard.css" rel="stylesheet">
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
	<div class="container ">
		<div class="col-lg-4">&nbsp;</div>
		<div class="col-lg-4 box-top box-shadow">
			<form class="bs-example form-horizontal">
				<fieldset>
					<div class="spacer"/></div>
					<h3>Your account is now activated.<br><br></h3>
					<div class="form-group">
						<div class="col-lg-12">
							<a href="${pageContext.request.contextPath}"><div class="btn btn-primary full-width">Home</div></a> 
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>

    <script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootswatch.js"></script>
  </body>
</html>