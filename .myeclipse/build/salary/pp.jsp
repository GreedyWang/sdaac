<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>奖金管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="calc/calendar.js"></script>
		<script type="text/javascript" src="calc/calendar-en.js"></script>
	<script type='text/javascript' src="js/cal-noday.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	<link type="text/css" rel="stylesheet" href="calc/calendar-system.css"/>
  </head>
  
  <body>
	<table>
   <tr>
		 <form action="personal.do?actionType=getIPSalary" method="post">
<jsp:include flush="true" page="SelectToolBar.jsp"></jsp:include>
   </form></tr></tr>

   </table>
<table cellspacing="1" cellpadding="4" width="90%"
			class="tableborder" id="table3">
   <thead>
   <tr>
				<td colspan="7" class="header">员工奖金
				</td>
				<td  class="header"><a href="personal.do?actionType=WriteToExcelAndDownLoadPP"><img src="images/save.gif"></a> 
				</td>
  </tr>
   <tr>
   <td align="center" class="altbg1"><b>员工编号</b></td>
   <td align="center" class="altbg1"><b>奖金</b></td>

   </tr> </thead>
  <tbody>
<c:forEach items="${IPSalary }" var="item">
<tr>
<td align="center" class="altbg1">${item.uid }</td>
<td align="center" class="altbg1">${item.ip}</td>

</tr>
</c:forEach>
<tr >
</tr>
<tr><jsp:include flush="true" page="page.jsp"></jsp:include> </tr>
   </tbody>
   </table>
  </body>
</html>
