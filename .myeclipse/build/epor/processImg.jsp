<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<title>查看流程图</title>
  </head>
  
  <body>

		<img src="epor/img/test.png" >
		<div style="left: ${point[0]};top: ${point[1]};width: ${point[3]};height:${point[2]};z-index: 2;position: absolute;border: 1px solid red"></div>
		<div style="left: '${point[0]}';top: '${point[0]}';width: 100px;height: 100px;z-index: 2;position: absolute;border: 1px; color: green;" ></div>
	
  </body>
</html>
