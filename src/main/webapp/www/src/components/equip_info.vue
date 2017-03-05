<template>
  <div class="popup popup-equip_info" >
    <div class="content">
				<div class="list-block" style="margin-top:0px;">
				    <ul>
				      <!-- Text inputs -->
				      <li>
				        <div class="item-content">
				          <div class="item-media"><i class="icon icon-form-name"></i></div>
				          <div class="item-inner">
				            <div class="item-title label">条码</div>
				            <div class="item-input">
				              <input type="text"  disabled="true" v-model="ecode">
				            </div>
				          </div>
				        </div>
				      </li>
				      <li>
				        <div class="item-content">
				          <div class="item-media"><i class="icon icon-form-name"></i></div>
				          <div class="item-inner">
				            <div class="item-title label">小类</div>
				            <div class="item-input">
				              <input type="text"  disabled="true" v-model="subtype_name">
				            </div>
				          </div>
				        </div>
				      </li>
				      <li>
				        <div class="item-content">
				          <div class="item-media"><i class="icon icon-form-name"></i></div>
				          <div class="item-inner">
				            <div class="item-title label">品名</div>
				            <div class="item-input">
				              <input type="text"  disabled="true" v-model="prod_name">
				            </div>
				          </div>
				        </div>
				      </li>
				      <li>
				        <div class="item-content">
				          <div class="item-media"><i class="icon icon-form-name"></i></div>
				          <div class="item-inner">
				            <div class="item-title label">设备型号</div>
				            <div class="item-input">
				              <input type="text"  disabled="true" v-model="style">
				            </div>
				          </div>
				        </div>
				      </li>
				      <li>
				        <div class="item-content">
				          <div class="item-media"><i class="icon icon-form-name"></i></div>
				          <div class="item-inner">
				            <div class="item-title label">品牌</div>
				            <div class="item-input">
				              <input type="text"  disabled="true" v-model="brand_name">
				            </div>
				          </div>
				        </div>
				      </li>

				      <li class="align-top">
				        <div class="item-content">
				          <div class="item-media"><i class="icon icon-form-comment"></i></div>
				          <div class="item-inner">
				            <div class="item-title label">规格</div>
				            <div class="item-input">
				              <textarea disabled="true">{{prod_spec}}</textarea>
				            </div>
				          </div>
				        </div>
				      </li>

              <li>
				        <div class="item-content">
				          <div class="item-media"><i class="icon icon-form-name"></i></div>
				          <div class="item-inner">
				            <div class="item-title label">所在位置</div>
				            <div class="item-input">
				              <input type="text"  disabled="true" v-model="position_in">
				            </div>
				          </div>
				        </div>
				      </li>
				    </ul>
            <div class="content-block">
  				    <div><a href="javascript:void(0);" class="button button-big button-fill button-danger close-popup" @click="closeModal">关闭</a></div>
  				  </div>
				  </div><!-- <div class="list-block" style="margin-top:0px;"> -->
			</div>
  </div>
</template>
<script>
export default {
  //name: 'app',
  data () {
    return {
      ecode:'',
      subtype_name:'',
      prod_name:'',
      style:'',
      brand_name:'',
      prod_spec:'',
      position_in:''
    }
  },
  methods: {
    getEquipinfo:function(ecode){
      var vue=this;
      $.showPreloader("正在取数....");
      $.post($.SP+'/mobile/task/getEquip_info.do', {ecode:ecode}, function(response){
        var root=response.root;
        for (var x in root) {
          vue.$data[x]=root[x];
        }
        $.hidePreloader();
      });
    },
    closeModal:function(){
      $.closeModal(".popup-equip_info");
    }
  }//methods
}
</script>
<style>
</style>
