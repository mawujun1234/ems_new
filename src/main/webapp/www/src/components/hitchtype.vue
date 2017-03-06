<template>
<div class="popup popup-hitchtype" >
  <header class="bar bar-nav">
    <h1 class="title">故障原因</h1>
    <button class="button pull-right button-danger button-fill close-popup">
    关闭
   </button>
  </header>
  <div class="content">
    <template  v-for="ht in allHitchtype">
    <div class="content-block-title">{{ht.name}}</div>
    <div class="list-block">
      <ul>
        <li class="item-content" v-for="tpl in ht.hitchReasonTpls">
          <div class="item-inner">
            <div class="item-title">{{tpl.name}}</div>
            <div class="item-after">
              <button id="btn_hitchtype" class="button pull-right button-success button-fill" @click="selectHitchtype(ht.id,ht.name,tpl.id,tpl.name)">
                选择
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
      allHitchtype:[]
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
    selectHitchtype:function(ht_id,ht_name,tpl_id,tpl_name){
      //alert(ht_id);
      //return;
      //var btn_hitchtype=$("#page_task_info  #btn_hitchtype");
      //alert(btn_hitchtype.attr("hitchtype_id"));
      $.closeModal(".popup-hitchtype");
      this.$emit("selectHitchtype",ht_id,ht_name,tpl_id,tpl_name);

    }
  }
}
</script>
<style>
</style>
