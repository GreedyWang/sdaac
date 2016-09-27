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
  </head>
  
  <body>
  <form  action="epor.do?actionType=doDownReport" enctype="multipart/form-data" method="post">
   上传:<input type="file" name="formFile" >	
   <input  type="text">
	<select name="type">
		<option value="1">购买内容报表</option>
		<option value="2">审批报表</option>
	</select>
   <textarea type="text" name="sql" cols="50" rows="5">and e.departmentid='10058' 
   and year(applicant_date)=2014</textarea>
 
   <input type="submit" value="submit">
   </form>
  </body>
</html>
