<template>
<div class="page page-current" id="page_pole_photo" >
  <header class="bar bar-nav">
    <a class="button button-link button-nav pull-left back" href="javascript:void(0);"  @click="back"> <span class="icon icon-left"></span>返回
    </a>
    <h1 class="title">点位照片</h1>
  </header>
  <div class="content" >
    <ul class="box">
      <!--
      <li class="item" style="background-image:url(http://static.runoob.com/images/demo/demo3.jpg);">
        照片
      </li>
    -->
      <li class="item" v-for="(elem,index) in pole_photos" :style="'background-image:url('+elem.thumb_url+');'">
        <div class="close" @click="photo_remove(index,elem.id)">X<div>
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
      pole_photos:[]
    }
  },
  beforeRouteEnter:function (to, from, next) {
    next(vm => {
      // 通过 `vm` 访问组件实例
      let pole_id=vm.$route.params.pole_id
      $.showPreloader("正在取数....");
      $.post($.SP+'/polePhoto/queryByPole.do', {pole_id:pole_id}, function(response){
          //var root=response.root;
          //root.handle_contact='2222';
          var arry=[];
          for(var i=0;i<response.length;i++){
            arry.push({
              id:response[i].id,
              thumb_url:$.SP+response[i].thumb_url
            });
          }
          vm.pole_photos=arry;
          $.hidePreloader();
      })
    })
  },
  methods: {
    back:function(){
      window.appvue.back();
      this.pole_photos=[];
    },
    photo_remove:function(index,id){
      var photo=this.pole_photos.splice(index,1)[0];

      $.post($.SP+'/polePhoto/deleteById.do', {id:photo.id}, function(response){

      });
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
                destinationType : Camera.DestinationType.FILE_URI,
                //destinationType:Camera.DestinationType.DATA_URL,
                sourceType : Camera.PictureSourceType.CAMERA,
                allowEdit : false,
                encodingType : Camera.EncodingType.JPEG,
                mediaType: Camera.MediaType.PICTURE,
                correctOrientation: true,
                targetWdith : screen.width,
                targetHeight : screen.height,
                //popoverOptions : CameraPopoverOptions,
                saveToPhotoAlbum : false
            };
            navigator.camera.getPicture(function(imageData){
              //alert(screen.width+"==="+screen.height);
              vm.getFileEntry(imageData);
              //vm.pole_photos.push({url:imageData});
            }, function(){
              alert("拍照失败:"+error);
            }, cameraOptions);

          }
        },
        {
          text: '从相册中选择',
          onClick: function() {
            var cameraOptions = {
                quality : 50,
                destinationType : Camera.DestinationType.FILE_URI,
                //destinationType:Camera.DestinationType.DATA_URL,
                //sourceType : Camera.PictureSourceType.PHOTOLIBRARY,
                sourceType : Camera.PictureSourceType.SAVEDPHOTOALBUM,
                allowEdit : true,
                encodingType : Camera.EncodingType.JPEG,
                mediaType: Camera.MediaType.PICTURE,
                correctOrientation: true,
                //targetWdith : screen.width,
                //targetHeight : screen.height,
                saveToPhotoAlbum : false
            };
            navigator.camera.getPicture(function(imageData){
              //alert(imageData);
              vm.getFileEntry(imageData);
              //vm.upload(imageData,imageData);
            }, function(){
              alert("选择图片失败");
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
    },
    getId:function(){
      var date=new Date();
      return date.getFullYear()+""+(date.getMonth()+1)+""+date.getDate()
      +""+date.getHours()+""+date.getMinutes()+""+date.getSeconds()+""+date.getMilliseconds();
    },
    upload:function(fileEntry,imgUri){
      var vm=this;
      //var fileURL = fileEntry.toURL();
      let fileURL="";
      if(typeof fileEntry=='string'){
        fileURL=fileEntry;
      } else {
        fileURL = fileEntry.toURL();
      }
      let pole_id=this.$route.params.pole_id;

      var options = new FileUploadOptions();
      options.fileKey="file";
      options.fileName=fileURL.substr(fileURL.lastIndexOf('/')+1);
      options.mimeType="image/jpeg";

      //var headers={'headerParam':'headerValue'};
      //options.headers = headers;
      var params = {};
      params.pole_id = pole_id;
      params.id = vm.getId();
      options.params = params;
      //先添加上去
      //var photo_index=vm.pole_photos.length;
      vm.pole_photos.push({thumb_url:imgUri,id:params.id});

      var ft = new FileTransfer();

      ft.upload(fileURL, encodeURI($.SP+"/polePhoto/upload.do"), function(fileUploadResult){
        //alert(fileUploadResult.responseCode);
        //alert(fileUploadResult.response);
        var obj=JSON.parse(fileUploadResult.response);
        if(obj.success){

        } else {
          navigator.notification.alert("文件上传失败！", null, "提醒");
        }
        //
      }, function(){
        alert("上传失败: Code = " + error.code);
      }, options);
    },
    getFileEntry:function(imgUri){
      var vm=this;
      window.resolveLocalFileSystemURL(imgUri, function success(fileEntry) {
          // Do something with the FileEntry object, like write to it, upload it, etc.
          //vm.writeFile(fileEntry, imgUri);
          vm.upload(fileEntry,imgUri);
          //alert("got file: " + fileEntry.fullPath);
          // displayFileData(fileEntry.nativeURL, "Native URL");

      }, function () {
        // If don't get the FileEntry (which may happen when testing
        // on some emulators), copy to a new FileEntry.
        alert("不能获取编辑过的图片");
          //vm.createNewFileEntry(imgUri);
      });
    },

    createNewFileEntry:function(imgUri){
      var vm=this;
      window.resolveLocalFileSystemURL(cordova.file.cacheDirectory, function success(dirEntry) {
          // JPEG file
          dirEntry.getFile("tempFile.jpeg", { create: true, exclusive: false }, function (fileEntry) {

              // Do something with it, like write to it, upload it, etc.
              //vm.writeFile(fileEntry, imgUri);
              vm.upload(fileEntry,imgUri);
              //alert("got file: " + fileEntry.fullPath);
              // displayFileData(fileEntry.fullPath, "File copied to");

          }, function(){
            alert("生成图片文件失败!");
          });

      }, function(){
        alert("解析图片路径失败!");
      });
    }
  }//methods
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
  padding-left:0.4rem;
}
.box .item {
  width:4rem;
  height:4rem;
  margin:0.2rem 0.2rem 0.2rem 0.2rem ;
  text-align: center;
  position:relative;
  background-size:100% auto;
  border:1px solid rgba(144,144,144,0.2);
  background-repeat:no-repeat;
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
