<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>

</head>
<body>
	<div class="col-lg-12" id="mainBody4">
		<div class="row">
			<h1>
				Creating new Traject&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-primary" data-loading-text="Loading..." id="myBtnBack">
					<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Return
				</button>
				&nbsp;
				<button type="button" class="btn btn-success" id="myBtnAdd">
					<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Course
				</button>
			</h1>
			<br>
		</div>

		<div class="row">
			<table class="table table-hover table-responsive" id="record">
				<tbody>
				<tr>
					<td style="width: 200px">Traject name</td>
					<td><a class="myeditable" id="new_name" data-type="text"></a></td>
				</tr>
				<tr>
					<td>AcademicYear</td>
					<td><a class="myeditable" id="new_year" data-type="number"
					></a></td>
				</tr>
				<tr>
					<td>Course</td>
					<td><a href="#" class="myeditable myCourse" id="courseId0" data-type="select"
					data-title="Select Course" >Courses</a></td>	
				</tr>	
				<tr id="addCourseDiv"></tr>
				</tbody>
			</table>
		<button type="button" class="btn btn-primary" id="save-btn" data-loading-text="Loading...">
					<span class="glyphicon glyphicon-chevron-down"></span>&nbsp;Submit
		</button>	
		</div>
	</div>
	
	<script type="text/javascript">
	
	$(function () {

	 $('#new_name').editable({
	        title: 'Pick new traject name',
	        placeholder: "Course Name",
	        validate: function(v) {
	        	if(!v) return "Cannot be empty";
	        }
	    });
	 
	 $('#new_year').editable({
	        title: 'Pick year of the traject',
	        validate: function(v) {
	        	if(!v) return "Cannot be empty";
	        }
	    });
	 
	 $('.myCourse').editable({
	        source: 'api/course/all/formated',
	        sourceCache: true,
	        alidate: function(v) {
	        	if(!v) return "Cannot be empty";
	    }})
	    ;  
	 
	 $('#save-btn').click(function() {
		   $('.myeditable').editable('submit', { 
		       url: 'api/traject/new',
		       params: function(params) {return JSON.stringify(params);
		    	},
		       ajaxOptions: {
		    	   contentType: 'application/json',
		           dataType: 'json' //assuming json response
	 			},
	 			success: function () {console.log("Success Respnse from server");
	 									var btn = $(this);
	 		   							btn.button('loading');
	 									$('#mainBody4').load("/calzone/trajectdashboard",
	 										function() {
	 											btn.button('reset');
	 											console.log("Pushed back");
	 									});},
	 			error: function() {console.log("Error response form server")}
		   });
		});
	});	  
	
	$('#myBtnBack').click(function() {
		var btn = $(this);
	    btn.button('loading');
		$('#mainBody4').load("/calzone/trajectdashboard",
			function() {
			btn.button('reset');
			console.log("Pushed back");
		});
	});
	
	function trajectEditable() {
		$('.myCourse').editable({
			source: 'api/course/all/formated',
		    sourceCache: true
		}); 
		
		
	}
	
	var ctr = 1;
	$('#myBtnAdd').click(function() {
		var newHtml =" <tr><td>Course</td><td><a href=\"#\" class=\"myeditable myCourse\" id=\"courseId";
		newHtml = newHtml + ctr;
		newHtml = newHtml + "\" data-type=\"select\" data-title=\"Select Course\" >Courses</a></td>	"
		newHtml = newHtml + " <tr id=\"addCourseDiv\"></tr>";
		console.log("Replace with: " + newHtml);
 		$('#addCourseDiv').replaceWith(newHtml);
 		ctr++;
 		trajectEditable();
	});
	
	

	</script>
</body>
</html>