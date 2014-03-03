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
				<form class="bs-example form-horizontal bottom">
					<fieldset>
						<div class="form-group">
							<div class="col-lg-12 btn-group">
							  <button type="button" class="btn btn-default dropdown-toggle full-width" data-toggle="dropdown">
							    Faculteit <span class="caret"></span>
							  </button>
							  <ul class="dropdown-menu" role="menu">
							    <li><a href="#">Action</a></li>
							    <li><a href="#">Another action</a></li>
							    <li><a href="#">Something else here</a></li>
							    <li class="divider"></li>
							    <li><a href="#">Separated link</a></li>
							  </ul>
							</div>
							<br><br>
							<div class="col-lg-12 btn-group">
							  <button type="button" class="btn btn-default dropdown-toggle full-width" data-toggle="dropdown">
							    Richting <span class="caret"></span>
							  </button>
							  <ul class="dropdown-menu" role="menu">
							    <li><a href="#">Action</a></li>
							    <li><a href="#">Another action</a></li>
							    <li><a href="#">Something else here</a></li>
							    <li class="divider"></li>
							    <li><a href="#">Separated link</a></li>
							  </ul>
							</div>
							<br><br>
							<div class="col-lg-12 btn-group">
							  <button type="button" class="btn btn-default dropdown-toggle full-width" data-toggle="dropdown">
							    Jaar <span class="caret"></span>
							  </button>
							  <ul class="dropdown-menu" role="menu">
							    <li><a href="#">Action</a></li>
							    <li><a href="#">Another action</a></li>
							    <li><a href="#">Something else here</a></li>
							    <li class="divider"></li>
							    <li><a href="#">Separated link</a></li>
							  </ul>
							</div>
							<br><br>
							<div class="col-lg-12">
								<a href="${pageContext.request.contextPath}/login"><div class="btn btn-primary full-width">Toon</div></a> 
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>

	<div id="footer">
		<div class="container">
			<div class="col-lg-7">&nbsp;</div>
			<div class="col-lg-4 box-bottom box-shadow">
				<form class="bs-example form-horizontal">
					<fieldset>
						<div class="form-group">
							<div class="col-lg-12">
								<a href="${pageContext.request.contextPath}/login"><div class="btn btn-primary full-width">Login</div></a> 
							</div>
						</div>
					</fieldset>
				</form>
				<div class="col-lg-12">
					<a href="${pageContext.request.contextPath}/login/create"><p>Lost password?</p></a> 
					<a href="${pageContext.request.contextPath}/login/create"><p>Sign Up</p></a> 
				</div>
			</div>
		</div>
	</div>

    <script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootswatch.js"></script>
  </body>
</html>
