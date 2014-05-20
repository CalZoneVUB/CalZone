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
</head>
<body>
	<script src="${pageContext.request.contextPath}/js/bsa.js"></script>

	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h1 id="type">
						<spring:message code="publisher.title.text" />
					</h1>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<input type="text" id="systemmessagebox" class="form-control" placeholder="<spring:message code="publishSystemMessage.systemMessage"/>"/><br>
				
				<a id="publish-message">
					<button type="button" class="btn btn-primary myeditable" data-loading-text="Loading...">
						<span class="glyphicon glyphicon-chevron-down">&nbsp;</span><spring:message code="general.publish"/>
					</button>
				</a>
			</div>
		</div>
	</div>
		
	<script>
	 
	$('#publish-message').click(function() {
		$('#publish-message').prop('disabled', true);
		var message = document.getElementById("systemmessagebox").value;
		var btn = $(this);
		btn.button('loading');
		   $.ajax({
			      type: "POST",
			      url: "/calzone/api/publish/systemmessage",
			      contentType: "application/json",
			      data: JSON.stringify({ 'message' : message }),
			      success: function (msg) {
			    	  $.ajax({
			    	      type: "POST",
			    	      url: "https://wilma.vub.ac.be/~se2_1314/website/home/ajaxPusherSendRequest/",
			    	      data:{
			    	    	  'name' : 'AdminMessage',
			    	    	  'ms' : message
			    		  	  },
			    	      success: function (msg) {
			    	    	  btn.button('reset');
			    	      }
			    	   });
			      }
			   });
	});
	</script>
</body>
</html>