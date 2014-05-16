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
			console.log("events");
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
	                	// Set the starttime to the chosen week (here 01/01/2014 00:00:00)
	                	var startTime = 1388358000;
	                	
	                	var schedId = $(this).attr('id');
	                	var dayOfWeek = $(this).attr('dayOfWeek');
	                	var startingHour = Math.round($(this).attr('startingHour') % 24);
	                	var startingDate = startTime + (dayOfWeek-1)*24*60*60 + startingHour*60*60;

	                	var endingHour = Math.round($(this).attr('endingHour') % 24);
	                	var endingDate = startTime + (dayOfWeek-1)*24*60*60 + endingHour*60*60;
	                	
	                	//alert(new Date(startingDate));
	                	var duration = $(this).attr('courseComponent').duration;
	                	//var endingDate = Math.round( startingDate + (duration*3600) );
	                	var type = $(this).attr('courseComponent').type;
	                	var id = $(this).attr('courseComponent').id;
	                	var courseName = $(this).attr('courseComponent').course.courseName;
	                	
	                	//var frozen = $(this).attr('frozen');
	                	var frozen = false;
	                	
	                	var title = courseName + '<br>' + type;
	                	
	                	var scheduleAlert = 'yes';
	                	
	                	var icons = '';
	                	/*if (frozen){
	                		icons = icons + '<span class=\"glyphicon glyphicon-lock \"> </span>';
	                		//'<span class=\"glyphicon glyphicon-warning-sign orange\"></span>'
	                	}*/
	                	
	                	/*if (scheduleAlert){
	                		//icons = icons + '<span class=\"glyphicon glyphicon-warning-sign orange\"></span>';
	                		icons = icons + '<span id=\"schedAlert_'+id+'\" class=\"glyphicon glyphicon-warning-sign orange\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"Warning\"> </span>';
	                		//icons = icons + '<button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="bottom" title="Tooltip on bottom">Tooltip on bottom</button>';
	                	}*/
	                	
	                    events.push({
	                    	id: id,
	                    	schedId: schedId,
	                        title: title,
	                        icon: icons,
	                        start: startingDate,
	                        end: endingDate,
	                        allDay:false,
	                        durationEditable: false,
	                        editable: !frozen
	                    });
	                });
	                $.ajax({
	    	            url: '/calzone/api/teacher/coursecomponents/block',
	    	            dataType: 'json',
	    	            data: {
	    	                // convert to UNIX timestamps
	    	                start: Math.round(start.getTime() / 1000),
	    	                end: Math.round(end.getTime() / 1000)
	    	            },
	    	            success: function(doc) {
	    	                $(doc).each(function() {
	    	                	// Set the starttime to the chosen week (here 01/01/2014 00:00:00)
	    	                	var startTime = 1388358000;
	    	                	
	    	                	var id = $(this).attr('id');
	    	                	var dayOfWeek = $(this).attr('dayOfWeek');
	    	                	var startingHour = Math.round($(this).attr('startingHour') % 24);
	    	                	var startingDate = startTime + (dayOfWeek-1)*24*60*60 + startingHour*60*60;

	    	                	var endingHour = Math.round($(this).attr('endingHour') % 24);
	    	                	var endingDate = startTime + (dayOfWeek-1)*24*60*60 + endingHour*60*60;
	    	                	
	    	                    events.push({
	    	                    	id: id,
	    	        				color: '#C80000',
	    	                        title: 'Bezet',
	    	                        icon: '',
	    	                        start: startingDate,
	    	                        end: endingDate,
	    	                        allDay:false,
	    	                        durationEditable: true,
	    	                        editable: true
	    	                    });
	    	                });
	    	                callback(events);
	    	            }
	    	        });
	            }
	        });
	    },
		drop: function(date, allDay) { // this function is called when something is dropped
			console.log("drop");
			// retrieve the dropped element's stored Event Object
			var originalEventObject = $(this).data('eventObject');
			
			// we need to copy it, so that multiple events don't have a reference to the same object
			var copiedEventObject = $.extend({}, originalEventObject);
			
			// assign the courseComponent data
			var start = new Date(date).getTime();
			var ending = start + 2*60*60*1000;
			
			// assign it the date that was reported
			copiedEventObject.start = date;
			copiedEventObject.allDay = allDay;
			copiedEventObject.icon = '';
			
			if( $(this).hasClass('block') ){
				copiedEventObject.color = '#C80000';
			} else {
				var ccId = parseInt($('a', this).attr('id'),10);
				copiedEventObject.id = ccId;
				copiedEventObject.durationEditable = false;
			}
			
			// render the event on the calendar
			// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
			$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
			
			// check if it is a block item
			if ($(this).hasClass('block')) {
				$.ajax({
	        		type: "POST",
	                url: '/calzone/api/teacher/pref/not',
	                contentType: "application/json",
	                dataType: 'json',
	                data: JSON.stringify({
	                	startingHour: start,
	                	endingHour: ending
	                }),
	                success: function(data) {
	                	if(data.status == "success"){
	                		$(data.message).each(function() {
	                			var sH = $(this).attr('startingHour');
	                			var eH = $(this).attr('endingHour');
	                			var startH = new Date(start).getHours();
	                			var endH = new Date(ending).getHours();
	    	                	if (sH==startH && eH==endH ){
	    	                		copiedEventObject.id = $(this).attr('id');
	    	                	}
	    	                });
	                	} else if (data.status == "error"){
	                		//DELETE THE EVENT
	                	}
	                },
	                error: function(data){
                    	BootstrapDialog.alert("Oops! Er liep iets fout. Probeer later opnieuw.");
	                }
	            });
			} else {
				// if so, add new coursecomponent pref to database and remove the element from the "Draggable Events" list
				$.ajax({
	        		type: "POST",
	                url: '/calzone/api/teacher/pref/component',
	                contentType: "application/json",
	                dataType: 'json',
	                data: JSON.stringify({
	                	courseComponentId: ccId,
	                	startingHour: start,
	                	endingHour: ending
	                }),
	                success: function(data) {
	                	if(data.status == "success"){
	                		$(data.message).each(function() {
	    	                	if ($(this).attr('courseComponent').id==ccId){
	    	                		copiedEventObject.schedId = $(this).attr('id');
	    	                	}
	    	                });
	                	} else if (data.status == "error"){
	                    	BootstrapDialog.alert("Ooops. Er liep iets fout...");
	                		//DELETE THE EVENT
	                	}
	                },
	                error: function(data){
	                	alert("Oops! Er liep iets fout. Probeer later opnieuw..");
	                }
	            });
            	$(this).remove();
			}
			
		},
	    eventDrag: function(event, element) {
	    	console.log("eventDrag");
	        event.title = "Dragged!";

	        $('#calendar').fullCalendar('updateEvent', event);
			//alert("Verslepen");

	    },
	    eventRender: function (event, element) {
	    	console.log("eventRender");
	    	element.find('.fc-event-title').html(element.find('.fc-event-title').text());
	    	$('#'+'schedAlert_'+event.id).tooltip('hide');
	    	//alert('schedAlert_'+event.id);
        },
        eventResize: function(event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view ) {
        	console.log("eventResize");
        	var eColor = event.color;
        	if (eColor=='#C80000'){
        		var id = event.id;
            	var newStart = new Date(event.start).getTime();
            	var newEnding = new Date(event.end).getTime();

            	
            	console.log(minuteDelta);
            	console.log(newStart);
    			console.log(newEnding);
            	
            	$.ajax({
            		type: "POST",
                    url: '/calzone/api/teacher/pref/not/move/'+id,
                    contentType: "application/json",
                    dataType: 'json',
                    data: JSON.stringify({
                    	startingHour: newStart,
                    	endingHour: newEnding
                    }),
                    success: function(data) {
                    	if(data.status == "success"){
	                    	BootstrapDialog.alert(data.message);
                    	} else if (data.status == "error"){
                    		revertFunc();
                    	}
                    },
                    error: function(data){
                    	revertFunc();
                    	BootstrapDialog.alert("Oops! Er liep iets fout. Probeer later opnieuw..");
                    }
                });
        	}
        },
        eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) {
        	console.log("eventDrop");
        	var eColor = event.color;
        	if (eColor=='#C80000'){
        		var id = event.id;
            	var newStart = new Date(event.start).getTime();
    			var newEnding = newStart + 2*60*60*1000;
            	$.ajax({
            		type: "POST",
                    url: '/calzone/api/teacher/pref/not/move/'+id,
                    contentType: "application/json",
                    dataType: 'json',
                    data: JSON.stringify({
                    	startingHour: newStart,
                    	endingHour: newEnding
                    }),
                    success: function(data) {
                    	if(data.status == "success"){
                    		alert(data.message);
                    	} else if (data.status == "error"){
                    		revertFunc();
                    	}
                    },
                    error: function(data){
                    	revertFunc();
                    	BootstrapDialog.alert("Oops! Er liep iets fout. Probeer later opnieuw..");
                    }
                });
        	} else {
        		var schedId = event.schedId;
        		var ccId = event.id;
            	var newStart = new Date(event.start).getTime();
    			var newEnding = newStart + 2*60*60*1000;
    			console.log(event.start);
            	console.log(newStart);
            	console.log(newEnding);
            	$.ajax({
            		type: "POST",
                    url: '/calzone/api/teacher/pref/component/move/'+schedId,
                    contentType: "application/json",
                    dataType: 'json',
                    data: JSON.stringify({
                    	courseComponentId: ccId,
                    	startingHour: newStart,
                    	endingHour: newEnding
                    }),
                    success: function(data) {
                    	if(data.status == "success"){
                    		BootstrapDialog.alert(data.message);
                    	} else if (data.status == "error"){
                    		revertFunc();
                    	}
                    },
                    error: function(data){
                    	revertFunc();
                    	BootstrapDialog.alert("Oops! Er liep iets fout. Probeer later opnieuw..");
                    }
                });
        	}
        }
	});

	$('#calender').fullCalendar( 'gotoDate', 2014, 0, 1);
});