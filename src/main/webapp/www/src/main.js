import Vue from 'vue'
import App from './App.vue'
//var $ = require('zepto');
//开启debug模式
Vue.config.debug = true;

import page_login from './pages/page_login.vue'
import page_function from './pages/page_function.vue'
import VueRouter from "vue-router";
Vue.use(VueRouter);

const routes = [
  { path: '/', component: page_login },
  { path: '/page_login', component: page_login },
  { path: '/page_function', component: page_function }
]


const router = new VueRouter({
  routes // （缩写）相当于 routes: routes
})

window.appvue=new Vue({
  el: '#app',

  router:router,
  render: h => h(App)
});
router.push('/page_login');
window.appvue.$on("e_route_page",function(path){//alert(1);
  router.push(path);
});

//app.currentRoute="/";

//application/json;charset=ISO-8859-1
$.ajaxSettings.dataType='json';
//$.ajaxSettings.accepts.json="application/json;charset=UTF-8";
$(function(){

	$(document).on('ajaxSuccess',function(e,xhr,options,response){
		handlerReturn(response);
	});
	$(document).on('ajaxError',function(e,xhr,options,response){
		if(xhr.status==503){
			handlerReturn(JSON.parse(xhr.responseText));
		} else {
			handlerReturn(response);
		}

	});
	function handlerReturn(response){
		if(response.success==false){
				if(response.msg){
					$.alert(response.msg);
					if(response.errorCode=='nologin'){
						//$.router.load("#od_loginpage");
					}
					//return;
				} else {
					//return;
				}
				$.hidePreloader();
		}
	}
});
