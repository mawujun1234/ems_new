<template>
  <!-- #page_taskes_search-->
  		<div class="page page-current" id="page_taskes_search_list">
  			<!-- 标题栏 -->
  			<header class="bar bar-nav">
  				<a class="button button-link button-nav pull-left back" href="javascript:void(0);" @click="back"> <span class="icon icon-left"></span>返回
  				</a>
  				<h1 class="title">搜索结果</h1>
  			</header>
  			<!-- 这里是页面内容区 -->
  			<div class="content infinite-scroll infinite-scroll-bottom" data-distance="50">
          <div class="list-block media-list " >
            <ul>
              <li v-for="task in taskes">
                <a href="javascript:void(0);" v-on:click="to_page_taskes_info(task.id)" class="item-link item-content">
                  <div class="item-inner">
                    <div class="item-title-row">
                      <div class="item-title">{{task.id}}<span style="font-size:0.5rem;">({{task.type_name}})</span></div>
                      <div class="item-after" style="font-size:0.6rem;">{{task.createDate}}</div>
                    </div>
                    <div class="item-subtitle">
                      <span style="font-size:0.5rem;color:red;" v-if="task.canEdit">({{task.status_name}})</span>
                      <span style="font-size:0.5rem;" v-else>({{task.status_name}})</span>
                      {{task.pole_code}}:{{task.pole_name}}</div>
                    <div class="item-text" style="font-size:0.6rem;">地址:{{task.pole_address}}</div>
                  </div>
                </a>
              </li>
            </ul>
          </div>
          <!-- 加载提示符-->
          <div class="infinite-scroll-preloader">
              <div class="preloader"></div>
          </div>
  			</div><!-- content-->
  		</div>
</template>
<script>
export default {
  //name: 'app',
  data () {
    return {
      start:0,
      limit:5,
      maxitem:80,//最大就只显示80条
      loading:false,

      taskes:[]
    }
  },

  mounted: function mounted() {
    var vm=this;
    //$.detachInfiniteScroll('#page_taskes_search_list .infinite-scroll');
    $.initScroller();//这个可能只需要运行一次就行了
    $.initInfiniteScroll("#page_taskes_search_list .content");
    $(document).on('infinite', '.infinite-scroll-bottom',function() {
        vm.search(vm.$route.params);
    });
  },
  beforeRouteEnter: function(to, from, next) {

    next(vm => {
      if(to.params.notback){
        vm.start=0;
        //vm.limit=5;
        vm.taskes=[];
        vm.search(vm.$route.params);
      }
    })
  },
  methods:{
    back:function(){
        window.appvue.back();
    },
    search:function(params){
      //console.log(params.name+"======"+params.create_date);
      var vm=this;
      // 如果正在加载，则退出
      if (vm.loading) return;
      // 设置flag
      vm.loading = true;

      params.start=vm.start;
      params.limit=vm.limit;
      $.post($.SP+'/mobile/task/searchTaskes.do', params, function(response){
        if(vm.taskes==null){
          vm.taskes=[];
        }

        for(var i=0;i<response.root.length;i++){
          vm.taskes.push(response.root[i]);
        }
        vm.loading = false;
        vm.start=response.page*response.limit;
        if(response.page==response.totalPages || vm.start>vm.maxitem){
          // 加载完毕，则注销无限加载事件，以防不必要的加载
          $.detachInfiniteScroll($('.infinite-scroll'));
          // 删除加载提示符
          $('.infinite-scroll-preloader').remove();
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
    }
  }
}


</script>
<style>
.infinite-scroll-preloader {
        margin-top:-20px;
      }
</style>
