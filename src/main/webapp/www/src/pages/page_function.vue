<template>
<!--#page_function  功能列表 -->
  <div class="page" id="page_function">
    <!-- 标题栏 -->
    <header class="bar bar-nav">
      <h1 class="title">功能列表</h1>
    </header>
    <!-- 这里是页面内容区 -->
    <div class="content">
      <div class="content-block-title" style="margin: 10px 0 10px 10px;">任务</div>
      <div class="function_row">
        <a class="function_grid" href="#page_taskes"><div
            class="icon icon-edit"></div>
          <p class="function_grid__label">维修(88)</p>
          <span class="badge">12</span></a> <a class="function_grid"
          href="#page_taskes"><div class="icon icon-browser"></div>
          <p class="function_grid__label">巡检</p></a> <a class="function_grid"
          href="#page_taskes"><div class="icon icon-remove"></div>
          <p class="function_grid__label">取消</p></a> <a class="function_grid"
          href="#page_taskes"><div class="icon icon-code"></div>
          <p class="function_grid__label">盘点</p></a>
          <a class="function_grid" href="#page_taskes">
            <div class="icon icon-app"></div>
            <p class="function_grid__label">新安装</p>
          </a>
          <a class="function_grid" href="#page_message">
            <div class="icon icon-message"></div>
            <p class="function_grid__label">消息</p>
          </a>
        <div style="clear: both;"></div>
      </div>
      <div class="content-block-title" style="margin: 10px 0 10px 10px;">其他</div>
      <div class="function_row">
        <a class="function_grid" href="#page_check_store"><div class="icon icon-code"></div><p class="function_grid__label">仓库盘点</p></a>
        <a class="function_grid" href="#page_equip_info"><div class="icon icon-search"></div><p class="function_grid__label">设备信息</p></a>
        <a class="function_grid" href="#page_check_store"><div class="icon icon-share"></div><p class="function_grid__label">拥有设备</p></a>
      </div>
    </div>
  </div>
  <!--#page_function  功能列表 -->
</template>

<script>
export default {
  //components: { page_login },
  data () {
    return {
      msg: ''
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
    font-size: 14px;
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
