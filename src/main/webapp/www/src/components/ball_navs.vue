<template>
<div>
  <ul id="ball_navs" data-open="收起" data-close="展开">
    <li @click="showNavigation">导航</li>
    <li @click="showMembers">成员</li>
    <li @click="showQRcodeInput">条码</li>
  </ul>

  <div class="popup popup-qrcode-input" style="z-index:20000;">
    <header class="bar bar-nav">
        <h1 class="title">手工输条码</h1>
        <button class="button pull-right button-danger button-fill close-popup">
        关闭
       </button>
    </header>
    <div class="content-block" style="margin-top:3rem;width:100%;">
      <div class="searchbar">
        <div class="search-input">
          <label class="icon icon-search" for="search"></label>
          <input type="search" id='search' placeholder='输入设备条码...'/>
        </div>
      </div>

      <p><a href="javascript:void(0);" class="button button-fill">查询 </a></p>
    </div>
  </div>
</div>
</template>
<script>
export default {
  //name: 'app',
  data () {
    return {

    }
  },
  mounted: function mounted() {
    //do something after mounting vue instance
    var ul=$("#ball_navs"),li=$("#ball_navs li"),i=li.length,n=i-1,r=90;
		ul.click(function(){
			$(this).toggleClass('active');

			if($(this).hasClass('active')){
				for(var a=0;a<i;a++){
					var x=-(r*Math.cos(90/n*a*(Math.PI/180))).toFixed(2);
					var y=(-r*Math.sin(90/n*a*(Math.PI/180))).toFixed(2);
					//var aa=(r*Math.cos(90/n*a*(Math.PI/180)));
					li.eq(a).css({
						'transition-delay':""+(50*a)+"ms",
						'-webkit-transition-delay':""+(50*a)+"ms",
						'-o-transition-delay':""+(50*a)+"ms",
						//'-webkit-font-smoothing': 'antialiased',
						'transform':"translate("+x+"px,"+y+"px)",
						'-webkit-transform':"translate("+x+"px,"+y+"px)",
						'-o-transform':"translate("+x+"px,"+y+"px)",
						'-ms-transform':"translate("+x+"px,"+y+"px)"
					});
				}
			}else{
				li.removeAttr('style');
			}
		});


    $("#page_task_info .popup-qrcode-input").on("close",function(){
      $("#page_task_info").css("z-index",vm.z_index);
    });
  },//mounted
  methods:{
    showQRcodeInput:function(){//alert(1);
      var vm=this;
      vm.z_index=$("#page_task_info").css("z-index");
      $("#page_task_info").css("z-index",20000);
       $.popup('.popup-qrcode-input');
    },
    showMembers:function(){
      var vm=this;
      vm.z_index=$("#page_task_info").css("z-index");
      $("#page_task_info").css("z-index",20000);
       $.popup('.popup_members');
    },
    showNavigation:function(){
      alert("请稍候...");
    }
  }
}
</script>
<style>
#ball_navs{
	position:fixed;
	right:1.5rem;
	bottom:4.5rem;
	width:2.2rem;
	height:2.2rem;
	line-height:2.2rem;
	list-style-type:none;
	margin:0;
	padding:0;
	text-align:center;
	border-color:red;
	cursor:pointer;
  z-index:15000;
}

#ball_navs>li,#ball_navs:after{
	position:absolute;
	left:0;top:0;
	width:100%;
	height:100%;
  font-size:0.7em;
	border-radius:50%;
	-webkit-border-radius:50%;
	background-color:rgba(0,255,255,1);
	border: 1px solid rgba(127,255,255,0.25);
}

#ball_navs>li{
	transition:all .6s;
	-webkit-transition:all .6s;
	-moz-transition:.6s;
}

#ball_navs:after{
	content:attr(data-close);
	z-index:1;
	border-radius:50%;
	-webkit-border-radius:50%;
}

#ball_navs.active:after{
	content:attr(data-open);
}
#ball_navs a{


}
</style>
