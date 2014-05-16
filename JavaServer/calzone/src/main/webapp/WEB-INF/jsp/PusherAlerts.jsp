<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<sec:authorize access="isAuthenticated()">	
<!-- Pusher for alerts -->
<script src="http://js.pusher.com/2.2/pusher.min.js" type="text/javascript"></script>

<!-- Bootstrap Model Dialog Alerts -->
<link href="${pageContext.request.contextPath}/bootstrap3-dialog/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/bootstrap3-dialog/js/bootstrap-dialog.min.js"></script>

<!-- Pusher Script For Live Notifications -->
<script type="text/javascript">
  // Enable pusher logging - don't include this in production
  Pusher.log = function(message) {
    if (window.console && window.console.log) {
      window.console.log(message);
    }
  };
  var pusher = new Pusher('26bd194340f7f5ce7bda');
  var channel = pusher.subscribe('test_channel');
  channel.bind('my_event', function(data) {
	  BootstrapDialog.alert('data.message');
  });
</script>
</sec:authorize>