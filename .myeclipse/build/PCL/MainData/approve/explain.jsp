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
  
  <script type="text/javascript">
	  document.getElementById('btn1').hidden = true;
	  document.getElementById('btn2').hidden = true;
  </script>
  
  <body>
 		<form action="mainData.do?actionType=doApprove" method = "post">
			<input type="hidden" name="apForm.item.uuid" value="form.uuid">  
			<input type="hidden" name="apForm.item.uuid" value="form.uuid">	
				<tr>
					<td ><textarea cols="180" rows="3" name="apForm.context"></textarea></td>
					<td> <input type="submit" value="提交/submit"></td>
				</tr>	
		</form>	
  </body>
</html>
