<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>EquipmentType</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.status_disable {
	text-decoration:line-through;
	color:gray;
}
</style>
	
	<%@include file="../common/init.jsp" %>
	<script type="text/javascript" src="CommCombo6.js"></script>
	<script type="text/javascript" src="EquipmentTypeApp.js"></script>
	
  </head>
  
  <body>
   
  </body>
</html>
