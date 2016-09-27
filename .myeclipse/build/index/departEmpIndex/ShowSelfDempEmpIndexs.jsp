<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/ext-all.js"></script>
	<script type="text/javascript" src="index/show/examples.js"></script>
	<script type="text/javascript" src="index/departEmpIndex/SelfDempEmpIndexs.js"></script>
	<script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>
  	<script type='text/javascript' src='/bpp/dwr/engine.js'></script>	
	<script type="text/javascript" src="vave/Statistics/GroupSummary.js"></script>
	<script type="text/javascript" src="index/departEmpIndex/tKpiAccount.js"></script>
	<script type="text/javascript" src="index/departEmpIndex/ShowSelfDempEmpIndexs.js"></script>
  </head>
   <style type="text/css">
        body .x-panel {
            margin-bottom:20px;
        }
        .icon-grid {
            background-image:url(images/grid.png) !important;
        }
        .icon-search {
            background-image:url(images/icons/search.gif) !important;
        }
          .icon-employee {
            background-image:url(images/icons/user.png) !important;
        }
        .icon-group {
            background-image:url(images/icons/group.png) !important;
        }
        #button-grid .x-panel-body {
            border:1px solid #99bbe8;
            border-top:0 none;
        }
       
    </style>
  <body>
 
	<div id="body">
	<div id="search"></div></div>
  </body>
</html>
