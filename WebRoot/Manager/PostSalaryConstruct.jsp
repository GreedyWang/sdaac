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
    
    <title>岗位工资结构表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
  </head>
  	
  <body>
	<table>

 

   </table>
<table cellspacing="1" cellpadding="4" width="90%"
			class="tableborder" id="table3">
   <thead >
   <tr>
				<td id="head" colspan="8" class="header">岗位工资等级标准
				</td>
  </tr>
   <tr>
   <td align="center" class="altbg1"><b>岗位类型</b></td>
   <td align="center" class="altbg1"><b>Max</b></td>
   <td align="center" class="altbg1"><b>70%</b></td>
   <td align="center" class="altbg1"><b>30%</b></td>
   <td align="center" class="altbg1"><b>Min</b></td>
   </tr> </thead>
  
  
  <tbody>
  <!-- onsubmit="return checkBlank()" -->
 

 <form action="post.do?actionType=doUpdatePost" method="post" onsubmit="return checkBlank(index)">
 <c:forEach items="${postType}" var="item">
 <tr>
 <td align="center" class="altbg1"><input name="item.type" id="item.type" type="hidden" value="${ item.type}" />${ item.type} </td>
  <td align="center" class="altbg1"><input  name="item.maxValue" id="item.maxValue" value="${ item.maxValue}"/></td>
<td align="center" class="altbg1"><input name="item.midValue" id="item.midValue" value="${item.midValue}"/></td>
  <td align="center" class="altbg1"><input name="item.midValue" id="item.midValue" value="${item.thrity}"/></td>
  <td align="center" class="altbg1"><input name="item.minValue" id="item.minValue" value="${item.minValue}"/></td>
   <tr>
 </c:forEach>
   <tr ><input type="submit" value="保存" /></tr>
   </from>
   </tbody>
   </table>
  </body>
</html>
