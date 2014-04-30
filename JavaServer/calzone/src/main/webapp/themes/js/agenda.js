Date.prototype.getWeek = function() {
    var onejan = new Date(this.getFullYear(),0,1);
    return Math.ceil((((this - onejan) / 86400000) + onejan.getDay()+1)/7);
}

var jumboHeight = $('.jumbotron').outerHeight();
function parallax(){
	var scrolled = $(window).scrollTop();
	$('.bg').css('height', (jumboHeight-scrolled) + 'px');
}

/* Function for parsing URL-parameters */
function getURLParameter(name) {
	return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null
}

$(window).scroll(function(e){
	parallax();
});

function goToByScroll(id){
	// Reove "link" from the ID
	id = id.replace("link", "");
	  // Scroll
	$('html,body').animate({
		scrollTop: $("#"+id).offset().top},
		'slow');
}

function initCal(){
	var now = new Date();
	var week = now.getWeek()+15 % 52;
	/* initialize the calendar
	-----------------------------------------------------------------*/
	$('#calendar').empty();
	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		editable: false,
		droppable: false,
		firstDay: 1,
		hiddenDays: [ 0 ],
		//theme: true,
		height: 650,
		defaultView: 'agendaWeek',
		weekMode: 'liquid',
		theme: false,
		allDaySlot:false,
		firstHour: 8,
		events: function(start, end, callback) {
	        $.ajax({
	            url: 'http://localhost:8080/calzone/api/calendar/course/33/'+week,
	            dataType: 'json',
	            data: {
	                // our hypothetical feed requires UNIX timestamps
	                start: Math.round(start.getTime() / 1000),
	                end: Math.round(end.getTime() / 1000)
	            },
	            success: function(doc) {
	                var events = [];
	                $(doc).each(function() {
	                	var startingDate = Math.round( $(this).attr('startingDate')/1000);
	                	var endingDate = Math.round( $(this).attr('endingDate')/1000);
	                    events.push({
	                        title: $(this).attr('courseComponent').course.courseName,
	                        start: startingDate,
	                        end: endingDate,
	                        allDay:false,
	                        durationEditable: false
	                    });
	                });
	                callback(events);

	                goToByScroll("main-content");
	            }
	        });
	    },
	    timeFormat: 'H:mm'
	});
}

function loadCourseData(f, c, y) {
	//alert(f+" "+c+" "+y);
	$("#go_btn").addClass('active disabled');
	if(f && c && y){
		$("#agenda-selection-title").text(y+" "+c);
		$("#main-content-container").show();
		initCal(f, c, y);
	}
	setTimeout(function() {
		// Do something after 2 seconds
		$("#go_btn").removeClass('active disabled');
	}, 2000);
}

$(function() {
	$('html,body').animate({
		scrollTop: 0},
		'fast');
	// Handler for .ready() called.
	$( "#target" ).submit(function( event ) {
		//alert( "Handler for .submit() called." );
		event.preventDefault();
		var f = $( "#ffac" ).val();
		var c = $( "#fcourse" ).val();
		var y = $( "#fyear" ).val();
		loadCourseData(f,c,y);
	});
	var divs = $('.fade');
	$(window).on('scroll', function() {
		var st = $(this).scrollTop();
		var jumboHeight = $('.jumbotron').outerHeight();
		if (st > jumboHeight-250){
			divs.css({ 
				'margin-top' : -(st/30)+"px", 
				'opacity' : 0 + st/35
			});
		} else {
			divs.css({ 
				'margin-top' : -(st/30)+"px", 
				'opacity' : 0
			});
		}
	});
	
	/* Check the URL for parameters */
	var f = getURLParameter("f");
	var c = getURLParameter("c");
	var y = getURLParameter("y");
	loadCourseData(f,c,y);
	
});