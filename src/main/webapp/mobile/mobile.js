$(function(){	
	$(document).on("pageInit", function(e, pageId, $page) {
//	  if(pageId != "loginpage" && !window.user) {
//		 $.router.load("#loginpage"); 
//		 return;
//	  }
 	  if(pageId == "function_page" || pageId == "setting_page") {
		  $("#bottom_bar").show();

		  $("#bottom_bar a.tab-item").removeClass("active");
	      $("#bottom_bar_"+pageId).addClass("active");
		  
		  sessionStorage["active_nav_id"]="#bottom_bar_"+pageId;  
	  } else {
		 $("#bottom_bar").hide();
	  }

	 
	});
	
	$("#bottom_bar a.tab-item").click(function(){
		$("#bottom_bar a.tab-item").removeClass("active");
		$(this).addClass("active");
		//window.nav_click_aaaa=true;
	});
});
	