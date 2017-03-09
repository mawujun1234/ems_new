<template>
  <!-- #page_task_info-->
  		<div class="page page-current" id="page_task_info" >
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
  				        <div class="item-content">
  				          <div class="item-media"><i class="icon icon-form-name"></i></div>
  				          <div class="item-inner">
  				            <div class="item-title label">故障类型</div>
  				            <div class="item-input">
  				              <input type="text" id="page_task_info_hitchType_name"  v-model="hitchType_name" >
  				            </div>
  				          </div>
  				        </div>
  				      </li>
                <li>
  				        <div class="item-content">
  				          <div class="item-media"><i class="icon icon-form-name"></i></div>
  				          <div class="item-inner">
  				            <div class="item-title label">原因模板</div>
  				            <div class="item-input">
  				              <input type="text" id="page_task_info_hitchReasonTpl_name" v-model="hitchReasonTpl_name" >
  				            </div>
  				          </div>
  				        </div>
  				      </li>
                <li class="align-top">
  				        <div class="item-content">
  				          <div class="item-media"><i class="icon icon-form-comment"></i></div>
  				          <div class="item-inner">
  				            <div class="item-title label">故障原因</div>
  				            <div class="item-input">
  				              <textarea v-model="hitchReason" @blur="updateHitchReason"></textarea>
  				            </div>
  				          </div>
  				        </div>
  				      </li>

                <li>
  				        <div class="item-content">
  				          <div class="item-media"><i class="icon icon-form-name"></i></div>
  				          <div class="item-inner">
  				            <div class="item-title label">处理方法</div>
  				            <div class="item-input">
  				              <input type="text" id="page_task_info_handleMethod_name" v-model="handleMethod_name" >
  				            </div>
  				          </div>
  				        </div>
  				      </li>
                <li class="align-top">
  				        <div class="item-content">
  				          <div class="item-media"><i class="icon icon-form-comment"></i></div>
  				          <div class="item-inner">
  				            <div class="item-title label">处理备注</div>
  				            <div class="item-input">
  				              <textarea v-model="handle_contact" @blur="updateHandleContact"></textarea>
  				            </div>
  				          </div>
  				        </div>
  				      </li>
<!--
  				      <li>
  				      	<div class="searchbar row" style="margin-left:0.5rem;">
  						    <div class="search-input col-80">
  						      <input type="search" id='page_task_info_search' placeholder='手工输入条码查询'/>
  						    </div>
  						    <a class="button button-fill button-primary col-20">查询</a>
  					  	</div>
  				      </li>
-->
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
  					  	    <a href="javascript:void(0);" class="item-link item-content" @click="show_popup_equip_info" :ecode="equip.ecode">
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

        <equip_info ref="equip_info"></equip_info>
        <hitchtype ref="hitchtype"></hitchtype>
        <handleMethod ref="handleMethod"></handleMethod>
        <ball_navs ref="ball_navs"></ball_navs>
        <popup_members ref="popup_members"></popup_members>
  		</div>
  		<!--#page_task_info  功能列表 -->
</template>
<script>

import equip_info from '../components/equip_info.vue'
import hitchtype from '../components/hitchtype.vue'
import handleMethod from '../components/handleMethod.vue'
import ball_navs from '../components/ball_navs.vue'
import popup_members from '../components/popup_members.vue'

export default {
  //name: 'app',
  data () {
    return {
      id:'',
      type:'',
      memo:'',
      pole_code:'',
      pole_name:'',
      pole_address:'',
      hitchType_id:'',
      hitchType_name:'',
      hitchReasonTpl_id:'',
      hitchReasonTpl_name:'',
      hitchReason:'',
      handleMethod_id:'',
      handleMethod_name:'',
      handle_contact:'',
      equiplist:[],
      members:[]

    }
  },
  components:{
    equip_info,hitchtype,handleMethod,ball_navs,popup_members
  },
  beforeRouteEnter  (to, from, next) {
    next(vm => {
      // 通过 `vm` 访问组件实例
      vm.id=to.params.task_id;
      vm.getTask();
    });
  },
  mounted:function(){
    var vm=this;
    /**
    $("#page_task_info").on('click','.popup-all', function () {
      vm.z_index=$("#page_task_info").css("z-index");
      $("#page_task_info").css("z-index",20000);
      vm.$refs.equip_info.getEquipinfo($(this).attr('ecode'));
      $.popup('.popup-equip_info');
    });
    **/
    $("#page_task_info .popup-equip_info").on("close",function(){
      $("#page_task_info").css("z-index",vm.z_index);
    });

    //--------------------------------故障模板和故障原因
    $("#page_task_info_hitchType_name,#page_task_info_hitchReasonTpl_name").click(function(){
      vm.z_index=$("#page_task_info").css("z-index");
      $("#page_task_info").css("z-index",20000);
      $.popup('.popup-hitchtype');
    });
    $("#page_task_info .popup-hitchtype").on("close",function(){
      $("#page_task_info").css("z-index",vm.z_index);
    });
    vm.$refs.hitchtype.$on('selectHitchtype', function (ht_id,ht_name,tpl_id,tpl_name) {
      vm.hitchType_id=ht_id;
      vm.hitchType_name=ht_name;
      vm.hitchReasonTpl_id=tpl_id;
      vm.hitchReasonTpl_name=tpl_name;
      vm.hitchReason=tpl_name;
      //更新任务的故障类型和故障原因
      $.post($.SP+'/mobile/task/updateTaskHitchtype.do',
        {
          id:vm.id,
          hitchType_id:ht_id,
          //hitchType_name:ht_name,
          hitchReasonTpl_id:tpl_id,
          hitchReason:tpl_name
        },function(response){

        });
    });

    //------------------------------------------处理方法
    $("#page_task_info_handleMethod_name").click(function(){
      vm.z_index=$("#page_task_info").css("z-index");
      $("#page_task_info").css("z-index",20000);
      $.popup('.popup-handleMethod');
    });
    $("#page_task_info .popup-handleMethod").on("close",function(){
      $("#page_task_info").css("z-index",vm.z_index);
    });
    vm.$refs.handleMethod.$on('selectHandleMethod', function (ht_id,ht_name) {
      vm.handleMethod_id=ht_id;
      vm.handleMethod_name=ht_name;
      //更新任务的故障类型和故障原因
      $.post($.SP+'/mobile/task/updateHandleMethod.do',
        {
          id:vm.id,
          handleMethod_id:ht_id
        },function(response){

        });
    });

    this.initevent();
  },
  updated:function(){
    //this.initevent();
    //console.log(1111111);

  },
  methods: {
    getTask: function() {
      var vue=this;
      $.showPreloader("正在取数....");
      $.post($.SP+'/mobile/task/getMobileTaskVO.do', {id:this.id}, function(response){
        var root=response.root;
        //root.handle_contact='2222';
        for (var x in root) {
          vue.$data[x]=root[x];
        }

        $.hidePreloader();
        //initevent();
      });
    },
    back:function(){
      window.appvue.to({ name: 'page_taskes',
        params: {
          back:true,
          type:this.type,
        }
      });
    },//back
    show_popup_equip_info:function(){
      var vm=this;
      vm.z_index=$("#page_task_info").css("z-index");
      $("#page_task_info").css("z-index",20000);
      vm.$refs.equip_info.getEquipinfo($(this).attr('ecode'));
      $.popup('.popup-equip_info');
    },
    initevent:function(){
      /****/
      //扫描的设备的清单，左划，出现删除按钮
      $("#page_task_info_equiplist_tab").on("swipeLeft","li",function(){
        $(this).siblings().removeClass("swipeLeft");
        $(this).addClass("swipeLeft");
      }).on("swipeRight","li",function(){
        $(this).removeClass("swipeLeft");
      });
      //鼠标经过的时候,兼容桌面程序
      $("#page_task_info_equiplist_tab").on("mouseover","li",function(){
        $(this).siblings().removeClass("swipeLeft");
        $(this).addClass("swipeLeft");
      }).on("mouseout","li",function(){
        $(this).removeClass("swipeLeft");
      });

    },//initevent
    updateHitchReason:function(){
      var vm=this;
      $.post($.SP+'/mobile/task/updateHitchReason.do',
        {
          id:vm.id,
          hitchReason:vm.hitchReason
        },function(response){

        });
    },
    updateHandleContact:function(){
      var vm=this;
      $.post($.SP+'/mobile/task/updateHandleContact.do',
        {
          id:vm.id,
          handle_contact:vm.handle_contact
        },function(response){

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
