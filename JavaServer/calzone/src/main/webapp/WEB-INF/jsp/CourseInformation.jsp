<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link
	href="${pageContext.request.contextPath}/themes/css/lumen/bootstrap.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
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
	
<!-- JavaScript at bottom except for Modernizr -->
<script
	src="${pageContext.request.contextPath}/themes/js/libs/modernizr.custom.js"></script>

</head>
<body>

	<sec:authorize access="isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBarSignedIn.jsp" />
	</sec:authorize>

	<sec:authorize access="!isAuthenticated()">
		<jsp:include page="/WEB-INF/jsp/NavigationBar.jsp" />
	</sec:authorize>
	
	<div class="container">
					<div class="col-lg-6 outer-box">
						<div class="box-shadow profile-box">
							<sec:authorize ifAnyGranted="ROLE_STUDENT">
								<br>
									<button type="button" class="btn btn-default modeldelete" id="edit-button" onclick="convertDate()">Edit course information</button> <!-- TODO verschillende talen -->
								<br>
							</sec:authorize>
							<br>
							<table style="border-spacing:50px">
							<tr>
								<td> <strong><spring:message code="courseInformation.coursetitle.text" /></strong> </td>
								<td> <a class="row1" href="#" data-type="text" data-pk="1"> ${testEntry.course.description} </a> </td>
							</tr>
							<!-- <br> -->
							<tr>
								<td><strong><spring:message code="courseInformation.courseID.text" /></strong></td>
								<td><a class="row2" href="#" data-type="text" data-pk="1">${testEntry.course.iD}</a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="courseInformation.room.text" /></strong> </td>
								<td><a class="row3" href="#" data-type="select2" data-pk="1"> ${testEntry.room.name}</a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="courseInformation.startDate.text" /></strong> </td>
								<td><a class="row4 datum" href="#" data-type="combodate" 
										data-format="DD-MM-YYYY HH:mm" data-template="DD / MM / YYYY HH:mm"  data-pk="1" 
										data-value="21-12-2012 8:30"> ${testEntry.startDate}</a> </td>
							</tr>
							<tr>
								<td><strong><spring:message code="courseInformation.endDate.text" /></strong> </td>
								<td> <a class="row5 datum" href="#" data-type="combodate" 
										data-format="DD-MM-YYYY HH:mm" data-template="DD / MM / YYYY HH:mm" data-pk="1" 
										id="endDate"> ${testEntry.endDate}</a> </td>
							</tr>
							</table>
						</div>
					</div>

				</div>
	<%-- <script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script> --%>
	<%-- <script src="${pageContext.request.contextPath}/js/bootswatch.js"></script> --%>
	<script
		src="${pageContext.request.contextPath}/js/jquery/jquery-2.1.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/calzone.js"></script>

	<!-- X-editable Bootstrap -->

	<!-- <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>   -->
	<script
		src="${pageContext.request.contextPath}/js/bootstrap-editable.min.js"></script>

	<!-- main.js -->
	<script src="${pageContext.request.contextPath}/js/xedit/courseInformation.js"></script>
	<script src="${pageContext.request.contextPath}/js/moment.min.js"></script>
	<script>
	//Format functie die het Date object extend zodat je direct kan formatteren
	//Bijvoorbeeld: .format("yyyy-MM-dd h:mm:ss"));
	Date.prototype.format = function(format)
	{
	  var o = {
	    "M+" : this.getMonth()+1, //month
	    "d+" : this.getDate(),    //day
	    "h+" : this.getHours(),   //hour
	    "m+" : this.getMinutes(), //minute
	    "s+" : this.getSeconds(), //second
	    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
	    "S" : this.getMilliseconds() //millisecond
	  }

	  if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
	    (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	  for(var k in o)if(new RegExp("("+ k +")").test(format))
	    format = format.replace(RegExp.$1,
	      RegExp.$1.length==1 ? o[k] :
	        ("00"+ o[k]).substr((""+ o[k]).length));
	  return format;
	}

	$( ".datum" ).each(function() {
	    var content = $( this ).html();
	    alert( "1. "+content);
	    var year = content.substring(content.length-4,content.length);
	    alert( "2. "+year);
	    var month = content.substring(4,8);
	    alert( "3. "+month);
	    var day = content.substring(8,11);
	    alert( "4. "+day);
	    var time = content.substring(11,17);
	    alert( "5. "+time);
	    var format_d = new Date(month+" "+day+" "+year+" "+time).format("dd-MM-yyyy h:mm");
	    //alert( format_d );
	    $( this ).attr('data-value', format_d);
	    //alert( $( this ).attr('data-value') );
	});
	</script>
	
	<jsp:include page="/WEB-INF/jsp/PusherAlerts.jsp" />
</body>
</html>

