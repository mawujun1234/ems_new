<template>
<div class="page page-current" id="page_show_photo" >
  <header class="bar bar-nav">
    <a class="button button-link button-nav pull-left back" href="javascript:void(0);"  @click="back"> <span class="icon icon-left"></span>返回
    </a>
    <h1 class="title">点位照片</h1>
  </header>
  <div class="content" >
    <ul class="box">
      <!--
      <li class="item" style="background-image:url(http://static.runoob.com/images/demo/demo3.jpg);background-repeat:no-repeat;">
        照片
      </li>
    -->
      <li class="item" v-for="(elem,index) in pole_photos">
        <img :src="elem.url" style="max-width: 100%;">
        <div class="close" @click="photo_remove(index)">X<div>
      </li>

      <li class="item add_photo" @click="pictureSourceType">
        <span class="icon icon-picture"></span><br/>
        添加照片
      </li>
    </ul>
  </div>
</div>
</template>
<script>
export default {
  data () {
    return {
      pole_photos:[{
        url:'http://static.runoob.com/images/demo/demo3.jpg'
      },{
        url:'http://static.runoob.com/images/demo/demo3.jpg'
      }]
    }
  },
  methods: {
    back:function(){
      window.appvue.back();
    },
    photo_remove:function(index){
      var photo=this.pole_photos.splice(index,1);
    },
    pictureSourceType:function(){
      var vm=this;
      var buttons1 = [
        {
          text: '请选择',
          label: true
        },
        {
          text: '拍照',
          bold: true,
          color: 'danger',
          onClick: function() {
            //$.alert("你选择了“卖出“");
            var cameraOptions = {
                quality : 50,
                destinationType : Camera.DestinationType.FILE_URI,//Camera.DestinationType.DATA_URL,
                sourceType : Camera.PictureSourceType.CAMERA,
                allowEdit : true,
                encodingType : Camera.EncodingType.JPEG,
                targetWdith : 100,
                targetHeight : 100,
                //popoverOptions : CameraPopoverOptions,
                saveToPhotoAlbum : true
            };
            navigator.camera.getPicture(function(imageData){
              alert(imageData);
              vm.pole_photos.push({url:imageData});
              //var image = document.getElementById('myImage');
              //image.src = "data:image/jpeg;base64," + imageData;

            }, function(){
              //alert("拍照失败");
            }, cameraOptions);

          }
        },
        {
          text: '从相册中选择',
          onClick: function() {
            var cameraOptions = {
                quality : 50,
                destinationType : Camera.DestinationType.FILE_URI,//Camera.DestinationType.DATA_URL,
                sourceType : Camera.PictureSourceType.PHOTOLIBRARY,
                allowEdit : true,
                encodingType : Camera.EncodingType.JPEG,
                targetWdith : 100,
                targetHeight : 100,
                //popoverOptions : CameraPopoverOptions,
                saveToPhotoAlbum : false
            };
            navigator.camera.getPicture(function(imageData){
              alert(imageData);
              vm.pole_photos.push({url:imageData});
              //var image = document.getElementById('myImage');
              //image.src = "data:image/jpeg;base64," + imageData;
            }, function(){
              //alert("拍照失败");
            }, cameraOptions);
          }
        }
      ];
      var buttons2 = [
        {
          text: '取消',
          bg: 'danger'
        }
      ];
      var groups = [buttons1, buttons2];
      $.actions(groups);
    }
  }
}
</script>
<style>
.box {
  display: flex;
  flex-direction: row;
  flex-wrap:wrap;
  justify-content: flex-start;
  align-items: center ;
  text-align: center;
  list-style-type:none;
  padding-left:0px;
}
.box .item {
  width:4rem;
  height:4rem;
  margin:0.2rem 0.2rem 0.2rem 0.2rem ;
  text-align: center;
  position:relative;
  background-size:contain;
  border:1px solid rgba(144,144,144,0.2);
}
.box .item .close{
  position: absolute;
  right:-0.3rem;
  top:-0.3rem;
  width:0.8rem;
  height:0.8rem;
  line-height: 0.8rem;
  font-size: 0.2rem;
  color: white;
  border-radius:0.8rem;
  background-color:rgba(16,16,16,0.8);
}
.box span {
  font-size: 1.3rem;
}
.box .add_photo {
  padding-top:0.4rem;
  font-size: 0.5rem;
  border:1px dashed gray;
}
</style>
