<template>
  <div class="list-block media-list " >
    <ul>
      <li v-for="task in taskes">
        <a href="javascript:void(0);" v-on:click="to_page_taskes_info" :task_id="task.id" class="item-link item-content">
          <div class="item-inner">
            <div class="item-title-row">
              <div class="item-title">{{task.id}}</div>
              <div class="item-after">{{task.createDate}}</div>
            </div>
            <div class="item-subtitle">{{task.pole_code}}:{{task.pole_name}}</div>
            <div class="item-text">地址:{{task.pole_address}}</div>
          </div>
        </a>
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  data () {
    return {
      taskes:[]
    }
  },
  methods:{
    refreshlist:function(type,status){
      window.debug&&console.log("refresh tasklist...");

      const vm=this;
      $.post($.SP+'/mobile/task/queryTaskes.do',{type:type,status:status},function(response){
        //alert(response.root.length);
        vm.taskes=response.root;
      });
    },
    to_page_taskes_info:function(event){
      //alert(this['task_'+type].total_num);
      // alert(event.target.tagName);//.attr("task_id"))
      // alert($(event.target).closest("a").attr("task_id"));
      //return;
      var task_id=$(event.target).closest("a").attr("task_id");
      window.appvue.to({ name: 'page_task_info',
        params: {
          task_id:task_id
        }
      });
    }//to_page_taskes
  }
}
</script>
<style>
</style>
