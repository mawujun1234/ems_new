<template>
  <div class="page page-current" id="page_show_photo" >
    <header class="bar bar-nav">
      <a class="button button-link button-nav pull-left back" href="javascript:void(0);"  @click="back"> <span class="icon icon-left"></span>返回
      </a>
      <h1 class="title">点位照片</h1>
    </header>
    <div class="content" >

    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {

    }
  },
  methods: {
    back:function(){
      window.appvue.back();
    }
  }
}
</script>
<style>
</style>
