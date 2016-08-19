<%@ page language="java"  pageEncoding="utf-8"%>
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
    <form action="epor.do?actionType=doApprovePR" method="post">
   <input type="hidden" name="approvedPR.prPrForm.id" value="${prForm.id }">
   <input type="hidden" name="approvedPR.prPrForm.state" value="${prForm.state }">
   <input type="hidden" name="approvedPR.tempolyee.uid" value="${logineduser.emp.uid }">
  		<input type="hidden" name="approvedPR.prPrForm.source"
				value="${prForm.source }">
   <table> 
   <tr>
   		<td>审批意见</td>
   		<td>项目编号</td>
   		<td>意见说明</td>
   		<td>提交</td>
   </tr>
   <tr>
   <td><select name="approvedPR.isApproved">
   <option value="1">批准</option>
   <option value="0">不批准</option>
   </select></td>
   <td><input type="text" name=""> </td>
   <td><textarea rows="" cols="100%" name="approvedPR.context"></textarea> </td>
   <td><input type="submit" value="提交"> </td>
   </tr>
   </table>
   </form>
  </body>
</html>
