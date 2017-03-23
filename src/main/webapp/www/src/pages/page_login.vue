<template>
<div class="page page-current" id="page_login">
  <header class="bar bar-nav">
    <h1 class='title' id="page_login_title">{{title}}</h1>
  </header>

  <div class="content">
    <div class="list-block">
      <ul>
        <li>
          <div class="item-content">
            <div class="item-media">
              <i class="icon icon-form-name"></i>
            </div>
            <div class="item-inner">
              <div class="item-title label">登录名:</div>
              <div class="item-input">
                <input id="page_login_loginname" type="text" v-model="loginname"  placeholder="输入登录名">
              </div>
            </div>
          </div>
        </li>
        <li>
          <div class="item-content">
            <div class="item-media">
              <i class="icon icon-form-password"></i>
            </div>
            <div class="item-inner">
              <div class="item-title label">密码</div>
              <div class="item-input">
                <input id="page_login_password" type="password" v-model="password"  placeholder="输入密码" class="">
              </div>
            </div>
          </div>
        </li>
        <li>
          <div class="item-content">
            <div class="item-media">
              <i class="icon icon-form-name"></i>
            </div>
            <div class="item-inner">
              <div class="item-title label">IP地址:</div>
              <div class="item-input">
                <input type="text" v-model="serverip"  placeholder="输入服务器地址">
              </div>
            </div>
          </div>
        </li>
        <li>
          <div class="item-content">
            <div class="item-media">
              <i class="icon icon-form-name"></i>
            </div>
            <div class="item-inner">
              <div class="item-title label">端口:</div>
              <div class="item-input">
                <input type="text" v-model="serverportal"  placeholder="输入服务器端口">
              </div>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <div class="content-block">
      <div class="row">
        <div class="col-50">
          <a href="#" class="button button-big button-fill button-danger">退出</a>
        </div>
        <div class="col-50">
          <a href="#" @click="login" id="page_login_login_btn"  class="button button-big button-fill button-success">登录</a>
        </div>
      </div>
    </div>
  </div>
  <div class="bottom">
    @2014 宁波东望智能系统工程有限公司
    <br/>
    版本:{{version}}
  </div>
</div>
</template>

<script>
export default {
  data () {
    return {
      title: '生产调度系统',
      version:'2.0.0',
      loginname:'yinzhou2',
      password:'123',
      serverip:'',
      serverportal:''
    }
  },
  mounted: function mounted() {
    //如果是通过浏览器进来就直接获取浏览器的地址和端口
    if(location.hostname=="localhost"){
      this.serverip="localhost";
      this.serverportal="8085";
      window.debug=true;
    } else {
      var u = navigator.userAgent;
      var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
      var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
      if(!isAndroid && !isiOS){
        this.serverip=location.hostname;
        this.serverportal=location.port;
      } else {
        //正式环境
        this.serverip="122.227.163.82";
        this.serverportal="8080";
      }
    }
    $.SP="http://"+this.serverip+""+this.serverportal;
    if(this.serverportal=='9090'){
      //测试库
      $.SP+="/test";
    }
  },
  methods:{
    login:function(){
      //page_function
      //alert(this.loginname+"===="+this.password);
      var params={
        loginname: this.loginname,
        password:this.password
      };
      $.SP="http://"+this.serverip+":"+this.serverportal;
      if(this.serverportal=='9090'){
        //测试库
        $.SP+="/test";
      }
      //alert($.SP);

       //return;
      $.showPreloader("正在登陆....");
      $.post($.SP+'/mobile/login/login.do', params, function(response){
        $.hidePreloader();
        if(response.success){
          setTimeout("onlineling()",120000);
          window.appvue.to("/page_function");//.$emit('e_route_page','/page_function');
        } else {
          $.toast(response.msg);
        }

      });
    }
  }
}

//alert($);
</script>

<style>
.bottom {
  position:absolute;
  bottom:0px;
  font-size:13px;
  text-align:center;
  width:100%;
}
</style>
