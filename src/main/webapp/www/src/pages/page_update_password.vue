<template>
<div class="page page-current" id="page_update_password">
  <header class="bar bar-nav">
    <a class="button button-link button-nav pull-left back" href="javascript:void(0);" @click="back"> <span class="icon icon-left"></span>返回
    </a>
    <h1 class="title">修改密码</h1>
  </header>

  <div class="content">
    <div class="list-block">
      <ul>
        <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-name"></i></div>
          <div class="item-inner">
            <div class="item-title label">旧密码:</div>
            <div class="item-input">
              <input type="password" v-model="old_pwd" placeholder="输入旧密码">
            </div>
          </div>
        </div>
      </li>
      <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-email"></i></div>
          <div class="item-inner">
            <div class="item-title label">新密码</div>
            <div class="item-input">
              <input type="password" v-model="new_pwd" placeholder="输入新密码">
            </div>
          </div>
        </div>
      </li>
      </ul>
    </div>

    <div class="content-block">
      <a href="javascript:void(0);" @click="update_password" class="button button-big button-fill button-success">提交</a>
    </div>
  </div>
</div>
</template>
<script>
export default {
  data () {
    return {
      old_pwd:'',
      new_pwd:''
    }
  },
  methods: {
    back:function(){
        window.appvue.back();
    },
    update_password:function(){
      let vm=this;
      $.post($.SP+'/mobile/login/updatePassword.do',{
        old_pwd:vm.old_pwd,
        new_pwd:vm.new_pwd
      },function(response){
        //vm.workunites=response.root;
        if(response.success){
          $.toast("更改成功!");
          window.appvue.back();
        } else {
          $.toast(response.msg);
        }

      });
    }
  }
}
</script>
<style>
</style>
