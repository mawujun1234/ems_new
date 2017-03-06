<template>
<div class="popup popup-handleMethod" >
  <header class="bar bar-nav">
    <h1 class="title">处理方法</h1>
    <button class="button pull-right button-danger button-fill close-popup">
    关闭
   </button>
  </header>
  <div class="content">
    <div class="list-block">
    <ul>
      <li class="item-content" v-for="hm in handleMethods">
        <div class="item-inner">
          <div class="item-title">{{hm.name}}</div>
          <div class="item-after">
            <button id="btn_hitchtype" class="button pull-right button-success button-fill" @click="selectHandleMethod(hm.id,hm.name)">
              选择
            </button>
          </div>
        </div>
      </li>
    </ul>
    </div>
  </div><!--content-->
</div><!--popup-->
</template>
<script>
export default {
  //name: 'app',
  data () {
    return {
      handleMethods:[]
    }
  },
  mounted:function() {
    this.getHandleMethodes();
  },
  methods:{
    getHandleMethodes:function(){
      var vm=this;
      $.post($.SP+'/mobile/task/queryHandleMethodes.do', {}, function(response){
        vm.handleMethods=response.root;
        //vm.inithitch();
      });
    },
    selectHandleMethod:function(ht_id,ht_name){
      $.closeModal(".popup-handleMethod");
      this.$emit("selectHandleMethod",ht_id,ht_name);

    }
  }
}
</script>
<style>
</style>
