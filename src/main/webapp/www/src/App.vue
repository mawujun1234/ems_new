<template>
  <div id="app" class="page-group">
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
      var toDepth =0;
      var fromDepth =0;
      if(to.path!="/"){
        toDepth=to.path.split('/').length;
      }
      if(from.path!="/"){
        fromDepth=from.path.split('/').length;
      }

      this.transitionName = toDepth < fromDepth ? 'slide-right' : 'slide-left';
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
