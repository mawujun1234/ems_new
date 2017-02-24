<template>
  <div id="app" class="page-group">
    <page_login></page_login>
    <page_function></page_function>
  </div>
</template>

<script>
import page_login from './pages/page_login.vue'
import page_function from './pages/page_function.vue'
export default {
  name: 'app',
  components: { page_login,page_function},
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
//alert($);
</script>

<style>

</style>
