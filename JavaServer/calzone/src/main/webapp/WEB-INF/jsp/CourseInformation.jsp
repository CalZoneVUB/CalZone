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
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/themes/css/lumen/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/themes/css/dashboard.css"
	rel="stylesheet">

</head>
<body>

	<jsp:include page="NavigationBarSignedIn.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-lg-6 outer-box">
				<div class="box-shadow profile-box">
					<form:form commandName="testEntry" method="POST">
						<br>
						<h4>
							<strong><spring:message
									code="courseInformation.coursetitle.text" /></strong>
							<form:input path="course.description" class="form-control" readonly="true"/>
							<form:errors path="course.description" cssClass="error"></form:errors>
						</h4>
						<h4>
							<strong><spring:message
									code="courseInformation.courseID.text" /></strong>
							<form:input path="course.iD" class="form-control" readonly="true"/>
							<form:errors path="course.iD" cssClass="error"></form:errors>
						</h4>
						<br>
						<h4>
							<strong><spring:message
									code="courseInformation.startDate.text" /></strong>
							<form:input path="startDate" class="form-control" readonly="true"/>
							<form:errors path="startDate" cssClass="error"></form:errors>

						</h4>
						<h4>
							<strong><spring:message
									code="courseInformation.endDate.text" /></strong>
							<form:input path="endDate" class="form-control" readonly="true"/>
							<form:errors path="endDate" cssClass="error"></form:errors>

						</h4>
						<br>
						<h4>
							<strong><spring:message
									code="courseInformation.room.text" /></strong>
							<form:input path="room.name" class="form-control" readonly="true"/>
							<form:errors path="room.name" cssClass="error"></form:errors>
						</h4>
						<button type="submit" class="btn btn-primary full-width">Update</button>
					</form:form>
				</div>
			</div>

		</div>
	</div>

</body>
</html>

