import Vue from 'vue'
import App from './App.vue'
//var $ = require('zepto');
//开启debug模式
Vue.config.debug = true;


new Vue({
  el: '#app',
  render: h => h(App)
});


$.ajaxSettings.dataType='json';
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
						$.router.load("#od_loginpage");
					}
					//return;
				} else {
					//return;
				}
				$.hidePreloader();
		}
	}
});
