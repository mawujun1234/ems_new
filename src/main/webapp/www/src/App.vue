<template>
  <div id="app" class="page-group">
    <transition :name="transitionName">
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
      transitionName:'fade'
    }
  },
  watch: {
    '$route' (to, from) {
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
  opacity: 0;
  -webkit-transform: translate(30px, 0);
  transform: translate(30px, 0);
}
.slide-left-leave-active, .slide-right-enter {
  opacity: 0;
  -webkit-transform: translate(-30px, 0);
  transform: translate(-30px, 0);
}
</style>
