<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'UploadSource.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


  </head>
  
  <body>
     <form action="cw.do?actionType=processTxt" method="post" enctype="multipart/form-data">
     <table>
      <tr><td><input type="file" name="file2">金穗合并后的数据</td></tr>
      <input type="hidden" name="tip" value="发票">
		<tr><td><input type="submit" value="上传"></td></tr>	
		</table>
	</form>
	<hr>
	 <form action="cw.do?actionType=processTxtOld" method="post" enctype="multipart/form-data">
     <table>
     <tr><td><input type="file" name="file">SAP数据</td></tr>
      <tr><td><input type="file" name="file2">金穗合并后的数据</td></tr>
		<tr><td><input type="submit" value="上传"></td></tr>	
		</table>
	</form>
  </body>
</html>
