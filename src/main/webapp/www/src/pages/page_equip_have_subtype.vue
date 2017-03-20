<template>
  <div class="page page-current" id="page_equip_have_subtype">
    <header class="bar bar-nav">
      <a class="button button-link button-nav pull-left back" href="javascript:void(0);"  @click="back"> <span class="icon icon-left"></span>返回
      </a>
      <h1 class="title">{{type_name}}</h1>
    </header>
    <div class="content">
      <div class="list-block" style="margin-top:0px;">
        <ul>
          <li class="item-content item-link" v-for="type in typenums">
            <!--<div class="item-media"><i class="icon icon-f7"></i></div>-->
            <div class="item-inner">
              <div class="item-title">{{type.name}}</div>
              <div class="item-after">{{type.num}}</div>
            </div>
          </li>
        </ul>
      </div>
    <div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      type_name:'',
      typenums:[]
    }
  },
  beforeRouteEnter (to, from, next) {
    next(vm => {
      vm.querytype_num();
    });
  },
  methods: {
    back:function(){
      window.appvue.back();
    },
    querytype_num:function(){
      let vm=this;

      let params=vm.$route.params;
      vm.type_name=params.type_name;
      $.post($.SP+'/mobile/task/querySubtype_num.do',params,function(response){
        vm.typenums=response.root;
      });
    }
  }
}
</script>
<style>
</style>
