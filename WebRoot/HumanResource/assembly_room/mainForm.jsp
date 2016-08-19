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
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/ext-all.js"></script>
	<script type="text/javascript" src="HumanResource/assembly_room/js/mainFrom.js"></script>

  </head>
 
  <body>
  <div id="hello-win"></div>
  <div id="hello-tabs"></div>
    <div id="hello-win2"></div>
  <div id="hello-tabs2"></div>
    <div id="hello-win3"></div>
  <div id="hello-tabs3"></div>
      <div id="hello-win4"></div>
  <div id="hello-tabs4"></div>
  <div id="west"></div>
   <div id="header" ></div>
    <div id="tree-div"></div>
     
     <div id="source"></div>
      <div id="centre"></div>
      <input id="uid" type="hidden" value="${logineduser.uid } ">
 	  <input id="aaa" type="hidden" value="${logineduser.emp.roleids_b }">
  </body>
</html>
