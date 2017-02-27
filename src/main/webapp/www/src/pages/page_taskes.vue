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
      <a href="#page_taskes_all" class="tab-link active button">全部<span>(88)</span></a>
      <a href="#page_taskes_newTask" class="tab-link button">新任务<span>(1)</span></a>
      <a href="#page_taskes_read" class="tab-link button">已阅<span>(0)</span></a>
      <a href="#page_taskes_handling" class="tab-link button">处理中<span>(2)</span></a>
      <a href="#page_taskes_submited" class="tab-link button">已提交<span>(4)</span></a>
    </div>

    <div class="tabs">
      <div id="page_taskes_all" class="tab active">
        <div class="list-block media-list" >
          <ul>
            <li>
              <a href="#page_task_info" class="item-link item-content">
                <div class="item-inner">
                  <div class="item-title-row">
                    <div class="item-title">20161114-0001</div>
                    <div class="item-after">2016-11-14 17:14</div>
                  </div>
                  <div class="item-subtitle">231003:姚江村委会</div>
                  <div class="item-text">地址:浙江省宁波市鄞州区桃江村委会后面</div>
                </div>
              </a>
            </li>
            <li>
              <a href="#page_task_info" class="item-link item-content">
                <div class="item-inner">
                  <div class="item-title-row">
                    <div class="item-title">20161114-0001</div>
                    <div class="item-after">2016-11-14 17:14</div>
                  </div>
                  <div class="item-subtitle">231003:姚江村委会</div>
                  <div class="item-text">地址:浙江省宁波市鄞州区桃江村委会后面</div>
                </div>
              </a>
            </li>
          </ul>
        </div>
      </div>
      <div id="page_taskes_newTask" class="tab">
        <div class="content-block">
          <p>This is tab 2 content</p>
        </div>
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
