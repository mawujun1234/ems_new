<template>
  <div id="app" class="page-group">
    <nav class="bar bar-tab" id="bottom_bar"
      style="z-index: 6666; display:none;">
      <a class="tab-item active" href="javascript:void(0);" @click="to_page_function"
        id="bottom_bar_page_function"> <span class="icon icon-home"></span>
        <span class="tab-label">功能</span>
      </a> <a class="tab-item" href="javascript:void(0);" @click="to_page_setting" id="bottom_bar_page_setting">
        <span class="icon icon-settings"></span> <span class="tab-label">我的</span>
      </a>
    </nav>
    <transition :name="transitionName">
      <keep-alive include="page_taskes">
      <router-view></router-view>
      </keep-alive>
    </transition>
  </div>
</template>

<script>
//import page_login from './pages/page_login.vue'
//import page_function from './pages/page_function.vue'
export default {
  name: 'app',
  //components: {page_login},
  data () {
    return {
      transitionName:'slide-right'
    }
  },
  watch: {
    '$route' (to, from) {
      this.transitionName=this.$router.transitionName;
    }
  },
  methods:{
    to_page_function:function(){
      $("#bottom_bar_page_function").addClass("active");
      $("#bottom_bar_page_setting").removeClass("active");
      window.appvue.to({ name: 'page_function',
        params: {
        }
      });
    },
    to_page_setting:function(){
      $("#bottom_bar_page_function").removeClass("active");
      $("#bottom_bar_page_setting").addClass("active");
      window.appvue.to({ name: 'page_setting',
        params: {
        }
      });
    }
  }
}
//alert($);
</script>

<style>
/* 可以设置不同的进入和离开动画 */
/* 设置持续时间和动画函数 */
.slide-right-enter-active,.slide-left-enter-active {
  transition: all .4s ease;
}
.slide-right-leave-active,.slide-left-leave-active {
  transition: all .4s ease;
  opacity: 0;
}
.slide-right-enter,.slide-left-leave-active  {
  transform: translateX(-100%);
  opacity: 0;
}
.slide-right-leave-active,.slide-left-enter  {
  transform: translateX(100%);
  opacity: 0;
}
</style>
