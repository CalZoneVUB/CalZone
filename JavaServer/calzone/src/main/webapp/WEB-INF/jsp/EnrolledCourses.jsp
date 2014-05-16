<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>CalZone</title>
<link
	href="${pageContext.request.contextPath}/themes/css/lumen/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/themes/css/lumen/bootstrap.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/themes/css/dashboard.css"
	rel="stylesheet">

<link href="${pageContext.request.contextPath}/themes/css/dashboard.css"
	rel="stylesheet">

<!-- Additional styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/themes/css/agenda.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/themes/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/themes/css/custom.css">

<!-- x-editable (bootstrap version) -->
<link
	href="${pageContext.request.contextPath}/css/bootstrap-editable.css"
	rel="stylesheet" />

<link
	href="${pageContext.request.contextPath}/css/dataTables.bootstrap.css"
	rel="stylesheet" />

<!-- JavaScript at bottom except for Modernizr -->
<script
	src="${pageContext.request.contextPath}/themes/js/libs/modernizr.custom.js"></script>
<link href="${pageContext.request.contextPath}/css/select2.css"
	rel="stylesheet" type="text/css">

</head>
<body>

	<sec:authorize access="isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBarSignedIn.jsp" />
	</sec:authorize>

	<sec:authorize access="!isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBar.jsp" />
	</sec:authorize>

	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h1 id="type">
						<spring:message code="EnrolledCourses.title.text" />
						<a href="EnrollCourses">
							<button class="btn btn-success ">
								<span class="glyphicon glyphicon-plus"></span>&nbsp;
								<spring:message code="EnrolledCourses.add.text" />
							</button>
						</a>
					</h1>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<div class="table-responsive">
					<table id="myTable" class="table table-striped table-hover">
						<thead>
							<tr>
								<th><spring:message code="EnrolledCourses.coursetitle.text" /></th>
								<th><spring:message code="EnrolledCourses.profname.text" /></th>
								<th><spring:message code="EnrolledCourses.assistant.text" /></th>
								<th><spring:message code="EnrolledCourses.courseID.text" /></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${enrollmentArrayList}" var="course">
								<tr id=${course.id}>
									<td>${course.courseName}</td>
									<!-- <td>&nbsp;</td> -->
									<td><ul style="list-style-type: none; padding-left: 0;">
											<!-- TOEGEVOEGD -->
											<%-- <c:if test="${not empty course.listOfProfessors}">
												<c:forEach items="${course.listOfProfessors}"
													var="professor">
													<li>${professor.person.firstName}
														${professor.person.lastName}</li>
												</c:forEach>
											</c:if> --%>
										</ul></td>
									<td><ul style="list-style-type: none; padding-left: 0;">
											<!-- TOEGEVOEGD -->
											<%-- 	<c:if test="${not empty course.listOfProfessors}">
												<c:forEach items="${course.listOfProfessors}"
													var="assistant">
													<li>${assistant.person.firstName}
														${assistant.person.lastName}</li>
												</c:forEach>
											</c:if> --%>
										</ul></td>

									<td>${course.id}</td>
									<td><a data-toggle="modal" href="#log-${course.id}"
										class="btn btn-primary btn-xs"><spring:message
												code="EnrolledCourses.confirmation.text" /></a></td>
								</tr>
								<div class="modal fade" id="log-${course.id}" tabindex="-1"
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
													data=${course.id } data-dismiss="modal"
													onclick="deleteCourse(${course.id})">
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
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<hr>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />

	<script
		src="${pageContext.request.contextPath}/js/jquery/jquery-2.1.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/calzone.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap-editable.js"></script>
	<script src="${pageContext.request.contextPath}/js/xedit/profile.js"></script>
	<script src="${pageContext.request.contextPath}/js/select2.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.dataTables.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/dataTables.bootstrap.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/dataTables.bootstrapPagination.js"></script>
	<script>
		jQuery(document).ready(function() {
			jQuery(".modeldelete").bind("click", function() {
				//alert($(this).attr("data"));
				var id = $(this).attr("data");
				$("#" + id).hide();
			});
		});
		
		function deleteCourse(id) {
			$.ajax({
				type: "GET",
				url:"EnrolledCourses/remove/" + id,
			})
			.success(function (data) {
				console.log(data);
			})
		}

		$(document).ready(function() {
			$('#myTable').DataTable();
		});
	</script>
	
	<jsp:include page="/WEB-INF/jsp/PusherAlerts.jsp" />

</body>
</html>