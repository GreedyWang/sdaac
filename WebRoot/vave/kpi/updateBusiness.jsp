<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
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
  
  <body>
  <form action="businessTopic.do?actionType=update" method="post">
    <table>
    <tr>
    	<td><label>经营课题</label></td>
    	<td><input name="bt.businessTitle" type="text" size="50" value="${rs.businessTitle }"> </td>
    </tr>
    <tr>
    	<td colspan="2"><textarea name="bt.description" rows="10" cols="60">${rs.description }</textarea> </td>
    </tr>
    <input name="bt.id" value="${rs.id }" type="hidden">
    <tr><td><input type="submit" value="提交"> </td></tr>
    </table>
    </form>
  </body>
</html>
