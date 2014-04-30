jQuery(document).ready(function() {
	jQuery(".modeldelete").bind("click", function() {
		//alert($(this).attr("data"));
		var id = $(this).attr("data");
		$("#" + id).hide();
	});
});
$(document).ready(function() {
	$('#search').keyup(function() {
		searchTable($(this).val());
	});
});

function searchTable(inputVal) {
	var table = $('#tblData');
	table.find('tr').each(function(index, row) {
		var allCells = $(row).find('td');
		if (allCells.length > 0) {
			var found = false;
			allCells.each(function(index, td) {
				var regExp = new RegExp(inputVal, 'i');
				if (regExp.test($(td).text())) {
					found = true;
					return false;
				}
			});
			if (found == true)
				$(row).show();
			else
				$(row).hide();
		}
	});
}

$.fn.pageMe = function(opts){
    var $this = this,
        defaults = {
            perPage: 10,
            showPrevNext: true,
            hidePageNumbers: false
        },
        settings = $.extend(defaults, opts);
    
    var listElement = $this;
    var perPage = settings.perPage; 
    var children = listElement.children();
    var pager = $('#myPager');
    
    if (typeof settings.childSelector!="undefined") {
        children = listElement.find(settings.childSelector);
    }
    
    if (typeof settings.pagerSelector!="undefined") {
        pager = $(settings.pagerSelector);
    }
    
    var numItems = children.size();
    var numPages = Math.ceil(numItems/perPage);

    pager.data("curr",0);
    
    if (settings.showPrevNext){
        $('<li><a href="#" class="prev_link">«</a></li>').appendTo(pager);
    }
    
    var curr = 0;
    while(numPages > curr && (settings.hidePageNumbers==false)){
    	if (curr > 4){
    		$('<li><a href="#" class="page_link" style="display:none;">'+(curr+1)+'</a></li>').appendTo(pager);
    	} else {
	        $('<li><a href="#" class="page_link">'+(curr+1)+'</a></li>').appendTo(pager);
    	}
        curr++;
    }
    
    if (settings.showPrevNext){
        $('<li><a href="#" class="next_link">»</a></li>').appendTo(pager);
    }
    
    pager.find('.page_link:first').addClass('active');
    pager.find('.prev_link').hide();
    if (numPages<=1) {
        pager.find('.next_link').hide();
    }
  	pager.children().eq(1).addClass("active");
    
    children.hide();
    children.slice(0, perPage).show();
    
    pager.find('li .page_link').click(function(){
        var clickedPage = $(this).html().valueOf()-1;
        goTo(clickedPage,perPage);
        return false;
    });
    pager.find('li .prev_link').click(function(){
        previous();
        return false;
    });
    pager.find('li .next_link').click(function(){
        next();
        return false;
    });
    
    function previous(){
        var goToPage = parseInt(pager.data("curr")) - 1;
        goTo(goToPage);
    }
     
    function next(){
        goToPage = parseInt(pager.data("curr")) + 1;
        goTo(goToPage);
    }
    
    function goTo(page){
        var startAt = page * perPage,
            endOn = startAt + perPage;
        
        children.css('display','none').slice(startAt, endOn).show();
        
        
        if (page>=1) {
            pager.find('.prev_link').show();
        }
        else {
            pager.find('.prev_link').hide();
        }
        
        if (page<(numPages-1)) {
            pager.find('.next_link').show();
        }
        else {
            pager.find('.next_link').hide();
        }
        
        pager.children().find('.page_link').hide();
        
        if (page >= 3){
        	//alert(pager.find(".page_link [attribute!='page']"));
	        pager.children().eq(page-1).find('.page_link').show();
	        pager.children().eq(page).find('.page_link').show();
	        pager.children().eq(page+1).find('.page_link').show();
	        pager.children().eq(page+2).find('.page_link').show();
	        pager.children().eq(page+3).find('.page_link').show();
        } else if (page==0 || page==1 || page ==2 ){
	        pager.children().eq(1).find('.page_link').show();
	        pager.children().eq(2).find('.page_link').show();
	        pager.children().eq(3).find('.page_link').show();
	        pager.children().eq(4).find('.page_link').show();
	        pager.children().eq(5).find('.page_link').show();
        }
        
        pager.data("curr",page);
      	pager.children().removeClass("active");
        pager.children().eq(page+1).addClass("active");
        
    }
};

$(document).ready(function(){
    
  $('#myTable').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:10});
    
});