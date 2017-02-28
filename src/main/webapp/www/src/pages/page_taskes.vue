<template>
<!-- #page_taskes-->
<div class="page page-current" id="page_taskes">
  <!-- 标题栏 -->
  <header class="bar bar-nav">
    <router-link class="button button-link button-nav pull-left back" to="/page_function"> <span class="icon icon-left"></span>返回
    </router-link>
    <a class="icon icon-search pull-right" href="#page_search"></a>
    <h1 class="title">任务列表</h1>
  </header>



  <!-- 这里是页面内容区 -->
  <div class="content">
    <div class="buttons-tab fixed-tab" data-offset="45">
      <a href="#page_taskes_all" @click="queryTaskes('all')" class="tab-link active button">全部<span>({{nums.all_num}})</span></a>
      <a href="#page_taskes_newTask" @click="queryTaskes('newTask')" class="tab-link button">新任务<span>({{nums.newTask_num}})</span></a>
      <a href="#page_taskes_read" @click="queryTaskes('read')" class="tab-link button">已阅<span>({{nums.read_num}})</span></a>
      <a href="#page_taskes_handling" @click="queryTaskes('handling')" class="tab-link button">处理中<span>({{nums.handling_num}})</span></a>
      <a href="#page_taskes_submited" @click="queryTaskes('submited')" class="tab-link button">已提交<span>({{nums.submited_num}})</span></a>
    </div>

    <div class="tabs">
      <div id="page_taskes_all" class="tab active">
        <page_taskes_list></page_taskes_list>
      </div>
      <div id="page_taskes_newTask" class="tab">

      </div>
      <div id="page_taskes_read" class="tab">
        <div class="content-block">
          <p>This is tab 3 content</p>
        </div>
      </div>
      <div id="page_taskes_handling" class="tab">
        <div class="content-block">
          <p>处理中的任务</p>
        </div>
      </div>
      <div id="page_taskes_submited" class="tab">
        <p>已提交任务</p>
      </div>
    </div>

  </div>
  <!-- 这里是页面内容区 -->
</div>
<!--#page_taskes  功能列表 -->
</template>

<script>


export default {
  data () {
    return {
      type:'',
      nums:{
        all_num:0,
        newTask_num:0,
        read_num:0,
        handling_num:0,
        submited_num:0
      }
    }
  },
  beforeRouteEnter  (to, from, next) {
    // 在当前路由改变，但是改组件被复用时调用
    // 举例来说，对于一个带有动态参数的路径 /foo/:id，在 /foo/1 和 /foo/2 之间跳转的时候，
    // 由于会渲染同样的 Foo 组件，因此组件实例会被复用。而这个钩子就会在这个情况下被调用。
    // 可以访问组件实例 `this`
    alert(to.params.type);
    next(vm => {
      // 通过 `vm` 访问组件实例
      vm.type=to.params.type;
      //alert("vm.type:"+vm.type);
      vm.queryTaskes("all");
    });
  },
  methods:{
    queryTaskes:function(status){
      alert(status);
      //$.post($.SP+'/mobile/task/queryTaskes.do',{type:this.type,status:status},function(response){

      //});
    }
  },
  watch: {
    '$route' (to, from) {
      // 对路由变化作出响应...
      //alert(to.params.type);
      //this.type=to.params.type;
    //  queryTaskes('all');
    }
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
