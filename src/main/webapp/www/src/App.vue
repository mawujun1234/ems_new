<template>
  <div id="app" class="page-group">
    <transition name="slide-fade">
      <router-view></router-view>
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
      transitionName:'slide-left'
    }
  },
  watch: {
    '$route' (to, from) {
      //alert(to.path);
      //alert(from.path);
      const toDepth = to.path.split('/').length
      const fromDepth = from.path.split('/').length
      this.transitionName = toDepth < fromDepth ? 'slide-right' : 'slide-left'
    }
  }
}
//alert($);
</script>

<style>
.fade-enter-active, .fade-leave-active {
  transition: opacity 1.5s ease;
}
.fade-enter, .fade-leave-active {
  opacity: 0
}

.slide-left-enter, .slide-right-leave-active {
  opacity: 1;
  transition: all 5.0s ease 2.5;
  -webkit-transform: translate(100%, 0);
  -moz-transform: translate(100%, 0);
  transform: translate(100%, 0);
}
.slide-left-leave-active, .slide-right-enter {
  opacity: 1;
  transition: all 5.0s ease 2.5;
  -webkit-transform: translate(-100%, 0);
  -moz-transform: translate(-100%, 0);
  transform: translate(-100%, 0);
}
/* 可以设置不同的进入和离开动画 */
/* 设置持续时间和动画函数 */
.slide-fade-enter-active {
  transition: all .3s ease;
}
.slide-fade-leave-active {
  transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slide-fade-enter, .slide-fade-leave-active {
  transform: translateX(100%);
  opacity: 0;
}
</style>
