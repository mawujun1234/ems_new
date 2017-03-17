<template>
  <!-- #page_taskes_search-->
  		<div class="page page-current" id="page_taskes_search">
  			<!-- 标题栏 -->
  			<header class="bar bar-nav">
  				<a class="button button-link button-nav pull-left back" href="javascript:void(0);" @click="back"> <span class="icon icon-left"></span>返回
  				</a>
  				<h1 class="title">搜索</h1>
  			</header>



  			<!-- 这里是页面内容区 -->
  			<div class="content">
  			<div class="content-padded">
  			  <div class="searchbar">
  			    <div class="search-input">
  			      <label class="icon icon-search" for="page_taskes_search_name"></label>
  			      <input type="text" v-model="name" id='page_taskes_search_name' placeholder='点位名称或地址...'/>
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
  			            <div class="item-title label" style="width:4.1rem;">下发日期:</div>
  			            <div class="item-input">
  			              <input id="page_taskes_search_create_date" type="text" v-model="create_date">
  			            </div>
  			          </div>
  			        </div>
  			      </li>
  			    </ul>
  			  </div><!-- list-block -->
  			  <div class="content-block">
  			    <p><a href="javascript:void(0);" :class="classObject " @click="search">搜索 </a></p>
  			  </div>
  			</div><!-- content -->
  		</div>
</template>
<script>
export default {
  //name: 'app',
  data () {
    return {
      type:"",
      name:"",
      create_date:""
    }
  },
  computed: {
    classObject: function () {
      return {
        disabled: !(this.name || this.create_date),
        "button button-big button-fill":true
      }
    }
  },
  mounted: function mounted() {
    //do something after mounting vue instance
    var  vm=this;
    $("#page_taskes_search_create_date").calendar({
      onChange:function(p, values, displayValues){
        vm.create_date=displayValues[0];
      }
    });
  },
  methods:{
    back:function(){
        window.appvue.back();
    },
    search:function(){
      //var name=$("#page_taskes_search_name").val();
      //var create_date=$("#page_taskes_search_create_date").val();
      //window.appvue.$refs.page_taskes_search_list.search(name,create_date);
      //alert(this.$route.params.type);
      window.appvue.to({ name: 'page_taskes_search_list', params: {
          name:this.name,
          create_date:this.create_date,
          notback:true,
          type:this.$route.params.type
        }
      });

    }
  }
}


</script>
<style>
</style>
