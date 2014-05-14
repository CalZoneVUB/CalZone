$(document).ready(function() {

	/* initialize the external events
	-----------------------------------------------------------------*/

	$('#external-events li.external-event').each(function() {
	
		// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
		// it doesn't need to have a start or end
		var eventObject = {
			title: $.trim($(this).text()) // use the element's text as the event title
		};
		
		// store the Event Object in the DOM element so we can get to it later
		$(this).data('eventObject', eventObject);
		
		// make the event draggable using jQuery UI
		$(this).draggable({
			zIndex: 999,
			scroll: false,
			revert: true,      // will cause the event to go back to its
			revertDuration: 0  //  original position after the drag
		});
		
	});


	/* initialize the calendar
	-----------------------------------------------------------------*/
	
	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
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
		defaultView: 'agendaWeek',
		weekMode: 'liquid',
		theme: false,
		allDaySlot:false,
		firstHour: 7,
		events: function(start, end, callback) {
	        $.ajax({
	            url: '/calzone/api/calendar/student/0',
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
	                	
	                	var title = courseName + '<br>' + type;
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
	            }
	        });
	    },
	    timeFormat: 'H:mm',
	    eventRender: function (event, element) {
	    	element.find('.fc-event-title').html(element.find('.fc-event-title').text());
        }
	});
});