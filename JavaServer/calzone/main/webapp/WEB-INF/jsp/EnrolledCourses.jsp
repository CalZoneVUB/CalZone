<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>CalZone</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/themes/css/lumen/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/themes/css/dashboard.css"
	rel="stylesheet">


<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>
<body>
	<script src="${pageContext.request.contextPath}/js/bsa.js"></script>

		<sec:authorize access="isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBarSignedIn.jsp" />
	</sec:authorize>
	
	<sec:authorize access="!isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBar.jsp" />
	</sec:authorize>

	<div class="container-fluid">

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
					<table class="table table-striped table-hover">
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
							<c:forEach items="${enrollmentArrayList}" var="enrollment">
								<tr id=${enrollment.getCourse().iD}>
									<td>${enrollment.getCourse().description}</td>
									<!-- <td>&nbsp;</td> -->
									<td><ul style="list-style-type: none; padding-left: 0;">
											<!-- TOEGEVOEGD -->
											<c:if test="${not empty enrollment.getCourse().listOfProfessors}">
												<c:forEach items="${enrollment.getCourse().listOfProfessors}"
													var="professor">
													<li>${professor.getFirstName()}
														${professor.getLastName()}</li>
												</c:forEach>
											</c:if>
										</ul></td>
									<td><ul style="list-style-type: none; padding-left: 0;">
											<!-- TOEGEVOEGD -->
											<c:if test="${not empty enrollment.getCourse().listOfAssistants}">
												<c:forEach items="${enrollment.getCourse().listOfAssistants}"
													var="assistant">
													<li>${assistant.getFirstName()}
														${assistant.getLastName()}</li>
												</c:forEach>
											</c:if>
										</ul></td>

									<td>${enrollment.getCourse().iD}</td>
									<td><a data-toggle="modal" href="#log-${enrollment.getCourse().iD}"
										class="btn btn-primary btn-xs"><spring:message
												code="EnrolledCourses.confirmation.text" /></a></td>
								</tr>
								<div class="modal fade" id="log-${enrollment.getCourse().iD}" tabindex="-1"
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
													data=${enrollment.getCourse().iD } data-dismiss="modal"
													onclick="location.href='./EnrolledCourses/remove/${enrollment.getCourse().iD}'">
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
		<script src="${pageContext.request.contextPath}/themes/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/themes/js/bootswatch.js"></script>
		<script>
			jQuery(document).ready(function() {
				jQuery(".modeldelete").bind("click", function() {
					//alert($(this).attr("data"));
					var id = $(this).attr("data");
					$("#" + id).hide();
				});
			});
		</script>

		<a href="EnrollCourses">
		<button class="btn btn-primary form-control " >
			<spring:message code="EnrolledCourses.add.text" />
		</button>
		</a>
		
	</div>
	<hr>
	<div class="container">
		<div class="row">
			<div class="col-md-12 text-center"><p>&copy; Vrije Universiteit Brussel - Team CalZone</p></div></div>
		</div>
	</div>
		
</body>
</html>