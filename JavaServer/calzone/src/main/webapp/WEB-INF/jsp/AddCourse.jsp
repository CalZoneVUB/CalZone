<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add course</title>
</head>
<body>
<h1>Voeg een vak toe.</h1>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="description" class="form-control" placeholder="Vakbeschrijving" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="ECTS" class="form-control" placeholder="Studiepunten" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="prerequisites" class="form-control" placeholder="Prerequisities" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="term" class="form-control" placeholder="Semester" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="studyTime" class="form-control" placeholder="Studie Tijd" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="catalogueID" class="form-control" placeholder="Identificatie Nummer" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="checkbox" name="reexaminationPossible" class="form-control" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="language" class="form-control" placeholder="Taal" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="faculty" class="form-control" placeholder="Faculteit" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="department" class="form-control" placeholder="Departement" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="educationalTeam" class="form-control" placeholder="Educatie Team" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="activities" class="form-control" placeholder="Activiteiten" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="courseMaterial" class="form-control" placeholder="Les Materiaal" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="learningGoals" class="form-control" placeholder="Leer Resultaten" />
	</div>
</div>
<div class="form-group">
	<div class="col-lg-12">
		<input type="text" name="grading" class="form-control" placeholder="Beoordelings Informatie" />
	</div>
</div>
<button type="button" onclick="alert('Button Pressed')">Submit</button>
</body>
</html>