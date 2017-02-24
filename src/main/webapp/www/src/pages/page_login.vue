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
                <input id="page_login_username" type="text" v-model="username"  placeholder="输入登录名">
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
      username:'',
      password:'',
      serverip:'127.0.0.1',
      serverportal:'8085'
    }
  },
  methods:{
    login:function(){
      //page_function
      //alert(this.username+"===="+this.password);
      var params={
        username: this.username,
        password:this.password
      };
      $.SP="http://"+this.serverip+":"+this.serverportal;
      //alert($.SP);
      $.showPreloader("正在登陆....");
      $.post($.SP+'/mobile/login/login.do', params, function(response){
        
        $.hidePreloader();
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
