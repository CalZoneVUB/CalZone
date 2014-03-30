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


<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>
<body>
	<script src="${pageContext.request.contextPath}/js/bsa.js"></script>

	<jsp:include page="NavigationBarSignedIn.jsp" />

	<div class="container-fluid">

		<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h1 id="type">
						<spring:message code="EnrollCourses.title.text" />
					</h1>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<div class="table-responsive">
				<form:form method="post" action="save.html" modelAttribute="crouseArrayList">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th><spring:message code="EnrolledCourses.coursetitle.text" /></th>
								<th><spring:message code="EnrolledCourses.profname.text" /></th>
								<th><spring:message code="EnrolledCourses.assistant.text" /></th>
								<th><spring:message code="EnrolledCourses.courseID.text" /></th>
								<th></th>
							</tr>
						</thead>
						<tbody id="myTable">
							<c:forEach items="${courseArrayList}" var="course">
								<tr id=${course.iD}>
									<td>${course.description}</td>
									<!-- <td>&nbsp;</td> -->
									<td><ul style="list-style-type: none; padding-left: 0;">
											<!-- TOEGEVOEGD -->
											<c:if test="${not empty course.listOfProfessors}">
												<c:forEach items="${course.listOfProfessors}"
													var="professor">
													<li>${professor.getFirstName()}
														${professor.getLastName()}</li>
												</c:forEach>
											</c:if>
										</ul></td>
									<td><ul style="list-style-type: none; padding-left: 0;">
											<!-- TOEGEVOEGD -->
											<c:if test="${not empty course.listOfAssistants}">
												<c:forEach items="${course.listOfAssistants}"
													var="assistant">
													<li>${assistant.getFirstName()}
														${assistant.getLastName()}</li>
												</c:forEach>
											</c:if>
										</ul></td>

									<td>${course.iD}</td>
									<td><a data-toggle="modal" href="#log-${course.iD}"
										class="btn btn-primary btn-xs"><spring:message
												code="EnrollCourses.confirmation.text" /></a></td>
								</tr>
								<div class="modal fade" id="log-${course.iD}" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<h4 class="modal-title">
													<spring:message code="EnrollCourses.newEnrollment.text" />
												</h4>
											</div>
											<div class="modal-body">
												<p>
													<strong><spring:message
															code="EnrollCourses.add.text" /></strong>
												</p>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-success modeldelete"
													data=${course.iD } data-dismiss="modal"
													onclick="location.href='./EnrollCourses/add/${course.iD}'">
													<spring:message code="EnrollCourses.confirmation.text" />
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
					<input type="submit" value="Save" />
					</form:form>
				</div>
				<div class="col-md-12 text-center">
				<ul class="pagination pager" id="myPager"></ul>
				</div>
			</div>
		</div>
	</div>
	<hr>
	<div class="container">
		<div class="row">
			<div class="col-md-12 text-center">
				<p>&copy; Vrije Universiteit Brussel - Team CalZone</p>
			</div>
		</div>
	</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootswatch.js"></script>
	<script>
		jQuery(document).ready(function() {
			jQuery(".modeldelete").bind("click", function() {
				//alert($(this).attr("data"));
				var id = $(this).attr("data");
				$("#" + id).hide();
			});
		});
		$.fn.pageMe = function(opts){
		    var $this = this,
		        defaults = {
		            perPage: 10,
		            showPrevNext: true,
		            hidePageNumbers: false
		        },
		        settings = $.extend(defaults, opts);
		    
		    var listElement = $this;
		    var perPage = settings.perPage; 
		    var children = listElement.children();
		    var pager = $('.pager');
		    
		    if (typeof settings.childSelector!="undefined") {
		        children = listElement.find(settings.childSelector);
		    }
		    
		    if (typeof settings.pagerSelector!="undefined") {
		        pager = $(settings.pagerSelector);
		    }
		    
		    var numItems = children.size();
		    var numPages = Math.ceil(numItems/perPage);

		    pager.data("curr",0);
		    
		    if (settings.showPrevNext){
		        $('<li><a href="#" class="prev_link">«</a></li>').appendTo(pager);
		    }
		    
		    var curr = 0;
		    while(numPages > curr && (settings.hidePageNumbers==false)){
		    	if (curr > 4){
		    		$('<li><a href="#" class="page_link" style="display:none;">'+(curr+1)+'</a></li>').appendTo(pager);
		    	} else {
			        $('<li><a href="#" class="page_link">'+(curr+1)+'</a></li>').appendTo(pager);
		    	}
		        curr++;
		    }
		    
		    if (settings.showPrevNext){
		        $('<li><a href="#" class="next_link">»</a></li>').appendTo(pager);
		    }
		    
		    pager.find('.page_link:first').addClass('active');
		    pager.find('.prev_link').hide();
		    if (numPages<=1) {
		        pager.find('.next_link').hide();
		    }
		  	pager.children().eq(1).addClass("active");
		    
		    children.hide();
		    children.slice(0, perPage).show();
		    
		    pager.find('li .page_link').click(function(){
		        var clickedPage = $(this).html().valueOf()-1;
		        goTo(clickedPage,perPage);
		        return false;
		    });
		    pager.find('li .prev_link').click(function(){
		        previous();
		        return false;
		    });
		    pager.find('li .next_link').click(function(){
		        next();
		        return false;
		    });
		    
		    function previous(){
		        var goToPage = parseInt(pager.data("curr")) - 1;
		        goTo(goToPage);
		    }
		     
		    function next(){
		        goToPage = parseInt(pager.data("curr")) + 1;
		        goTo(goToPage);
		    }
		    
		    function goTo(page){
		        var startAt = page * perPage,
		            endOn = startAt + perPage;
		        
		        children.css('display','none').slice(startAt, endOn).show();
		        
		        
		        if (page>=1) {
		            pager.find('.prev_link').show();
		        }
		        else {
		            pager.find('.prev_link').hide();
		        }
		        
		        if (page<(numPages-1)) {
		            pager.find('.next_link').show();
		        }
		        else {
		            pager.find('.next_link').hide();
		        }
		        
		        pager.children().find('.page_link').hide();
		        
		        if (page >= 3){
		        	//alert(pager.find(".page_link [attribute!='page']"));
			        pager.children().eq(page-1).find('.page_link').show();
			        pager.children().eq(page).find('.page_link').show();
			        pager.children().eq(page+1).find('.page_link').show();
			        pager.children().eq(page+2).find('.page_link').show();
			        pager.children().eq(page+3).find('.page_link').show();
		        } else if (page==0 || page==1 || page ==2 ){
			        pager.children().eq(1).find('.page_link').show();
			        pager.children().eq(2).find('.page_link').show();
			        pager.children().eq(3).find('.page_link').show();
			        pager.children().eq(4).find('.page_link').show();
			        pager.children().eq(5).find('.page_link').show();
		        }
		        
		        pager.data("curr",page);
		      	pager.children().removeClass("active");
		        pager.children().eq(page+1).addClass("active");
		        
		    }
		};

		$(document).ready(function(){
		    
		  $('#myTable').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:15});
		    
		});
	</script>

</body>
</html>



$('table tr:contains('+val+')').hide();
