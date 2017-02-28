<template>
<!--#page_function  功能列表 -->
  <div class="page page-current" id="page_function">
    <!-- 标题栏 -->
    <header class="bar bar-nav">
      <h1 class="title">功能列表</h1>
    </header>
    <!-- 这里是页面内容区 -->
    <div class="content">
      <div class="content-block-title" style="margin: 10px 0 10px 10px;">任务</div>
      <div class="function_row">
        <a class="function_grid" href="javascript:void(0);" @click="to_page_taskes('repair')" v-if="mobile_page_function_task_repair"><div
            class="icon icon-edit"></div>
          <p class="function_grid__label">维修<span v-if="task_repair.totalnum>0">({{task_repair.totalnum}})</span></p>
          <span class="badge" v-if="task_repair.badgenum>0">{{task_repair.badgenum}}</span></a>
      <a class="function_grid" href="javascript:void(0);" @click="to_page_taskes('patrol')" v-if="mobile_page_function_task_patrol">
        <div class="icon icon-browser"></div>
          <p class="function_grid__label">巡检<span v-if="task_patrol.totalnum>0">({{task_patrol.totalnum}})</span></p>
          <span class="badge" v-if="task_patrol.badgenum>0">{{task_patrol.badgenum}}</span></a>
      <a class="function_grid" href="javascript:void(0);" @click="to_page_taskes('cancel')" v-if="mobile_page_function_task_cancel">
        <div class="icon icon-remove"></div>
          <p class="function_grid__label">取消<span v-if="task_cancel.totalnum>0">({{task_cancel.totalnum}})</span></p>
          <span class="badge" v-if="task_cancel.badgenum>0">{{task_cancel.badgenum}}</span></a>
      <a class="function_grid" href="javascript:void(0);" @click="to_page_taskes('check')" v-if="mobile_page_function_task_check">
        <div class="icon icon-code"></div>
          <p class="function_grid__label">盘点<span v-if="task_check.totalnum>0">({{task_check.totalnum}})</span></p>
          <span class="badge" v-if="task_check.badgenum>0">{{task_check.badgenum}}</span></a>
      <a class="function_grid" href="javascript:void(0);" @click="to_page_taskes('newInstall')" v-if="mobile_page_function_task_newInstall">
          <div class="icon icon-app"></div>
          <p class="function_grid__label">新安装<span v-if="task_newInstall.totalnum>0">({{task_newInstall.totalnum}})</span></p>
          <span class="badge" v-if="task_newInstall.badgenum>0">{{task_newInstall.badgenum}}</span></a>
      <a class="function_grid" href="#page_message" v-if="mobile_page_function_message">
            <div class="icon icon-message"></div>
            <p class="function_grid__label">消息</p>
          </a>
        <div style="clear: both;"></div>
      </div>
      <div class="content-block-title" style="margin: 10px 0 10px 10px;">其他</div>
      <div class="function_row">
        <a class="function_grid" href="#page_check_store" v-if="mobile_page_function_store_check"><div class="icon icon-code"></div><p class="function_grid__label">仓库盘点</p></a>
        <a class="function_grid" href="#page_equip_info" v-if="mobile_page_function_equip_info"><div class="icon icon-search"></div><p class="function_grid__label">设备信息</p></a>
        <a class="function_grid" href="#page_check_store" v-if="mobile_page_function_equip_have"><div class="icon icon-share"></div><p class="function_grid__label">拥有设备</p></a>
      </div>
    </div>
  </div>
  <!--#page_function  功能列表 -->
</template>

<script>
export default {
   data () {
    return {
        mobile_page_function_task_repair:false,
        mobile_page_function_task_patrol:false,
        mobile_page_function_task_cancel:false,
        mobile_page_function_task_check:false,
        mobile_page_function_task_newInstall:false,
        mobile_page_function_message:false,
        mobile_page_function_store_check:false,
        mobile_page_function_equip_info:false,
        mobile_page_function_equip_have:false,
        task_repair:{
          totalnum:1,
          badgenum:1
        },
        task_patrol:{
          totalnum:0,
          badgenum:0
        },
        task_cancel:{
          totalnum:0,
          badgenum:0
        },
        task_check:{
          totalnum:0,
          badgenum:0
        },
        task_newInstall:{
          totalnum:0,
          badgenum:0
        },
      }
    },
    mounted:function(){
      this.queryMobileMenuByUser();
    },
    methods:{
      queryMobileMenuByUser:function(){
        $.showPreloader("正在加载数据....");
        var vue=this;
        //$.SP="http://127.0.0.1:8085";
        $.post($.SP+'/mobile/login/queryMobileMenuByUser.do', {}, function(response){
          var root=response.root;
          for (var x in root) {
            vue.$data[x]=root[x];
          }
          $.hidePreloader();
        });
      },
      to_page_taskes:function(type){
        //alert(type);
        window.appvue.to({ name: 'page_taskes', params: { type: type }});
      }
    }
}
//alert($);
</script>

<style>
.function_row {
	width: 100%;
	margin: 0 auto;
	text-align:center;
	 border-top: 1px solid #D9D9D9;
}
.function_grid {
    position: relative;
    float: left;
    padding: 20px 10px;
    width: 33.33333333%;
    box-sizing: border-box;
    border-right: 1px solid #D9D9D9;
    border-bottom: 1px solid #D9D9D9;
    font-size: 0.7rem;
}

.function_grid:before {
    content: " ";
    position: absolute;
    right: 0;
    top: 0;
    width: 1px;
    bottom: 0;
    border-right: 1px solid #D9D9D9;
    color: #D9D9D9;
    -webkit-transform-origin: 100% 0;
    transform-origin: 100% 0;
    -webkit-transform: scaleX(0.5);
    transform: scaleX(0.5);
}
.function_grid:after {
    content: " ";
    position: absolute;
    left: 0;
    bottom: 0;
    right: 0;
    height: 1px;
    border-bottom: 1px solid #D9D9D9;
    color: #D9D9D9;
    -webkit-transform-origin: 0 100%;
    transform-origin: 0 100%;
    -webkit-transform: scaleY(0.5);
    transform: scaleY(0.5);
    clear:both;
}
.function_grid__label {
    display: block;
    text-align: center;
    color: #000000;
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
    margin:0px;
}
.function_grid .badge {
    position: absolute;
    top: .1rem;
    left: 70%;
    z-index: 100;
    height: .8rem;
    min-width: .8rem;
    padding: 0 .2rem;
    font-size: .6rem;
    line-height: .8rem;
    color: white;
    vertical-align: top;
    background: red;
    border-radius: .5rem;
    margin-left: .1rem;
}
</style>
