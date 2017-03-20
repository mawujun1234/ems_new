<template>
  <div class="page page-current" id="page_equip_have">
    <header class="bar bar-nav">
      <a class="button button-link button-nav pull-left back" href="javascript:void(0);"  @click="back"> <span class="icon icon-left"></span>返回
      </a>
      <h1 class="title">拥有设备统计</h1>
    </header>
    <div class="content">
      <div class="buttons-tab">
        <template v-for="(workunit, index) in workunites">
          <a :id="'#a_'+workunit.id" :href="'#'+workunit.id" :class="'tab-link button '+(index==0?'active':'')" >{{workunit.name}}</a>
        </template>
      </div>
      <div class="content-block" style="padding:0 0 0 0;margin-top:0px;">
        <div class="tabs">
          <template v-for="(workunit, index) in workunites">
          <div :id="workunit.id" :class="'tab '+(index==0?'active':'')">
              <div class="list-block" style="margin-top:0px;">
                <ul>
                  <li class="item-content item-link" v-for="type in workunit.typenums" @click="to_subtype(workunit.id,type.id,type.name)">
                    <div class="item-media"><i class="icon icon-f7"></i></div>
                    <div class="item-inner">
                      <div class="item-title">{{type.name}}</div>
                      <div class="item-after">{{type.num}}</div>
                    </div>
                  </li>
                </ul>
              </div>

          </div>
          </template>
        </div>
      </div>


    <div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      //typenums:[]
      workunites:[{
        id:'11',
        name:'作业单位1',
        typenums:[]
      },{
        id:'22',
        name:'作业单位2',
        typenums:[]
      }]
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
      $.post($.SP+'/mobile/task/queryType_num.do',{},function(response){
        vm.workunites=response.root;
        //alert(response.root[0].id);
        //$("#a_"+response.root[0].id).trigger('click');
      });
    },
    to_subtype:function(workunit_id,type_id,type_name){
      window.appvue.to({ name: 'page_equip_have_subtype',
        params: {
          workunit_id:workunit_id,
          type_id:type_id,
          type_name:type_name
        }
      });
    }
  }
}
</script>
<style>
</style>
