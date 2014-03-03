<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css"
	media="screen">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootswatch.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/calzone.css">
<style>
Body,HTML {
	height: 100%;
	margin: 0;
}
</style>
</head>
<body>

<jsp:include page="NavigationBar.jsp" />

<div class="panel panel-default">
	<div class="panel-heading">Officiële VUB-vakken</div>
	<div class="panel-body">
		<table style="width: 500px">
			<tr>
				<td>Naam Vak</td>
				<td>ID</td>
				<td>Titularis</td>
				<td>
					<button type="button" class="btn btn-default btn-xs">
						<span class="glyphicon glyphicon-remove"></span>
					</button>
				</td>
			</tr>
			<tr>
				<td>Naam Vak</td>
				<td>ID</td>
				<td>Titularis</td>
				<td>
					<button type="button" class="btn btn-default btn-xs">
						<span class="glyphicon glyphicon-remove"></span>
					</button>
				</td>
			</tr>
			<tr>
				<td>Naam Vak</td>
				<td>ID</td>
				<td>Titularis</td>
				<td>
					<button type="button" class="btn btn-default btn-xs">
						<span class="glyphicon glyphicon-remove"></span>
					</button>
				</td>
			</tr>
		</table>
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<a href="${pageContext.request.contextPath}"><div
				class="btn btn-primary full-width">Inschrijven Op Cursus</div></a>
	</div>
</div>

</body>
</html>