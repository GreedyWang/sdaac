<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
	<link rel="stylesheet" target="text/css" href="css/index.css">
	<script type='text/javascript' src='/bpp/dwr/interface/prFormListManager.js'></script>
	  <script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>
	
    <script type='text/javascript' src='/bpp/dwr/engine.js'></script>
	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>

	<script type="text/javascript" src="ext/ext-all.js"></script>
		<script type="text/javascript" src="HumanResource/assembly_room/js/showList.js"></script>
  </head>
	<style type="text/css">
        .icon-upload {
            background-image:url(images/icons/upload.png) !important;
        }
      
    </style>
  <body>

            
    <div id="centre"></div>
    <div id="top" >
	
	</div>
	<input id="paramUid" type="hidden" value="${param.uid }">
	   <input id="types" type="hidden" value="1">  
	   
  </body>
</html>