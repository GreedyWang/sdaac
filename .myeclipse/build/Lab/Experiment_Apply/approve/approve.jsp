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
 	<jsp:include page="Experiment_Apply/innerForm.jsp"></jsp:include>
 	<form action="labAction.do?actionType=doApprove" method="post">
 	<table>
 		<tr>
 			<td>
 				<select>
 					<option>批准</option>
 					<option>不批准</option>
 					<option>需解释</option> 
 				</select> 
 			</td>
 			<td>
 				<textarea rows="4" cols="80"></textarea>
 			</td>
 		</tr>
 	</table>
 	</form>
  </body>
</html>
