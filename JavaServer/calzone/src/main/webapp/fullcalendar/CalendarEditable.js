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
		editable: true,
		droppable: true, // this allows things to be dropped onto the calendar !!!
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
	            url: 'api/calendar/student/241/1',
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
	                	
	                	var frozen = $(this).attr('frozen');
	                	
	                	var title = courseName + '<br>' + type;
	                	
	                	var scheduleAlert = 'yes';
	                	
	                	var icons = '';
	                	if (frozen){
	                		icons = icons + '<span class=\"glyphicon glyphicon-lock \"> </span>';
	                		//'<span class=\"glyphicon glyphicon-warning-sign orange\"></span>'
	                	}
	                	
	                	if (scheduleAlert){
	                		//icons = icons + '<span class=\"glyphicon glyphicon-warning-sign orange\"></span>';
	                		icons = icons + '<span id=\"schedAlert_'+id+'\" class=\"glyphicon glyphicon-warning-sign orange\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"Warning\"> </span>';
	                		//icons = icons + '<button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="bottom" title="Tooltip on bottom">Tooltip on bottom</button>';
	                	}
	                	
	                    events.push({
	                    	id: id,
	                        title: title,
	                        icon: icons,
	                        start: startingDate,
	                        end: endingDate,
	                        allDay:false,
	                        durationEditable: false,
	                        editable: !frozen
	                    });
	                });
	                callback(events);
	            }
	        });
	    },
	    timeFormat: 'H:mm',
		drop: function(date, allDay) { // this function is called when something is dropped
		
			alert("Add item to schedule");
			// retrieve the dropped element's stored Event Object
			var originalEventObject = $(this).data('eventObject');
			
			// we need to copy it, so that multiple events don't have a reference to the same object
			var copiedEventObject = $.extend({}, originalEventObject);
			
			// assign it the date that was reported
			copiedEventObject.start = date;
			copiedEventObject.allDay = allDay;
			
			if( $(this).hasClass('block') ){
				copiedEventObject.color = '#C80000';
			} else {
				copiedEventObject.durationEditable = false;
			}
			
			// render the event on the calendar
			// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
			$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

			
			// is the "remove after drop" checkbox checked? and not a block item
			if ($('#drop-remove').is(':checked') && !$(this).hasClass('block')) {
				// if so, remove the element from the "Draggable Events" list
				$(this).remove();
			}
			
		},
		eventClick: function(event, element) {

	        event.title = "CLICKED!";

	        $('#calendar').fullCalendar('updateEvent', event);
			alert("Geklikt");

	    },
	    eventDrag: function(event, element) {

	        event.title = "Dragged!";

	        $('#calendar').fullCalendar('updateEvent', event);
			alert("Verslepen");

	    },
	    eventRender: function (event, element) {
	    	element.find('.fc-event-title').html(element.find('.fc-event-title').text());
	    	$('#'+'schedAlert_'+event.id).tooltip('hide');
	    	//alert('schedAlert_'+event.id);
        }
	});
});