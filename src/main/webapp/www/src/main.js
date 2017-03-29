//import "babel-polyfill";
import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
//var $ = require('zepto');
//开启debug模式
window.debug=false;
Vue.config.debug = window.debug;

import App from './App.vue'
import page_login from './pages/page_login.vue'
import page_function from './pages/page_function.vue'
import page_taskes from './pages/page_taskes.vue'
import page_task_info from './pages/page_task_info.vue'
import page_taskes_search from './pages/page_taskes_search.vue'
import page_taskes_search_list from './pages/page_taskes_search_list.vue'
import page_equip_have from './pages/page_equip_have.vue'
import page_equip_have_subtype from './pages/page_equip_have_subtype.vue'
import page_setting from './pages/page_setting.vue'
import page_update_password from './pages/page_update_password.vue'

import VueRouter from "vue-router";
Vue.use(VueRouter);

import remutils from './assets/remutils'


const routes = [
  //{ path: '/', component: page_login },
  { path: '/page_login', component: page_login,name: 'page_login' },
  { path: '/page_function', component: page_function ,name: 'page_function'},
  { path: '/page_taskes/:type', component: page_taskes,name: 'page_taskes'},
  { path: '/page_taskes_search', component: page_taskes_search,name: 'page_taskes_search'},
  { path: '/page_taskes_search_list', component: page_taskes_search_list,name: 'page_taskes_search_list'},
  { path: '/page_task_info/:task_id', component: page_task_info ,name: 'page_task_info'},
  { path: '/page_equip_have', component: page_equip_have,name: 'page_equip_have'},
  { path: '/page_equip_have_subtype', component: page_equip_have_subtype,name: 'page_equip_have_subtype'},
  { path: '/page_setting', component: page_setting,name: 'page_setting'},
  { path: '/page_update_password', component: page_update_password,name: 'page_update_password'}
]

const router = new VueRouter({
  routes // （缩写）相当于 routes: routes
});
router.afterEach(route => {
  store.commit("incream_router_len");
  if(route.path=='/page_function' || route.path=='/page_setting'){
    $("#bottom_bar").show();
  } else {
    $("#bottom_bar").hide();
  }
})

const store = new Vuex.Store({
  state: {
    //count:11,
    router:{
      len:0
    },
    task_repair:{
      newTask_num:0,
      read_num:0,
      handling_num:0,
      submited_num:0
    },
    task_patrol:{
      //total_num:0,
      newTask_num:0,
      read_num:0,
      handling_num:0,
      submited_num:0
    },
    task_cancel:{
      //total_num:0,
      newTask_num:0,
      read_num:0,
      handling_num:0,
      submited_num:0
    },
    task_check:{
      //total_num:0,
      newTask_num:0,
      read_num:0,
      handling_num:0,
      submited_num:0
    },
    task_newInstall:{
      //total_num:0,
      newTask_num:0,
      read_num:0,
      handling_num:0,
      submited_num:0
    }
  },
  mutations: {
    init_taskes_num (state,arry) {
      for(var i=0;i<arry.length;i++){
        var tasknum=state["task_"+arry[i].type];
        for (var x in arry[i]) {
          if(x!='type' && x!='total_num'){
            tasknum[x]=arry[i][x];
          }
        }
      }
    },
    update_taskes_num (state,obj) {
      //alert(obj.type+"==="+obj.status+"=="+obj.num);
      state['task_'+obj.type][obj.status+"_num"]=obj.num;
    },
    incream_router_len (state){
      state.router.len++;
    }
  }
})

const appvue=new Vue({
  el: '#app',
  store,
  router:router,
  render: h => h(App),
  mounted: function mounted() {
    //do something after mounting vue instance
    //alert(1);
    this.to("page_login");
  },
  methods:{
    to:function(path){
      router.transitionName='slide-left';
      router.push(path);
    },
    back:function(){
      router.transitionName='slide-right';
      router.go(-1);
    }
  }
});
window.appvue=appvue;


//window.appvue.$on("e_route_page",function(path){//alert(1);
//  router.push(path);
//});

//app.currentRoute="/";

//application/json;charset=ISO-8859-1
$.ajaxSettings.dataType='json';
$.SP="";
$(function(){
  $(document).on('ajaxBeforeSend', function(e, xhr, options){
    xhr.withCredentials = true;
  })
	//$(document).on('ajaxSuccess',function(e,xhr,options,response){
		//handlerReturn(response);
	//});
	$(document).on('ajaxError',function(e,xhr,options,response){
		if(xhr.status==503){
			handlerReturn(JSON.parse(xhr.responseText));
		} else if(xhr.status==0){
      $.alert("请求超时，检查地址，端口!");
    } else {
			//handlerReturn(response);
      $.alert(xhr.status+"：非正常请求，请联系管理员！");
		}
    $.hidePreloader();
	});
	function handlerReturn(response){
		if(response.success==false){
				if(response.msg){
					alert(response.msg);
					if(response.errorCode=='nologin'){
						//$.router.load("#od_loginpage");
					}
					//return;
				} else {
					//return;
				}
				$.hidePreloader();
		} else {
      $.alert("系统发生异常");
    }
	}

  window.onlineling=function() {
    $.post($.SP+'/user/onlineling.do',{},function(response){
      if(!response.success){
			   router.push('/page_login');
			}
    });
		setTimeout("onlineling()",120000);
	}

  $.isMobile=function(){
    var userAgentInfo = navigator.userAgent;
    var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");
    var flag = false;
    for (var v = 0; v < Agents.length; v++) {
      if (userAgentInfo.indexOf(Agents[v]) > 0) { flag = true; break; }
    }
    return flag;
  }

});

document.addEventListener('deviceready', function(){
  document.addEventListener("backbutton", onBackKeyDown, false);
  function onBackKeyDown() {
    // Handle the back button
    //alert(window.location.href);
    var page_id=window.location.href.split("#/")[1];

    if (page_id.indexOf("page_login")==-1
      && page_id.indexOf("page_function")==-1
      && page_id.indexOf("page_setting")==-1 ) {
        window.appvue.back();
    } else {
        var r=confirm("确定要退出?");
        if (r==true){
          alert("你要退出");
        } else {
          alert("不退出");
        }
    }
  }
}, false);
