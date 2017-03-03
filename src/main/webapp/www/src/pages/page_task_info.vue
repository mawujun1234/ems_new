<template>
  <!-- #page_task_info-->
  		<div class="page page-current" id="page_task_info">
  			<div id="qrcode_button" class="hi-icon-wrap hi-icon-effect-7">
         			<a href="javascript:void(0);"  id="page_task_info_scanQRCode_btn" class="hi-icon hi-icon-cycle external">扫一扫</a>
  			</div>
  			<!-- 标题栏 -->
  			<header class="bar bar-nav">
  				<a class="button button-link button-nav pull-left back" href="javascript:void(0);"  @click="back"> <span class="icon icon-left"></span>返回
  				</a>
  				<h1 class="title">任务信息</h1>
  			</header>
  			<!-- 这里是页面内容区 -->
  			<div class="content">
  				<div class="list-block" style="margin-top:0px;">
  				    <ul>
  				      <!-- Text inputs -->
  				      <li>
  				        <div class="item-content">
  				          <div class="item-media"><i class="icon icon-form-name"></i></div>
  				          <div class="item-inner">
  				            <div class="item-title label">任务编号</div>
  				            <div class="item-input">
  				              <input type="text"  disabled="true" v-model="id">
  				            </div>
  				          </div>
  				        </div>
  				      </li>
  				      <li>
  				        <div class="item-content">
  				          <div class="item-media"><i class="icon icon-form-name"></i></div>
  				          <div class="item-inner">
  				            <div class="item-title label">点位编号</div>
  				            <div class="item-input">
  				              <input type="text"  disabled="true" v-model="pole_code">
  				            </div>
  				          </div>
  				        </div>
  				      </li>
  				      <li>
  				        <div class="item-content">
  				          <div class="item-media"><i class="icon icon-form-name"></i></div>
  				          <div class="item-inner">
  				            <div class="item-title label">点位名称</div>
  				            <div class="item-input">
  				              <input type="text"  disabled="true" v-model="pole_name">
  				            </div>
  				          </div>
  				        </div>
  				      </li>
                <li>
  				        <div class="item-content">
  				          <div class="item-media"><i class="icon icon-form-name"></i></div>
  				          <div class="item-inner">
  				            <div class="item-title label">点位地址</div>
  				            <div class="item-input">
  				              <input type="text"  disabled="true" v-model="pole_address">
  				            </div>
  				          </div>
  				        </div>
  				      </li>

  				      <li class="align-top">
  				        <div class="item-content">
  				          <div class="item-media"><i class="icon icon-form-comment"></i></div>
  				          <div class="item-inner">
  				            <div class="item-title label">任务描述</div>
  				            <div class="item-input">
  				              <textarea>{{memo}}</textarea>
  				            </div>
  				          </div>
  				        </div>
  				      </li>
  				      <li>
  				      	<div class="searchbar row" style="margin-left:0.5rem;">
  						    <div class="search-input col-80">
  						      <input type="search" id='page_task_info_search' placeholder='手工输入条码-->查询'/>
  						    </div>
  						    <a class="button button-fill button-primary col-20">查询</a>
  					  	</div>
  				      </li>

  				    </ul>
  				  </div>

  				  <div class="buttons-tab">
  				    <a href="#page_task_info_equiplist_tab" class="tab-link active button">设备清单</a>
  				    <a href="#page_task_info_members_tab" class="tab-link button">成员</a>
  				  </div>
  				    <div class="tabs">
  				      <div id="page_task_info_equiplist_tab" class="tab active ">
  				      <div class="list-block" >
  				        <ul>
  					  	  <li v-for="equip in equiplist">
  					  	    <a href="#page_equip_info" class="item-link item-content">
  						        <div class="item-media"><i class="icon icon-f7"></i></div>
  						        <div class="item-inner">
  						          <div class="item-title">{{equip.ecode}}</div>
  								      <div class="item-after">{{equip.subtype_name}}</div>
  						        </div>
  					        </a>
  					        <a href="javascript:;" class="remove">删除</a>
  					      </li>
  					    </ul>
  					  </div>
  				      </div>
  				      <div id="page_task_info_members_tab" class="tab">
  				        <div class="list-block" >
  					        <ul>
  						  	  <li v-for="member in members">
  						  	    <a href="#page_equip_info" class="item-link item-content">
  							        <div class="item-media"><i class="icon icon-f7"></i></div>
  							        <div class="item-inner">
  							          <div class="item-title">{{member.name}}</div>
  									      <div class="item-after">{{member.workunit_name}}</div>
  							        </div>
  						        </a>
  						        <a href="javascript:;" class="remove">删除</a>
  						      </li>
  						    </ul>
  						 </div>
  				      </div><!-- page_task_info_member_tab -->
  				    </div>

  				  <div class="content-block">
  				    <div class="row">
  				      <div class="col-50"><a href="#" class="button button-big button-fill button-danger back">返回</a></div>
  				      <div class="col-50"><a href="#" class="button button-big button-fill button-success">提交</a></div>
  				    </div>
  				  </div>
  			</div>
  		</div>
  		<!--#page_task_info  功能列表 -->
</template>
<script>
export default {
  //name: 'app',
  data () {
    return {
      id:'',
      memo:'',
      pole_code:'',
      pole_name:'',
      pole_address:'',
      equiplist:[],
      members:[]
    }
  },
  beforeRouteEnter  (to, from, next) {
    next(vm => {
      // 通过 `vm` 访问组件实例
      vm.id=to.params.task_id;
      vm.getTask();
    });
  },
  mounted:function(){
    /**
    //扫描的设备的清单，左划，出现删除按钮
    $("#page_task_info_equiplist_tab li").on("swipeLeft",function(){
      $(this).siblings().removeClass("swipeLeft");
      $(this).addClass("swipeLeft");
    }).on("swipeRight",function(){
      $(this).removeClass("swipeLeft");
    });

    $("#page_task_info_equiplist_tab li").on("dragstart",function(){
      if($(this).hasClass("swipeLeft")){
        $(this).removeClass("swipeLeft");
      } else {
        $(this).siblings().removeClass("swipeLeft");
        $(this).addClass("swipeLeft");
      }
    });
    **/

  },
  methods: {
    getTask: function() {
      var vue=this;
      $.showPreloader("正在取数....");
      $.post($.SP+'/mobile/task/getTask.do', {id:this.id}, function(response){
        var root=response.root;
        for (var x in root) {
          vue.$data[x]=root[x];
        }
        $.hidePreloader();
      });
    },
    back:function(){
      window.appvue.to({ name: 'page_taskes',
        params: {
          back:true
        }
      });
    }
  }
}
</script>
<style>
#page_task_info .tabs .tab .list-block{
	margin-top:0px;
}
#page_task_info .tabs .tab .item-after {
	font-size:0.5rem;
	margin-right:0px;
}
#page_task_info .tabs .tab li {
	display:flex;
	flex-flow:row;
	overflow:hidden;
	transition:all 0.3s linear;
	-webkit-transition:all 0.3s linear;
}
#page_task_info .tabs .tab li a.item-link{
	flex:1;
}
#page_task_info .tabs .tab li a.remove{
	width:3rem;
	line-height:230%;
	display:block;
	background-color: #ff0000;
	color: #fff;
	text-align: center;
	text-decoration:none;
	margin-right:-2.8rem;
	border-bottom:1px solid #989898;;
}
#page_task_info .tabs .tab li.swipeLeft {
	transform:translateX(-3rem);
	-webkit-transform:translateX(-3rem);
	overflow:visible;
}

#page_equip_info .content .item-title.label {
	width:30%;
}
</style>
