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

  </head>
  
  <body>
  <form action="epor.do?actionType=doUpdateSubject" method="post">
   <table>
   	<tr>
   		<td>编号</td>
   		<td>描述</td>
   		<td>备注</td>
   	</tr>
   	<tr>
   		<td><input type="text" name="number" value="${rs.number}"> </td>
   		<td><input type="text" name="descrption" value="${rs.descrption}"></td>
   		<td><input type="text" name="remark" value="${rs.remark}"></td>
   		<td><input type="hidden" name="id" value="${rs.id}"></td>
   	</tr>
   	<tr>
   		<td>
   			<input type="submit" value="修改">
   		</td>
   	</tr>
   </table>
   </form>
  </body>
</html>
