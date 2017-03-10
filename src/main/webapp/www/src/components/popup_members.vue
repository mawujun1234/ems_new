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
      workunites:[]
    }
  },
  mounted:function() {
    this.getHitchtype();
  },
  methods:{
    getHitchtype:function(){
      var vm=this;
      $.post($.SP+'/mobile/task/queryAllHitchtype.do', {}, function(response){
        vm.allHitchtype=response.root;
        //vm.inithitch();
      });
    },
    selectMember:function(wk_id,wk_name,memb_id,memb_name){
      //alert(ht_id);
      //return;
      //var btn_hitchtype=$("#page_task_info  #btn_hitchtype");
      //alert(btn_hitchtype.attr("hitchtype_id"));
      $.closeModal(".popup_members");
      this.$emit("selectMember",wk_id,wk_name,memb_id,memb_name);

    }
  }
}
</script>
<style>
</style>
