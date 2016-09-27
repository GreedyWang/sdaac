<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addExcel.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body> 
		<table>
		<tr><td><h3>导入标准工时</h3></td></tr>
		<tr>
		<form action="figureAndPostId.do?actionType=updateFigureByExcel" enctype="multipart/form-data" method="post">
		<td><input type="file" name="formFile"></td>
		<input type="hidden" name="excelType" value="standworktime">
		<td><input type="submit" value="提交"> </td>
		</form>

		</tr>
		</table>
  </body>
</html>
