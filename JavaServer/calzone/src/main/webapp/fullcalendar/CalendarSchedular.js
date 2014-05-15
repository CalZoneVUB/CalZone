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
		year: 2014,
		month: 0,
		date: 1,
		header:false,
		// *** use long day names by using 'dddd' ***
        columnFormat: {
            week: 'dddd' // Monday, Wednesday, etc
        },
		dayNames: ['Zondag','Maandag','Dinsdag','Woensdag','Donderdag','Vrijdag','Zaterdag'],
		axisFormat: 'H:mm',
	    timeFormat: 'H:mm',
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
	            url: '/calzone/api/teacher/coursecomponents/prefs',
	            dataType: 'json',
	            data: {
	                // convert to UNIX timestamps
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

	                	var rID = $(this).attr('room').id;
	                	var rName = $(this).attr('room').vubNotation;
	                	
	                	var frozen = $(this).attr('frozen');
	                	
	                	var title = courseName + '<br>' + type;
	                	
	                	var scheduleAlert = 'yes';
	                	
	                	var icons = '<span class=\"glyphicon glyphicon-lock \"> </span>';
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
	                    	roomId: rID,
	                    	roomName: rName,
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
		drop: function(date, allDay) { // this function is called when something is dropped
		
			// retrieve the dropped element's stored Event Object
			var originalEventObject = $(this).data('eventObject');
			
			// we need to copy it, so that multiple events don't have a reference to the same object
			var copiedEventObject = $.extend({}, originalEventObject);
			
			// assign the courseComponent data
			var ccId = parseInt($('a', this).attr('id'),10);
			var start = new Date(date).getTime();
			var ending = start + 2*60*1000;
			
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
			
			$.ajax({
        		type: "POST",
                url: '/calzone/api/teacher/pref/component',
                contentType: "application/json",
                data: JSON.stringify({
                	courseComponentId: ccId,
                	startingHour: start,
                	endingHour: ending
                }),
                success: function(data) {
                	if(data.status == "success"){
                		alert(data.message);
                	} else if (data.status == "error"){
                		alert(data.message);
                	}
                },
                error: function(data){
                	alert("Oops! Er liep iets fout. Probeer later opnieuw..");
                }
            });
			
			// is not a block item
			if (!$(this).hasClass('block')) {
				// if so, remove the element from the "Draggable Events" list
				$(this).remove();
			}
			
		},
	    eventDrag: function(event, element) {

	        event.title = "Dragged!";

	        $('#calendar').fullCalendar('updateEvent', event);
			//alert("Verslepen");

	    },
	    eventRender: function (event, element) {
	    	element.find('.fc-event-title').html(element.find('.fc-event-title').text());
	    	$('#'+'schedAlert_'+event.id).tooltip('hide');
	    	//alert('schedAlert_'+event.id);
        },
        eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) {
        	
    		var id = event.id;
        	var newStart = new Date(event.start).getTime();
        	
        	$("#entryChangeModalBody").html('<p>Bent u zeker dat u deze les wil verzetten?</p>');
        	
          	$('#entryChangeModal').modal('show');
          	
          	/* the callback functions for our calender
        	-----------------------------------------------------------------*/
        	$("#entryChangeModalX").bind("click", function() {
        		$('#entryChangeModal').modal('hide');
        		revertFunc();
        		$("#entryChangeModalCancel").unbind();
        		$(this).unbind();
        	});
        	
        	$("#entryChangeModalCancel").bind("click", function() {
        		$('#entryChangeModal').modal('hide');
        		revertFunc();
        		$("#entryChangeModalX").unbind();
        		$(this).unbind();
        	});
        	
        	$("#entryChangeModalSave").bind("click", function() {
        		var id = event.id;
            	var newStart = new Date(event.start).getTime();
        		$(this).attr("disabled", "disabled");
            	$.ajax({
            		type: "POST",
                    url: '/calzone/api/calendar/move/time',
                    contentType: "application/json",
                    data: JSON.stringify({
                    	entryId: id,
                    	newStartDate: newStart
                    }),
                    success: function(data) {
                    	if(data.status == "success"){
                    		$('#entryChangeModal').modal('hide');
                    		$(this).attr("disabled", "enable");
                    	} else if (data.status == "error"){
                    		alert(data.message);
                    	}
                    },
                    error: function(data){
                    	alert("Oops! Er liep iets fout. Probeer later opnieuw..");
                    }
                });
            	// Clear this function after completion...
        		$(this).unbind();
        	});
        }
	});

	$('#calender').fullCalendar( 'gotoDate', 2014, 0, 1);
});