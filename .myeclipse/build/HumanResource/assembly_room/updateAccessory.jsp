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
  <form action="">
    <table>
    	<tr>
    		<td>配件名称</td>
    		<td>类型</td>
    		<td>状态</td>
    		<td>备注</td>
    	</tr>
    	<tr>
    		<td> <input type="text" value="${rs.name }" name=""> </td>
    		<td><input type="text" value="${rs.type }" name=""></td>
    		<td><input type="text" value="${rs.state }" name=""></td>
    		<td><input type="text" value="${rs.remark }" name=""></td>
    	</tr>
    	<tr>
    		<td>
    			<input type="submit" value="确认">
    		</td>
    	</tr>
    </table>
   </form>
  </body>
</html>
