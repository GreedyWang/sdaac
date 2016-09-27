<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>基本属性</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
  </head>

 <script type="text/javascript">

 </script>
 
  <body>
  
    <table cellspacing="1" cellpadding="4" width="90%" class="tableborder">
    
    	<tr class="header">
    	<td>Part No.</td>
    	<td>Drawing No.</td>
    	<td>Part Name(Chinese)</td>
    	<td>Part Name(English)</td>
    	<td>Unit</td>
    	<td>Suppliers</td>
    	<td>Parts Status</td>
    	</tr>
    	<tr class="altbg1">
    	<td>${detail.no }</td>
    	<td>${detail.drawingNO }</td>
    	<td>${detail.name }</td>
    	<td>${detail.ename }</td>
    	<td>${detail.unit }</td>
    	<td>${detail.source }</td>
    	<td>${detail.state }</td>
    	</tr>
    </table>
  </body>
</html>
