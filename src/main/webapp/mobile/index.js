$(function(){	
	$(document).on("pageInit", function(e, pageId, $page) {
//	  if(pageId != "loginpage" && !window.user) {
//		 $.router.load("#loginpage"); 
//		 return;
//	  }
 	  if(pageId == "page_function" || pageId == "page_setting") {
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
	//扫一扫按钮拖动
	var $qrcode_button = document.getElementById('qrcode_button');
	$qrcode_button.addEventListener('touchmove', function(event) {
		if (event.targetTouches.length == 1) {
		　　 event.preventDefault(); 
			var touch = event.targetTouches[0];
			$qrcode_button.style.left = touch.pageX-50 + 'px';
			$qrcode_button.style.top = touch.pageY-50 + 'px';
		}
	}, false);
	//扫一扫
	$("#page_task_info_scanQRCode_btn").click(function(){
		alert("扫一扫");
	});
});
	