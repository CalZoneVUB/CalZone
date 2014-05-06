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

<div class="col-lg-12" id="mainBodyAddClassroom">
	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h1 id="type">
						Create New Classroom
						&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-primary" data-loading-text="Loading..." id="myBtnBackAddClassroom">
					<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Return
				</button>
					</h1>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<spring:message code="classrooms.room.text"/>: <a class="myeditableCA" id="name" href="#" data-type="text"></a><br>
				<spring:message code="classrooms.capacity.text"/>: <a class="myeditableCA" id="capacity" href="#" data-type="text"></a><br>
				<spring:message code="classrooms.roomtype.text"/>: <a class="myeditableCA" id="roomtype" href="#" data-type="select"></a><br>
				<spring:message code="classrooms.building.text"/>:  <a href="#" class="myeditableCA" data-type="select" id="building"></a><br>
				<spring:message code="classrooms.floor.text"/>:  <a href="#" class="myeditableCA" data-type="select" id="floor"></a><br>
				<spring:message code="classrooms.projector.text"/> <a class="myeditableCA" id="projectorEquipped" href="#" data-type="select"></a><br>
				<spring:message code="classrooms.smartboard.text"/> <a class="myeditableCA" id="smartBoardEquipped" href="#" data-type="select"></a><br>
				<spring:message code="classrooms.recording.text"/> <a class="myeditableCA" id="recorderEquipped" href="#" data-type="select"></a><br>
				
				<a id="classroom-submit">
				<br>
					<button type="button" class="btn btn-primary myeditable" data-loading-text="Loading...">
						<span class="glyphicon glyphicon-chevron-down">&nbsp;</span><spring:message code="general.save"/>
					</button>
				</a>
			</div>
		</div>
	</div>
</div>
	
	<script>
	$('#myBtnBackAddClassroom').click(function() {
		var btn = $(this);
	    btn.button('loading');
		$('#mainBodyAddClassroom').load("/calzone/classrooms",
			function() {
			btn.button('reset');
			console.log("Pushed back");
		});
	});
	
	 //all fields required
	$('#name').editable('option', 'validate', function (v) {
	    if (!v) return '<spring:message code="general.fieldrequired.text"/>';
	});
	 
	function isNumber(n) {
		  return !isNaN(parseFloat(n)) && isFinite(n);
	}
	
	$('#capacity').editable('option', 'validate', function (v) {
		if (!isNumber(v)) return '<spring:message code="validation.nan"/>';
		else if (!v) return '<spring:message code="general.fieldrequired.text"/>';
	});
	$('#roomtype').editable({
	        value: 'ClassRoom',
	        source: [{
	            value: 'ClassRoom',
	            text: '<spring:message code="classrooms.classroom"/>'
	        }, {
	            value: 'ComputerRoom',
	            text: '<spring:message code="classrooms.computerroom"/>'
	        }]
	    },
	    'validate', function (v) {

	        if (!v) return '<spring:message code="general.fieldrequired.text"/>';
	    });
	$('#projectorEquipped').editable({
	        value: 'false',
	        source: [{
	            value: 'false',
	            text: '<spring:message code="general.no.text"/>'
	        }, {
	            value: 'true',
	            text: '<spring:message code="general.yes.text"/>'
	        }]
	    },
	    'validate', function (v) {

	        if (!v) return 'Required field!';
	    });
	$('#smartBoardEquipped').editable({
	        value: 'false',
	        source: [{
	            value: 'false',
	            text: '<spring:message code="general.no.text"/>'
	        }, {
	            value: 'true',
	            text: '<spring:message code="general.yes.text"/>'
	        }]
	    },
	    'validate', function (v) {

	        if (!v) return 'Required field!';
	    });
	$('#recorderEquipped').editable({
	        value: 'false',
	        source: [{
	            value: 'false',
	            text: '<spring:message code="general.no.text"/>'
	        }, {
	            value: 'true',
	            text: '<spring:message code="general.yes.text"/>'
	        }]
	    },
	    'validate', function (v) {

	        if (!v) return '<spring:message code="general.fieldrequired.text"/>';
	    });

	var floorDataSource = JSON.parse('${floorSource}');
	$('#building').editable({
	   // url: '/post',
	    source: '${buildingSource}',
	    title: 'BuildingPlaceholder',
	    success: function (response, newValue) {
	        $('#floor').editable('option', 'source', floorDataSource[newValue]);
	        $('#floor').editable('setValue', true);
	    }
	});

	$('#floor').editable({
	    //url: '/post',
	    title: 'FloorPlaceholder',
	    sourceError: 'Please select a building first'
	});
	
	$('#classroom-submit').click(function() {
		/* var $editables = $(this).siblings('.myeditable'); */
		var editables = "";
		editables = $('.myeditableCA');
		console.log(editables);
		var values = editables.editable('getValue');
		console.log(values);
		editables.editable('submit', {
			url : '/calzone/api/classroom/new',
			ajaxOptions : {
				data : JSON.stringify(values),
				contentType : 'application/json',
				dataType : 'json' //assuming json response
			}
		});
	});
	
	</script>
</body>
</html>