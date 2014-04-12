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

function loadCourseData(f, c, y) {
	//alert(f+" "+c+" "+y);
	$("#go_btn").addClass('active disabled');
	if(f && c && y){
		$("#agenda-selection-title").text(y+" "+c);
		setTimeout(function() {
			goToByScroll("main-content");
		}, 1000);
	}
	setTimeout(function() {
		// Do something after 2 seconds
		$("#go_btn").removeClass('active disabled');
	}, 2000);
}

$(function() {
	// Handler for .ready() called.
	$( "#target" ).submit(function( event ) {
		//alert( "Handler for .submit() called." );
		event.preventDefault();
		var f = $( "#ffac" ).val();
		var c = $( "#fcourse" ).val();
		var y = $( "#fyear" ).val()
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