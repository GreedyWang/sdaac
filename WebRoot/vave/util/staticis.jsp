<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-1_0-rt.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'staticis.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
   
  
  
  <body>
  <table id="title" width="60%">
  <thead>
       
 
 <tr id="intro_right">
 <td> 计算附表<br><br></td>
 <td>周期(天)<br><br></td>
 <td>进度<br><br></td>
 <td>一次性费用（元）<br><br></td>
 <td>项目<br><br></td>
 <td>数量<br><br></td>
 <td>单位<br><br></td>
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${VaveSchedule}" var="item">

 <tr>
 <td>${item.statistics.item }</td>
 <td>${item.statistics.cycle }</td>
 <td>${item.statistics.progress }</td>
 <td>${item.statistics.oneTimeCosts }</td>
 <td>${item.project.project }</td>
 <td>${item.project.quantity }</td>
 <td>${item.project.unit }</td>
 </tr>

 </c:forEach>
  </body>
</html>
