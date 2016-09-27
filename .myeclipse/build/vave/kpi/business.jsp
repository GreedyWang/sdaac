<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'business.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  business.jsp
  <body>
  <form action="businessTopic.do?actionType=release" method="post">
    <table>
    <tr>
    	<td><label>经营课题</label><input name="bt.businessTitle" type="text" size="50"> </td>
    </tr>
    <tr>
    	<td><textarea name="bt.description" rows="10" cols="60"></textarea> </td>
    </tr>
    <tr><td><input type="submit" value="提交"> </td></tr>
    <input name="bt.publisher.uid" value="${logineduser.emp.uid }" type="hidden">
    <input name="bt.level" value="${logineduser.emp.tdepartment.id }" type="hidden">
    </table>
    </form>
  </body>
</html>
