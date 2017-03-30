<template>
<div class="popup popup_members" >
  <header class="bar bar-nav">
    <h1 class="title">挑选组员</h1>
    <button class="button pull-right button-danger button-fill close-popup">
    关闭
   </button>
  </header>
  <div class="content">
    <template  v-for="wk in workunites">
    <div class="content-block-title">{{wk.name}}</div>
    <div class="list-block">
      <ul>
        <li class="item-content" v-for="memb in wk.members">
          <div class="item-inner">
            <div class="item-title">{{memb.name}}</div>
            <div class="item-after">
              <button id="btn_hitchtype" class="button pull-right button-success button-fill" @click="selectMember(wk.id,wk.name,memb.id,memb.name)">
                加入
              </button>
            </div>
          </div>
        </li>
      </ul>
    </div>
    </template>
  </div><!--content-->
</div><!--popup-->
</template>
<script>
export default {
  //name: 'app',
  data () {
    return {
      task_id:'',
      workunites:[]
    }
  },
  mounted:function() {

  },
  methods:{
    queryWorkunites:function(task_id){
      var vm=this;
      vm.task_id=task_id;
      $.post($.SP+'/mobile/task/queryMembers.do', {task_id:task_id}, function(response){
        vm.workunites=response;
        //vm.inithitch();
      });
    },
    selectMember:function(wk_id,wk_name,memb_id,memb_name){
      var vm=this;
      vm.$emit("selectMember",memb_id);

      // $.post($.SP+'/mobile/task/selectMember.do', {task_id:vm.task_id,user_id:memb_id}, function(response){
      //   $.closeModal(".popup_members");
      //   vm.$emit("selectMember",wk_id,wk_name,memb_id,memb_name);
      // });
    }
  }
}
</script>
<style>
</style>
