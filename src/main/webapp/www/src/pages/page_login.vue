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
        <li>
          <div class="item-content">
            <div class="item-media"><i class="icon icon-form-toggle"></i></div>
            <div class="item-inner">
              <div class="item-title label">正式库</div>
              <div class="item-input">
                <label class="label-switch">
                  <input type="checkbox" v-model="isprod" @click="changeIsprod">
                  <div class="checkbox"></div>
                </label>
              </div>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <div class="content-block">
      <div class="row">
        <div class="col-50">
          <a href="javascript:void(0);" @click="logout" class="button button-big button-fill button-danger">退出</a>
        </div>
        <div class="col-50">
          <a href="javascript:void(0);" @click="login" id="page_login_login_btn"  class="button button-big button-fill button-success">登录</a>
        </div>
      </div>
    </div>
  </div>
  <div class="bottom_version">
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
      serverip:'172.16.3.45',
      serverportal:'8085',
      ctx:'',
      isprod:true
    }
  },
  mounted: function mounted() {
    $("#page_login input[type=text],input[type=password]").focus(function(){
      $("#page_login .bottom_version").hide();
    }).blur(function(){
      $("#page_login .bottom_version").show();
    });
    //初始化服务器地址
    //通过网页进行开发的时候，npm
    if(location.hostname=="localhost"){
      this.serverip="localhost";
      this.serverportal="8085";
      window.debug=true;
    } else {
      var u = navigator.userAgent;
      var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
      var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
      if(!isAndroid && !isiOS){//通过网页进行访问
        this.serverip=location.hostname;
        this.serverportal=location.port;
      } else {
        //正式环境等测试差不多的时候，把注释去掉
        //this.serverip="122.227.163.82";
        //this.serverportal="8080";
      }

    }

      $.SP="http://"+this.serverip+":"+this.serverportal+this.ctx;

  },
  methods:{
    changeIsprod:function(){
      if(!this.isprod){
        this.serverip="122.227.163.82";
        this.serverportal="9090";
        this.ctx="/test"
      } else {
        this.serverip="122.227.163.82";
        this.serverportal="8080";
        this.ctx=""
      }
    },
    login:function(){
      var vm=this;
      //page_function
      //alert(this.loginname+"===="+this.password);
      var params={
        loginname: this.loginname,
        password:this.password
      };
      $.SP="http://"+this.serverip+":"+this.serverportal+this.ctx;


      $.showPreloader("正在登陆....");
      //  alert($.SP);
        $.post($.SP+'/mobile/login/login.do', params, function(response){
        $.hidePreloader();

        if(response.success){

          window.login_user=response.root;
          window.login_user.session_id=response.session_id
          vm.loc();
          //window.appvue.to("/page_function");//.$emit('e_route_page','/page_function');
          setTimeout("onlineling()",120000);
          window.appvue.to({name:"page_function"});
        } else {
          $.toast(response.msg);
        }

      });
    },
    logout:function(){
      //if(navigator){
      //  navigator.app.exitApp();
    //  }
      window.exitApp();
    },
    loc:function(){//启动定位功能
      var vm=this;
      if(window.BaiduNavi){
        window.BaiduNavi.loc(function(){
          //alert("成功");
        },function(error){
          alert(error);
        },[{
          user_id:window.login_user.id,
          login_name:window.login_user.loginName,
          session_id:window.login_user.session_id,
          uuid:device.uuid,
          gps_host:vm.serverip,
          gps_port:"9091"
        }]);
      }

    }
  }
}

//alert($);
</script>

<style>
.bottom_version {
  position:absolute;
  bottom:0px;
  font-size:13px;
  text-align:center;
  width:100%;
}
</style>
