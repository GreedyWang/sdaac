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
  <form action="epor.do?actionType=doUpdateProject" method="post">
   <table>
   	<tr>
   		<td>项目编号</td>
   		<td>项目SAP号</td>
   		<td>项目内容</td>
   		<td>项目经理姓名</td>
   		<td>项目经理工号</td>
   	</tr>
   	<tr>
   		<td><input type="text" name="projectId" value="${rs.projectId}"> </td>
   		<td><input type="text" name="sapNo" value="${rs.sapNo}"></td>
   		<td><input type="text" name="context" value="${rs.context}"></td>
   		<td><input type="text" name="managerUid" value="${rs.managerUid}"></td>
   		<td><input type="text" name="number" value="${rs.number}"></td>
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
