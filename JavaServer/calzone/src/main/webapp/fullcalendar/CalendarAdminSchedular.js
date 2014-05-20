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
$(document).ready(function() {
	
	var calShow = function(valueSelect) {
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
		
		$('#TopBanner').show();
		$('#ScheduleTraject').attr('data-traject', valueSelect);
		$('#FreezeTraject').attr('data-traject', valueSelect);
		$('#ScheduleActions').show();
		
		$('#calendar').replaceWith('<div id="calendar" style="height:100px;"></div>');
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
			editable: true,
			droppable: false, // this allows things to be dropped onto the calendar !!!
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
		            url: '/calzone/api/calendar/traject/'+valueSelect,
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
		                	var place = $(this).attr('room').vubNotation;
		                	
		                	var title = courseName + '<br>' + place +'<br>' + type;
		                	
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
	        	console.log("eventClick");
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
		          	  BootstrapDialog.alert("Oops... Er ging iets fout.");
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
	  		          	  BootstrapDialog.alert("Oops... Er ging iets fout.");
	                    }
	                });
	            	// Clear this function after completion...
	        		$(this).unbind();
	        	});
		        //$('#calendar').fullCalendar('updateEvent', event);
	
		    },
		    eventDrag: function(event, element) {
		    	console.log("eventDrag");
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
	        	console.log("eventDrop");
	    		var id = event.id;
	        	var newStart = new Date(event.start).getTime();
	        	var id = event.id;
	            var newStart = new Date(event.start).getTime();
	        	$(this).attr("disabled", "disabled");
	            $.ajax({
	            		type: "POST",
	                    url: '/calzone/api/calendar/move/time?silent=true',
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
	                    		//alert(data.message);
	                    	}
	                    },
	                    error: function(data){
	                    	//alert("Oops! Er liep iets fout. Probeer later opnieuw..");
	                    }
	                });
	        }
		});
	};
	
	$('#ScheduleTrajectView').click(function () {
		var valueSelect = $('#TrajectSelectionSchedular').val();
		calShow(valueSelect);
	});
	
	$('#ScheduleTrajectViewNotFrozen').click(function () {
		var valueSelect = $('#TrajectSelectionSchedularNotFrozen').val();
		calShow(valueSelect);
	});
	
	//Load all the trajects into the selection box
	$.ajax({
        url: '/calzone/api/traject/all/formated',
        dataType: 'json',
        success: function(data) {
			var options = '';
    		$(data).each(function() {
    		     options += '<option value="' + $(this).attr('value') + '">' + $(this).attr('text') + '</option>';
            });
    		$("select#TrajectSelectionSchedular").html(options);
        },
        error: function(data){
        	  BootstrapDialog.alert("Oops! Kon trajecten niet ophalen [url: /calzone/api/traject/all/formated].");
        }
    });

	//Load unfrozen trajects into the selection box
	$.ajax({
        url: '/calzone/api/traject/all/formated/notfronzen',
        dataType: 'json',
        success: function(data) {
			var options = '';
    		$(data).each(function() {
    		     options += '<option value="' + $(this).attr('value') + '">' + $(this).attr('text') + '</option>';
            });
    		$("select#TrajectSelectionSchedularNotFrozen").html(options);
        },
        error: function(data){
      	  BootstrapDialog.alert("Oops! Kon trajecten niet ophalen [url: api/traject/all/formated/notfronzen].");
        }
    });
	
	//Schedule the currently selected trajectory
	$('#ScheduleTraject').click(function () {
		var value = $(this).attr('data-traject');
		$.ajax({
	        url: '/calzone/api/traject/schedule/'+value,
	        dataType: 'json',
	        success: function(data) {
				//Succesvolle callback na een schedule
	        	calShow(value);
	        },
	        error: function(data){
	        	  BootstrapDialog.alert("Oops! Kon trajecten niet ophalen [url: api/traject/all/formated/notfronzen].");
	        	//alert("Oops! Kon traject niet schedulen...");
	        }
	    });
	});
	
	//Freeze the currently selected trajectory
	$('#FreezeTraject').click(function () {
		var value = $(this).attr('data-traject');
		$.ajax({
	        url: '/calzone/api/traject/freeze/'+value,
	        dataType: 'json',
	        success: function(data) {
				//Succesvolle callback na een schedule
	        },
	        error: function(data){
	        	  BootstrapDialog.alert("Oops! Kon traject niet schedulen...");
	        }
	    });
	});
	
	$('#ViewSchedulingConstraints').click(function () {
		$("#constraintsTable").html('<tr></tr>');
		$('#constraintsModal').modal('show');
		$("#modalProgressBar").show();
		var value = $('#ScheduleTraject').attr('data-traject');
		$.ajax({
	        url: '/calzone/api/traject/constraints/'+value,
	        dataType: 'json',
	        success: function(data) {
	        	if (data.status=='success'){
	        		//Succesvolle callback na een schedule
	    			var constraints = '';
		        	$(data.message).each(function(index, value) {
		        		var content = '<div class="alert alert-info">'+value+'</div>';
		        		constraints += '<tr><td>'+content+'</td></tr>';
		        	});
		    		$("#modalProgressBar").hide();
		    		if (constraints.length==0){
			    		$("#constraintsTable").html('<div class="alert alert-info">Er werden geen constraints geschonden.</div>');
		    		} else {
			    		$("#constraintsTable").html(constraints);
		    		}
	        	}
	        },
	        error: function(data){
	        	  BootstrapDialog.alert("Oops! Kon traject niet bevriezen...");
	        }
	    });
	});
	
});