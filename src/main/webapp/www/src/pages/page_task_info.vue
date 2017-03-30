<template>
  <!-- #page_task_info-->
  		<div class="page page-current" id="page_task_info" >
  			<!--<div id="qrcode_button" class="hi-icon-wrap hi-icon-effect-7">
         			<a href="javascript:void(0);"  id="page_task_info_scanQRCode_btn" class="hi-icon hi-icon-cycle external">扫一扫</a>
  			</div>-->
  			<!-- 标题栏 -->
  			<header class="bar bar-nav">
  				<a class="button button-link button-nav pull-left back" href="javascript:void(0);"  @click="back"> <span class="icon icon-left"></span>返回
  				</a>
  				<h1 class="title">任务信息</h1>
  			</header>
  			<!-- 这里是页面内容区 -->
  			<div class="content" :style="canedit?'margin-bottom:3rem;':''">
  				<div class="list-block" style="margin-top:0px;">
  				    <ul>
  				      <!-- Text inputs -->
  				      <li>
  				        <div class="item-content">
  				          <div class="item-media"><i class="icon icon-form-name"></i></div>
  				          <div class="item-inner">
  				            <div class="item-title label">任务编号:</div>
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
  				            <div class="item-title label">状态:</div>
  				            <div class="item-input">
  				              <input type="text"  disabled="true" v-model="status_name">
  				            </div>
  				          </div>
  				        </div>
  				      </li>
  				      <li>
  				        <div class="item-content">
  				          <div class="item-media"><i class="icon icon-form-name"></i></div>
  				          <div class="item-inner">
  				            <div class="item-title label">点位编号:</div>
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
  				            <div class="item-title label">点位名称:</div>
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
  				            <div class="item-title label">点位地址:</div>
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
  				            <div class="item-title label">任务描述:</div>
  				            <div class="item-input">
  				              <textarea>{{memo}}</textarea>
  				            </div>
  				          </div>
  				        </div>
  				      </li>

                <template v-if="type=='repair'">
                <li>
  				        <div class="item-content">
  				          <div class="item-media"><i class="icon icon-form-name"></i></div>
  				          <div class="item-inner">
  				            <div class="item-title label">故障类型*:</div>
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
  				            <div class="item-title label">原因模板*:</div>
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
  				            <div class="item-title label">故障原因*:</div>
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
  				            <div class="item-title label">处理方法*:</div>
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
  				            <div class="item-title label">处理备注*:</div>
  				            <div class="item-input">
  				              <textarea v-model="handle_contact" @blur="updateHandleContact"></textarea>
  				            </div>
  				          </div>
  				        </div>
  				      </li>
                </template>
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
  					  	  <li v-for="equip in equiplist" :style="equip.install_type=='uninstall'?'color:red':(equip.install_type=='install'?'color:green':'')" class="item-link item-content" @click="show_popup_equip_info(equip.ecode)" >
  						        <div class="item-media"><i class="icon icon-f7"></i></div>
  						        <div class="item-inner">
  						          <div class="item-title">{{equip.ecode}}
                          <span style="font-size:0.5rem;" v-if="equip.install_type=='uninstall'">(卸)</span>
                          <span style="font-size:0.5rem;" v-if="equip.install_type=='install'">(装)</span>
                          <span style="font-size:0.5rem;" v-if="equip.install_type=='patrol'">(扫)</span>
                        </div>
  								      <div class="item-after">{{equip.subtype_name}}</div>
  						        </div>
  					        <a href="javascript:void(0);" v-if="canedit" class="remove"  @click.stop.prevent="delete_equip_info(equip.ecode)">删除</a>
  					      </li>
  					    </ul>
  					  </div>
  				      </div>
  				      <div id="page_task_info_members_tab" class="tab">
  				        <div class="list-block" >
  					        <ul>
  						  	  <li v-for="member in members" class="item-link item-content">
  							        <div class="item-media"><i class="icon icon-f7"></i></div>
  							        <div class="item-inner">
  							          <div class="item-title">{{member.name}}</div>
  									      <div class="item-after">{{member.workunit_name}}</div>
  							        </div>
  						        <a href="javascript:void(0);" v-if="canedit" class="remove" @click.stop.prevent="delete_member(member.id)">删除</a>
  						      </li>
  						    </ul>
  						 </div>
  				      </div><!-- page_task_info_member_tab -->
  				    </div>


  			</div>
        <div v-if="canedit" style="position:fixed;bottom:10px;left:0px;right:20px;width:90%;z-index:666;margin:0 auto;">
          <div class="row" >
            <div class="col-50"><a href="javascript:void(0);" @click="show_qrcode_camare" class="button button-big button-fill button-danger">扫一扫</a></div>
            <div class="col-50"><a href="javascript:void(0);" @click="submit" class="button button-big button-fill button-success">提交</a></div>
          </div>
        </div>
        <equip_info ref="equip_info"></equip_info>
        <hitchtype ref="hitchtype"></hitchtype>
        <handleMethod ref="handleMethod"></handleMethod>
        <ball_navs ref="ball_navs"></ball_navs>
        <popup_members ref="popup_members" v-on:selectMember="add_member"></popup_members>
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
      status:'',
      status_name:'',
      canedit:false,
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
    //$("#page_task_info_hitchType_name,#page_task_info_hitchReasonTpl_name").click(function(){
    $("#page_task_info").on('click','#page_task_info_hitchType_name,#page_task_info_hitchReasonTpl_name', function () {
      //alert(vm.status);
      if(!vm.canedit){
        return;
      }
      vm.z_index=$("#page_task_info").css("z-index");
      $("#page_task_info").css("z-index",20000);
      $.popup('.popup-hitchtype');
      window.popup_class='.popup-hitchtype';
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
    //$("#page_task_info_handleMethod_name").click(function(){
    $("#page_task_info").on('click','#page_task_info_handleMethod_name', function () {
      if(!vm.canedit){
        return;
      }
      vm.z_index=$("#page_task_info").css("z-index");
      $("#page_task_info").css("z-index",20000);
      $.popup('.popup-handleMethod');
      window.popup_class='.popup-handleMethod';
    });
    $("#page_task_info .popup-handleMethod").on("close",function(){
      $("#page_task_info").css("z-index",vm.z_index);
    });
    vm.$refs.handleMethod.$on('selectHandleMethod', function (ht_id,ht_name) {
      vm.handleMethod_id=ht_id;
      vm.handleMethod_name=ht_name;
      //更新任务的故障类型和故障原因
      $.post($.SP+'/mobile/task/updateHandleMethod.do',{
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
      window.appvue.back();
    },//back
    show_qrcode_camare:function(){//扫描二维码
      if(!$.isMobile()){
          $.toast("请在手机上操作");
          return;
      }
      let vm=this;

      cordova.plugins.barcodeScanner.scan(
        function (result) {
          if(result.cancelled){
            return;
          }
          if(result.text.indexOf("member:")==-1){
            vm.scan_qrcode(result.text);
          } else {
            vm.add_member(result.text.substr(7));
          }
            // alert("We got a barcode\n" +
            //       "Result: " + result.text + "\n" +
            //       "Format: " + result.format + "\n" +
            //       "Cancelled: " + result.cancelled);
        },
        function (error) {
            alert("扫描失败: " + error);
        },
        {
            preferFrontCamera : false, // iOS and Android
            showFlipCameraButton : true, // iOS and Android
            showTorchButton : true, // iOS and Android
            torchOn: false, // Android, launch with the torch switched on (if available)
            //prompt : "Place a barcode inside the scan area", // Android
            resultDisplayDuration: 500, // Android, display scanned text for X ms. 0 suppresses it entirely, default 1500
            formats : "QR_CODE,PDF_417", // default: all but PDF_417 and RSS_EXPANDED
            orientation : "portrait", // Android only (portrait|landscape), default unset so it rotates with the device
            disableAnimations : true, // iOS
            disableSuccessBeep: false // iOS
        }
     );
    },
    scan_qrcode:function(ecode){
      var vm=this;
      //var parent=vm.$parent;
      if(!vm.canedit){
        $.toast("任务已提交,不准添加！");
        return;
      }


      $.post($.SP+'/mobile/task/scanEquip_info.do',{
          ecode:ecode,
          task_id:this.id
        },function(response){

          if(response.root){
            vm.equiplist.push(response.root);
          }
          //$("#page_task_info_ecode_input").val("");
          $.closeModal(".popup-qrcode-input");
          vm.update_install_type(ecode,response.root.install_type);
      });
    },
    update_install_type:function(ecode,install_type){
      var vm=this;
      if(install_type=='uninstall' && vm.type=='repair'){
        //当任务是维修类型，并且扫描返回的设备是卸载的时候，就给出提示，是不是要卸载
        let r=confirm("是否拆卸该设备？？如果不需要，请点击'取消'");
        //let install_type=null;
        if(!r){
          $.post($.SP+'/mobile/task/update_install_type.do',{
              ecode:ecode,
              task_id:vm.id,
              install_type:'patrol'
            },function(response){
              vm.equiplist[vm.equiplist.length-1].install_type='patrol';
          });
        }
      }

    },
    submit:function(){
      if(!this.canedit){
        return;
      }
      var vue=this;
      $.showPreloader("正在提交....");
      $.post($.SP+'/mobile/task/submit.do', {task_id:this.id}, function(response){
        //var root=response.root;
        if(response.success){
          window.appvue.back();
        }

        $.hidePreloader();
      });
    },
    show_popup_equip_info:function(ecode){
      var vm=this;
      vm.z_index=$("#page_task_info").css("z-index");
      $("#page_task_info").css("z-index",20000);
      vm.$refs.equip_info.getEquipinfo(ecode);
      $.popup('.popup-equip_info');
      window.popup_class='.popup-equip_info';
    },
    initevent:function(){
      var vm=this;
      /****/
      //扫描的设备的清单，左划，出现删除按钮
      $("#page_task_info_equiplist_tab,#page_task_info_members_tab").on("swipeLeft","li",function(){
        if(!vm.canedit){
          return;
        }
        $(this).siblings().removeClass("swipeLeft");
        $(this).addClass("swipeLeft");
      }).on("swipeRight","li",function(){
        if(!vm.canedit){
          return;
        }
        $(this).removeClass("swipeLeft");
      });

      if(!$.isMobile()){
      //鼠标经过的时候,兼容桌面程序
      $("#page_task_info_equiplist_tab,#page_task_info_members_tab").on("mouseover","li",function(){
        //alert(vm.canedit);
        if(!vm.canedit){
          return;
        }
        $(this).siblings().removeClass("swipeLeft");
        $(this).addClass("swipeLeft");
      }).on("mouseout","li",function(){
        if(!vm.canedit){
          return;
        }
        $(this).removeClass("swipeLeft");
      });
      }

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
    },
    delete_equip_info:function(ecode){
      var r=confirm("确定要删除吗?")
      if (r==true){
        var vm=this;
        $.post($.SP+'/mobile/task/delete_equip_info.do',
          {
            ecode:ecode,
            task_id:vm.id
          },function(response){
            //vm.equiplist.push(response.root);
            var index=-1;
            for(var i=0;i<vm.equiplist.length;i++) {
              if(ecode==vm.equiplist[i].ecode){
                index=i;
              }
            }
            if(index!=-1){
              vm.equiplist.splice(index,1);
            }

          });
        }//if (r==true){
    },
    // add_member:function(wk_id,wk_name,memb_id,memb_name){
    //   var vm=this;
    //   $.post($.SP+'/mobile/task/selectMember.do', {task_id:vm.task_id,user_id:memb_id}, function(response){
    //     $.closeModal(".popup_members");
    //     //vm.$emit("selectMember",wk_id,wk_name,memb_id,memb_name);
    //     if(vm.members==null){
    //       vm.members=[];
    //     }
    //     vm.members.push({
    //       id:memb_id,
    //       name:memb_name,
    //       workunit_id:wk_id,
    //       workunit_name:wk_name
    //     });
    //   });
    // },
    add_member:function(memb_id){
      var vm=this;
      $.post($.SP+'/mobile/task/addMember.do', {task_id:vm.id,user_id:memb_id}, function(response){
        $.closeModal(".popup_members");
        if(vm.members==null){
          vm.members=[];
        }
        var root=response.root;
        vm.members.push({
          id:memb_id,
          name:root.name,
          workunit_id:root.workunit_id,
          workunit_name:root.workunit_name
        });

      });
    },
    delete_member:function(user_id){
      var r=confirm("确定要删除吗?")
      if (r==true){
        var vm=this;
        $.post($.SP+'/mobile/task/deleteMember.do',
          {
            user_id:user_id,
            task_id:vm.id
          },function(response){
            var index=-1;
            for(var i=0;i<vm.members.length;i++) {
              if(user_id==vm.members[i].id){
                index=i;
              }
            }
            if(index!=-1){
              vm.members.splice(index,1);
            }

          });
        }//if (r==true){
    },
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
	border-bottom:1px solid #989898;

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
