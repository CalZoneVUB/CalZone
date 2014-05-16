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

	if (!$('#TrajectSelectionSchedular').length){
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
			editable: true,
			droppable: false, // this allows things to be dropped onto the calendar !!!
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
		            url: '/calzone/api/calendar/students/0',
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
	
		                	var rID = $(this).attr('room').id;
		                	var rName = $(this).attr('room').vubNotation;
		                	
		                	var frozen = $(this).attr('frozen');
		                	
		                	var title = courseName + '<br>' + type;
		                	
		                	var scheduleAlert = 'yes';
		                	
		                	var icons = '';
		                	/*if (frozen){
		                		icons = icons + '<span class=\"glyphicon glyphicon-lock \"> </span>';
		                		//'<span class=\"glyphicon glyphicon-warning-sign orange\"></span>'
		                	}
		                	
		                	if (scheduleAlert){
		                		//icons = icons + '<span class=\"glyphicon glyphicon-warning-sign orange\"></span>';
		                		icons = icons + '<span id=\"schedAlert_'+id+'\" class=\"glyphicon glyphicon-warning-sign orange\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"Warning\"> </span>';
		                		//icons = icons + '<button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="bottom" title="Tooltip on bottom">Tooltip on bottom</button>';
		                	}*/
		                	
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
				var id = event.id;
	        	var newStart = new Date(event.start).getTime();
	        	
	        	// Show modal and fill selection field with available rooms..
	        	$('#entryEditModal').modal('show');
				$('#entryEditModalSelect').replaceWith('<select id="entryEditModalSelect" class="form-control"><option value="default">'+event.roomName+' (huidige lokaal)</option></select>');
	        	$.ajax({
		            url: '/calzone/api/classroom',
		            dataType: 'json',
		            success: function(data) {
						$.each(data, function(key, value) {
						    $('#entryEditModalSelect')
						         .append($("<option></option>")
						         .attr("value",value.id)
						         .text(value.vubNotation));
						});
		            },
		            error: function(data){
			        	BootstrapDialog.alert("Oops! Er ging iets mis...");
		            }
	        	});
	          	
	          	/* the callback functions for our calender
	        	-----------------------------------------------------------------*/
	        	$("#entryEditModalX").bind("click", function() {
	        		$('#entryEditModal').modal('hide');
	        		$("#entryEditModalCancel").unbind();
	        		$(this).unbind();
	        	});
	        	
	        	$("#entryEditModalCancel").bind("click", function() {
	        		$('#entryEditModal').modal('hide');
	        		$("#entryEditModalX").unbind();
	        		$(this).unbind();
	        	});
	        	
	        	$("#entryEditModalSave").bind("click", function() {
	        		var eId = event.id;
	            	var rId = $('#entryEditModalSelect').val();
	        		$(this).attr("disabled", "disabled");
	            	$.ajax({
	            		type: "POST",
	                    url: '/calzone/api/calendar/move/room',
	                    contentType: "application/json",
	                    data: JSON.stringify({
	                    	entryId: eId,
	                    	roomId: rId,
	                    }),
	                    success: function(data) {
	                    	if(data.status == "success"){
	                    		event.roomId = rId;
	                    		$('#entryEditModal').modal('hide');
	                    		$(this).attr("disabled", "enable");
	                    	} else if (data.status == "error"){
	                    		BootstrapDialog.alert(data.message);
	                    	}
	                    },
	                    error: function(data){
	                    	BootstrapDialog.alert("Oops!Er liep iets fout. Probeer later opnieuw...");
	                    }
	                });
	            	// Clear this function after completion...
	        		$(this).unbind();
	        	});
	
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
	                    		BootstrapDialog.alert(data.message);
	                    	}
	                    },
	                    error: function(data){
	                    	BootstrapDialog.alert("Oops! Er liep iets fout. Probeer later opnieuw.");
	                    }
	                });
	            	// Clear this function after completion...
	        		$(this).unbind();
	        	});
	        }
		});
	}
	
});