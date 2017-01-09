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

	
	<%@include file="../common/init.jsp" %>
	<script type="text/javascript" src="../baseinfo/CommCombo6.js"></script>
	<script type="text/javascript" src="RMgrRepairApp.js"></script>
	<script type="text/javascript">
		
	</script>
	
	<style>
		
		.repair_edit{
			 background: url(../icons/application_form_edit.png) left top no-repeat !important;  
		}
		.repair_look{
			 background: url(../icons/application_form_magnify.png) left top no-repeat !important;  
		}
		.scrap_edit{
			 background: url(../icons/book_edit.png) left top no-repeat !important;  
		}
		.scrap_look{
			 background: url(../icons/book_magnify.png) left top no-repeat !important;  
		}
		
	</style>
  </head>
  
  <body>
   
  </body>
</html>
