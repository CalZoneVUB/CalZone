//$('.row2') corresponding to a class in the jsp file

	$('.row1').editable({
		type: 'text',
		name: 'name',
		url: '/calzone/api/addCourse',
		title: 'Enter name',
		mode: 'dropdown',
		placement: 'bottom',
		ajaxOptions: {
			dataType: 'json'
		},
		success: function(response) {
			if(response.status == "error") return response.message;
		},
		validate: function(value) {
			if($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});
	
	$('.row2').editable({
		type: 'number',
		name: 'ECTS',
		url: '/calzone/api/addCourse',
		title: 'Enter ECTS',
		placement: 'bottom',
		ajaxOptions: {
			dataType: 'json'
		},
		success: function(response) {
			if(response.status == "error") return response.message;
		},
		validate: function(value) {
			if($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});

	$('.row3').editable({
		type: 'number',
		name: 'study time',
		url: '/calzone/api/addCourse',
		title: 'Enter study time',
		placement: 'bottom',
		ajaxOptions: {
			dataType: 'json'
		},
		success: function(response) {
			if(response.status == "error") return response.message;
		},
		validate: function(value) {
			if($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});
	
	//TODO vakken toevoegen als prerequisities met select2 dropdown mode (xeditable)
	
	$('.row5').editable({
		type: 'text',
		name: 'ECTS',
		url: '/calzone/api/addCourse',
		title: 'Enter ECTS',
		placement: 'bottom',
		source: [
	              {value: 1, text: 'S1: Semester one'},
	              {value: 2, text: 'S2: Semester two'},
	              {value: 3, text: 'S3: Both semesters'},
	              {value: 4, text: 'S4: 2 Years: Semester one odd academic year'},
	              {value: 5, text: 'S5: 2 Years: Semester two odd academic year'},
	              {value: 6, text: 'S6: Both semesters odd academic year'},
	              {value: 7, text: 'S7: 2 Years: Semester one even academic year'},
	              {value: 8, text: 'S8: 2 Years: Semester two even academic year'},
	              {value: 9, text: 'S9: 2 Years: Both semesters even academic year'},
	              {value: 10, text: 'EX: Exam'}
	           ],
		ajaxOptions: {
			dataType: 'json'
		},
		success: function(response) {
			if(response.status == "error") return response.message;
		},
		validate: function(value) {
			if($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});
	
	$('.row6').editable({
		type: 'Text',
		name: 'Re-examination',
		url: '/calzone/api/addCourse',
		title: 'Yes/No',
		placement: 'bottom',
		source: [
	              {value: 1, text: 'Yes'},
	              {value: 2, text: 'No'}
	             ],
		ajaxOptions: {
			dataType: 'json'
		},
		success: function(response) {
			if(response.status == "error") return response.message;
		},
		validate: function(value) {
			if($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});
	
	$('.row7').editable({
		type: 'text',
		name: 'Language',
		url: '/calzone/api/addCourse',
		title: 'Choose language',
		placement: 'bottom',
		source: [
	              {value: 1, text: 'English'},
	              {value: 2, text: 'Nederlands'}
	             ],
		ajaxOptions: {
			dataType: 'json'
		},
		success: function(response) {
			if(response.status == "error") return response.message;
		},
		validate: function(value) {
			if($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});
	
	$('.row8').editable({
		type: 'text',
		name: 'Learning Goals',
		url: '/calzone/api/addCourse',
		title: 'Learning goals:',
		placement: 'bottom',
		ajaxOptions: {
			dataType: 'json'
		},
		success: function(response) {
			if(response.status == "error") return response.message;
		},
		validate: function(value) {
			if($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});
	
	$('.row9').editable({
		type: 'text',
		name: 'Grading',
		url: '/calzone/api/addCourse',
		title: 'Grading information',
		placement: 'bottom',
		ajaxOptions: {
			dataType: 'json'
		},
		success: function(response) {
			if(response.status == "error") return response.message;
		},
		validate: function(value) {
			if($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});
	
	$('.row10').editable({
		type: 'number',
		name: 'Duration',
		url: '/calzone/api/addCourse',
		title: 'Class duration',
		placement: 'bottom',
		ajaxOptions: {
			dataType: 'json'
		},
		success: function(response) {
			if(response.status == "error") return response.message;
		},
		validate: function(value) {
			if($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});
	
	$('.row11').editable({
		type: 'text',
		name: 'Description',
		url: '/calzone/api/addCourse',
		title: 'Course description',
		placement: 'bottom',
		ajaxOptions: {
			dataType: 'json'
		},
		success: function(response) {
			if(response.status == "error") return response.message;
		},
		validate: function(value) {
			if($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});