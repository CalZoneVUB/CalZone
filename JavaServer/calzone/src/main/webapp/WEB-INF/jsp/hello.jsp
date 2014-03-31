<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="shortcut icon" href="../../assets/ico/favicon.ico">

		<title>Uurrooster</title>

		<!-- Bootstrap core CSS -->
		<link
			href="${pageContext.request.contextPath}/themes/css/lumen/bootstrap.css"
			rel="stylesheet">

		<!-- Custom styles for this template -->
		<link href="${pageContext.request.contextPath}/themes/css/dashboard.css"
			rel="stylesheet">
		
		<style>
		.timeline {
			list-style: none;
			padding: 20px 0 20px;
			position: relative;
		}

		.timeline:before {
			top: 0;
			bottom: 0;
			position: absolute;
			content: " ";
			width: 3px;
			background-color: #eeeeee;
			left: 50%;
			margin-left: -1.5px;
		}

		.timeline > li {
			margin-bottom: 20px;
			position: relative;
		}

		.timeline > li:before,
		.timeline > li:after {
			content: " ";
			display: table;
		}

		.timeline > li:after {
			clear: both;
		}

		.timeline > li:before,
		.timeline > li:after {
			content: " ";
			display: table;
		}

		.timeline > li:after {
		clear: both;
		}

		.timeline > li > .timeline-panel {
			width: 46%;
			float: left;
			border: 1px solid #d4d4d4;
			border-radius: 2px;
			padding: 20px;
			position: relative;
			-webkit-box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
			box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
		}

		.timeline > li > .timeline-panel:before {
			position: absolute;
			top: 26px;
			right: -15px;
			display: inline-block;
			border-top: 15px solid transparent;
			border-left: 15px solid #ccc;
			border-right: 0 solid #ccc;
			border-bottom: 15px solid transparent;
			content: " ";
		}

		.timeline > li > .timeline-panel:after {
			position: absolute;
			top: 27px;
			right: -14px;
			display: inline-block;
			border-top: 14px solid transparent;
			border-left: 14px solid #fff;
			border-right: 0 solid #fff;
			border-bottom: 14px solid transparent;
			content: " ";
		}

		.timeline > li > .timeline-badge {
			color: #fff;
			width: 50px;
			height: 50px;
			line-height: 50px;
			font-size: 1.4em;
			text-align: center;
			position: absolute;
			top: 16px;
			left: 50%;
			margin-left: -25px;
			background-color: #999999;
			z-index: 100;
			border-top-right-radius: 50%;
			border-top-left-radius: 50%;
			border-bottom-right-radius: 50%;
			border-bottom-left-radius: 50%;
		}

		.timeline > li.timeline-inverted > .timeline-panel {
			float: right;
		}

		.timeline > li.timeline-inverted > .timeline-panel:before {
			border-left-width: 0;
			border-right-width: 15px;
			left: -15px;
			right: auto;
		}

		.timeline > li.timeline-inverted > .timeline-panel:after {
			border-left-width: 0;
			border-right-width: 14px;
			left: -14px;
			right: auto;
		}

		.timeline-badge.primary {
			background-color: #2e6da4 !important;
		}

		.timeline-badge.success {
			background-color: #3f903f !important;
		}

		.timeline-badge.warning {
			background-color: #f0ad4e !important;
		}

		.timeline-badge.danger {
			background-color: #d9534f !important;
		}

		.timeline-badge.info {
			background-color: #5bc0de !important;
		}

		.timeline-title {
			margin-top: 0;
			color: inherit;
		}

		.timeline-body > p,
		.timeline-body > ul {
			margin-bottom: 0;
		}

		.timeline-body > p + p {
			margin-top: 5px;
		}
		
		.timeline-alerts {
			opacity: 0.8;
			float:right;
			color: #fff;
			padding: 5px 8px 5px 8px;
			border-top-right-radius: 50%;
			border-top-left-radius: 50%;
			border-bottom-right-radius: 50%;
			border-bottom-left-radius: 50%;
		}
		
		.timeline-alerts:hover {
			opacity: 1;
		}

		@media (max-width: 767px) {
			ul.timeline:before {
			left: 40px;
		}

		ul.timeline > li > .timeline-panel {
			width: calc(100% - 90px);
			width: -moz-calc(100% - 90px);
			width: -webkit-calc(100% - 90px);
		}

		ul.timeline > li > .timeline-badge {
			left: 15px;
			margin-left: 0;
			top: 16px;
		}

		ul.timeline > li > .timeline-panel {
			float: right;
		}

		ul.timeline > li > .timeline-panel:before {
			border-left-width: 0;
			border-right-width: 15px;
			left: -15px;
			right: auto;
		}

		ul.timeline > li > .timeline-panel:after {
			border-left-width: 0;
			border-right-width: 14px;
			left: -14px;
			right: auto;
		}
		}
		</style>
	</head>

  <body>

	<div class="container-fluid">
      <div class="row">
        <div class="col-sm-12 col-md-12 main">
			
			
			<ul class="timeline">
				<li>
				  <div class="timeline-badge"><i class="glyphicon glyphicon-time"></i></div>
				  <div class="timeline-panel">
					<div class="timeline-heading">
					  <h4 class="timeline-title">Vandaag</h4>
					  <p><small class="text-muted"><i class="glyphicon glyphicon-time"></i> eerste les over 3 uur</small></p>
					</div>
					<div class="timeline-body">
					  <p></p>
					</div>
				  </div>
				</li>
				<li class="timeline-inverted">
				  <div class="timeline-badge warning"><i class="glyphicon glyphicon-bullhorn"></i></div>
				  <div class="timeline-panel">
					<div class="timeline-heading">
					  <h4 class="timeline-title">Wijziging in rooster</h4>
					</div>
					<div class="timeline-body">
					  <p>G.1.022
						  <br>MAES DOMINIQUE
						  <br>10:00 - 13:00
					  </p>
					</div>
				  </div>
				</li>
				<li class="timeline-inverted">
				  <div class="timeline-badge success">10:00</div>
				  <div class="timeline-panel">
					<div class="timeline-heading">
					  <h4 class="timeline-title">Kansrekening en statistiek (HOC)</h4>
					</div>
					<div class="timeline-body">
						<div class="timeline-badge warning timeline-alerts "><i class="glyphicon glyphicon-bullhorn"></i></div>
						<div class="timeline-content">
							<p>G.1.022
								<br>MAES DOMINIQUE
								<br>10:00 - 13:00
							</p>
							<hr>
							<div class="alert alert-warning"><i class="glyphicon glyphicon-bullhorn"></i> Opgelet: les is één uur vervroegt.</div>
						</div>
					</div>
				  </div>
				</li>
				<li class="timeline-inverted">
				  <div class="timeline-badge success">16:00</div>
				  <div class="timeline-panel">
					<div class="timeline-heading">
					  <h4 class="timeline-title">Numerieke wiskunde (HOC+WPO)</h4>
					</div>
					<div class="timeline-body">
					  <p>F.5.211
						  <br>Barbe Kurt
						  <br>16:00 - 18:00
					  </p>
					</div>
				  </div>
				</li>
				<li>
				  <div class="timeline-badge"><i class="glyphicon glyphicon-time"></i></div>
				  <div class="timeline-panel">
					<div class="timeline-heading">
					  <h4 class="timeline-title">Donderdag</h4>
					  <p><small class="text-muted"><i class="glyphicon glyphicon-time"></i> eerste les om 14 uur</small></p>
					</div>
					<div class="timeline-body">
					  <p></p>
					</div>
				  </div>
				</li>
				<li class="timeline-inverted">
				  <div class="timeline-badge success">14:00</div>
				  <div class="timeline-panel">
					<div class="timeline-heading">
					  <h4 class="timeline-title">Kansrekening en statistiek (WPO)</h4>
					</div>
					<div class="timeline-body">
					  <p>G.1.021
						  <br>Thumas Dorien
						  <br>14:00 - 16:00
					  </p>
					</div>
				  </div>
				</li>
				<li>
				  <div class="timeline-badge info"><i class="glyphicon glyphicon-floppy-disk"></i></div>
				  <div class="timeline-panel">
					<div class="timeline-heading">
					  <h4 class="timeline-title">Agenda item</h4>
					</div>
					<div class="timeline-body">
					  <p>met icons en actions bar.</p>
					  <hr>
					  <div class="btn-group">
						<button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown">
						  <i class="glyphicon glyphicon-cog"></i> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
						  <li><a href="#">Go fish</a></li>
						  <li><a href="#">Cacalzone</a></li>
						  <li><a href="#">Something else here</a></li>
						  <li class="divider"></li>
						  <li><a href="#">Separated link</a></li>
						</ul>
					  </div>
					</div>
				  </div>
				</li>
				<li>
				  <div class="timeline-panel">
					<div class="timeline-heading">
					  <h4 class="timeline-title">Mussum ipsum cacilds</h4>
					</div>
					<div class="timeline-body">
					  <p>Mussum ipsum cacilds, vidis litro abertis. Consetis adipiscings elitis. Pra lá , depois divoltis porris, paradis. Paisis, filhis, espiritis santis. Mé faiz elementum girarzis, nisi eros vermeio, in elementis mé pra quem é amistosis quis leo. Manduma pindureta quium dia nois paga. Sapien in monti palavris qui num significa nadis i pareci latim. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.</p>
					</div>
				  </div>
				</li>
				<li class="timeline-inverted">
				  <div class="timeline-badge success"><i class="glyphicon glyphicon-thumbs-up"></i></div>
				  <div class="timeline-panel">
					<div class="timeline-heading">
					  <h4 class="timeline-title">Mussum ipsum cacilds</h4>
					</div>
					<div class="timeline-body">
					  <p>Mussum ipsum cacilds, vidis litro abertis. Consetis adipiscings elitis. Pra lá , depois divoltis porris, paradis. Paisis, filhis, espiritis santis. Mé faiz elementum girarzis, nisi eros vermeio, in elementis mé pra quem é amistosis quis leo. Manduma pindureta quium dia nois paga. Sapien in monti palavris qui num significa nadis i pareci latim. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.</p>
					</div>
				  </div>
				</li>
			</ul>
		</div>
	</div>
	<hr>

	<div class="container">
		<div class="row">
		  <div class="col-md-12 text-center"><p>Miaaaauuuw.</p></div>
		</div>
	</div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootswatch.js"></script>

  </body>
</html>