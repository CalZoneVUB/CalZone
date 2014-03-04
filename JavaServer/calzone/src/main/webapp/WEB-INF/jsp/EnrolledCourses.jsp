<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>CalZone</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css"
	media="screen">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootswatch.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/calzone.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/profile.css">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
      <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<script src="${pageContext.request.contextPath}/js/bsa.js"></script>

	<jsp:include page="NavigationBar.jsp" />

	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h1 id="type">
						<spring:message code="EnrolledCourses.title.text" />
					</h1>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th><spring:message code="EnrolledCourses.coursetitle.text" /></th>
								<th><spring:message code="EnrolledCourses.profname.text" /></th>
								<th><spring:message code="EnrolledCourses.courseID.text" /></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${courseArrayList}" var="course">
								<tr id=${course.iD}>
									<td>&nbsp;</td>
									<c:forEach items="${course.listOfProfessors}" var="professor">
										<td>${professor.firstName} ${professor.lastName}</td>
									</c:forEach>
									<td>${course.iD}</td>
									<td><a data-toggle="modal" href="#log-${course.iD}"
										class="btn btn-primary btn-xs"><spring:message code="EnrolledCourses.confirmation.text" /></a></td>
								</tr>
								<div class="modal fade" id="log-${course.iD}" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<h4 class="modal-title">
													<spring:message code="EnrolledCourses.warning.text" />
												</h4>
											</div>
											<div class="modal-body">
												<p>
													<strong><spring:message
															code="EnrolledCourses.remove.text" /></strong>
												</p>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-danger modeldelete"
													data=${course.iD} data-dismiss="modal">
													<spring:message code="EnrolledCourses.confirmation.text" />
												</button>
												<button type="button" class="btn btn-default modeldelete"
													data-dismiss="modal">
													<span class="glyphicon glyphicon-remove"></span>
													<spring:message code="EnrolledCourses.cancel.text" />
												</button>
											</div>
										</div>
									</div>
								</div>


								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<script
			src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootswatch.js"></script>
		<script>
		jQuery( document).ready(function(){
			jQuery(".modeldelete").bind("click", function(){
				//alert($(this).attr("data"));
				var id =$(this).attr("data");
				$("#"+id).hide();
			});
		}
		);
		</script>
</body>
</html>