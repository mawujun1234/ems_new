<template>
  <!-- #page_taskes_search-->
  		<div class="page page-current" id="page_taskes_search">
  			<!-- 标题栏 -->
  			<header class="bar bar-nav">
  				<a class="button button-link button-nav pull-left back" href="#page_taskes"> <span class="icon icon-left"></span>返回
  				</a>
  				<h1 class="title">搜索</h1>
  			</header>



  			<!-- 这里是页面内容区 -->
  			<div class="content">
  			<div class="content-padded">
  			  <div class="searchbar">
  			    <div class="search-input">
  			      <label class="icon icon-search" for="search"></label>
  			      <input type="search" id='search' placeholder='输入关键字...'/>
  			    </div>
  			  </div>
  			</div>

  			<div class="list-block" style="margin-top:0.5rem;">
  			    <ul>
  			      <!-- Text inputs -->
  			      <li>
  			        <div class="item-content">
  			          <div class="item-media"><i class="icon icon-form-name"></i></div>
  			          <div class="item-inner">
  			            <div class="item-title label" style="width:25%;">日期</div>
  			            <div class="item-input">
  			              <input id="my-input" type="text" >
  			            </div>
  			          </div>
  			        </div>
  			      </li>
  			    </ul>
  			  </div><!-- list-block -->
  			  <div class="content-block">
  			    <p><a href="#page_taskes" class="button button-big button-fill ">搜索 </a></p>
  			  </div>
  			</div><!-- content -->
  		</div>
</template>
<script>
export default {
  //name: 'app',
  data () {
    return {

    }
  },
  mounted: function mounted() {
    //do something after mounting vue instance
    $("#my-input").calendar({

    });
  }
}


</script>
<style>
</style>
