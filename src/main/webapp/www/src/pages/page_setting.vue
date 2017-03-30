<template>
  <!-- #page_setting 设置界面 -->
  <div class="page page-current" id="page_setting" >
    <!-- 标题栏 -->
    <header class="bar bar-nav">
      <h1 class="title">我的</h1>
    </header>
    <!-- 这里是页面内容区 -->
    <div class="content" style="margin-bottom:2.5rem;">
      <div class="list-block media-list" style="margin-top:1rem;">
          <ul>
            <li class="item-link item-content" v-for="workunit in workunites">
                <div class="item-media"><span class="icon icon-me" style="width:3rem;font-size:2rem;"></span></div>
                <div class="item-inner">
                  <div class="item-title-row">
                    <div class="item-title">{{workunit.loginname}}</div>
                    <div class="item-after">*</div>
                  </div>
                  <div class="item-subtitle">{{workunit.workunit_name}}</div>
                  <div class="item-text">{{workunit.lastlogintime}}</div>
                </div>
            </li>
          </ul>
        </div>

        <div class="list-block">
          <ul>
            <li class="item-link item-content" @click="to_update_password">
                <div class="item-media"><i class="icon icon-f7"></i></div>
                <div class="item-inner">
                  <div class="item-title"><span class="icon icon-edit" style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;</span>修改密码</div>
                </div>
            </li>
            <li>
            <li class="item-link item-content" @click="to_qrcode">
                <div class="item-media"><i class="icon icon-f7"></i></div>
                <div class="item-inner">
                  <div class="item-title"><span class="icon icon-code" style="color:#FF1493;">&nbsp;&nbsp;&nbsp;&nbsp;</span>二维码</div>
                </div>
            </li>
          </ul>
        </div>

        <div class="list-block">
          <ul>
            <li class="item-link item-content" @click="logout">
                <div class="item-media"><i class="icon icon-f7"></i></div>
                <div class="item-inner">
                  <div class="item-title"><span class="icon icon-remove" style="color:#FF1493;">&nbsp;&nbsp;&nbsp;&nbsp;</span>退出</div>
                </div>
            </li>
          </ul>
        </div>
    </div><!-- content -->
  </div>
  <!--#page_setting  功能列表 -->
</template>
<script>
export default {
  data () {
    return {
      user_id:'',
      workunites:[]
    }
  },
  mounted: function() {
    //do something after mounting vue instance
    let vm=this;
    $.post($.SP+'/mobile/login/queryMyinfo.do',{},function(response){
      vm.workunites=response.root;
      vm.user_id=response.user_id;
    });
  },
  methods: {
    to_update_password:function(){
      window.appvue.to({ name: 'page_update_password',
        params: {
        }
      });
    },
    to_qrcode:function(){
      if(!$.isMobile()){
          $.toast("请在手机上操作");
          return;
      }

      cordova.plugins.barcodeScanner.encode(cordova.plugins.barcodeScanner.Encode.TEXT_TYPE, "member:"+this.user_id, function(success) {
            //alert("encode success: " + success);
          }, function(fail) {
            alert("encoding failed: " + fail);
          }
        );
      return;
    },
    logout:function(){
      let vm=this;
      $.post($.SP+'/mobile/login/logout.do',{},function(response){
        if(response.success){
          //var index=vm.$store.state.router.len
          //window.appvue.$router.go(-index)
          window.exitApp();
        }
      });
    }
  }
}
</script>
<style>
</style>
