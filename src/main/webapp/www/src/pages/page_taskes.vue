<template>
<!-- #page_taskes-->
<div class="page page-current" id="page_taskes">
  <!-- 标题栏 -->
  <header class="bar bar-nav">
    <router-link class="button button-link button-nav pull-left back" to="/page_function"> <span class="icon icon-left"></span>返回
    </router-link>
    <a class="icon icon-search pull-right" href="javascript:void(0);" @click="to_page_taskes_search"></a>
    <h1 class="title">任务列表</h1>
  </header>



  <!-- 这里是页面内容区 -->
  <div class="content infinite-scroll infinite-scroll-bottom" data-distance="50">

    <div class="buttons-tab fixed-tab" data-offset="45">
      <a id="a_page_taskes_newTask" href="#page_taskes_newTask" @click="showTasklist('newTask',false)" class="tab-link button active ">新任务<span>({{newTask_num}})</span></a>
      <a href="#page_taskes_read" @click="showTasklist('read',false)" class="tab-link button">已阅<span>({{read_num}})</span></a>
      <a href="#page_taskes_handling" @click="showTasklist('handling',false)" class="tab-link button">处理中<span>({{handling_num}})</span></a>
      <a href="#page_taskes_submited" @click="showTasklist('submited',false)" class="tab-link button">已提交<span>({{submited_num}})</span></a>
    </div>

    <div class="tabs">
      <div id="page_taskes_newTask" class="tab active">
        <newTask_taskes_list ref="newTask_taskes_list"></newTask_taskes_list>
      </div>
      <div id="page_taskes_read" class="tab">
        <read_taskes_list ref="read_taskes_list"></read_taskes_list>
      </div>
      <div id="page_taskes_handling" class="tab">
        <handling_taskes_list ref="handling_taskes_list"></handling_taskes_list>
      </div>
      <div id="page_taskes_submited" class="tab">
        <submited_taskes_list ref="submited_taskes_list"></submited_taskes_list>
      </div>
    </div>
    <!-- 加载提示符-->
    <div class="infinite-scroll-preloader taskes-list" style="margin-top:0px;">
        <div class="preloader"></div>
    </div>
  </div>
  <!-- 这里是页面内容区 -->
</div>
<!--#page_taskes  功能列表 -->
</template>

<script>
import taskes_list from '../components/taskes_list.vue'
import newTask_taskes_list from '../components/newTask_taskes_list.vue'
import read_taskes_list from '../components/read_taskes_list.vue'
import handling_taskes_list from '../components/handling_taskes_list.vue'
import submited_taskes_list from '../components/submited_taskes_list.vue'

export default {

  data () {
    return {
      type:'',
      notback:true,
      status:''//当前查询的状态
      /*nums:{
        ,newTask_num:0,
        read_num:0,
        handling_num:0,
        submited_num:0
}*/
    }
  },
  components:{
    newTask_taskes_list,read_taskes_list,handling_taskes_list,submited_taskes_list
  },
  computed: {
    newTask_num:function(){
      if(!this.type){
        return 0;
      }
      return this.$store.state['task_'+this.type].newTask_num;
    },
    read_num:function(){
      if(!this.type){
        return 0;
      }
      return this.$store.state['task_'+this.type].read_num;
    },
    handling_num:function(){
      if(!this.type){
        return 0;
      }
      return this.$store.state['task_'+this.type].handling_num;
    },
    submited_num:function(){
      if(!this.type){
        return 0;
      }
      return this.$store.state['task_'+this.type].submited_num;
    }
  },
  mounted: function() {
    var vm=this;
    //do something after mounting vue instance
    $.initScroller();//这个可能只需要运行一次就行了

    //$.initInfiniteScroll("#page_taskes .content");
    $(document).on('infinite', '#page_taskes .infinite-scroll-bottom',function() {
        //vm.search(vm.$route.params);
      //alert(111);
      //vm.isinfinite=true;
      vm.showTasklist(vm.status,true);
    });
  },
  beforeRouteEnter (to, from, next) {
    next(vm => {
      if(to.params.notback){
        // 通过 `vm` 访问组件实例
        //vm.isinfinite=false;
        vm.type=to.params.type;
        vm.notback=to.params.notback;
        /*vm.nums.newTask_num=to.params.newTask_num;
        vm.nums.read_num=to.params.read_num;
        vm.nums.handling_num=to.params.handling_num;
        vm.nums.submited_num=to.params.submited_num;
        */
        //alert("vm.type:"+vm.type);
        //刚进来的时候，初始化界面数据
        //vm.status=newTask;
        //vm.showTasklist("newTask");
        $("#a_page_taskes_newTask").trigger('click',"newTask",false);
      }
    });
  },
  methods:{
    to_page_taskes_search:function(){
      window.appvue.to({ name: 'page_taskes_search',
        params: {
          type:this.type
        }
      });
    },
    showTasklist:function(status,isinfinite){
      this.status=status;

      var child_name=status+"_taskes_list";
      this.$refs[child_name].refreshlist(this.type,status,isinfinite);
      //$.initPullToRefresh(".pull-to-refresh-content");
      //alert(1);
      //alert(status);

    }
  },
  watch: {
    //'$route' (to, from) {
      // 对路由变化作出响应...kkkk
      //alert(to.params.notback);
      //this.type=to.params.type;
    //  queryTaskes('all');
    //}
  }
}
//alert($);
</script>

<style>
#page_taskes .buttons-tab .tab-link {
	font-size: 0.5rem;
}
#page_taskes .buttons-tab .tab-link span {
	font-size: 0.5rem;
}
#page_taskes .buttons-tab .tab-link.active {
	font-size:0.7rem;
}

#page_taskes .tabs .tab .list-block {
	margin: 0px;
}

#page_taskes .tabs .tab .list-block .item-after {
	font-size: 0.6rem;
	padding-top:0.2rem;
}
#page_taskes .tabs .tab .list-block .item-subtitle {
	font-size: 0.6rem;
}
#page_taskes .tabs .tab .list-block .item-text {
	font-size: 0.6rem;
	height: 2rem;
}

</style>
