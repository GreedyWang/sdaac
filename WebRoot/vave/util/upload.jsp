<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>信息录入</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="calc/calendar.js"></script>
	<script type="text/javascript" src="calc/calendar-en.js"></script>
	 <script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>
 <script type='text/javascript' src='/bpp/dwr/interface/PostManager.js'></script>

  <script type='text/javascript' src='/bpp/dwr/engine.js'></script>
  <script type='text/javascript' src="js/cal.js"></script>
	<link type="text/css" rel="stylesheet" href="calc/calendar-system.css"/>
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
  </head>
  	
  <body>
	<table>

 

   </table>
<table cellspacing="1" cellpadding="4" width="90%"
			class="tableborder" id="table3">
   <thead >
   <tr>
				<td id="head" colspan="8" class="header">附加文件
				</td>
  </tr>
   <tr>
   
   </tr> </thead>
  
  
  <tbody>
  <!-- onsubmit="return checkBlank()" -->
 <form action="servlet/FileUploadServlet" enctype="multipart/form-data" method="post" >
   <tr>
   <td align="center" class="altbg1">
    <input type="file" name="file">
   <input type="submit" value="保存" /></tr>
   </from>

<tr >
</tr>
   </tbody>
   </table>
  </body>
</html>
