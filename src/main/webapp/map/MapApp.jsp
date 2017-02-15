<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta charset="UTF-8">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ED0fe5c7c869da5ee4260b4006e811b8"></script>
	<style  type="text/css">
		 /**隐藏百度地图的标签**/
    .anchorBL{
    	display:none;
    }
	</style>
	<%@include file="../common/init.jsp" %>
	<script type="text/javascript" src="../baseinfo/CommCombo6.js"></script>
	<script type="text/javascript" src="MapApp.js"></script>
	<script type="text/javascript">

	</script>
  </head>
  
  <body>
   
  </body>
</html>
