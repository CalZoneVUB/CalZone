//$('.row2') corresponding to a class in the jsp file

$('#edit-button').click(function(e) {
	e.stopPropagation();
	
	$('.row3').editable({
		type: 'text',
		name: 'room',
		url: '/calzone/api/courseInformation',
		title: 'Enter new room',
		mode: 'dropdown',
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
	
	$('.row4').editable({
		type: 'text',
		name: 'startDate',
		url: '/calzone/api/courseInformation',
		title: 'Enter new start date',
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

	$('.row5').editable({
		type: 'text',
		name: 'endDate',
		url: '/calzone/api/courseInformation',
		title: 'Enter new end date',
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

});