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
	<div class="col-lg-12" id="mainBodyAddProgram">
		<div class="row">
			<h1>
				Creating new Program&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-primary" data-loading-text="Loading..." id="myBtnBackPrograms">
					<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Return
				</button>
				&nbsp;
				<button type="button" class="btn btn-success" id="myBtnAddTraject">
					<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Traject 
				</button>
			</h1>
			<br>
		</div>

		<div class="row">
			<table class="table table-hover table-responsive" id="record2">
				<tbody>
				<tr>
					<td style="width: 200px">Program name</td>
					<td><a class="myeditable" id="new_name_prog" data-type="text"></a></td>
				</tr>
				<tr>
					<td>Traject</td>
					<td><a href="#" class="myeditable myTraject" id="trajectId0" data-type="select"
					data-title="Select Traject" >Trajectories</a></td>	
				</tr>	
				<tr id="addTrajectDiv"></tr>
				</tbody>
			</table>
		<button type="button" class="btn btn-primary" id="save-btn-prog" data-loading-text="Loading...">
					<span class="glyphicon glyphicon-chevron-down"></span>&nbsp;Submit
		</button>	
		</div>
	</div>
	
	<script type="text/javascript">
	
	$(function () {

	 $('#new_name_prog').editable({
	        title: 'Pick new program name',
	        placeholder: "Program Name",
	        validate: function(v) {
	        	if(!v) return "Cannot be empty";
	        }
	    });
	 
	 $('.myTraject').editable({
	        source: 'api/traject/all/formated',
	        sourceCache: true,
	        alidate: function(v) {
	        	if(!v) return "Cannot be empty";
	    }})
	    ;  
	 
	 $('#save-btn-prog').click(function() {
		   $('.myeditable').editable('submit', { 
		       url: 'api/program/new',
		       params: function(params) {return JSON.stringify(params);
		    	},
		       ajaxOptions: {
		    	   contentType: 'application/json',
		           dataType: 'json' //assuming json response
	 			},
	 			success: function () {console.log("Success Response from server");
	 									var btn = $(this);
	 		   							btn.button('loading');
	 									$('#mainBodyAddProgram').load("/calzone/programdashboard",
	 										function() {
	 											btn.button('reset');
	 											console.log("Pushed back");
	 									});},
	 			error: function() {console.log("Error response form server")}
		   });
		});
	});	  
	
	$('#myBtnBackPrograms').click(function() {
		var btn = $(this);
	    btn.button('loading');
		$('#mainBodyAddProgram').load("/calzone/programdashboard",
			function() {
			btn.button('reset');
			console.log("Pushed back");
		});
	});
	
	function programEditable() {
		$('.myTraject').editable({
			source: 'api/traject/all/formated',
		    sourceCache: true
		}); 
		
		
	}
	
	var ctr = 1;
	$('#myBtnAddTraject').click(function() {
		var newHtml =" <tr><td>Traject</td><td><a href=\"#\" class=\"myeditable myTraject\" id=\"trajectId";
		newHtml = newHtml + ctr;
		newHtml = newHtml + "\" data-type=\"select\" data-title=\"Select Traject\" >Trajectories</a></td>	"
		newHtml = newHtml + " <tr id=\"addTrajectDiv\"></tr>";
		console.log("Replace with: " + newHtml);
 		$('#addTrajectDiv').replaceWith(newHtml);
 		ctr++;
 		trajectEditable();
	});
	
	

	</script>
</body>
</html>