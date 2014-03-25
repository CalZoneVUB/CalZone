$('.row2').editable({
    type: 'text',
    name: 'firstName',
    url: '/post',
    title: 'Enter new First Name'
});

$('.row3').editable({
    type: 'text',
    name: 'lastName',
    url: '/post',
    title: 'Enter new Last Name'
});

$('.row5').editable({
    type: 'text',
    name: 'email',
    url: '/post',
    title: 'Enter new email address'
});

$('.row6').editable({
    type: 'date',
    name: 'birthdate',
    url: '/post',
    title: 'Pick new date'
});

$('.row7').editable({
    source: [
             {value: 1, text: "Nederlands"}, 
             {value: 2, text: "English"}
            ],
    name: 'language',
    url: '/post',
    title: 'Pick new language'
});