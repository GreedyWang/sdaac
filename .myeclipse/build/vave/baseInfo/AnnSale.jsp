<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'AnnSale.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css" media="screen">@import url(css/vave.css);</style>

  </head>
  
  <body>
  <table>
    <tr>
    <td>顾客代码</td>
    <td>车型</td>
    <td>预计销售量</td>
    <td>操作</td>
    </tr>
    </table>
  	<div style="border:1px solid black; padding:5px;height:115px;color:black;overflow: auto">
    <table>
 
    <tbody>
  	<c:forEach items="${vaveInfos}" var="item">
    <tr>
    <td>${item.id.costomCode }</td>
    <td>${item.id.vehicles}</td>
    <td align="right">${item.id.volume}</td>
    <td>
	<img src="images/del.gif" />
	<img src="images/update.gif" />
	</td>
    </tr>
    </c:forEach>
    </tbody>
    </table>
    </div>
   
    <form action="vaveInfo.do?actionType=doAddbyExcel" enctype="multipart/form-data" method="post" >
   <tr>
   <td align="center" class="altbg1">
    <input type="file" name="formFile">
   <input type="submit" value="保存" /></tr>
   </from>
  </body>
</html>
