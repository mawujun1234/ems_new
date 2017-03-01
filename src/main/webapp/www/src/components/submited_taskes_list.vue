<template>
  <div class="list-block media-list " >
    <ul>
      <li v-for="task in taskes">
        <a href="#page_task_info" class="item-link item-content">
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
        alert(response.pager.root.length);
        vm.taskes=response.pager.root;
      });
    }
  }
}
</script>
<style>
</style>
