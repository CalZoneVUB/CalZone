var isMobile = {
    Android: function() {
        return navigator.userAgent.match(/Android/i);
    },
    BlackBerry: function() {
        return navigator.userAgent.match(/BlackBerry/i);
    },
    iOS: function() {
        return navigator.userAgent.match(/iPhone|iPad|iPod/i);
    },
    Opera: function() {
        return navigator.userAgent.match(/Opera Mini/i);
    },
    Windows: function() {
        return navigator.userAgent.match(/IEMobile/i);
    },
    any: function() {
        return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
    }
};

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

function initCal(t){
	var now = new Date();
	var week = now.getWeek()+15 % 52;
	/* initialize the calendar
	-----------------------------------------------------------------*/
	if(isMobile.any()){
		var mHeaderLeft = '';
		var mHeaderCenter = 'prev,next today';
		var mHeaderRight = '';
		var mDefaultView = 'agendaDay';
	} else {
		var mHeaderLeft = 'prev,next today';
		var mHeaderCenter = 'title';
		var mHeaderRight = 'month,agendaWeek,agendaDay';
		var mDefaultView = 'agendaWeek';
	}
	$('#calendar').empty();
	$('#calendar').fullCalendar({
		header: {
			left: mHeaderLeft,
			center: mHeaderCenter,
			right: mHeaderRight
		},
        columnFormat: {
            week: 'dddd dd/MM'
        },
		/* Begin of Localization */
		buttonText: {
			today: 'Vandaag',
			month: 'Maand',
			day: 'Dag',
			week: 'Week'
		},
	    monthNames: ['Januari', 'Februari', 'Maart', 'April', 'Mei', 'Juni', 'Juli', 'Augustus','September', 'Oktober', 'November', 'December'],
		monthNamesShort: ['Jan','Feb','Maa','Apr','Mei','Jun','Jul','Aug','Sept','Okt','Nov','Dec'],
		dayNames: ['Zondag','Maandag','Dinsdag','Woensdag','Donderdag','Vrijdag','Zaterdag'],
		dayNamesShort: ['Zo','Ma','Di','Wo','Do','Vr','Za'],
		/* End of Localization */
		axisFormat: 'H:mm',
	    timeFormat: 'H:mm',
		editable: false,
		droppable: false,
		firstDay: 1,
		hiddenDays: [ 0 ],
		//theme: true,
		height: 650,
		defaultView: mDefaultView,
		weekMode: 'liquid',
		theme: false,
		allDaySlot:false,
		firstHour: 7,
		events: function(start, end, callback) {
	        $.ajax({
	            url: '/calzone/api/calendar/traject/'+t,
	            dataType: 'json',
	            data: {
	                // our hypothetical feed requires UNIX timestamps
	                start: Math.round(start.getTime() / 1000),
	                end: Math.round(end.getTime() / 1000)
	            },
	            success: function(doc) {
	                var events = [];
	                $(doc).each(function() {
	                	var id = $(this).attr('id');
	                	
	                	var startingDate = Math.round( $(this).attr('startingDate')/1000);
	                	var duration = $(this).attr('courseComponent').duration;
	                	var endingDate = Math.round( startingDate + (duration*3600) );
	                	
	                	var type = $(this).attr('courseComponent').type;
	                	var courseName = $(this).attr('courseComponent').course.courseName;
	                	var place = $(this).attr('room').vubNotation;
	                	
	                	var title = courseName + '<br>' + place +'<br>' + type;
	                    events.push({
	                    	id: id,
	                        title: title,
	                        icon: '',
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
	    eventRender: function (event, element) {
	    	element.find('.fc-event-title').html(element.find('.fc-event-title').text());
        }
	});
}

function loadCourseData(t) {
	//alert(f+" "+c+" "+y);
	$("#go_btn").addClass('active disabled');
	if(t){
		location.hash=t;
		$("#agenda-selection-title").text(t);
		$("#main-content-container").show();
		initCal(t);
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
		var p = $( "#program" ).val();
		var t = $( "#trajectory" ).val();
		loadCourseData(t);
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
	var t = getURLParameter("t");
	if (t){
		loadCourseData(t);
	} else if (location.hash) {
		var h = location.hash;
		var hash = h.substring(1, h.length);
		loadCourseData(hash);
	}
	
});