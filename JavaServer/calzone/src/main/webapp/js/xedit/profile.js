//$('.row2') corresponding to a class in the jsp file

$('.row2').editable({
    type: 'text',
    name: 'firstName',
    url: '/calzone/api/profile',
    title: 'Enter new First Name',
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
    type: 'text',
    name: 'lastName',
    url: '/calzone/api/profile',
    title: 'Enter new Last Name',
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
    name: 'email',
    url: '/calzone/api/profile',
    title: 'Enter new email address',
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
    type: 'date',
    name: 'birthdate',
    url: '/calzone/api/profile',
    title: 'Pick new date',
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
    source: [
             {value: 1, text: "Nederlands"}, 
             {value: 2, text: "English"}
            ],
    name: 'language',
    url: '/calzone/api/profile',
    title: 'Pick new language',
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