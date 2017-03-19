<template>
  <div class="list-block media-list " >
    <ul>
      <li v-for="task in taskes">
        <a href="javascript:void(0);" v-on:click="to_page_taskes_info(task.id)"  class="item-link item-content">
          <div class="item-inner">
            <div class="item-title-row">
              <div class="item-title">{{task.id}}</div>
              <div class="item-after">{{task.createDate}}</div>
            </div>
            <div class="item-subtitle">{{task.pole_code}}:{{task.pole_name}}</div>
            <div class="item-text" style="font-size:0.6rem;">地址:{{task.pole_address}}</div>
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
      start:0,
      limit:4,
      maxitem:80,//最大就只显示80条
      loading:false,

      taskes:[]
    }
  },
  methods:{
    refreshlist:function(type,status,isinfinite){
      //window.debug&&console.log("refresh tasklist...");
      const vm=this;
      // 如果正在加载，则退出
      if (vm.loading) return;
      // 设置flag
      vm.loading = true;

      if(!isinfinite){//alert(1);
        vm.start=0;
        vm.taskes=[];
        $('.infinite-scroll-preloader.taskes-list').show();
        $.initInfiniteScroll("#page_taskes .content");
      }

      var params={
        type:type,
        status:status,
        start:vm.start,
        limit:vm.limit
      }

      $.post($.SP+'/mobile/task/queryTaskes.do',params,function(response){
        //alert(response.root.length);
        //vm.taskes=response.root;
        if(vm.taskes==null){
          vm.taskes=[];
        }

        for(var i=0;i<response.root.length;i++){
          vm.taskes.push(response.root[i]);
        }
        //修改标签上的数字
        if(vm.$store.state['task_'+type][status+"_num"]!=response.total){
          vm.$store.commit("update_taskes_num",{type:type,status:status,num:response.total});//
        }

        vm.loading = false;
        vm.start=response.page*response.limit;
        if(response.root.length==0 || response.page==response.totalPages || vm.start>vm.maxitem){
          // 加载完毕，则注销无限加载事件，以防不必要的加载
          $.detachInfiniteScroll($('#page_taskes .infinite-scroll'));
          // 删除加载提示符
          $('.infinite-scroll-preloader.taskes-list').hide();
          return;
        }
        //容器发生改变,如果是js滚动，需要刷新滚动
        $.refreshScroller();
      });
    },
    to_page_taskes_info:function(task_id){
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
