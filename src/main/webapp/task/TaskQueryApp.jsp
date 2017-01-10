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
	<%
	String autoLoad=request.getParameter("autoLoad");
	if(autoLoad==null || "".equals(autoLoad.trim())){
		autoLoad="true";
	}
	//String autoLoad="true";
	%>
	<script type="text/javascript">
		var autoLoad=<%=autoLoad%>;
		var grid=null;
		function query4Pole(params){
		
		window.reload=function(){
			if(!grid){
				return;
			}
			grid.getStore().load({params:{
					pole_id:params
				}
			});
			clearInterval(interval);
		}
			var interval=setInterval("reload()",500);
		}
		window.query4Pole=query4Pole;
	</script>
	<script type="text/javascript" src="TaskQueryApp.js"></script>
  </head>
  
  <body>
   
  </body>
</html>
