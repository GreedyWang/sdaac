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
 		<form action="mainData.do?actionType=doApprove" method = "post">
					<input type="hidden" name="form.uuid" value="${rs.uuid}">
					<input type="hidden" name="form.state" value="${rs.state}"> 
					<input type="hidden" name="form.tempolyeeByApplicantId.tdepartment.id" value="${rs.tempolyeeByApplicantId.tdepartment.id}">     
				<tr>
					<td> 
					<select name="apForm.isApproved">
						<option value="1">批准</option>
						<option value="3">需解释</option>
						<option value="2">不批准</option>
					</select> 
					</td>
					<td><textarea name="apForm.context" rols="6" cols="160"></textarea> </td>
					<td> <input type="submit" value="提交/submit"></td>
				</tr>	
		</form>	
  </body>
</html>
