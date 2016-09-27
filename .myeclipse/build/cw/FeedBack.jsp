<%@ page language="java"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'FeedBack.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    <form action="cw.do?actionType=feedbackLogs" method="post">
    	<table>
    		<tr><td>旧金穗号</td><td><input type="text" name="cwLogs.jinsuiNo" value="${param.jinsuiNo }" readonly="readonly"></td></tr>
    		<tr><td>新金穗号</td><td><input type="text" name="cwLogs.feedbackNo"  ></td></tr>
    		<tr><td><input type="submit" value="提交">  </td> </tr>
    	</table>
    </form>
  </body>
</html>
