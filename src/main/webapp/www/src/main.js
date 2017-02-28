import Vue from 'vue'
import App from './App.vue'
//var $ = require('zepto');
//开启debug模式
Vue.config.debug = true;

import page_login from './pages/page_login.vue'
import page_function from './pages/page_function.vue'
import page_taskes from './pages/page_taskes.vue'
import page_taskes_list from './pages/page_taskes_list.vue'
import VueRouter from "vue-router";
Vue.use(VueRouter);

import remutils from './assets/remutils'


const routes = [
  { path: '/', component: page_login },
  { path: '/page_login', component: page_login,name: 'page_login' },
  { path: '/page_function', component: page_function ,name: 'page_function'},
  { path: '/page_taskes/:type', component: page_taskes,name: 'page_taskes'
    ,children:[{
          // 当 /user/:id/profile 匹配成功，
          // UserProfile 会被渲染在 User 的 <router-view> 中
          path: 'all',
          component: page_taskes_list
        },{
          path: 'newTask',
          component: page_taskes_list
        }]
  }
]

const router = new VueRouter({
  routes // （缩写）相当于 routes: routes
})

const appvue=new Vue({
  el: '#app',

  router:router,
  render: h => h(App),
  methods:{
    to:function(path){
      router.push(path);
    }
  }
});
window.appvue=appvue;

router.push('/page_login');
//window.appvue.$on("e_route_page",function(path){//alert(1);
//  router.push(path);
//});

//app.currentRoute="/";

//application/json;charset=ISO-8859-1
$.ajaxSettings.dataType='json';

$(function(){
  $(document).on('ajaxBeforeSend', function(e, xhr, options){
    xhr.withCredentials = true;
  })
	$(document).on('ajaxSuccess',function(e,xhr,options,response){
		handlerReturn(response);
	});
	$(document).on('ajaxError',function(e,xhr,options,response){
		if(xhr.status==503){
			handlerReturn(JSON.parse(xhr.responseText));
		} else {
			//handlerReturn(response);
      $.alert(xhr.status+"：非正常请求，请联系管理员！");
		}
    $.hidePreloader();
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
